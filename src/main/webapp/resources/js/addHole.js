var SendInfo = [];

$(this).parents('table').find('input:checked').each(function () {
    var domain = {
        id: $("idEmployee").val(),
        name: $("#id-manuf-name").val(),
        address: $("#id-manuf-address").val(),
        phone: $("#id-manuf-phone").val(),
    }

    SendInfo.push(domain);
});

