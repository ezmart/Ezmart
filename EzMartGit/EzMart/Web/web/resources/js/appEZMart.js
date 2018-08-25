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

function setDadosModal(sectorId, sectorName) {
    document.getElementById('sector-id').innerHTML = sectorId + " - " + sectorName;
}


        