<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDEidRKiY7WNKV4FG8y5eb5Z0TIrLOCeDE&callback=initMap"
async defer></script>

<script type="text/javascript">
    var map;
    var geocoder;


    function initMap() {
        
        //Inicializa o MAPS
        initialize();

    }

    //Inicializa o MAPS com as localizações dos estabelecimentos
    function initialize() {
        var latlng = new google.maps.LatLng(-22.260292, -45.7048742);
        var options = {
            zoom: 15,
            center: latlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };

        map = new google.maps.Map(document.getElementById("map"), options);
        geocoder = new google.maps.Geocoder();

        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 10,
            center: new google.maps.LatLng(-22.260292, -45.7048742),
            mapTypeId: google.maps.MapTypeId.ROADMAP
        });

        $.ajax({
            url: 'api/system/localMarkets',
            type: 'GET',
            dataType: "json",
            success: function (response) {
                var marker;
                //var infowindow = new google.maps.InfoWindow();

                response.forEach(function (locationItens) {
                    marker = new google.maps.Marker({
                        position: new google.maps.LatLng(locationItens.latitude, locationItens.longitude),
                        map: map
                    });

//                    google.maps.event.addListener(marker, 'click', (function (marker, 0) {
//                        return function () {
//                            infowindow.setContent(locations[0][0]);
//                            infowindow.open(map, marker);
//                        }
//                    })(marker, i));
                });

            },
            error: function () {
                console.log('Falha ao carregar! =/');
            }
        });
    }

</script>