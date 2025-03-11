const URL = 'http://localhost:8080/api/v1/tours';  // Set the correct API URL

// Function to get all tours and populate the table
function getAllTours() {
    $.ajax({
        url: `${URL}/getAll`,  // Ensure this URL is correct
        type: 'GET',
        dataType: 'json',
        success: function(response) {
            console.log('API Response:', response);  // Log the response
            if (Array.isArray(response) && response.length > 0) {
                let tourTableBody = $('#tourTableBody');
                tourTableBody.empty();  // Clear any existing rows
                response.forEach(function(tour) {
                    // Append rows dynamically to the table
                    tourTableBody.append(`
                        <tr>
                            <td>${tour.tourID}</td>
                            <td>${tour.tourName}</td>
                            <td>${tour.location}</td>
                            <td>${tour.duration}</td>
                            <td>${tour.price}</td>
                            <td>${tour.tourType}</td>
                            <td>${tour.availableSeats}</td>
                            <td>${tour.startDate}</td>
                            <td>${tour.endDate}</td>
                            <td>
                                <button class="btn btn-sm btn-info" onclick="editTour(${tour.tourID})">Edit</button>
                                <button class="btn btn-sm btn-danger" onclick="deleteTour(${tour.tourID})">Delete</button>
                            </td>
                        </tr>
                    `);
                });
            } else {
                console.error('Response is not an array or is empty');
                alert('No tours found!');
            }
        },
        error: function(xhr, status, error) {
            console.error('Error fetching tours:', error);
            alert('Error fetching tours!');
        }
    });
}

// Function to handle form submission (Save new tour)
$("#tourForm").submit(function(event) {
    event.preventDefault();  // Prevent form from submitting the traditional way

    const tourData = {
        tourName: $("#tourName").val(),
        description: $("#description").val(),
        location: $("#location").val(),
        duration: $("#duration").val(),
        price: $("#price").val(),
        tourType: $("#tourType").val(),
        availableSeats: $("#availableSeats").val(),
        startDate: $("#startDate").val(),
        endDate: $("#endDate").val(),
        images: $("#images").val()
    };

    // Send the data to your backend to save it
    $.ajax({
        url: `${URL}/create`,  // Replace with the correct URL for creating the tour
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(tourData),  // Ensure this is the correct structure
        success: function (response) {
            console.log("Tour added response:", response);
            if (response) {
                alert("Tour added successfully!");
                getAllTours();  // Reload the table after adding
                $('#tourForm')[0].reset();  // Reset form
            } else {
                alert("Failed to add tour!");
            }
        },
        error: function (xhr, status, error) {
            console.error("Error adding tour:", error);
            alert("Error adding tour!");
        }
    });
});
// Function to handle the Edit button (populate form with the tour data)
function editTour(tourID) {
    $.ajax({
        url: `${URL}/get/${tourID}`,  // Replace with correct URL for getting tour by ID
        type: "GET",
        dataType: "json",
        success: function(tour) {
            console.log("Tour to edit:", tour);  // Log the fetched tour for debugging
            // Populate the form with the tour data
            $("#tourID").val(tour.tourID);  // Set the Tour ID as read-only
            $("#tourName").val(tour.tourName);
            $("#location").val(tour.location);
            $("#duration").val(tour.duration);
            $("#price").val(tour.price);
            $("#tourType").val(tour.tourType);
            $("#availableSeats").val(tour.availableSeats);
            $("#startDate").val(tour.startDate);
            $("#endDate").val(tour.endDate);
            $("#images").val(tour.images);

            // Show the update and delete buttons, hide the save button
            $("#saveButton").hide();
            $("#updateButton").show();
            $("#deleteButton").show();
        },
        error: function(xhr, status, error) {
            console.error("Error fetching tour details:", error);
            alert("Error fetching tour details!");
        }
    });
}

// Function to update an existing tour
function updateTour() {
    const tourData = {
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
        images: $("#images").val()
    };

    $.ajax({
        url: `${URL}/update`,  // Replace with the correct URL for updating the tour
        type: "PUT",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(tourData),
        success: function(response) {
            console.log("Tour updated response:", response);  // Log the response for debugging
            if (response) {
                alert("Tour updated successfully!");
                getAllTours();  // Reload the table after updating
                $('#tourForm')[0].reset();  // Reset form
                $("#saveButton").show();
                $("#updateButton").hide();
                $("#deleteButton").hide();
            } else {
                alert("Failed to update tour!");
            }
        },
        error: function(xhr, status, error) {
            console.error("Error updating tour:", error);
            alert("Error updating tour!");
        }
    });
}

// Function to delete a tour
function deleteTour(tourID) {
    $.ajax({
        url: `${URL}/delete/${tourID}`,  // Replace with correct URL for deleting the tour
        type: "DELETE",
        success: function(response) {
            console.log("Tour deleted response:", response);  // Log the response for debugging
            if (response) {
                alert("Tour deleted successfully!");
                getAllTours();  // Reload the table after deletion
            } else {
                alert("Failed to delete tour!");
            }
        },
        error: function(xhr, status, error) {
            console.error("Error deleting tour:", error);
            alert("Error deleting tour!");
        }
    });
}

// Initialize the page by loading all tours
$(document).ready(function() {
    getAllTours();
});
