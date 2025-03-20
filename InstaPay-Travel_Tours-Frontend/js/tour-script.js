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

$(document).ready(function () {
    $("#tourForm").on("submit", function (e) {
        e.preventDefault();

        const tourName = $("#tourName").val().trim();
        const description = $("#description").val().trim();
        const location = $("#location").val().trim();
        const duration = $("#duration").val();
        const price = $("#price").val();
        const tourType = $("#tourType").val();
        const availableSeats = $("#availableSeats").val();
        const startDate = $("#startDate").val();
        const endDate = $("#endDate").val();
        const images = $("#images")[0].files;

        if (!tourName || !description || !location || !duration || !price || !tourType || !availableSeats || !startDate || !endDate) {
            alert("Please fill in all required fields.");
            return;
        }

        const formData = new FormData();
        formData.append("tourName", tourName);
        formData.append("description", description);
        formData.append("location", location);
        formData.append("duration", duration);
        formData.append("price", price);
        formData.append("tourType", tourType);
        formData.append("availableSeats", availableSeats);
        formData.append("startDate", startDate);
        formData.append("endDate", endDate);


        if (images.length > 0) {
            for (let i = 0; i < images.length; i++) {
                formData.append("images", images[i]);
            }
        }

        for (let pair of formData.entries()) {
            console.log(pair[0], pair[1]);
        }

        $.ajax({
            url: `${apiUrl}/save`,
            type: "POST",
            data: formData,
            contentType: false,
            processData: false,
            success: function (response) {
                alert("Tour added successfully!");


                const newTour = {
                    tourID: response.tourID,
                    tourName: $("#tourName").val(),
                    location: $("#location").val(),
                    duration: $("#duration").val(),
                    price: $("#price").val(),
                    tourType: $("#tourType").val(),
                    availableSeats: $("#availableSeats").val(),
                    startDate: $("#startDate").val(),
                    endDate: $("#endDate").val(),
                    description: $("#description").val(),
                    images: $("#images")[0].files[0].name // Or the appropriate image URL from backend
                };

                const tableBody = $("#tourTableBody");
                tableBody.append(`
            <tr>
                <td>${newTour.tourID}</td>
                <td>${newTour.tourName}</td>
                <td>${newTour.location}</td>
                <td>${newTour.duration}</td>
                <td>${newTour.price}</td>
                <td>${newTour.tourType}</td>
                <td>${newTour.availableSeats}</td>
                <td>${newTour.startDate}</td>
                <td>${newTour.endDate}</td>
                <td>${newTour.description}</td>
                <td>
                    <img src="${newTour.images}" alt="Tour Image" style="max-width: 80px; height: auto; border-radius: 5px;">
                </td>
                <td>
                    <button class="btn btn-info" onclick="editTour(${newTour.tourID})">Edit</button>
                    <button class="btn btn-danger" onclick="deleteTour(${newTour.tourID})">Delete</button>
                </td>
            </tr>
        `);


                $("#tourForm")[0].reset();
                loadTours();
            },
            error: function (xhr, status, error) {
                console.error("Error:", error);
                alert("Failed to add tour! Please try again.");
            }
        });

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

    var file = $("#images")[0].files[0];
    if (file) {
        tourData.append("images", file);
    } else if (imageData) {
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
