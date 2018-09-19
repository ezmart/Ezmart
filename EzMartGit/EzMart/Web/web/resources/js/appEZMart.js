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

    //Mascara para CEP no cadastro de consumidor
    var $zipCodeUserConsumerRegister = $("#zipCodeUserConsumerRegister");
    $zipCodeUserConsumerRegister.mask('00000-000', {reverse: true});

    //Mascara para CEP no cadastro de estabelicimento
    var $zipCodeUserEstablishmentRegister = $("#zipCodeUserEstablishmentRegister");
    $zipCodeUserEstablishmentRegister.mask('00000-000', {reverse: true});
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

//function setDadaProductList(data) {
//    document.getElementById('idDeleteProductList').value = data;
////    alert(data);
//}