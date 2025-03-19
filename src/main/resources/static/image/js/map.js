let map, searchBox, marker;

function initMap() {
    // Initialize the map
    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 40.730610, lng: -73.935242 }, // Default location
        zoom: 12,
    });

    // Create a search box
    searchBox = new google.maps.places.SearchBox(document.getElementById("searchBox"));

    // Bias the SearchBox results towards current map's viewport
    map.addListener("bounds_changed", () => {
        searchBox.setBounds(map.getBounds());
    });

    // Listen for the user selecting a place from the search box
    searchBox.addListener("places_changed", () => {
        const places = searchBox.getPlaces();

        if (places.length == 0) {
            return;
        }

        // Clear any existing markers
        if (marker) {
            marker.setMap(null);
        }

        // Get the first place
        const place = places[0];

        // Create a marker for the selected place
        marker = new google.maps.Marker({
            position: place.geometry.location,
            map: map,
            title: place.name,
        });

        // Zoom in on the selected place
        map.setCenter(place.geometry.location);
        map.setZoom(15);
    });
}
