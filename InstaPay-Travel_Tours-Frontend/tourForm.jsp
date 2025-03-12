<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tour Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .tour-header {
            text-align: center;
            margin-bottom: 30px;
        }
        .tour-header h1 {
            color: #2c3e50;
        }
        .tour-image {
            width: 100%;
            height: auto;
            margin-bottom: 20px;
        }
        .tour-details {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }
        .tour-info {
            width: 48%;
        }
        .tour-info h2 {
            color: #e67e22;
        }
        .tour-info p {
            color: #34495e;
        }
        .price {
            font-size: 20px;
            font-weight: bold;
            color: #27ae60;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="tour-header">
        <h1 id="tourName"></h1>
        <p id="location"></p>
    </div>

    <img id="tourImage" class="tour-image" alt="Tour Image" style="display: none;">

    <div class="tour-details">
        <div class="tour-info"><h2>Description</h2><p id="description"></p></div>
        <div class="tour-info"><h2>Duration</h2><p id="duration"></p></div>
        <div class="tour-info"><h2>Tour Type</h2><p id="tourType"></p></div>
        <div class="tour-info"><h2>Price</h2><p class="price" id="price"></p></div>
        <div class="tour-info"><h2>Available Seats</h2><p id="availableSeats"></p></div>
        <div class="tour-info"><h2>Start Date</h2><p id="startDate"></p></div>
        <div class="tour-info"><h2>End Date</h2><p id="endDate"></p></div>
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        fetchTourDetails();
    });

    function fetchTourDetails() {
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "getTourDetails.php", true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                const tour = JSON.parse(xhr.responseText);
                document.getElementById("tourName").innerText = tour.tourName;
                document.getElementById("location").innerText = tour.location;
                document.getElementById("description").innerText = tour.description;
                document.getElementById("duration").innerText = tour.duration + " days";
                document.getElementById("tourType").innerText = tour.tourType;
                document.getElementById("price").innerText = tour.price;
                document.getElementById("availableSeats").innerText = tour.availableSeats;
                document.getElementById("startDate").innerText = tour.startDate;
                document.getElementById("endDate").innerText = tour.endDate;

                if (tour.images) {
                    const imgElement = document.getElementById("tourImage");
                    imgElement.src = "data:image/jpeg;base64," + tour.images;
                    imgElement.style.display = "block";
                }
            }
        };
        xhr.send();
    }
</script>
</body>
</html>