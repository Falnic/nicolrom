<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="addHoleCSS" value="/resources/style/addHole.css"/>
<link rel="stylesheet" type="text/css" href="${addHoleCSS}">

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="../bootstrapImports.jsp"/>
    <jsp:include page="../backofficeHeader.jsp"/>
    <title>Update Hole</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-lg-12 text-center">
            <h1>Modifica Groapa</h1>
        </div>
    </div>
    <form method="post" autocomplete="off" name="updateHoleForm">
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-4">
                <table>
                    <tr>
                        <td><label class="control-label" for="holeDatePicker">Data</label></td>
                        <td><input type="date" class="form-control" name="holeDate" id="holeDatePicker"
                                   placeholder="dd/mm/yyyy" autocomplete="false" max="${currentDate}"
                                   value="${hole.date}"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="street">Strada</label></td>
                        <td><input type="text" class="form-control" name="street" id="street" autocomplete="false"
                            value="${hole.street}"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="streetNr">Numar</label></td>
                        <td><input type="text" class="form-control" name="streetNr" id="streetNr" autocomplete="false"
                            value="${hole.streetNr}"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="locality">Localitate</label></td>
                        <td><input type="text" class="form-control" name="locality" id="locality" autocomplete="false"
                            value="${hole.locality}"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="district">Judet</label></td>
                        <td><input type="text" class="form-control" name="district" id="district" autocomplete="false"
                            value="${hole.district}"/>
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
                                    <c:if test="${area.areaId == hole.area.areaId}">
                                        <option value="${area.areaId}" selected>${area.type}</option>
                                    </c:if>
                                    <c:if test="${area.areaId != hole.area.areaId}">
                                        <option value="${area.areaId}">${area.type}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="holeLenght">Lungime</label></td>
                        <td><input type="number" step="0.01" min="0" class="form-control" name="holeLenght"
                                   id="holeLenght" autocomplete="false" placeholder="m" value="${hole.holeLength}"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="holeWidth">Latime</label></td>
                        <td><input type="number" step="0.01" min="0" class="form-control" name="holeWidth"
                                   id="holeWidth" autocomplete="false" placeholder="m" value="${hole.holeWidth}"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="holeDepth">Adancime</label></td>
                        <td><input type="number" step="0.01" min="0" class="form-control" name="holeDepth"
                                   id="holeDepth" autocomplete="false" placeholder="m" value="${hole.holeDepth}"/>
                    </tr>
                </table>
            </div>
            <div class="col-lg-2"></div>
        </div>
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-8">
                <b>Sapatura executata de:</b>
                <div class="custom-control custom-radio custom-control-inline">
                    <c:if test="${hole.executor == 'Nicol Rom'}">
                        <input class="custom-control-input" type="radio" name="executor" id="executorNicolRom"
                               value="Nicol Rom" onclick="removeAutoFields()" checked>
                        <label class="custom-control-label" for="executorNicolRom">Nicol Rom</label>
                    </c:if>
                    <c:if test="${hole.executor != 'Nicol Rom'}">
                        <input class="custom-control-input" type="radio" name="executor" id="executorNicolRom"
                               value="Nicol Rom" onclick="removeAutoFields()">
                        <label class="custom-control-label" for="executorNicolRom">Nicol Rom</label>
                    </c:if>
                </div>
                <div class="custom-control custom-radio custom-control-inline">
                    <c:if test="${hole.executor == 'Delgaz Grid'}">
                        <input class="custom-control-input" type="radio" name="executor" id="executorDelGaz"
                               value="Delgaz Grid" onclick="addAutoFields()" checked>
                        <label class="custom-control-label" for="executorDelGaz">Delgaz Grid</label>
                    </c:if>
                    <c:if test="${hole.executor != 'Delgaz Grid'}">
                        <input class="custom-control-input" type="radio" name="executor" id="executorDelGaz"
                               value="Delgaz Grid" onclick="addAutoFields()">
                        <label class="custom-control-label" for="executorDelGaz">Delgaz Grid</label>
                    </c:if>
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
                                            <option value="${employee.idEmployee}" >${employee.name}</option>
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
        <div class="row" id="newRouteFields" style="display: none">
            <div class="col-lg-2"></div>
            <div class="form-group col-lg-4">
                <label class="control-label" for="autoRouteDistance">Distanta parcursa de bascula</label>
                <input type="number" class="form-control" name="autoRouteDistance" id="autoRouteDistance"
                       autocomplete="false" value="${hole.autoRouteDistance}"/>
                <small id="emailHelp" class="form-text text-muted">Distanta parcursa de bascula de la locul sapaturii pana la groapa de gunoi</small>
            </div>
            <div class="form-group col-lg-4">
                <label class="control-label" for="autoStationaryTime">Ore de stationare bascula</label>
                <input type="number" class="form-control" name="autoStationaryTime" id="autoStationaryTime"
                       autocomplete="false" value="${hole.autoStationaryTime}"/>
            </div>
            <div class="col-lg-2"></div>
        </div>
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-2">
                <input type="submit" class="btn btn-warning btn-lg " value="Modifica"/>
            </div>
            <div class="col-lg-8"></div>
        </div>
        <input type="hidden" value="${hole.holeId}">
    </form>
</div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>

<script>
    $(function() {

        if ("Nicol Rom" > "${hole.executor}"){
            $("#selectEmployees-SOFER").selectpicker('val', ${selectedEmployees_SOFER});
            $("#newRouteFields").show();
            $("#mecanicDiv").hide();
            $("#necalificatDiv").hide();
        } else {
            $("#selectEmployees-SOFER").selectpicker('val', ${selectedEmployees_SOFER});
            $("#selectEmployees-MECANIC").selectpicker('val', ${selectedEmployees_MECANIC});
            $("#selectEmployees-NECALIFICAT").selectpicker('val', ${selectedEmployees_NECALIFICAT});
        }

        $("form[name='updateHoleForm']").validate({
            rules: {
                holeDate:"required",
                street: "required",
                streetNr: "required",
                locality: "required",
                district: "required",
                holeLenght: "required",
                holeWidth: "required",
                holeDepth: "required",
                autoRouteDistance: {
                    required: "#executorDelGaz:checked",
                },
                autoStationaryTime: {
                    required: "#executorDelGaz:checked",
                },
                employees_SOFER : "required",
                employees_MECANIC : {
                    required : "#executorNicolRom:checked"
                },
                employees_NECALIFICAT : {
                    required : "#executorNicolRom:checked"
                }
            },
            messages: {
                holeDate: "Selectati data",
                street: "Introduceti strada",
                streetNr: "Introduceti numarul strazii",
                locality: "Introduceti localitatea",
                district: "Introduceti judetul",
                holeLenght: "Introduceti lungimea",
                holeWidth: "Introduceti latimea",
                holeDepth: "Introduceti adancimea",
                autoRouteDistance: "Introduceti distanta",
                autoStationaryTime: "Introduceti timpul de stationare",
                employees_SOFER : "Alegeti Soferi",
                employees_MECANIC : "Alegeti Mecanic",
                employees_NECALIFICAT : "Alegeti Necalificat"
            },
            submitHandler: function(form) {
                form.submit();
            }
        });
    } );

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
    }
</script>
</body>
</html>