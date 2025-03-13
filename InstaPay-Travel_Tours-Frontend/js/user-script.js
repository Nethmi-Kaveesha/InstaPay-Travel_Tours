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
        phone_number: $("#phone_number").val(),
        gender: $("#gender").val()
    };

    // Log user data for debugging
    console.log(user);

    // Basic email validation
    let email = $("#email").val();
    let emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    if (!emailPattern.test(email)) {
        alert("Please enter a valid email address!");
        return;
    }

    // Phone number validation (10 digits)
    let phoneNumber = $("#phone_number").val();
    let phonePattern = /^\d{10}$/; // Adjust pattern based on your requirements
    if (!phonePattern.test(phoneNumber)) {
        alert("Please enter a valid 10-digit phone number!");
        return;
    }

    // Validate all fields
    if (!user.name || !user.role || !user.phone_number || !user.gender || !user.email || !user.password) {
        alert("Please fill all fields!");
        return;
    }

    $.ajax({
        url: `${URL}/register`,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(user),
        success: function () {
            alert("User saved successfully!");
            window.location.href = "user.html"; // Redirect to user page
            getAll(); // Refresh the user list
            clearForm();
        },
        error: function (xhr, status, error) {
            console.error("Error saving user:", error);
            alert("Error saving user!");
        }
    });
}

function getAll() {
    $.ajax({
        url: `${URL}/getAll`,
        type: "GET",
        dataType: "json",
        success: function (response) {
            console.log("Full Response:", response);

            let users = Array.isArray(response) ? response : response.data;

            if (!Array.isArray(users)) {
                console.error("Error: Expected array, got", typeof users);
                return;
            }

            $("#userTableBody").empty();
            users.forEach(user => {
                $("#userTableBody").append(`
                    <tr>
                        <td>${user.email}</td>
                        <td>${user.name}</td>
                        <td>${user.role}</td>
                        <td>${user.phone_number}</td>
                        <td>${user.gender}</td>
                        <td>
                            <button class="btn btn-sm btn-info" onclick="fillTextFields('${user.email}', '${user.name}', '${user.role}', '${user.phone_number}', '${user.gender}')">Edit</button>
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

function fillTextFields(email, name, role, phone_number, gender) {
    $("#email").val(email);
    $("#name").val(name);
    $("#role").val(role);
    $("#phone_number").val(phone_number);
    $("#gender").val(gender);

    selectedUserId = email;

    $("#saveButton").hide();
    $("#updateButton").show();
    $("#deleteButton").show();
}
function updateUser() {
    console.log("Updating user with email:", selectedUserId);  // Debugging log
    let updatedUser = {
        email: selectedUserId,
        name: $("#name").val(),
        role: $("#role").val(),
        phone_number: $("#phone_number").val(),
        gender: $("#gender").val()
    };

    // Continue with validation and AJAX...

    // Validate all fields
    if (!updatedUser.name || !updatedUser.role || !updatedUser.phone_number || !updatedUser.gender) {
        alert("Please fill all fields!");
        return;
    }

    // Phone number validation (10 digits)
    let phonePattern = /^\d{10}$/; // Adjust pattern based on your requirements
    if (!phonePattern.test(updatedUser.phone_number)) {
        alert("Please enter a valid 10-digit phone number!");
        return;
    }

    $.ajax({
        url: `${URL}/update`,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(updatedUser),
        success: function () {
            alert("User updated successfully!");
            window.location.href = "user.html";
            getAll();  // Refresh the user table
            clearForm();
        },
        error: function (xhr, status, error) {
            console.error("Error updating user:", error);
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
        error: function (xhr, status, error) {
            console.error("Error deleting user:", error);
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
    getAll(); // Load all users on page load
});
