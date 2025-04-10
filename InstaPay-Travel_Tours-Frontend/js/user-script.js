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

    console.log(user);

    let email = $("#email").val();
    let emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    if (!emailPattern.test(email)) {
        alert("Please enter a valid email address!");
        return;
    }

    let phoneNumber = $("#phone_number").val();
    let phonePattern = /^\d{10}$/;
    if (!phonePattern.test(phoneNumber)) {
        alert("Please enter a valid 10-digit phone number!");
        return;
    }

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
            window.location.href = "user.html";
            getAll();
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
        headers: {
            "Authorization": "Bearer " + localStorage.getItem('token')
        },
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
                            <button class="btn btn-sm btn-info" onclick="fillTextFields('${user.uid}', '${user.email}', '${user.name}', '${user.role}', '${user.phone_number}', '${user.gender}')">Edit</button>
                            <button class="btn btn-sm btn-danger" onclick="deleteUserByEmail('${user.email}')">Delete</button>
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

function fillTextFields(uid, email, name, role, phone_number, gender) {
    $("#email").val(email);
    $("#name").val(name);
    $("#role").val(role);
    $("#phone_number").val(phone_number);
    $("#gender").val(gender);

    selectedUserId = uid;

    $("#saveButton").hide();
    $("#updateButton").show();
    $("#deleteButton").show();
}

function updateUser() {
    console.log("Updating user with UID:", selectedUserId);  // Debugging log
    let updatedUser = {
        uid: selectedUserId,
        name: $("#name").val(),
        role: $("#role").val(),
        phone_number: $("#phone_number").val(),
        gender: $("#gender").val()
    };

    if (!updatedUser.name || !updatedUser.role || !updatedUser.phone_number || !updatedUser.gender) {
        alert("Please fill all fields!");
        return;
    }

    let phonePattern = /^\d{10}$/;
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
            getAll();
            clearForm();
        },
        error: function (xhr, status, error) {
            console.error("Error updating user:", error);
            alert("Error updating user!");
        }
    });
}

function deleteUserByEmail(email) {
    fetch(`http://localhost:8080/api/v1/user/delete/email/${email}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            // 'Authorization': 'Bearer YOUR_TOKEN' // if needed
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Failed to delete user, status: ${response.status}`);
            }
            return response.text();
        })
        .then(data => {
            console.log('User deleted:', data);
        })
        .catch(error => {
            console.error('Error deleting user:', error);
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
