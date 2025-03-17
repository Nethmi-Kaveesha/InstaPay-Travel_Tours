const URL = "http://localhost:8080/api/v1/reviews";
let selectedReviewId = null;
let loggedInUserId = "user_id_here"; // Replace this with actual user ID retrieval logic

$(document).ready(function () {
    getAllReviews();
});

// Handle form submission
$("#reviewForm").submit(function (event) {
    event.preventDefault();
    if (selectedReviewId) {
        updateReview();
    } else {
        saveReview();
    }
});

// Save a new review
function saveReview() {
    let review = {
        userid: loggedInUserId, // Use logged-in user ID
        tourId: $("#tourid").val(),
        rating: $("#rating").val(),
        reviewText: $("#reviewText").val(),
    };

    console.log("Saving Review:", review); // Debugging

    $.ajax({
        url: `${URL}/save`,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(review),
        success: function () {
            alert("Review saved successfully!");
            getAllReviews();
            clearForm();
        },
        error: function () {
            alert("Error saving review!");
        }
    });
}

// Update an existing review
function updateReview() {
    let updatedReview = {
        reviewid: selectedReviewId,
        userid: loggedInUserId, // Use logged-in user ID
        tourId: $("#tourid").val(),
        rating: $("#rating").val(),
        reviewText: $("#reviewText").val(),
    };

    console.log("Updating Review:", updatedReview); // Debugging

    $.ajax({
        url: `${URL}/update`,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(updatedReview),
        success: function () {
            alert("Review updated successfully!");
            getAllReviews();
            clearForm();
        },
        error: function () {
            alert("Error updating review!");
        }
    });
}

// Delete a review
function deleteReview(id) {
    if (!confirm("Are you sure you want to delete this review?")) return;

    $.ajax({
        url: `${URL}/delete/${id}`,
        type: "DELETE",
        success: function () {
            alert("Review deleted successfully!");
            getAllReviews();
            clearForm();
        },
        error: function () {
            alert("Error deleting review!");
        }
    });
}

// Fetch and display all reviews
function getAllReviews() {
    $.ajax({
        url: `${URL}/getAll`,
        type: "GET",
        dataType: "json",
        success: function (response) {
            console.log("Fetched Reviews:", response); // Debugging
            $("#reviewTableBody").empty();

            response.forEach(review => {
                let userId = review.userid ? review.userid : "Unknown"; // Handle undefined user ID
                $("#reviewTableBody").append(`
                    <tr>
                        <td>${review.reviewid}</td>
                        <td>${userId}</td>
                        <td>${review.tourId}</td>
                        <td>${review.rating}</td>
                        <td>${review.reviewText}</td>
                        <td>
                            <button class="btn btn-sm btn-info" onclick="fillReviewFields('${review.reviewid}', '${userId}', '${review.tourId}', '${review.rating}', '${review.reviewText}')">Edit</button>
                            <button class="btn btn-sm btn-danger" onclick="deleteReview('${review.reviewid}')">Delete</button>
                        </td>
                    </tr>`);
            });
        },
        error: function () {
            alert("Error fetching reviews!");
        }
    });
}

// Fill form fields for editing a review
function fillReviewFields(id, userId, tourId, rating, reviewText) {
    $("#reviewid").val(id);
    $("#userid").val(userId || "Unknown"); // Handle undefined user ID
    $("#tourid").val(tourId);
    $("#rating").val(rating);
    $("#reviewText").val(reviewText);

    selectedReviewId = id;
    $("#saveButton").hide();
    $("#updateButton").show();
    $("#deleteButton").show();
}

// Clear form fields
function clearForm() {
    $("#reviewForm")[0].reset();
    $("#updateButton").hide();
    $("#deleteButton").hide();
    $("#saveButton").show();
    selectedReviewId = null;
}
