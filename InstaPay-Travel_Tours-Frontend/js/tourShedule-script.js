const URL = "http://localhost:8080/api/v1/tourschedule";
let selectedTourScheduleId = null;

$("#tourScheduleForm").submit(function (event) {
    event.preventDefault();
    if (selectedTourScheduleId) {
        updateTourSchedule();
    } else {
        saveData();
    }
});

function saveData() {
    let tourSchedule = {
        tourID: $("#tourid").val(),
        startDate: $("#startDate").val(),
        endDate: $("#endDate").val(),
        meetingPoint: $("#meetingPoint").val(),
        guideID: $("#guideid").val()
    };

    $.ajax({
        url: `${URL}/save`,
        type: "POST",

        contentType: "application/json",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem('token')
        },
        data: JSON.stringify(tourSchedule),
        success: function () {
            alert("Tour Schedule saved successfully!");
            getAll();
            clearForm();
        },
        error: function () {
            alert("Error saving Tour Schedule!");
        }
    });
}

function getAll() {
    $.ajax({
        url: `${URL}/getAll`,
        type: "GET",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem('token')
        },
        dataType: "json", // Ensures response is parsed as JSON
        success: function (response) {
            console.log("Full Response:", response);

            if (!Array.isArray(response)) {
                console.error("Error: Expected array, got", typeof response);
                return;
            }

            let tourSchedules = response;

            $("#tourScheduleTableBody").empty();
            tourSchedules.forEach(tourSchedule => {
                $("#tourScheduleTableBody").append(`
                    <tr>
                        <td>${tourSchedule.scheduleId}</td>
                        <td>${tourSchedule.tourid}</td>
                        <td>${tourSchedule.startDate}</td>
                        <td>${tourSchedule.endDate}</td>
                        <td>${tourSchedule.meetingPoint}</td>
                        <td>${tourSchedule.guideid}</td>
                        <td>
                            <button class="btn btn-sm btn-info" onclick="fillTextFields('${tourSchedule.scheduleId}', '${tourSchedule.tourid}', '${tourSchedule.startDate}', '${tourSchedule.endDate}', '${tourSchedule.meetingPoint}', '${tourSchedule.guideid}')">Edit</button>
                            <button class="btn btn-sm btn-danger" onclick="deleteTourSchedule('${tourSchedule.scheduleID}')">Delete</button>
                        </td>
                    </tr>`);
            });
        },
        error: function (xhr, status, error) {
            console.error("Error fetching Tour Schedules:", error);
            alert("Error fetching Tour Schedules!");
        }
    });
}

function fillTextFields(scheduleId,tourid, startDate, endDate, meetingPoint, guideid) {
    $("#scheduleId").val(scheduleId);
    $("#tourid").val(tourid);
    $("#startDate").val(startDate);
    $("#endDate").val(endDate);
    $("#meetingPoint").val(meetingPoint);
    $("#guideid").val(guideid);

    selectedTourScheduleId = scheduleId;

    $("#saveButton").hide();
    $("#updateButton").show();
    $("#deleteButton").show();
}

function updateTourSchedule() {
    let updatedTourSchedule = {
        scheduleID: selectedTourScheduleId,
        tourID: $("#tourid").val(),
        startDate: $("#startDate").val(),
        endDate: $("#endDate").val(),
        meetingPoint: $("#meetingPoint").val(),
        guideID: $("#guideid").val()
    };

    if (!updatedTourSchedule.tourID || !updatedTourSchedule.startDate || !updatedTourSchedule.endDate) {
        alert("Please fill all fields!");
        return;
    }

    $.ajax({
        url: `${URL}/update`,
        type: "PUT",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem('token')
        },
        contentType: "application/json",
        data: JSON.stringify(updatedTourSchedule),
        success: function () {
            alert("Tour Schedule updated successfully!");
            getAll();
            clearForm();
        },
        error: function () {
            alert("Error updating Tour Schedule!");
        }
    });
}

function deleteTourSchedule(id) {
    if (!confirm("Are you sure you want to delete this Tour Schedule?")) return;

    $.ajax({
        url: `${URL}/delete/${id}`,
        type: "DELETE",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem('token')
        },
        success: function () {
            alert("Tour Schedule deleted successfully!");
            getAll();
            clearForm();
        },
        error: function () {
            alert("Error deleting Tour Schedule!");
        }
    });
}

function clearForm() {
    $("#tourScheduleForm")[0].reset();
    $("#updateButton").hide();
    $("#deleteButton").hide();
    $("#saveButton").show();
    selectedTourScheduleId = null;
}

$(document).ready(function () {
    getAll();
});
