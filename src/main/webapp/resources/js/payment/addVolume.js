$("#district").change(function () {
    var district = $("#district option:selected").val();
    var startDate = $("#startDateSearch").val();
    var endDate = $("#endDateSearch").val();

    if ((startDate == "") || (endDate == "") || (district != "")){
        var url = "http://localhost:8081/NicolRom/backoffice/volumes/add-getHolesByDistrict"
        $.ajax({
            url : url,
            type : 'GET',
            data : {
                'district' : district
            },
            success : function (data) {
                populate(JSON.parse(data));
            },
            error : function(request,error)
            {
                alert("Request: "+JSON.stringify(request));
            }
        });
    }
});

function populate(holes){
    $("#holesTB tr").remove()
    for (i in holes){
        var date = new Date(holes[i].date);
        var day = date.getDate();
        var month = date.getMonth();
        var year = date.getFullYear();
        var hole = "                                <tr>\n" +
            "                                    <td>\n" +
            "                                        <input type=\"checkbox\" id=\"select-Hole-\"" + holes[i].id + " name=\"holes\">\n" +
            "                                    </td>\n" +
            "                                    <td>" + day + "-" + month + "-" + year + "</td>\n" +
            "                                    <td>" + holes[i].address + "</td>\n" +
            "                                    <td>" + holes[i].district + "</td>\n" +
            "                                </tr>"
        $("#holesTB").append(hole);
    }
    console.log("populate");
}