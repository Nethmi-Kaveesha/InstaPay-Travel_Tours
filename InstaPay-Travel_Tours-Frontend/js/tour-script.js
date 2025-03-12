// URL to the API that communicates with your backend (adjust according to your server)
const apiUrl = "http://localhost:8080/api/v1/tours";
let imageData = "";
// Variable to store selected tourID for Update/Delete operations
let selectedTourID = null;

function convertToBase64(event) {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            imageData = e.target.result;
            $("#imagePreview").attr("src", imageData).show();
        };
        reader.readAsDataURL(file);
    }
}

// Load tours into the table
function loadTours() {
    $.get(apiUrl + "/getAll", function(tours) {
        const tableBody = $("#tourTableBody");
        tableBody.empty();
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
                    <td><img src="${tour.images}" alt="Tour Image" style="max-width: 50px;"></td>
                    <td>
                        <button class="btn btn-info" onclick="editTour(${tour.tourID})">Edit</button>
                        <button class="btn btn-danger" onclick="deleteTour(${tour.tourID})">Delete</button>
                    </td>
                </tr>
            `);
        });
    });
}

// Save a new tour
$("#tourForm").submit(function(event) {
    event.preventDefault();

    // Capture form data
    const tourData = {
        tourName: $("#tourName").val(),
        location: $("#location").val(),
        duration: $("#duration").val(),
        price: $("#price").val(),
        tourType: $("#tourType").val(),
        availableSeats: $("#availableSeats").val(),
        startDate: $("#startDate").val(),
        endDate: $("#endDate").val(),
        description: $("#description").val(), // Ensure this is consistent
        image: $("#images").val()
    };

    // Handle image file input
    const imageFile = $("#images")[0].files[0]; // Capture the selected image file

    // If an image file is selected, handle it
    if (imageFile) {
        const reader = new FileReader();
        reader.onload = function(e) {
            // Capture the base64 image data
            tourData.images = e.target.result; // Store the base64 string in tourData
            submitTourData(tourData); // Submit the form with the base64 image data
        };
        reader.readAsDataURL(imageFile); // Read the file as base64 string
    } else {
        // If no image file is selected, submit with an empty string or default value
        tourData.images = ""; // Optional: Set a default value for no image
        submitTourData(tourData); // Submit the form without the image
    }
});

// Function to send tour data to the server for saving
function submitTourData(tourData) {
    console.log("Submitting Tour Data: ", tourData);

    // Send data to the server using AJAX POST request
    $.ajax({
        url: apiUrl + "/save",
        type: "POST",
        contentType: "application/json", // Ensure the content type is JSON
        data: JSON.stringify(tourData), // Convert tourData object to JSON string
        success: function(response) {
            alert(response.message); // Show success message
            loadTours(); // Reload tours after saving
            resetForm(); // Reset the form
        },
        error: function(response) {
            console.error("Error: ", response);
            alert("Error: " + response.responseJSON.message); // Show error message
        }
    });
}

// Edit a tour
function editTour(tourID) {
    $.get(apiUrl + "/get/" + tourID, function(tour) {
        // Populate the form with the current tour's data
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
        $("#images").val(tour.images);

        // If tour has an image, show it
        if (tour.images) {
            $("#imagePreview").attr("src", tour.images).show();
        }

        selectedTourID = tour.tourID;

        // Switch the buttons based on editing mode
        $("#saveButton").hide();
        $("#updateButton").show();
        $("#deleteButton").show();
    });
}

// Update the selected tour
function updateTour() {
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
        images: $("#images").val()
    };

    // Send the updated data to the server using AJAX PUT request
    $.ajax({
        url: apiUrl + "/update",
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(tourData),
        success: function(response) {
            alert(response.message); // Show success message
            loadTours(); // Reload tours after updating
            resetForm(); // Reset the form
        },
        error: function(response) {
            console.error("Error: ", response);
            alert("Error: " + response.responseJSON.message); // Show error message
        }
    });
}

// Delete a selected tour
function deleteTour(tourID) {
    $.ajax({
        url: apiUrl + "/delete/" + tourID,
        type: "DELETE",
        success: function(response) {
            alert(response.message); // Show success message
            loadTours(); // Reload tours after deleting
            resetForm(); // Reset the form
        },
        error: function(response) {
            console.error("Error: ", response);
            alert("Error: " + response.responseJSON.message); // Show error message
        }
    });
}

// Reset the form and buttons
function resetForm() {
    $("#tourForm")[0].reset(); // Reset form fields
    $("#imagePreview").hide(); // Hide the image preview
    selectedTourID = null; // Clear selectedTourID
    $("#saveButton").show(); // Show save button
    $("#updateButton").hide(); // Hide update button
    $("#deleteButton").hide(); // Hide delete button
}

// Initial load of tours
loadTours();
