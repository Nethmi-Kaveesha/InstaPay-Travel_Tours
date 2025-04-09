const URL = "http://localhost:8080/reviews";
let selectedReviewId = null;

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
        customerName: $("#name").val(),
        email: $("#email").val(),
        rating: $("#rating").val(),
        reviewText: $("#comment").val(),
    };

    $.ajax({
        url: `${URL}/save`,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(review),
        success: function () {
            alert("Review submitted successfully!");
            getAllReviews();
            clearForm();
        },
        error: function () {
            alert("Error submitting Review!");
        }
    });
}

function getAllReviews() {
    $.ajax({
        url: `${URL}/getAll`,
        type: "GET",
        dataType: "json",
        success: function (response) {
            console.log("Full Response:", response);

            if (!Array.isArray(response)) {
                console.error("Error: Expected array, got", typeof response);
                return;
            }

            let reviews = response;

            $("#reviewTableBody").empty();
            reviews.forEach(review => {
                $("#reviewTableBody").append(`
                    <tr>
                        <td>${review.id}</td>
                        <td>${review.name}</td>
                        <td>${review.email}</td>
                        <td>${review.rating}</td>
                        <td>${review.comment}</td>
                        <td>
                            <button class="btn btn-sm btn-info" onclick="fillReviewFields('${review.id}', '${review.name}', '${review.email}', '${review.rating}', '${review.comment}')">Edit</button>
                            <button class="btn btn-sm btn-danger" onclick="deleteReview('${review.id}')">Delete</button>
                        </td>
                    </tr>`);
            });
        },
        error: function () {
            alert("Error fetching reviews!");
        }
    });
}

function fillReviewFields(id, name, email, rating, reviewText) {
    $("#id").val(id);
    $("#name").val(name);
    $("#email").val(email);
    $("#rating").val(rating);
    $("#comment").val(reviewText);

    selectedReviewId = id;

    $("#submitButton").hide();
    $("#updateButton").show();
    $("#deleteButton").show();
}

function updateReview() {
    let updatedReview = {
        id: selectedReviewId,
        name: $("#name").val(),
        email: $("#email").val(),
        rating: $("#rating").val(),
        comment: $("#comment").val(),
    };

    if (!updatedReview.name || !updatedReview.email || !updatedReview.rating || !updatedReview.comment) {
        alert("Please fill all fields!");
        return;
    }

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

function clearForm() {
    $("#reviewForm")[0].reset();
    $("#updateButton").hide();
    $("#deleteButton").hide();
    $("#submitButton").show();
    selectedReviewId = null;
}

$(document).ready(function () {
    getAllReviews();
});
