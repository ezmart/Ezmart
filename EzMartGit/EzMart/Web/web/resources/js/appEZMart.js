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
    document.getElementById('productNameModal').value = productName;
    document.getElementById('productImgValue').src = img;
    document.getElementById('productSectorNameModal').value = productSectorNameModal;
    document.getElementById('productBrandModal').value = productBrandModal;
    document.getElementById('productProviderNameModal').value = productProviderNameModal;
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

                var inputTag = document.createElement('input');
                inputTag.type = "text";
                inputTag.style = "display: none";
                inputTag.name = "value";
                inputTag.id = "idCreateProductList";
                inputTag.value = locationItens.id;
                
                var tdTag1 = document.createElement('td');
                
                var tdTag2 = document.createElement('td');
                
                var tdTag3 = document.createElement('td');
                tdTag3.style = "align-items: flex-end";
                tdTag3.class = "right-align";
                var trTag = document.createElement('tr');
                trTag.appendChild(tdTag1);
                trTag.appendChild(tdTag2);
                trTag.appendChild(tdTag3);
                trTag.appendChild(inputTag);
                
                var formTag = document.createElement('form');
                formTag.method = "POST";
                formTag.appendChild(trTag);
                //formTag.appendChild(trTag);

                mydiv.appendChild(formTag);
                
                var labelTag = document.createElement('label');
                labelTag.innerHTML = locationItens.name;
                labelTag.style = "color:black; margin-left: 15px";
                tdTag2.appendChild(labelTag);
                
                var imgTag = document.createElement('img');
                imgTag.className = "responsive-img";
                imgTag.width = "60";
                imgTag.height = "60";
                imgTag.src = "/ezmartWeb/resources/img/product/" + locationItens.id + ".jpg";
                tdTag1.appendChild(imgTag);
                
                var buttonTag = document.createElement('button');
                buttonTag.type = "submit";
                buttonTag.innerHTML = "Adicionar";
                //aTag.setAttribute('href', "/ezmartWeb/products-" + listId);
                buttonTag.className = "btn btn-sm btn-default btn-small green modal-close";
                buttonTag.style = "color:white";
                tdTag3.appendChild(buttonTag);
                
                var iTag = document.createElement('i');
                iTag.innerHTML = "add_shopping_cart";
                iTag.className = "material-icons right";
                buttonTag.appendChild(iTag);
            });
        },
        error: function () {
            document.getElementById('searchProduct').value = "Nenhum produto encontrado";
        }
    });
}
//function setDadaModalProductList() {
//    var searchProduct = document.getElementById('searchProduct').value;
//    var listId = document.getElementById('listId').value;
//    //console.log(listId);
//    document.getElementById("content_insert").innerHTML = "";
//    $.ajax({
//        url: 'api/system/products-' + searchProduct,
//        type: 'GET',
//        dataType: "json",
//        success: function (response) {
//
//            document.getElementById('searchProduct').value = "Achei algo";
//            //console.log(response);
//            var mydiv = document.getElementById("content_insert");
//            var h3TagProduct = document.createElement('h5');
//            h3TagProduct.innerHTML = "Produtos encontrados";
//            mydiv.appendChild(h3TagProduct);
//            response.forEach(function (locationItens) {
//                //console.log(locationItens.name);
//                //console.log(locationItens.id);
//                //<input type="text" style="display: none"  name="value" id="idCreateProductList" value="">
//
//                var inputTag = document.createElement('input');
//                inputTag.type = "text";
//                inputTag.style = "display: none";
//                inputTag.name = "value";
//                inputTag.id = "idCreateProductList";
//                inputTag.value = locationItens.id;
//                var tdTag1 = document.createElement('td');
//                var tdTag2 = document.createElement('td');
//                var tdTag3 = document.createElement('td');
//                tdTag3.style = "align-items: flex-end";
//                tdTag3.class = "right-align";
//                var trTag = document.createElement('tr');
//                trTag.appendChild(tdTag1);
//                trTag.appendChild(tdTag2);
//                trTag.appendChild(tdTag3);
//                trTag.appendChild(inputTag);
//                var formTag = document.createElement('form');
//                formTag.method = "POST";
//                formTag.appendChild(trTag);
//                //formTag.appendChild(trTag);
//
//                mydiv.appendChild(formTag);
//                var labelTag = document.createElement('label');
//                labelTag.innerHTML = locationItens.name;
//                labelTag.style = "color:black; margin-left: 15px";
//                tdTag2.appendChild(labelTag);
//                var imgTag = document.createElement('img');
//                imgTag.className = "responsive-img";
//                imgTag.width = "60";
//                imgTag.height = "60";
//                imgTag.src = "/ezmartWeb/resources/img/product/" + locationItens.id + ".jpg";
//                tdTag1.appendChild(imgTag);
//                var buttonTag = document.createElement('button');
//                buttonTag.type = "submit";
//                buttonTag.innerHTML = "Adicionar";
//                //aTag.setAttribute('href', "/ezmartWeb/products-" + listId);
//                buttonTag.className = "btn btn-sm btn-default btn-small green modal-close";
//                buttonTag.style = "color:white";
//                tdTag3.appendChild(buttonTag);
//                var iTag = document.createElement('i');
//                iTag.innerHTML = "add_shopping_cart";
//                iTag.className = "material-icons right";
//                buttonTag.appendChild(iTag);
//            });
//        },
//        error: function () {
//            document.getElementById('searchProduct').value = "Nenhum produto encontrado";
//        }
//    });
//}
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