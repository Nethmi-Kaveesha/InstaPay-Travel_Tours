const URL = "http://localhost:8080/api/v1/user";
let selectedUserId = null;

$("#userForm").submit(function (event) {
    event.preventDefault();
    if (selectedUserId) {
        updateUser();
    } else {
        saveData();
    }
});

function saveData() {
    let user = {
        email: $("#email").val(),
        password: $("#password").val(),
        name: $("#name").val(),
        role: $("#role").val(),
        phoneNumber: $("#phoneNumber").val(),
        gender: $("#gender").val()
    };

    $.ajax({
        url: `${URL}/save`,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(user),
        success: function () {
            alert("User saved successfully!");
            getAll();
            clearForm();
        },
        error: function () {
            alert("Error saving user!");
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

            // Check if the response contains a data field (if using ResponseUtil)
            let users = Array.isArray(response) ? response : response.data;

            if (!Array.isArray(users)) {
                console.error("Error: Expected array, got", typeof users);
                return;
            }

            // Render the table
            $("#userTableBody").empty();
            users.forEach(user => {
                $("#userTableBody").append(`
                <tr>
                    <td>${user.email}</td>
                    <td>${user.name}</td>
                    <td>${user.role}</td>
                    <td>${user.phoneNumber}</td>
                    <td>${user.gender}</td>
                    <td>
                        <button class="btn btn-sm btn-info" onclick="fillTextFields('${user.email}', '${user.name}', '${user.role}', '${user.phoneNumber}', '${user.gender}')">Edit</button>
                        <button class="btn btn-sm btn-danger" onclick="deleteUser('${user.email}')">Delete</button>
                    </td>
                </tr>`);
            });
        },
        error: function (xhr, status, error) {
            console.error("Error fetching users:", error);
            alert("Error fetching users!");
        }
    });
}

function fillTextFields(email, name, role, phoneNumber, gender) {
    $("#email").val(email);
    $("#name").val(name);
    $("#role").val(role);
    $("#phoneNumber").val(phoneNumber);
    $("#gender").val(gender);

    selectedUserId = email;

    $("#saveButton").hide();
    $("#updateButton").show();
    $("#deleteButton").show();
}

function updateUser() {
    let updatedUser = {
        email: selectedUserId,
        name: $("#name").val(),
        role: $("#role").val(),
        phoneNumber: $("#phoneNumber").val(),
        gender: $("#gender").val()
    };

    if (!updatedUser.name || !updatedUser.role || !updatedUser.phoneNumber || !updatedUser.gender) {
        alert("Please fill all fields!");
        return;
    }

    $.ajax({
        url: `${URL}/update`,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(updatedUser),
        success: function () {
            alert("User updated successfully!");
            getAll();
            clearForm();
        },
        error: function () {
            alert("Error updating user!");
        }
    });
}

function deleteUser(email) {
    if (!confirm("Are you sure you want to delete this user?")) return;

    $.ajax({
        url: `${URL}/delete/${email}`,
        type: "DELETE",
        success: function () {
            alert("User deleted successfully!");
            getAll();
            clearForm();
        },
        error: function () {
            alert("Error deleting user!");
        }
    });
}

function clearForm() {
    $("#userForm")[0].reset();
    $("#updateButton").hide();
    $("#deleteButton").hide();
    $("#saveButton").show();
    selectedUserId = null;
}

$(document).ready(function () {
    getAll();
});
