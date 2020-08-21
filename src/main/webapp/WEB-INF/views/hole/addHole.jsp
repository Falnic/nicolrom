<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <div class="col-lg-12 text-center">
            Eroare, sapatura a fost deja adaugata
        </div>
    </c:if>
    <form method="post" autocomplete="off" name="addHoleForm">
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-4">
                <table>
                    <tr>
                        <td><label class="control-label" for="holeDatePicker">Data</label></td>
                        <td><input type="date" class="form-control" name="holeDate" id="holeDatePicker" placeholder="dd/mm/yyyy" autocomplete="false" max="${currentDate}"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="street">Strada</label></td>
                        <td><input type="text" class="form-control" name="street" id="street" autocomplete="false"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="streetNr">Numar</label></td>
                        <td><input type="text" class="form-control" name="streetNr" id="streetNr" autocomplete="false"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="locality">Localitate</label></td>
                        <td><input type="text" class="form-control" name="locality" id="locality" autocomplete="false"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="district">Judet</label></td>
                        <td><input type="text" class="form-control" name="district" id="district" autocomplete="false"/>
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
                                    <option value="${area.areaId}">${area.type}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="holeLenght">Lungime</label></td>
                        <td><input type="number" step="0.01" min="0" class="form-control" name="holeLenght"
                                   id="holeLenght" autocomplete="false" placeholder="m"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="holeWidth">Latime</label></td>
                        <td><input type="number" step="0.01" min="0" class="form-control" name="holeWidth"
                                   id="holeWidth" autocomplete="false" placeholder="m"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="holeDepth">Adancime</label></td>
                        <td><input type="number" step="0.01" min="0" class="form-control" name="holeDepth"
                                   id="holeDepth" autocomplete="false" placeholder="m"/>
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
        <div class="row" id="newRouteFields" style="display: none">
            <div class="col-lg-2"></div>
                <div class="form-group col-lg-4">
                    <label class="control-label" for="autoRouteDistance">Distanta parcursa de bascula</label>
                    <input type="number" class="form-control" name="autoRouteDistance" id="autoRouteDistance" autocomplete="false"/>
                    <small id="emailHelp" class="form-text text-muted">Distanta parcursa de bascula de la locul sapaturii pana la groapa de gunoi</small>
                </div>
                <div class="form-group col-lg-4">
                    <label class="control-label" for="autoStationaryTime">Ore de stationare bascula</label>
                    <input type="number" class="form-control" name="autoStationaryTime" id="autoStationaryTime" autocomplete="false"/>
                </div>
            <div class="col-lg-2"></div>
        </div>
        <div class="row">
            <div class="col-lg-2"></div>
            <div class="col-lg-2">
                <input type="submit" class="btn btn-primary btn-lg " value="Salveaza"/>
            </div>
            <div class="col-lg-8"></div>
        </div>
    </form>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>

<script>
    $(function() {

        $("form[name='addHoleForm']").validate({
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