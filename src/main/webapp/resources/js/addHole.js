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



//Functions used in addHole
$('#idAddEmployeesToTeam').click(function () {
    console.log("In js!");
    var arr = [];

    $('input[id="idEmployee"]:checked').each(function(){
        var toInt = parseInt(this.value);
        arr.push(toInt);

        $.ajax({
            type : "POST",
            url:"../holes/addEmployeeToTeam",
            data : {
                employeeArr: arr
            },
            success : function(response) {
                // $("#prodTable").load( "../product #prodTable" );
                console.log("success");
            },
            error : function(e) {
                alert('Error: ' + e);
            }
        });
    });
});