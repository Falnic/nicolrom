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