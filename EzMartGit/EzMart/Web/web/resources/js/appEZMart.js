$(document).ready(function () {
    $('.parallax').parallax();
});
$(document).ready(function () {
    $('input#input_text, textarea#textarea1').characterCounter();
});
$(document).ready(function () {
    $('ul.tabs').tabs();
});
$(document).ready(function () {
    $('.slider').slider({full_width: true});
});
$(document).ready(function () {
    $('select').material_select();
});
$(document).ready(function () {
    $(".button-collapse").sideNav();
});
$(document).ready(function () {
// the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
    $('.modal-trigger').leanModal();
});
window.onload = function () {
    $(document).ready(function () {
        $('select').material_select();
    });
};
function setDadosModalSector(sectorId, sectorName) {
    document.getElementById('sector-id').innerHTML = sectorId + " - " + sectorName;
    document.getElementById('idSectorDelete').value = sectorId;
}

function setNameSectorEdit(sectorId, sectorName) {
    document.getElementById('idSector').value = sectorId;
    document.querySelector("[name='sectorNameEdit']").value = sectorName;
}

function setDadosModalProvider(providerId, providerName) {
    document.getElementById('provider-id').innerHTML = providerId + " - " + providerName;
    document.getElementById('idProviderDelete').value = providerId;
}

function setProviderEdit(providerId, providerCnpj, providerName, providerBusinessName) {
    document.getElementById('idProvider').value = providerId;
    document.querySelector("[name='providerCnpjEdit']").value = providerCnpj;
    document.querySelector("[name='providerNameEdit']").value = providerName;
    document.querySelector("[name='providerBusinessNameEdit']").value = providerBusinessName;
}

function setDadosModalProduct(productId, productName) {
    document.getElementById('product-id').innerHTML = productId + " - " + productName;
    document.getElementById('idProductDelete').value = productId;
}

function setProductEdit(productId, productName, productBarCode, productBrand, productSector, productProvider) {
    document.getElementById('idProduct').value = productId;
    document.querySelector("[name='productNameEdit']").value = productName;
    document.querySelector("[name='productBarCodeEdit']").value = productBarCode;
    document.querySelector("[name='productBrandEdit']").value = productBrand;
    document.querySelector("[name='productSectorEdit']").value = productSector;
//    document.querySelector("[name='productProviderEdit']").value = productProvider;

}

function setProductEstablishmentId(productId) {
    document.getElementById('idProduct').value = productId;
}

function setProductEstablishmentEdit(establishmentProductId, price) {
    document.getElementById('idEstablishmentProductId').value = establishmentProductId;
    document.querySelector("[name='priceProduct']").value = price;
}

function setProductEstablishmentDelete(establishmentProductId, barCodeProduct, nameProduct) {
    document.getElementById('idProductDelete').value = establishmentProductId;
    document.getElementById('barCodeProduct').innerHTML = barCodeProduct;
    document.getElementById('nameProduct').innerHTML = nameProduct;
}

function setEstablishmentProductId(establishmentProductId) {
    document.getElementById('idEstablishmentProduct').value = establishmentProductId;
}

//function setEstablishmentIdForQuotation$(establishmentId) {
//    document.getElementById('idEstablishmentProduct').value = establishmentId;
//}

function setDadosModalUser(userId, consumerName, establishmentName) {
    var complement = '';
    if (consumerName != null && consumerName != '') {
        complement = userId + " - " + consumerName;
    } else {
        complement = userId + " - " + establishmentName;
    }
    document.getElementById('user-id').innerHTML = complement;
    document.getElementById('idUserDelete').value = userId;
}

function setUserEdit(userIdEdit, nameEdit, lastNameEdit, cpfEdit, emailEdit, addressLocationEdit, numberHouseEdit, neighborhoodEdit,
        stateIdEdit, cityIdEdit, zipCodeEdit, telephoneEdit, adminEdit, activeUserEdit) {

    document.getElementById('idUserEdit').value = userIdEdit;
    document.querySelector("[name='nameEdit']").value = nameEdit;
    document.querySelector("[name='lastNameEdit']").value = lastNameEdit;
    document.querySelector("[name='cpfEdit']").value = cpfEdit;
    document.querySelector("[name='emailEdit']").value = emailEdit;
    document.querySelector("[name='addressLocationEdit']").value = addressLocationEdit;
    document.querySelector("[name='numberHouseEdit']").value = numberHouseEdit;
    document.querySelector("[name='neighborhoodEdit']").value = neighborhoodEdit;
//    document.querySelector("[name='stateIdEdit']").value = stateIdEdit;
//    document.querySelector("[name='cityIdEdit']").value = cityIdEdit;
    document.querySelector("[name='zipCodeEdit']").value = zipCodeEdit;
    document.querySelector("[name='telephoneEdit']").value = telephoneEdit;
//    if (adminEdit)
//        document.querySelector("[name='adminEditTrue']").selected = true;
//    else
//        document.querySelector("[name='adminEditFalse']").selected = true;
//    if (activeUserEdit)
//        document.querySelector("[name='activeUserTrue']").selected = true;
//    else
//        document.querySelector("[name='activeUserFalse']").selected = true;

}

function setUserEstEdit(userIdEdit, companyNameEdit, businessNameEdit, cnpjEdit, emailEdit, secondEmailEdit, addressLocationEdit,
        numberHouseEdit, neighborhoodEdit, stateIdEdit, cityIdEdit, zipCodeEdit, telephoneEdit,
        planEdit, planStartEdit, planFinishEdit, activeUserEdit) {

    document.getElementById('idUserEstEdit').value = userIdEdit;
    document.querySelector("[name='companyNameEdit']").value = companyNameEdit;
    document.querySelector("[name='businessNameEdit']").value = businessNameEdit;
    document.querySelector("[name='cnpjEdit']").value = cnpjEdit;
    document.querySelector("[name='emailEstEdit']").value = emailEdit;
    document.querySelector("[name='secondEmailEdit']").value = secondEmailEdit;
    document.querySelector("[name='addressLocationEstEdit']").value = addressLocationEdit;
    document.querySelector("[name='numberHouseEstEdit']").value = numberHouseEdit;
    document.querySelector("[name='neighborhoodEstEdit']").value = neighborhoodEdit;
//    document.querySelector("[name='stateIdEstEdit']").value = stateIdEdit;
//    document.querySelector("[name='cityIdEstEdit']").value = cityIdEdit;
    document.querySelector("[name='zipCodeEstEdit']").value = zipCodeEdit;
    document.querySelector("[name='telephoneEstEdit']").value = telephoneEdit;
    document.querySelector("[name='planEdit']").value = planEdit;
    document.querySelector("[name='planStartEdit']").value = planStartEdit;
    document.querySelector("[name='planFinishEdit']").value = planFinishEdit;
//    if (activeUserEdit)
//        document.querySelector("[name='activeUserTrue']").selected = true;
//    else
//        document.querySelector("[name='activeUserFalse']").selected = true;

}

$(document).ready(function () {

    var $cnpj = $("#providerCnpj");
    $cnpj.mask('00.000.000/0000-00', {reverse: true});
    var $cnpjEdit = $("#cnpjProviderEdit");
    $cnpjEdit.mask('00.000.000/0000-00', {reverse: true});
    var $cnpjUser = $("#cnpjUser");
    $cnpjUser.mask('00.000.000/0000-00', {reverse: true});
    //Mascara para CNPJ no cadastro
    var $cnpjUserRegister = $("#cnpjUserRegister");
    $cnpjUserRegister.mask('00.000.000/0000-00', {reverse: true});
    //Mascara para CPF no cadastro
    var $cpfUserRegister = $("#cpfUserRegister");
    $cpfUserRegister.mask('000.000.000-00', {reverse: true});
    //Mascara para CELULAR no cadastro de consumidor
    var $cellUserConsumerRegister = $("#cellUserConsumerRegister");
    $cellUserConsumerRegister.mask('00 00000-0000', {reverse: true});
    //Mascara para CELULAR no cadastro de estabelicimento
    var $cellUserEstablishmentRegister = $("#cellUserEstablishmentRegister");
    $cellUserEstablishmentRegister.mask('00 00000-0000', {reverse: true});
    //Mascara para CEP
    var $zipCode = $("#zipCode");
    $zipCode.mask('00000-000', {reverse: true});
});
//Deleta a lista dos usuário
function setDeleteDadaModalShoppingList(data) {
    document.getElementById('idDeleteShoppingList').value = data;
//    alert(data);
}

//Deleta a lista dos usuário
function setUpdateDadaModalShoppingList(data) {
    document.getElementById('idUpdateNameShoppingList').value = data;
//    alert(data);
}

//Deleta o produto da lista do usuário
function setDeleteDadaModalProductInList(data) {
    document.getElementById('idDeleteProductList').value = data;
}

//Insere o o ID de produdo do modal de add no home
function setProductId(id) {
    document.getElementById('productId').value = id;
}

//Seta o objeto no modal de visualização do produto bo home
function setModalData(productName, img, productSectorNameModal, productBrandModal, productProviderNameModal) {
    document.getElementById('productNameModal').value = "Nome do produto: " + productName;
    document.getElementById('productImgValue').src = img;
    //document.getElementById('productSectorNameModal').value = productSectorNameModal;
    document.getElementById('productBrandModal').value = "Marca: " + productBrandModal;
    document.getElementById('productProviderNameModal').value = "Fornecedor: " + productProviderNameModal;
    //alert(productName);
}

//Busca o produto a ser pesquisado no sistema
function setDadaModalProductList() {
    var searchProduct = document.getElementById('searchProduct').value;
    var listId = document.getElementById('listId').value;
    //console.log(listId);
    document.getElementById("content_insert").innerHTML = "";
    $.ajax({
        url: 'api/system/products-' + searchProduct,
        type: 'GET',
        dataType: "json",
        success: function (response) {

            document.getElementById('searchProduct').value = "Achei algo";
            //console.log(response);
            var mydiv = document.getElementById("content_insert");
            var h3TagProduct = document.createElement('h5');
            h3TagProduct.innerHTML = "Produtos encontrados";
            mydiv.appendChild(h3TagProduct);
            response.forEach(function (locationItens) {
                //console.log(locationItens.name);
                //console.log(locationItens.id);
                //<input type="text" style="display: none"  name="value" id="idCreateProductList" value="">
                var tagDiv = document.createElement('div');
                tagDiv.className = " row col s12";

                var tagDiv1 = document.createElement('div');
                tagDiv1.className = "col s3";
                var imgTag = document.createElement('img');
                imgTag.className = "responsive-img";
                imgTag.width = "60";
                imgTag.height = "60";
                imgTag.src = "/ezmartWeb/resources/img/product/" + locationItens.id + ".jpg";
                tagDiv1.appendChild(imgTag);

                var tagDiv2 = document.createElement('div');
                tagDiv2.className = "col s5";
                var labelTag = document.createElement('label');
                labelTag.innerHTML = locationItens.name;
                labelTag.style = "color:black; margin-left: 15px";
                tagDiv2.appendChild(labelTag);

                var tagDiv3 = document.createElement('div');
                tagDiv3.className = "col s4";

                var buttonTag = document.createElement('button');
                buttonTag.type = "submit";
                buttonTag.innerHTML = "Adicionar";
                //aTag.setAttribute('href', "/ezmartWeb/products-" + listId);
                buttonTag.className = "btn btn-sm btn-default btn-small green modal-close";
                buttonTag.style = "color:white";
                var iTag = document.createElement('i');
                iTag.innerHTML = "add_shopping_cart";
                iTag.className = "material-icons right";
                buttonTag.appendChild(iTag);
                tagDiv3.appendChild(buttonTag);

                var inputTag = document.createElement('input');
                inputTag.type = "text";
                inputTag.style = "display: none";
                inputTag.name = "value";
                inputTag.id = "idCreateProductList";
                inputTag.value = locationItens.id;


                var formTag = document.createElement('form');
                formTag.method = "POST";

                tagDiv.appendChild(tagDiv1);
                tagDiv.appendChild(tagDiv2);
                tagDiv.appendChild(tagDiv3);

                formTag.appendChild(tagDiv);
                formTag.appendChild(inputTag);

                mydiv.appendChild(formTag);
            });
        },
        error: function () {
            document.getElementById('searchProduct').value = "Nenhum produto encontrado";
        }
    });
}

//Busca o produto a ser pesquisado no sistema
function setCitiesWithState() {
    var stateId = document.getElementById('stateId').value;
    console.log(stateId);
    $.ajax({
        url: 'api/system/getCities-' + stateId,
        type: 'GET',
        dataType: "json",
        success: function (response) {
            console.log(response);

            document.getElementById('cityId').innerHTML = "";
            $("#cityId").material_select();

            var selectTag = document.getElementById("cityId");
            var optionTagEmpty = document.createElement('option');
            optionTagEmpty.innerHTML = "Selecione sua cidade";
            selectTag.appendChild(optionTagEmpty);

            response.forEach(function (locationItens) {
                console.log(locationItens.name);

                var optionTag = document.createElement('option');
                optionTag.innerHTML = locationItens.name;
                optionTag.value = locationItens.id;

                selectTag.appendChild(optionTag);
            });

            $("#cityId").material_select();
        },
        error: function () {
            document.getElementById('cityId').innerHTML = "";

            var selectTag = document.getElementById("cityId");
            var optionTagEmpty = document.createElement('option');
            optionTagEmpty.innerHTML = "Selecione sua cidade";
            selectTag.appendChild(optionTagEmpty);

            $("#cityId").material_select();
        }
    });
}

$(document).ready(function () {
    
    var satisfaction = document.getElementById('satisfaction').value;
    var priceProduct = document.getElementById('priceProduct').value;
    var prodDiversity = document.getElementById('prodDiversity').value;
    var employees = document.getElementById('employees').value;
    var ambience = document.getElementById('ambience').value;
    
    var ctx = document.getElementById("bar-chart").getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'horizontalBar',
        data: {
            labels: ["Grau de satisfação", "Preços dos produtos", "Diversidade de produtos", "Funcionários", "Ambiente"],
            datasets: [{
                    label: 'Avaliação',
                    data: [satisfaction, priceProduct, prodDiversity, employees, ambience],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.8)',
                        'rgba(54, 162, 235, 0.8)',
                        'rgba(255, 206, 86, 0.8)',
                        'rgba(75, 192, 192, 0.8)',
                        'rgba(153, 102, 255, 0.8)'
                    ],
                    borderColor: [
                        'rgba(255,99,132,1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)'
                    ],
                    borderWidth: 2
                }]
        },
        options: {
            scales: {
                yAxes: [{
                        ticks: {
                            beginAtZero: true
                        }
                    }]
            },
            title: {
                display: true,
                fontSize: 20,
                text: "MÉDIA DE AVALIAÇÃO (máx. 5)"
            }
        }
    });
});