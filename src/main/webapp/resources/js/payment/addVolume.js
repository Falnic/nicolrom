$("#district").change(function () {
    var district = $("#district option:selected").val();
    var startDate = $("#startDateSearch").val();
    var endDate = $("#endDateSearch").val();

    if ((startDate == "") && (endDate == "") && (district != "")){
        var url = "http://localhost:8081/NicolRom/backoffice/volumes/add-getHolesByDistrict";
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
    else if ((startDate != "") && (endDate != "") && (district == "")) {
        getHolesByDatePeriod(startDate, endDate);
    } else {
        getHolesByDatePeriodAndDistrict(startDate, endDate, district);
    }
});

$("#startDateSearch").change(function (){
    var district = $("#district option:selected").val();
    var startDate = $("#startDateSearch").val();
    var endDate = $("#endDateSearch").val();

    if ((startDate !== "") && (endDate !== "") && (district === "")){
        getHolesByDatePeriod(startDate, endDate);
    }
    else {
        getHolesByDatePeriodAndDistrict(startDate, endDate, district);
    }
});

$("#endDateSearch").change(function (){
    var district = $("#district option:selected").val();
    var startDate = $("#startDateSearch").val();
    var endDate = $("#endDateSearch").val();

    if ((startDate != "") && (endDate != "") && (district == "")){
        getHolesByDatePeriod(startDate, endDate);
    }
    else
    {
        getHolesByDatePeriodAndDistrict(startDate, endDate, district);
    }
});

$("#select-all").click(function () {
    var checked = this.checked;
    $('input[type="checkbox"]').each(function() {
        this.checked = checked;
    });
})

$("form[name='addVolumeForm']").validate({
    rules: {
        volumeNr:"required",
        contractNr : "required"
    },
    messages: {
        volumeNr: "Introduceti numarul de volum",
        material: "Introduceti numarul contractului"
    },
    submitHandler: function(form) {
        form.submit();
    }
});

function getHolesByDatePeriod(startDate, endDate){
    var url = "http://localhost:8081/NicolRom/backoffice/volumes/add-getHolesByDatePeriod";
    $.ajax({
        url : url,
        type : 'GET',
        data : {
            'startDate' : startDate,
            'endDate' : endDate
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

function getHolesByDatePeriodAndDistrict(startDate, endDate, district) {
    if ((startDate !== "") && (endDate !== "") && (district !== "")){
        var url = "http://localhost:8081/NicolRom/backoffice/volumes/add-getHolesByDatePeriodAndDistrict";
        $.ajax({
            url : url,
            type : 'GET',
            data : {
                'startDate' : startDate,
                'endDate' : endDate,
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
}

function populate(holes){
    $("#holesTB tr").remove()
    for (i in holes){
        var date = new Date(holes[i].date);
        var day = date.getDate();
        var month = date.getMonth() + 1;
        var year = date.getFullYear();
        var hole = "                                <tr>\n" +
            "                                    <td>\n" +
            "                                        <input type=\"checkbox\" value='" + holes[i].id + "' id=\"select-Hole-\"" + holes[i].id + " name=\"holes\">\n" +
            "                                    </td>\n" +
            "                                    <td>" + day + "-" + month + "-" + year + "</td>\n" +
            "                                    <td>" + holes[i].address + "</td>\n" +
            "                                    <td>" + holes[i].district + "</td>\n" +
            "                                </tr>"
        $("#holesTB").append(hole);
    }
}