<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<jsp:include page="../bootstrapImports.jsp"/>
<jsp:include page="../backofficeHeader.jsp"/>

<c:url var="addHoleCSS" value="/resources/style/addHole.css"/>
<link rel="stylesheet" type="text/css" href="${addHoleCSS}">

<div class="container">
    <div class="row">
        <div class="col-lg-12 text-center">
            <h1>Adauga Sapatura </h1>
        </div>
    </div>
    <c:if test="${not empty error}">
        <div class="col-lg-12 text-center error">
            <h5>Eroare, exista in sistem o sapatura cu aceeasi data</h5>
        </div>
    </c:if>
    <form method="post" autocomplete="off" name="addHoleForm">
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-4">
                <table>
                    <tr>
                        <td><label class="control-label" for="holeDatePicker">Data</label></td>
                        <td><input type="date" class="form-control" name="holeDate" id="holeDatePicker"
                                   autocomplete="false" max="${currentDate}"/>
                    </tr>
                    <tr id="countyTr">
                        <td><label class="control-label" for="county">Judet</label></td>
                        <td><input type="text" class="form-control" name="county" id="county" readonly value="CLUJ"/>
                    </tr>
                    <tr id="localityTr">
                        <td><label for="localitySelectId">Localitate</label></td>
                        <td>
                            <select name="locality" id="localitySelectId" class="selectpicker"
                                    data-live-search="true" title="Alege Localitatea">
                                <c:forEach var="locality" items="${localities}">
                                    <option value="${locality}">${locality}</option>
                                </c:forEach>
                            </select>
                        </td>
<%--                        <td><label class="control-label" for="locality">Localitate</label></td>--%>
<%--                        <td><input type="text" class="form-control" name="locality" id="locality" autocomplete="false"/>--%>
                    </tr>
                    <tr id="streetTr">
                        <td><label for="streetSelectId">Strada</label></td>
                        <td>
                            <select name="street" id="streetSelectId" class="selectpicker" title="Alege Strada"
                                    data-live-search="true" disabled>
                            </select>
                        </td>
<%--                        <td><label class="control-label" for="street">Strada</label></td>--%>
<%--                        <td><input type="text" class="form-control" name="street" id="street" autocomplete="false"/>--%>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="streetNr">Numar</label></td>
                        <td><input type="text" class="form-control" name="streetNr" id="streetNr" autocomplete="false"/>
                    </tr>
                    <tr id="districtTr">
                        <td><label class="control-label" for="district">District</label></td>
                        <td><input type="text" class="form-control" name="district" id="district" readonly/>
                    </tr>
                </table>
            </div>
            <div class="col-lg-4">
                <table>
                    <tr>
                        <td><label class="control-label" for="selectArea">Zona</label></td>
                        <td>
                            <select name="area" id="selectArea"
                                    class="browser-default custom-select">
                                <c:forEach var="area" items="${areas}">
                                    <c:choose>
                                        <c:when test="${'Asfalt'.equals(area.type)}">
                                            <option value="${area.areaId}" selected>${area.type}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${area.areaId}">${area.type}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="holeLenght">Lungime</label></td>
                        <td><input type="number" step="0.01" min="0" class="form-control" name="holeLenght"
                                   id="holeLenght" autocomplete="false" placeholder="m" oninput="calculateAutoStationaryTime()"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="holeWidth">Latime</label></td>
                        <td><input type="number" step="0.01" min="0" class="form-control" name="holeWidth"
                                   id="holeWidth" autocomplete="false" placeholder="m" oninput="calculateAutoStationaryTime()"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="holeDepth">Adancime</label></td>
                        <td><input type="number" step="0.01" min="0" class="form-control" name="holeDepth"
                                   id="holeDepth" autocomplete="false" placeholder="m" oninput="calculateAutoStationaryTime()"/>
                    </tr>
                </table>
            </div>
            <div class="col-lg-2"></div>
        </div>
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="form-check col-lg-3">
                <label class="form-check-label" for="noStreetCheck">
                    <small class="form-text text-muted">
                        Click aici pentru adaugarea unei strazi care nu exista in sistem
                    </small>
                </label>
            </div>
            <div class="col-lg-1">
                <input type="checkbox" class="form-check-input" id="noStreetCheck">
            </div>
        </div>
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-8">
                <b>Sapatura executata de:</b>
                <div class="custom-control custom-radio custom-control-inline">
                    <input class="custom-control-input" type="radio" name="executor" id="executorNicolRom"
                           value="Nicol Rom" checked onclick="removeAutoFields()">
                    <label class="custom-control-label" for="executorNicolRom">Nicol Rom</label>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <input class="custom-control-input" type="radio" name="executor" id="executorDelGaz"
                           value="Delgaz Grid" onclick="addAutoFields()">
                    <label class="custom-control-label" for="executorDelGaz">Delgaz Grid</label>
                </div>
            </div>
            <div class="col-lg-2"></div>
        </div>
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-8">
                <div class="row">
                    <div class="col-lg-4" id="soferDiv">
                        <h4><label class="control-label" for="selectEmployees-SOFER">SOFER</label></h4>
                        <table class="table" id="SOFER-table">
                            <tr>
                                <td>
                                    <select name="employees_SOFER" id="selectEmployees-SOFER" class="selectpicker" multiple
                                            data-live-search="true" data-actions-box="true" data-selected-text-format="count">
                                        <c:forEach var="employee" items="${positionEmployeesMap_SOFER}">
                                            <option value="${employee.idEmployee}">${employee.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="col-lg-4" id="mecanicDiv">
                        <h4><label class="control-label" for="selectEmployees-MECANIC">MECANIC</label></h4>
                        <table class="table" id="MECANIC-table">
                            <tr>
                                <td>
                                    <select name="employees_MECANIC" id="selectEmployees-MECANIC" class="selectpicker" multiple
                                            data-live-search="true" data-actions-box="true" data-selected-text-format="count">
                                        <c:forEach var="employee" items="${positionEmployeesMap_MECANIC}">
                                            <option value="${employee.idEmployee}">${employee.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="col-lg-4" id="necalificatDiv">
                        <h4><label class="control-label" for="selectEmployees-NECALIFICAT">NECALIFICAT</label></h4>
                        <table class="table" id="NECALIFICAT-table">
                            <tr>
                                <td>
                                    <select name="employees_NECALIFICAT" id="selectEmployees-NECALIFICAT" class="selectpicker" multiple
                                            data-live-search="true" data-actions-box="true" data-selected-text-format="count">
                                        <c:forEach var="employee" items="${positionEmployeesMap_NECALIFICAT}">
                                            <option value="${employee.idEmployee}">${employee.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-lg-2"></div>
        </div>
        <div id="chooseMachine_Div">
        </div>
        <div class="row" id="newRouteFields" style="display: none">
            <div class="col-lg-2"></div>
                <div class="form-group col-lg-4">
                    <label class="control-label" for="autoRouteDistance">Distanta parcursa de bascula</label>
                    <input type="number" step="0.01" min="0" class="form-control" name="autoRouteDistance" id="autoRouteDistance" autocomplete="false"/>
                    <small id="emailHelp" class="form-text text-muted">Distanta parcursa de bascula de la locul sapaturii pana la groapa de gunoi</small>
                </div>
                <div class="form-group col-lg-4">
                    <label class="control-label" for="autoStationaryTime">Ore de stationare bascula</label>
                    <input type="number" step="1" min="0" class="form-control" name="autoStationaryTime" id="autoStationaryTime" autocomplete="false"/>
                </div>
            <div class="col-lg-2"></div>
        </div>
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-2">
                <input type="submit" class="btn btn-primary btn-lg " value="Salveaza"/>
            </div>
            <div class="col-lg-4"></div>
            <div class="col-lg-2">
                <a class="btn btn-lg btn-danger" id="cancelBtn" href="<c:url value="/backoffice/holes"/>" role="button">Anuleaza</a>
            </div>
            <div class="col-lg-2"></div>
        </div>
    </form>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>

<script>
    $(function() {
        $("#localitySelectId").change(function () {
            var locality = $("#localitySelectId option:selected").val();
            var url = "http://localhost:8081/NicolRom/backoffice/holes/add-getStreets";
            $.ajax({
                url : url,
                type : 'GET',
                data : {
                    'locality' : locality
                },
                success : function(data) {
                    prepareStreetSelect(data);
                },
                error : function(request,error)
                {
                    alert("Request: "+JSON.stringify(request));
                }
            });
        });

        $("#streetSelectId").change(function () {
            var street = $("#streetSelectId option:selected").val();
            var url = "http://localhost:8081/NicolRom/backoffice/holes/add-getDistrict";
            $.ajax({
                url : url,
                type : 'GET',
                data : {
                    'street' : street
                },
                success : function(data) {
                    showDistrict(data);
                },
                error : function(request,error)
                {
                    alert("Request: "+JSON.stringify(request));
                }
            });
        });

        // $("#noStreetCheck").click(function () {
        //
        // })

        $('#cancelBtn').click(function() {
            return window.confirm("Sunteti sigur?");
        });
        var employeesJSON = ${employeesJSON_SOFER};
        $("#selectEmployees-SOFER").change(function () {
            appendChooseMachineHeader();
            var optionsSelected = $("#selectEmployees-SOFER option:selected");
            var chooseMachineDivs = $("#chooseMachine_Div > div");
            if (chooseMachineDivs.length < (optionsSelected.length + 1)){
                appendSelectEmployeeMachine(optionsSelected, employeesJSON);
            } else {
                removeUnselectedOption(optionsSelected, chooseMachineDivs);
            }
        });

        $("form[name='addHoleForm']").validate({
            rules: {
                holeDate:"required",
                street: "required",
                streetNr: "required",
                locality: "required",
                county: "required",
                district: "required",
                holeLenght: "required",
                holeWidth: "required",
                holeDepth: "required",
                autoRouteDistance: {
                    required: "#executorDelGaz:checked",
                },
                autoStationaryTime: {
                    required: "#executorDelGaz:checked",
                    digits: true,
                },
            },
            messages: {
                holeDate: "Selectati data",
                street: "Introduceti strada",
                streetNr: "Introduceti numarul strazii",
                locality: "Introduceti localitatea",
                county: "Introduceti judetul",
                district: "Introduceti districtul",
                holeLenght: "Introduceti lungimea",
                holeWidth: "Introduceti latimea",
                holeDepth: "Introduceti adancimea",
                autoRouteDistance: "Introduceti distanta",
                autoStationaryTime: {
                    required : "Introduceti timpul de stationare",
                    digits: "Introduceti valoare fara zecimale",
                }
            },
            submitHandler: function(form) {
                form.submit();
            }
        });
    } );

    function prepareStreetSelect(localitiesList) {
        $("#streetSelectId option").remove();
        var arrayLength = localitiesList.length;
        $("#streetSelectId").prop("disabled", false);
        for (var i = 0; i < arrayLength; i++) {
            $("#streetSelectId").append(new Option(localitiesList[i], localitiesList[i]));
        }
        $("#streetSelectId").selectpicker("refresh");
    }

    function showDistrict(district) {
        $("#district").val(district);
        $("#district").attr("readonly", true);
    }

    function removeAutoFields() {
        $("#newRouteFields").hide("slow");
        $("#autoRouteDistance").val("");
        $("#autoStationaryTime").val("");
        $("#mecanicDiv").show("slow");
        $("#necalificatDiv").show("slow");
    }

    function addAutoFields() {
        $("#newRouteFields").show("slow");
        $("#mecanicDiv").hide("slow");
        $("#necalificatDiv").hide("slow");
        $('#selectEmployees-MECANIC').selectpicker("deselectAll", true).selectpicker("refresh");
        $("#selectEmployees-NECALIFICAT").selectpicker("deselectAll", true).selectpicker("refresh");
        calculateAutoStationaryTime();
    }

    function appendChooseMachineHeader() {
        if ($("#chooseMachineHeaderDiv").length === 0){
            $("#chooseMachine_Div").append(
                '<div class="row" id="chooseMachineHeaderDiv">' +
                '<div class="col-lg-2"></div>' +
                '<div class="col-lg-8">' +
                '<h5>Alege masina:</h5>' +
                '</div>' +
                '<div class="col-lg-2"></div>' +
                '</div>');
        }
    }

    function appendSelectEmployeeMachine(optionsSelected, employeesJSON){
        optionsSelected.each(function () {
            var employeeId = $(this).val();
            var employeeSelectMachine = $("#machineSelect-employee-" + employeeId);
            if (employeeSelectMachine.length == 0){
                $("#chooseMachine_Div").append(
                    '<div class="row" id="chooseMachine-employee-' + employeeId + '">' +
                    '   <div class="col-lg-2"></div>' +
                    '   <div class="col-lg-2">' +
                    '       <label class="control-label" for="machineSelect-employee-' + employeeId + '">' + $(this).text() + '</label>' +
                    '   </div>' +
                    '   <div class="col-lg-2">' +
                    '       <select name="machineSelect_SOFER" id="machineSelect-employee-' + employeeId + '" class="selectpicker">' +
                    '       </select>' +
                    '   </div>' +
                    '   <div class="col-lg-2"></div>' +
                    '</div>');
                for (i in employeesJSON){
                    if (employeesJSON[i].idEmployee == employeeId){
                        var employeeMachines = employeesJSON[i].machines;
                        for (j in employeeMachines){
                            var text = employeeMachines[j].licensePlate + " " + employeeMachines[j].capacity + " mc";
                            $("#machineSelect-employee-" + employeeId).append(new Option(text, employeeMachines[j].machineryId));
                        }
                        $("#machineSelect-employee-" + employeeId).selectpicker("refresh");
                    }
                }
            }
        })
    }

    function removeUnselectedOption(optionsSelected, chooseMachineDivs){
        chooseMachineDivs.each(function () {
            var div_id = $(this).attr("id");
            var flag = false;
            if (optionsSelected.length > 0){
                optionsSelected.each(function () {
                    if (div_id.search($(this).val()) > 0){
                        flag = true;
                    }
                })
            } else {
                $(this).remove();
            }
            if ((flag === false) && ($(this).attr("id") !== "chooseMachineHeaderDiv")){
                $(this).remove();
            }
        })
    }

    function calculateAutoStationaryTime() {
        if ($("#executorDelGaz:checked").length != 0){
            var holeLength = $("#holeLenght").val();
            var holeWidth = $("#holeWidth").val();
            var holeDepth = $("#holeDepth").val();
            var volume = holeLength * holeWidth * holeDepth;
            var autoStationaryTime = 0;

            switch (true) {
                case (volume < 5.6):
                    autoStationaryTime = 1;
                    break;
                case (volume < 10.0) :
                    autoStationaryTime = 2;
                    break;
                case (volume < 16.8):
                    autoStationaryTime = 3;
                    break;
                case (volume < 22.4):
                    autoStationaryTime = 4;
                    break;
                case (volume < 28.4):
                    autoStationaryTime = 5;
                    break;
                case (volume < 33.6):
                    autoStationaryTime = 6;
                    break;
                case (volume < 39.2):
                    autoStationaryTime = 7;
                    break;
                case (volume < 44.8):
                    autoStationaryTime = 8;
                    break;
                case (volume < 50.4):
                    autoStationaryTime = 9;
                    break;
                case (volume < 56.0):
                    autoStationaryTime = 10;
                    break;
                default:
                    autoStationaryTime = Math.round(volume / 5.5);
                    break;
            }
            $("#autoStationaryTime").val(autoStationaryTime);
        }
    }
</script>