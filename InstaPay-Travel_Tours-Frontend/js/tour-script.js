const URL = "http://localhost:8080/api/v1/tours";
let selectedTourId = null;

$("#tourForm").submit(function (event) {
    event.preventDefault();
    if (selectedTourId) {
        updateTour();
    } else {
        saveTour();
    }
});

// Handle image upload and conversion to base64
function handleImageUpload(files) {
    const file = files[0];
    if (file) {
        const reader = new FileReader();
        reader.onloadend = function () {
            // Here you can handle the uploaded image (e.g., save as base64 or upload to your server)
            $("#images").val(reader.result);  // Set the image data as the value (Base64 encoded)
        };
        reader.readAsDataURL(file); // Convert the image to a base64 string
    }
}

// When a user selects an image
$("#images").change(function (event) {
    handleImageUpload(event.target.files);
});

function saveTour() {
    let tour = {
        tourID: $("#tourID").val(),
        tourName: $("#tourName").val(),
        description: $("#description").val(),
        location: $("#location").val(),
        duration: $("#duration").val(),
        price: $("#price").val(),
        tourType: $("#tourType").val(),
        availableSeats: $("#availableSeats").val(),
        startDate: $("#startDate").val(),
        endDate: $("#endDate").val(),
        images: $("#images").val()  // This will be a base64 string or an image URL
    };

    $.ajax({
        url: `${URL}/save`,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(tour),
        success: function () {
            alert("Tour saved successfully!");
            getAllTours();
            clearForm();
        },
        error: function () {
            alert("Error saving Tour!");
        }
    });
}

function getAllTours() {
    $.ajax({
        url: `${URL}/getAll`,
        type: "GET",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem('token')
        },
        dataType: "json",
        success: function (response) {
            console.log("Full Response:", response);

            if (!Array.isArray(response)) {
                console.error("Error: Expected array, got", typeof response);
                return;
            }

            let tours = response;

            $("#tourTableBody").empty();
            tours.forEach(tour => {
                let imageHTML = '';
                if (tour.images) {
                    // If the image is stored as base64
                    imageHTML = `<img src="${tour.images}" alt="${tour.tourName}" style="width: 100px; height: 100px; object-fit: cover;">`;
                } else {
                    imageHTML = '<p>No image available</p>';
                }

                $("#tourTableBody").append(`
                    <tr>
                        <td>${tour.tourID}</td>
                        <td>${tour.tourName}</td>
                        <td>${tour.description}</td>
                        <td>${tour.location}</td>
                        <td>${tour.duration}</td>
                        <td>${tour.price}</td>
                        <td>${tour.tourType}</td>
                        <td>${tour.availableSeats}</td>
                        <td>${tour.startDate}</td>
                        <td>${tour.endDate}</td>
                        <td>${imageHTML}</td>
                        <td>
                            <button class="btn btn-sm btn-info" onclick="fillTextFields('${tour.tourID}', '${tour.tourName}', '${tour.description}', '${tour.location}', '${tour.duration}', '${tour.price}', '${tour.tourType}', '${tour.availableSeats}', '${tour.startDate}', '${tour.endDate}', '${tour.images}')">Edit</button>
                            <button class="btn btn-sm btn-danger" onclick="deleteTour('${tour.tourID}')">Delete</button>
                        </td>
                    </tr>`);
            });
        },
        error: function (xhr, status, error) {
            console.error("Error fetching tours:", error);
            alert("Error fetching tours!");
        }
    });
}

function fillTextFields(id, tourName, description, location, duration, price, tourType, availableSeats, startDate, endDate, images) {
    $("#tourID").val(id);
    $("#tourName").val(tourName);
    $("#description").val(description);
    $("#location").val(location);
    $("#duration").val(duration);
    $("#price").val(price);
    $("#tourType").val(tourType);
    $("#availableSeats").val(availableSeats);
    $("#startDate").val(startDate);
    $("#endDate").val(endDate);
    $("#images").val(images);

    selectedTourId = id;

    $("#saveButton").hide();
    $("#updateButton").show();
    $("#deleteButton").show();
}

function updateTour() {
    let updatedTour = {
        tourID: selectedTourId,
        tourName: $("#tourName").val(),
        description: $("#description").val(),
        location: $("#location").val(),
        duration: $("#duration").val(),
        price: $("#price").val(),
        tourType: $("#tourType").val(),
        availableSeats: $("#availableSeats").val(),
        startDate: $("#startDate").val(),
        endDate: $("#endDate").val(),
        images: $("#images").val()  // This will be a base64 string or an image URL
    };

    if (!updatedTour.tourName || !updatedTour.location || !updatedTour.price) {
        alert("Please fill all required fields!");
        return;
    }

    $.ajax({
        url: `${URL}/update`,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(updatedTour),
        success: function () {
            alert("Tour updated successfully!");
            getAllTours();
            clearForm();
        },
        error: function () {
            alert("Error updating Tour!");
        }
    });
}

function deleteTour(id) {
    if (!confirm("Are you sure you want to delete this Tour?")) return;

    $.ajax({
        url: `${URL}/delete/${id}`,
        type: "DELETE",
        success: function () {
            alert("Tour deleted successfully!");
            getAllTours();
            clearForm();
        },
        error: function () {
            alert("Error deleting Tour!");
        }
    });
}

function clearForm() {
    $("#tourForm")[0].reset();
    $("#updateButton").hide();
    $("#deleteButton").hide();
    $("#saveButton").show();
    selectedTourId = null;
}

$(document).ready(function () {
    getAllTours();
});
