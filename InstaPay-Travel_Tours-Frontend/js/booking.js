// Declare global variable for tours data
let toursData = [];

// Declare global variable for storing the bookingId after booking is placed
let currentBookingId = null;

// When the 'Pay Now' button is clicked
document.getElementById("payNowButton").addEventListener("click", function () {

    document.getElementById("bookingId").value = selectedBooking.bookingId;
    document.getElementById("amount").value = selectedBooking.totalAmount.toFixed(2);

    // Show modal
    const myModal = new bootstrap.Modal(document.getElementById("paymentModal"));
    myModal.show();
});

// Function to retrieve selected booking details
function getSelectedBooking() {
    const selectedRow = document.querySelector(".booking-row.selected");
    if (!selectedRow) {
        return null;  // Return null if no booking row is selected
    }

    return {
        bookingId: selectedRow.dataset.bookingId,  // Get the booking ID
        totalAmount: parseFloat(selectedRow.dataset.amount)  // Get the total amount
    };
}

// Confirm payment and send to backend
function confirmPayment() {
    const bookingId = document.getElementById("bookingId").value;
    const cardNumber = document.getElementById("cardNumber").value;
    const cardName = document.getElementById("cardName").value;
    const expiry = document.getElementById("expiry").value;
    const cvv = document.getElementById("cvv").value;
    const amount = document.getElementById("amount").value;

    // Validate the form fields
    if (!bookingId || !cardNumber || !cardName || !expiry || !cvv || !amount) {
        Swal.fire("Error", "Please fill in all the fields.", "warning");
        return;
    }

    // Prepare the payload
    const payload = {
        bookingId: bookingId,
        cardNumber: cardNumber,
        cardName: cardName,
        expiry: expiry,
        cvv: cvv,
        amount: parseFloat(amount)
    };

    // Make an AJAX request to backend to process payment
    $.ajax({
        url: "http://localhost:8080/payment/confirm",  // Your payment confirmation API
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(payload),
        success: function (response) {
            Swal.fire("Success", "Payment completed successfully!", "success");

            // Close the payment modal after success
            const paymentModal = bootstrap.Modal.getInstance(document.getElementById("paymentModal"));
            paymentModal.hide();

            // Update booking status in UI (if applicable)
            const bookingStatusElement = document.getElementById(`status-${payload.bookingId}`);
            if (bookingStatusElement) {
                bookingStatusElement.innerText = "PAID";
            }

            // Optionally, redirect to a confirmation page or other UI actions
            // window.location.href = "confirmationPage.html";
        },
        error: function (xhr, status, error) {
            console.error("Payment error:", xhr.responseText);
            Swal.fire("Error", "Payment failed. Try again later.", "error");
        }
    });
}
document.addEventListener("DOMContentLoaded", function () {
    const bookingForm = document.getElementById("bookingForm");
    const bookingDetailsContainer = document.getElementById("bookingDetails");

    let toursData = [];

    fetchUsers();
    fetchTours();

    function fetchUsers() {
        fetch("http://localhost:8080/api/v1/user/getAll")
            .then(response => response.json())
            .then(data => {
                const userIdDropdown = document.getElementById("uid");
                data.forEach(user => {
                    const option = document.createElement("option");
                    option.value = user.uid;
                    option.textContent = user.name;
                    userIdDropdown.appendChild(option);
                });
            })
            .catch(error => {
                console.error("Error fetching users:", error);
                Swal.fire("Error", "Failed to load users.", "error");
            });
    }

    function fetchTours() {
        fetch("http://localhost:8080/api/v1/tours/getAll")
            .then(response => response.json())
            .then(data => {
                toursData = data;
                const tourDropdowns = document.querySelectorAll(".tourDropdown");
                tourDropdowns.forEach(dropdown => {
                    dropdown.innerHTML = '<option value="">Select a Tour</option>';
                    data.forEach(tour => {
                        const option = document.createElement("option");
                        option.value = tour.tourID;
                        option.textContent = tour.tourName;
                        option.dataset.price = tour.price;
                        option.dataset.availableSeats = tour.availableSeats;
                        dropdown.appendChild(option);
                    });
                });
            })
            .catch(error => {
                console.error("Error fetching tours:", error);
                Swal.fire("Error", "Failed to load tours.", "error");
            });
    }

    document.getElementById("addDetail").addEventListener("click", function () {
        const detailDiv = document.createElement("div");
        detailDiv.classList.add("booking-detail");

        detailDiv.innerHTML = `
                <label>Tour:</label>
                <select class="tourDropdown" required>
                    <option value="">Select a Tour</option>
                </select>

                <label>Quantity:</label>
                <input type="number" class="quantity" required min="1">

                <label>Price:</label>
                <input type="number" class="price" step="0.01" required readonly>

                <button type="button" class="removeDetail">Remove</button>
            `;

        bookingDetailsContainer.appendChild(detailDiv);
        fetchTours();
    });

    bookingDetailsContainer.addEventListener("change", function (event) {
        const detail = event.target.closest(".booking-detail");

        if (event.target.classList.contains("tourDropdown")) {
            const selectedOption = event.target.selectedOptions[0];
            const price = parseFloat(selectedOption.dataset.price) || 0;
            const availableSeats = parseInt(selectedOption.dataset.availableSeats) || 0;

            const quantityInput = detail.querySelector(".quantity");
            const priceInput = detail.querySelector(".price");

            quantityInput.value = availableSeats > 0 ? 1 : 0;
            quantityInput.max = availableSeats;
            priceInput.value = price;

            if (availableSeats === 0) {
                Swal.fire("Unavailable", "No seats available for this tour.", "warning");
            }

            updateTotalPrice();
        }

        if (event.target.classList.contains("quantity")) {
            const quantityInput = event.target;
            let quantity = parseInt(quantityInput.value) || 1;

            const tourId = detail.querySelector(".tourDropdown").value;
            const tour = toursData.find(t => t.tourID == tourId);

            if (quantity < 1) {
                Swal.fire("Invalid Quantity", "Quantity cannot be less than 1.", "warning");
                quantityInput.value = 1;
                quantity = 1;
            }

            if (tour && quantity > tour.availableSeats) {
                Swal.fire("Error", `Only ${tour.availableSeats} seats available for this tour.`, "error");
                quantityInput.value = tour.availableSeats;
            }

            updateTotalPrice();
        }
    });


    function updateTotalPrice() {
        let total = 0;
        document.querySelectorAll(".booking-detail").forEach(detail => {
            const quantity = parseFloat(detail.querySelector(".quantity").value) || 0;
            const price = parseFloat(detail.querySelector(".price").value) || 0;
            total += quantity * price;
        });
        document.getElementById("totalPrice").value = total.toFixed(2);
    }

    bookingDetailsContainer.addEventListener("click", function (event) {
        if (event.target.classList.contains("removeDetail")) {
            event.target.closest(".booking-detail").remove();
            updateTotalPrice();
        }
    });

    bookingForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const userId = document.getElementById("uid").value;
        const bookingDate = document.getElementById("bookingDate").value;
        const totalPrice = document.getElementById("totalPrice").value;

        if (!userId || !bookingDate || !totalPrice) {
            Swal.fire("Error", "Please fill in all the required fields.", "error");
            return;
        }

        const bookingDetails = [];
        let hasInvalid = false;

        document.querySelectorAll(".booking-detail").forEach(detail => {
            const tourId = detail.querySelector(".tourDropdown").value;
            const quantity = parseInt(detail.querySelector(".quantity").value);
            const price = parseFloat(detail.querySelector(".price").value);

            const tour = toursData.find(t => t.tourID == tourId);

            if (!tourId || !quantity || !price || quantity < 1 || quantity > tour.availableSeats) {
                hasInvalid = true;
                return;
            }

            bookingDetails.push({ tourId, quantity, price });
        });

        if (hasInvalid) {
            Swal.fire("Error", "Please make sure all quantities are valid and within available seats.", "error");
            return;
        }

        const bookingData = {
            userId,
            bookingDate,
            totalPrice,
            bookingDetails
        };

        fetch("http://localhost:8080/api/v1/booking/place", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(bookingData)
        })
            .then(response => response.json())
            .then(data => {
                decreaseAvailableSeats(bookingDetails);
                Swal.fire({
                    icon: "success",
                    title: "Success!",
                    text: "Your booking has been confirmed!",
                    showCancelButton: true,
                    confirmButtonText: "Go to Payment",
                    cancelButtonText: "Stay Here",
                }).then((result) => {
                    if (result.isConfirmed) {
                        window.location.href = "payment.html";
                    }
                });
                bookingForm.reset();
                bookingDetailsContainer.innerHTML = '';
            })
            .catch(error => {
                console.error("Error submitting booking:", error);
                Swal.fire("Error", "Failed to place booking!", "error");
            });
    });

    function decreaseAvailableSeats(bookingDetails) {
        bookingDetails.forEach(detail => {
            const tour = toursData.find(t => t.tourID == detail.tourId);
            if (tour) {
                tour.availableSeats -= detail.quantity;
            }
        });
    }

    });
