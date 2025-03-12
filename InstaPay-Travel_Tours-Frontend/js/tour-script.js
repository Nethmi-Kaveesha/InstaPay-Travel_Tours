// URL to the API that communicates with your backend (adjust according to your server)
const apiUrl = "http://localhost:8080/api/v1/tours";
let imageData = "";
let selectedTourID = null; // Stores selected tourID for Update/Delete

// Convert image to Base64
function convertToBase64(event) {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            imageData = e.target.result; // Store globally for reuse
            $("#imagePreview").attr("src", imageData).show();
        };
        reader.readAsDataURL(file);
    }
}
function loadTours() {
    $.get(apiUrl + "/getAll", function(tours) {
        console.log("Tours Loaded:", tours);  // Log the data received from the API
        const tableBody = $("#tourTableBody");
        tableBody.empty();

        if (tours && tours.length > 0) {
            tours.forEach(tour => {
                tableBody.append(`
                    <tr>
                        <td>${tour.tourID || ''}</td>
                        <td>${tour.tourName || ''}</td>
                        <td>${tour.location || ''}</td>
                        <td>${tour.duration || ''}</td>
                        <td>${tour.price || ''}</td>
                        <td>${tour.tourType || ''}</td>
                        <td>${tour.availableSeats || ''}</td>
                        <td>${tour.startDate || ''}</td>
                        <td>${tour.endDate || ''}</td>
                        <td>${tour.description || ''}</td>
                        <td>
                            <img src="${tour.images}" alt="Tour Image" style="max-width: 80px; height: auto; border-radius: 5px;">
                        </td>
                        <td>
                            <button class="btn btn-info" onclick="editTour(${tour.tourID})">Edit</button>
                            <button class="btn btn-danger" onclick="deleteTour(${tour.tourID})">Delete</button>
                        </td>
                    </tr>
                `);
            });
        } else {
            tableBody.append("<tr><td colspan='11'>No tours available</td></tr>");
        }
    }).fail(function() {
        console.error("Error loading tours from API");
        alert("Error loading tours.");
    });
}

// Save a new tour
$("#tourForm").submit(function(event) {
    event.preventDefault(); // Prevent default form submission

    // Validate form fields
    const tourName = $("#tourName").val();
    const location = $("#location").val();
    const duration = $("#duration").val();
    const price = $("#price").val();
    const tourType = $("#tourType").val();
    const availableSeats = $("#availableSeats").val();
    const startDate = $("#startDate").val();
    const endDate = $("#endDate").val();
    const description = $("#description").val();

    // Validate if required fields are filled
    if (!tourName || !location || !duration || !price || !tourType || !availableSeats || !startDate || !endDate) {
        alert("Please fill all required fields.");
        return;
    }

    // Ensure imageData is set
    if (!imageData) {
        alert("Please upload an image.");
        return;
    }

    const tourData = {
        tourName: tourName,
        location: location,
        duration: duration,
        price: price,
        tourType: tourType,
        availableSeats: availableSeats,
        startDate: startDate,
        endDate: endDate,
        description: description,
        images: imageData // Use globally stored Base64 image data
    };

    console.log("Submitting Tour Data:", tourData);

    saveTourAPI(tourData); // Move the call to saveTourAPI outside of the form submit function
});


// Function to save tour via API
function saveTourAPI(tourData) {
    $.ajax({
        url: apiUrl,  // API endpoint to save tour data
        method: 'POST',
        data: JSON.stringify(tourData),
        contentType: 'application/json',
        success: function(response) {
            console.log('Tour saved successfully:', response); // Log the response from the server
            alert('Tour saved successfully');
            loadTours(); // Reload the tours list
            resetForm();
        },
        error: function (error) {
            console.error('Error saving tour:', error); // Log error details
            alert('There was an error saving the tour.');
        }
    });
}


// Edit a tour
function editTour(tourID) {
    $.get(apiUrl + "/get/" + tourID, function(tour) {
        $("#tourID").val(tour.tourID);
        $("#tourName").val(tour.tourName);
        $("#location").val(tour.location);
        $("#duration").val(tour.duration);
        $("#price").val(tour.price);
        $("#tourType").val(tour.tourType);
        $("#availableSeats").val(tour.availableSeats);
        $("#startDate").val(tour.startDate);
        $("#endDate").val(tour.endDate);
        $("#description").val(tour.description);

        if (tour.images) {
            $("#imagePreview").attr("src", tour.images).show();
            imageData = tour.images; // Store base64 image data
        } else {
            $("#imagePreview").hide();
            imageData = "";
        }

        selectedTourID = tour.tourID;
        $("#saveButton").hide();
        $("#updateButton").show();
        $("#deleteButton").show();
    }).fail(function(error) {
        console.error("Error loading tour:", error);
    });
}

// Update the selected tour
function updateTour() {
    if (!selectedTourID) {
        alert("No tour selected for update.");
        return;
    }

    const tourData = {
        tourID: selectedTourID,
        tourName: $("#tourName").val(),
        location: $("#location").val(),
        duration: $("#duration").val(),
        price: $("#price").val(),
        tourType: $("#tourType").val(),
        availableSeats: $("#availableSeats").val(),
        startDate: $("#startDate").val(),
        endDate: $("#endDate").val(),
        description: $("#description").val(),
        images: imageData // Use globally stored Base64 image data
    };

    $.ajax({
        url: apiUrl + "/update",
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(tourData),
        success: function(response) {
            alert(response.message);
            loadTours();
            resetForm();
        },
        error: function(xhr) {
            console.error("Error: ", xhr.responseText);
            alert("Error: " + xhr.responseText);
        }
    });
}

// Delete a selected tour
function deleteTour(tourID) {
    if (!confirm("Are you sure you want to delete this tour?")) return;

    $.ajax({
        url: apiUrl + "/delete/" + tourID,
        type: "DELETE",
        success: function(response) {
            alert(response.message);
            loadTours();
            resetForm();
        },
        error: function(xhr) {
            console.error("Error: ", xhr.responseText);
            alert("Error: " + xhr.responseText);
        }
    });
}

// Reset the form and buttons
function resetForm() {
    $("#tourForm")[0].reset();
    $("#imagePreview").hide();
    imageData = ""; // Clear global image data
    selectedTourID = null;
    $("#saveButton").show();
    $("#updateButton").hide();
    $("#deleteButton").hide();
}

// Initial load of tours
loadTours();
