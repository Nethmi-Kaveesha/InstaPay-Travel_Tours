const URL = "http://localhost:8080/api/v1/tourguide";
let selectedTourGuideId = null;

$("#tourGuideForm").submit(function (event) {
    event.preventDefault();
    if (selectedTourGuideId) {
        updateTourGuide();
    } else {
        saveData();
    }
});

function saveData() {
    let tourGuide = {
        guideID: $("#guideID").val(),
        fullName: $("#fullName").val(),
        email: $("#email").val(),
        phoneNumber: $("#phoneNumber").val(),
        experience: $("#experience").val(),
        languagesSpoken: $("#languagesSpoken").val()
    };

    $.ajax({
        url: `${URL}/save`,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(tourGuide),
        success: function () {
            alert("Tour Guide saved successfully!");
            getAll();
            clearForm();
        },
        error: function () {
            alert("Error saving Tour Guide!");
        }
    });
}

function getAll() {
    $.ajax({
        url: `${URL}/getAll`,
        type: "GET",
        dataType: "json", // Ensures response is parsed as JSON
        success: function (response) {
            console.log("Full Response:", response);

            if (!Array.isArray(response)) {
                console.error("Error: Expected array, got", typeof response);
                return;
            }

            let tourGuides = response;

            $("#tourGuideTableBody").empty();
            tourGuides.forEach(tourGuide => {
                $("#tourGuideTableBody").append(`
                    <tr>
                        <td>${tourGuide.guideID}</td>
                        <td>${tourGuide.fullName}</td>
                        <td>${tourGuide.email}</td>
                        <td>${tourGuide.phoneNumber}</td>
                        <td>${tourGuide.experience}</td>
                        <td>${tourGuide.languagesSpoken}</td>
                        <td>
                            <button class="btn btn-sm btn-info" onclick="fillTextFields('${tourGuide.guideID}', '${tourGuide.fullName}', '${tourGuide.email}', '${tourGuide.phoneNumber}', '${tourGuide.experience}', '${tourGuide.languagesSpoken}')">Edit</button>
                            <button class="btn btn-sm btn-danger" onclick="deleteTourGuide('${tourGuide.guideID}')">Delete</button>
                        </td>
                    </tr>`);
            });
        },
        error: function (xhr, status, error) {
            console.error("Error fetching Tour Guides:", error);
            alert("Error fetching Tour Guides!");
        }
    });
}

function fillTextFields(id, fullName, email, phoneNumber, experience, languagesSpoken) {
    $("#guideID").val(id);
    $("#fullName").val(fullName);
    $("#email").val(email);
    $("#phoneNumber").val(phoneNumber);
    $("#experience").val(experience);
    $("#languagesSpoken").val(languagesSpoken);

    selectedTourGuideId = id;

    $("#saveButton").hide();
    $("#updateButton").show();
    $("#deleteButton").show();
}

function updateTourGuide() {
    let updatedTourGuide = {
        guideID: selectedTourGuideId,
        fullName: $("#fullName").val(),
        email: $("#email").val(),
        phoneNumber: $("#phoneNumber").val(),
        experience: $("#experience").val(),
        languagesSpoken: $("#languagesSpoken").val()
    };

    if (!updatedTourGuide.fullName || !updatedTourGuide.email || !updatedTourGuide.phoneNumber) {
        alert("Please fill all fields!");
        return;
    }

    $.ajax({
        url: `${URL}/update`,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(updatedTourGuide),
        success: function () {
            alert("Tour Guide updated successfully!");
            getAll();
            clearForm();
        },
        error: function () {
            alert("Error updating Tour Guide!");
        }
    });
}

function deleteTourGuide(id) {
    if (!confirm("Are you sure you want to delete this Tour Guide?")) return;

    $.ajax({
        url: `${URL}/delete/${id}`,
        type: "DELETE",
        success: function () {
            alert("Tour Guide deleted successfully!");
            getAll();
            clearForm();
        },
        error: function () {
            alert("Error deleting Tour Guide!");
        }
    });
}

function clearForm() {
    $("#tourGuideForm")[0].reset();
    $("#updateButton").hide();
    $("#deleteButton").hide();
    $("#saveButton").show();
    selectedTourGuideId = null;
}

$(document).ready(function () {
    getAll();
});
