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
                        <td><input type="text" class="form-control" name="holeDate" id="holeDatePicker" placeholder="luna-zi-an" autocomplete="false"/>
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
                                    <select name="employees" id="selectEmployees-SOFER" class="browser-default custom-select"
                                            onchange="insertSOFERAddButton()">
                                        <option value="${null}" selected>Alege Sofer</option>
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
                                    <select name="employees" id="selectEmployees-MECANIC" class="browser-default custom-select"
                                            onchange="insertMECANICAddButton()">
                                        <option value="${null}" selected>Alege Mecanic</option>
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
                                    <select name="employees" id="selectEmployees-NECALIFICAT" class="browser-default custom-select"
                                            onchange="insertNECALIFICATAddButton()">
                                        <option value="${null}" selected>Alege Necalificat</option>
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
            <div class="col-lg-5"></div>
            <div class="col-lg-2">
                <input type="submit" class="btn btn-primary btn-lg btn-block" value="Salveaza"/>
            </div>
            <div class="col-lg-5"></div>
        </div>
    </form>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>

<script>
    $( function() {
        $( "#holeDatePicker" ).datepicker();

        $("form[name='addHoleForm']").validate({
            rules: {
                holeDate: "required",
                street: "required",
                streetNr: "required",
                locality: "required",
                district: "required",
                autoRouteDistance: {
                    required: "#executorDelGaz:checked",
                },
                autoStationaryTime: {
                    required: "#executorDelGaz:checked",
                },
            },
            messages: {
                holeDate: "Selectati data",
                street: "Introduceti strada",
                streetNr: "Introduceti numarul strazii",
                locality: "Introduceti localitatea",
                district: "Introduceti judetul",
                autoRouteDistance: "Introduceti distanta",
                autoStationaryTime: "Introduceti timpul de stationare",
            },
            submitHandler: function(form) {
                form.submit();
            }
        });
    } );


    function insertSOFERAddButton() {
        $("#addSOFERButtonTr").remove();
        $("#SOFER-table").append('        <tr id="addSOFERButtonTr">\n' +
            '                                <td>\n' +
            '                                    <button type="button" id="addSOFERButton" class="btn btn-secondary btn-block" onclick="addRowSOFER()">Adauga Sofer</button>\n' +
            '                                </td>\n' +
            '                            </tr>')
    }

    function addRowSOFER() {
        $("#SOFER-table").append('<tr>\n' +
            '                                <td>\n' +
            '                                    <select name="employees" id="selectEmployees-SOFER" class="browser-default custom-select"\n' +
            '                                            onchange="insertSOFERAddButton()">\n' +
            '                                        <option value="${null}" selected>Alege Sofer</option>\n' +
            '                                        <c:forEach var="employee" items="${positionEmployeesMap_SOFER}">\n' +
            '                                            <option value="${employee.idEmployee}">${employee.name}</option>\n' +
            '                                        </c:forEach>\n' +
            '                                    </select>\n' +
            '                                </td>\n' +
            '                            </tr>')
    }

    function insertMECANICAddButton() {
        $("#addMECANICButtonTr").remove();
        $("#MECANIC-table").append('        <tr id="addMECANICButtonTr">\n' +
            '                                <td>\n' +
            '                                    <button type="button" id="addMECANICButton" class="btn btn-secondary btn-block" onclick="addRowMECANIC()">Adauga Mecanic</button>\n' +
            '                                </td>\n' +
            '                            </tr>')
    }

    function addRowMECANIC() {
        $("#MECANIC-table").append('<tr>\n' +
            '                                <td>\n' +
            '                                    <select name="employees" id="selectEmployees-MECANIC" class="browser-default custom-select"\n' +
            '                                            onchange="insertMECANICAddButton()">\n' +
            '                                        <option value="${null}" selected>Alege Mecanic</option>\n' +
            '                                        <c:forEach var="employee" items="${positionEmployeesMap_MECANIC}">\n' +
            '                                            <option value="${employee.idEmployee}">${employee.name}</option>\n' +
            '                                        </c:forEach>\n' +
            '                                    </select>\n' +
            '                                </td>\n' +
            '                            </tr>')
    }

    function insertNECALIFICATAddButton() {
        $("#addNECALIFICATButtonTr").remove();
        $("#NECALIFICAT-table").append('        <tr id="addNECALIFICATButtonTr">\n' +
            '                                <td>\n' +
            '                                    <button type="button" id="addNECALIFICATButton" class="btn btn-secondary btn-block" onclick="addRowNECALIFICAT()">Adauga Necalificat</button>\n' +
            '                                </td>\n' +
            '                            </tr>')
    }

    function addRowNECALIFICAT() {
        $("#NECALIFICAT-table").append('<tr>\n' +
            '                                <td>\n' +
            '                                    <select name="employees" id="selectEmployees-NECALIFICAT" class="browser-default custom-select"\n' +
            '                                            onchange="insertNECALIFICATAddButton()">\n' +
            '                                        <option value="${null}" selected>Alege Necalificat</option>\n' +
            '                                        <c:forEach var="employee" items="${positionEmployeesMap_NECALIFICAT}">\n' +
            '                                            <option value="${employee.idEmployee}">${employee.name}</option>\n' +
            '                                        </c:forEach>\n' +
            '                                    </select>\n' +
            '                                </td>\n' +
            '                            </tr>')
    }

    function removeAutoFields() {
        $("#newRouteFields").hide("slow");
        $("#mecanicDiv").show("slow");
        $("#necalificatDiv").show("slow");
    }

    function addAutoFields() {
        $("#newRouteFields").show("slow");
        $("#mecanicDiv").hide("slow");
        $("#necalificatDiv").hide("slow");
    }
</script>