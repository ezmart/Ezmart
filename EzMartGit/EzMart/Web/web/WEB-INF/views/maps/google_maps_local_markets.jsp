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


                //console.log(response);
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

        document.getElementById("content_insert").innerHTML = "";

        $.ajax({
            url: '/ezmartWeb/localMarkets-' + establishmentId,
            type: 'GET',
            dataType: "json",
            success: function (response) {


                console.log(response);

                response.forEach(function (item) {

                    var mydiv = document.getElementById("content_insert");
                    var tagH3 = document.createElement('h4');
                    tagH3.innerHTML = "Produtos do mercado em relação a sua lista favorita";
                    var tagBr4 = document.createElement('br');
                    var tagDiv1 = document.createElement('div');
                    tagDiv1.className = "col s12";

                    var tagDiv2 = document.createElement('div');
                    tagDiv2.className = "col s12";

                    var labelTag = document.createElement('label');
                    labelTag.innerHTML = "Nome do estabelecimento: " + item.establishmentName;
                    labelTag.style = "color:black; margin-left: 15px; font-size: x-large";

                    tagDiv1.appendChild(labelTag);

                    var labelTag2 = document.createElement('label');
                    labelTag2.innerHTML = "Preço total: R$ " + item.totalPrice;
                    labelTag2.style = "color:black; margin-left: 15px; font-size: x-large;";

                    tagDiv2.appendChild(labelTag2);

                    mydiv.appendChild(tagH3);
                    mydiv.appendChild(tagBr4);
                    mydiv.appendChild(tagDiv1);
                    mydiv.appendChild(tagDiv2);

                    item.productModelList.forEach(function (value) {
                        var tagDivAux2 = document.createElement('div');
                        tagDivAux2.className = "col s12";

                        var tagImg = document.createElement('img');
                        tagImg.className = "responsive-img";
                        tagImg.src = "/ezmartWeb/resources/img/product/" + value.productId + ".jpg";
                        tagImg.width = "90";
                        //tagImg.height = "60";

                        var tagBr = document.createElement('br');
                        var tagBr1 = document.createElement('br');
                        var tagBr2 = document.createElement('br');
                        var tagBr3 = document.createElement('br');


                        var labelTagAux1 = document.createElement('label');
                        labelTagAux1.innerHTML = "Nome do produto: " + value.productName;
                        labelTagAux1.style = "color:black; margin-left: 15px; font-size: x-large";

                        var labelTagAux2 = document.createElement('label');
                        labelTagAux2.innerHTML = "Preço do produto: R$ " + value.price;
                        labelTagAux2.style = "color:black; margin-left: 15px; font-size: x-large";

                        var labelTagAux3 = document.createElement('label');
                        labelTagAux3.innerHTML = "------------------------------------";
                        labelTagAux3.style = "color:black; margin-left: 15px; font-size: x-large";
                        labelTagAux3.className = "center";

                        tagDivAux2.appendChild(tagImg);
                        tagDivAux2.appendChild(tagBr1);
                        tagDivAux2.appendChild(labelTagAux1);
                        tagDivAux2.appendChild(tagBr);
                        tagDivAux2.appendChild(labelTagAux2);
                        tagDivAux2.appendChild(tagBr2);
                        tagDivAux2.appendChild(labelTagAux3);
                        tagDivAux2.appendChild(tagBr3);


                        mydiv.appendChild(tagDivAux2);
                    });




                    //var tagP1 = document.createElement('p');

                });


                console.log('OK! =)');
            },
            error: function (response) {

                var mydiv = document.getElementById("content_insert");
                var h3TagProduct = document.createElement('h5');
                h3TagProduct.innerHTML = "Falhou";
                mydiv.appendChild(h3TagProduct)
                console.log('Falha ao carregar! =/');
            }
        });
    }
</script>