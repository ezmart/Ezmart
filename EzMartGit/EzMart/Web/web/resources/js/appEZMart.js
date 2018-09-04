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

function setProductEdit(productId, productName, productBarCode, productBrand) {
    document.getElementById('idProduct').value = productId;
    document.querySelector("[name='productNameEdit']").value = productName;
    document.querySelector("[name='productBarCodeEdit']").value = productBarCode;
    document.querySelector("[name='productBrandEdit']").value = productBrand;
}

$(document).ready(function () {
    var $cnpj = $("#providerCnpj");
    $cnpj.mask('00.000.000/0000-00', {reverse: true});

    var $cnpjEdit = $("#cnpjProviderEdit");
    $cnpjEdit.mask('00.000.000/0000-00', {reverse: true});
});

function setDadaModalList(data) {
    document.getElementById('idDelete').value = data;
//    alert(data);
}

function setDadaModalProductList(data) {
    document.getElementById('idCreate').value = data;
//    alert(data);
}