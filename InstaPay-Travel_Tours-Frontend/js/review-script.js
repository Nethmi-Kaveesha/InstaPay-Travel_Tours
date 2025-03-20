const URL = "http://localhost:8080/api/v1/reviews";
let selectedReviewId = null;
let loggedInUserId = "user_id_here";

$(document).ready(function () {
    getAllReviews();
});

$("#reviewForm").submit(function (event) {
    event.preventDefault();
    if (selectedReviewId) {
        updateReview();
    } else {
        saveReview();
    }
});

function saveReview() {
    let review = {
        userid: loggedInUserId,
        tourId: $("#tourid").val(),
        rating: $("#rating").val(),
        reviewText: $("#reviewText").val(),
    };

    console.log("Saving Review:", review);

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


function updateReview() {
    let updatedReview = {
        reviewid: selectedReviewId,
        userid: loggedInUserId,
        tourId: $("#tourid").val(),
        rating: $("#rating").val(),
        reviewText: $("#reviewText").val(),
    };

    console.log("Updating Review:", updatedReview);

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


function getAllReviews() {
    $.ajax({
        url: `${URL}/getAll`,
        type: "GET",
        dataType: "json",
        success: function (response) {
            console.log("Fetched Reviews:", response);
            $("#reviewTableBody").empty();

            response.forEach(review => {
                let userId = review.userid ? review.userid : "Unknown";
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


function fillReviewFields(id, userId, tourId, rating, reviewText) {
    $("#reviewid").val(id);
    $("#userid").val(userId || "Unknown");
    $("#tourid").val(tourId);
    $("#rating").val(rating);
    $("#reviewText").val(reviewText);

    selectedReviewId = id;
    $("#saveButton").hide();
    $("#updateButton").show();
    $("#deleteButton").show();
}


function clearForm() {
    $("#reviewForm")[0].reset();
    $("#updateButton").hide();
    $("#deleteButton").hide();
    $("#saveButton").show();
    selectedReviewId = null;
}
