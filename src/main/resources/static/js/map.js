let map, searchBox, marker;

function initMap() {

    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 40.730610, lng: -73.935242 },
        zoom: 12,
    });


    searchBox = new google.maps.places.SearchBox(document.getElementById("searchBox"));


    map.addListener("bounds_changed", () => {
        searchBox.setBounds(map.getBounds());
    });

    searchBox.addListener("places_changed", () => {
        const places = searchBox.getPlaces();

        if (places.length == 0) {
            return;
        }

        if (marker) {
            marker.setMap(null);
        }

        const place = places[0];

        marker = new google.maps.Marker({
            position: place.geometry.location,
            map: map,
            title: place.name,
        });

        map.setCenter(place.geometry.location);
        map.setZoom(15);
    });
}
