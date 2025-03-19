const apiUrl = "http://localhost:8080/api/v1/tours";
let imageData = "";
let selectedTourID = null;

function loadTours() {
    console.log("Loading tours...");

    $.get(apiUrl + "/getAll")
        .done(function (tours) {
            console.log("Tours Loaded:", tours);
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
        })
        .fail(function (error) {
            console.error("Error loading tours from API", error);
            alert("Error loading tours.");
        });
}

$("#tourForm").submit(function (event) {
    event.preventDefault();

    const tourData = new FormData();
    tourData.append("tourName", $("#tourName").val());
    tourData.append("location", $("#location").val());
    tourData.append("duration", $("#duration").val());
    tourData.append("price", $("#price").val());
    tourData.append("tourType", $("#tourType").val());
    tourData.append("availableSeats", $("#availableSeats").val());
    tourData.append("startDate", $("#startDate").val());
    tourData.append("endDate", $("#endDate").val());
    tourData.append("description", $("#description").val());

    var files = $("#images")[0].files;
    if (files.length === 0) {
        alert("Please select at least one image file.");
        return;
    }
    for (var i = 0; i < files.length; i++) {
        tourData.append("images", files[i]);
    }

    $.ajax({
        url: apiUrl + "/save",
        method: "POST",
        data: tourData,
        processData: false,
        contentType: false,
        success: function (response) {
            console.log("Tour saved successfully:", response);
            alert("Tour saved successfully");
            loadTours();
            resetForm();
        },
        error: function (error) {
            console.error("Error saving tour:", error);
            alert("There was an error saving the tour.");
        }
    });
});

function editTour(tourID) {
    $.get(apiUrl + "/get/" + tourID)
        .done(function (tour) {
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

            // Set image preview if available
            if (tour.images) {
                $("#imagePreview").attr("src", tour.images).show();
                imageData = tour.images;
            } else {
                $("#imagePreview").hide();
                imageData = "";
            }

            selectedTourID = tour.tourID;
            $("#saveButton").hide();
            $("#updateButton, #deleteButton").show();
        })
        .fail(function (error) {
            console.error("Error loading tour:", error);
            alert("Error loading tour details.");
        });
}

function updateTour() {
    if (!selectedTourID) {
        alert("No tour selected for update.");
        return;
    }

    const tourData = new FormData();
    tourData.append("tourID", selectedTourID);
    tourData.append("tourName", $("#tourName").val());
    tourData.append("location", $("#location").val());
    tourData.append("duration", $("#duration").val());
    tourData.append("price", $("#price").val());
    tourData.append("tourType", $("#tourType").val());
    tourData.append("availableSeats", $("#availableSeats").val());
    tourData.append("startDate", $("#startDate").val());
    tourData.append("endDate", $("#endDate").val());
    tourData.append("description", $("#description").val());

    // Check if a new file is selected
    var file = $("#images")[0].files[0];
    if (file) {
        tourData.append("images", file);
    } else if (imageData) {
        // Retain old image if no new file is selected
        tourData.append("images", imageData);
    }

    $.ajax({
        url: apiUrl + "/update",
        method: "PUT",
        data: tourData,
        processData: false,
        contentType: false,
        success: function () {
            alert("Tour updated successfully");
            loadTours();
            resetForm();
        },
        error: function (xhr) {
            alert("Error: " + xhr.responseText);
        }
    });
}


function deleteTour(tourID) {
    if (!confirm("Are you sure you want to delete this tour?")) return;

    $.ajax({
        url: apiUrl + "/delete/" + tourID,
        type: "DELETE",
        success: function () {
            alert("Tour deleted successfully");
            loadTours();
            resetForm();
        },
        error: function (xhr) {
            alert("Error: " + xhr.responseText);
        }
    });
}

function resetForm() {
    $("#tourForm")[0].reset();
    $("#imagePreview").hide();
    imageData = "";
    selectedTourID = null;
    $("#saveButton").show();
    $("#updateButton, #deleteButton").hide();
}


$(document).ready(function() {
    loadTours();
});