<script>
    var map;
    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: -23.5489, lng: -46.6388},
            zoom: 8
        });
    }
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDEidRKiY7WNKV4FG8y5eb5Z0TIrLOCeDE&callback=initMap"
async defer></script>