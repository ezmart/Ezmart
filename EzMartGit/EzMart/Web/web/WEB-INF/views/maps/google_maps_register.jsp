<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDEidRKiY7WNKV4FG8y5eb5Z0TIrLOCeDE&callback=initMap"
async defer></script>
<script>
    var map;
    var geocoder;
    var marker;

    function initMap() {

        //Inicializa o MAPS
        initialize();

        // verifica se o navegador tem suporte a geolocalização
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) { // callback de sucesso
                // ajusta a posição do marker para a localização do usuário
                marker.setPosition(new google.maps.LatLng(position.coords.latitude, position.coords.longitude));
                var lat = marker.getPosition().lat();
                var lng = marker.getPosition().lng();

                setNewValueMap(lat, lng);
                //Seta os valores de acordo com a localização
                codeLatLng(lat, lng);
            },
                    function (error) { // callback de erro
                        alert('Erro ao obter localização!');
                        console.log('Erro ao obter localização.', error);
                    });
        } else {
            console.log('Navegador não suporta Geolocalização!');
        }
    }

    //Inicializa o MAPS
    function initialize() {

        var latlng = new google.maps.LatLng(-22.260292, -45.7048742);
        var options = {
            zoom: 15,
            center: latlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };

        map = new google.maps.Map(document.getElementById("map"), options);

        geocoder = new google.maps.Geocoder();

        //var image = 'https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png';
        marker = new google.maps.Marker({
            map: map,
            draggable: true
                    //icon: image
        });

        marker.setPosition(latlng);

    }

    function setNewValueMap(lat, lng) {

        var latlng = new google.maps.LatLng(lat, lng);
        var options = {
            zoom: 17,
            center: latlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            styles: [{
                    "featureType": "all",
                    "elementType": "all",
                    "stylers": [
                        {
                            "invert_lightness": true
                        },
                        {
                            "saturation": 10
                        },
                        {
                            "lightness": 30
                        },
                        {
                            "gamma": 0.5
                        },
                        {
                            "hue": "#435158"
                        }
                    ]
                }]

        };

        map = null;
        map = new google.maps.Map(document.getElementById("map"), options);

        marker = null;
        marker = new google.maps.Marker({
            map: map,
            draggable: true
                    //icon: image
        });

        marker.setPosition(latlng);
    }

    function codeLatLng(lat, lng) {
        var latlng = new google.maps.LatLng(lat, lng);
        geocoder.geocode({
            'latLng': latlng
        }, function (results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
                if (results[1]) {
                    //var formattedAddress = results[0].formatted_address.split(",");
                    var addressComponents = results[1].address_components;

                    document.getElementById("addressLocation").value = addressComponents[1].short_name;
                    document.getElementById("numberHouse").value = addressComponents[0].short_name;
                    document.getElementById("neighborhood").value = addressComponents[2].short_name;
                    document.getElementById("zipCode").value = addressComponents[6].short_name;

                    document.getElementById("latitude").value = lat;
                    document.getElementById("longitude").value = lng;

                    console.log(results[1]);
                } else {
                    alert('No results found');
                }
            } else {
                alert('Geocoder failed due to: ' + status);
            }
        });
    }


</script>
