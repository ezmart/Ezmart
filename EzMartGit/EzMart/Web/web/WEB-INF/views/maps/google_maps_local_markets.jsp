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


                console.log(response);
                response.forEach(function (locationItens) {
                    var marker = null;

                    var contentString =
                            '<p><b>' + locationItens.establishmentsName + '</b></p>';

                    var infowindow = new google.maps.InfoWindow({
                        content: contentString
                    });

                    marker = new google.maps.Marker({
                        position: new google.maps.LatLng(locationItens.latitude, locationItens.longitude),
                        title: locationItens.establishmentsName,
                        map: map
                    });

                    marker.addListener('click', function () {
                        infowindow.open(map, marker);
                    });

                    marker.addListener('dblclick', function () {
                        getMarketWithTheList(locationItens.id);
                    });
                });
            },
            error: function () {
                console.log('Falha ao carregar! =/');
            }
        });
    }

    function getMarketWithTheList(establishmentId) {

        $.ajax({
            url: '/ezmartWeb/localMarkets-' + establishmentId,
            type: 'POST',
            success: function (response) {
                console.log('OK! =)');
            },
            error: function () {
                console.log('Falha ao carregar! =/');
            }
        });
    }
</script>