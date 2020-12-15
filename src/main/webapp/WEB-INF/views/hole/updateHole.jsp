<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="../bootstrapImports.jsp"/>
    <jsp:include page="../backofficeHeader.jsp"/>
    <c:url var="updateHoleCSS" value="/resources/style/updateHole.css"/>
    <link rel="stylesheet" type="text/css" href="${updateHoleCSS}">
    <title>Update Hole</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-lg-12 text-center">
            <h1>Modifica Defect</h1>
        </div>
    </div>
    <c:if test="${not empty error}">
        <div class="col-lg-12 text-center error">
            <h5>Eroare, exista in sistem o sapatura cu aceeasi data</h5>
        </div>
    </c:if>
    <form method="post" autocomplete="off" name="updateHoleForm">
        <fieldset>
            <legend>Date Generale</legend>
            <div class="row">
                <div class="col-lg-4">
                    <div class="row">
                        <label class="col-lg-3 col-form-label" for="street">Strada</label>
                        <div class="col-lg-9">
                            <input type="text" class="form-control" name="street" id="street" autocomplete="false"
                                   value="${hole.street}"/>
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-lg-3 col-form-label" for="streetNr">Numar</label>
                        <div class="col-lg-9">
                            <input type="text" class="form-control" name="streetNr" id="streetNr" autocomplete="false"
                                   value="${hole.streetNr}"/>
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-lg-3 col-form-label" for="locality">Localitate</label>
                        <div class="col-lg-9">
                            <input type="text" class="form-control" name="locality" id="locality" autocomplete="false"
                                   value="${hole.locality}"/>
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-lg-3 col-form-label" for="county">Judet</label>
                        <div class="col-lg-9">
                            <input type="text" class="form-control" name="county" id="county" autocomplete="false"
                                   value="${hole.county}"/>
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-lg-3 col-form-label" for="district">District</label>
                        <div class="col-lg-9">
                            <input type="text" class="form-control" name="district" id="district" autocomplete="false"
                                   value="${hole.district}"/>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2"></div>
                <div class="col-lg-4">
                    <div class="row">
                        <label class="col-lg-3 col-form-label" for="selectArea">Zona</label>
                        <div class="col-lg-9">
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
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-lg-3 col-form-label" for="holeLenght">Lungime</label>
                        <div class="col-lg-9">
                            <input type="number" step="0.01" min="0" class="form-control" name="holeLenght"
                                   id="holeLenght" autocomplete="false" placeholder="m" value="${hole.holeLength}"/>
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-lg-3 col-form-label" for="holeWidth">Latime</label>
                        <div class="col-lg-9">
                            <input type="number" step="0.01" min="0" class="form-control" name="holeWidth"
                                   id="holeWidth" autocomplete="false" placeholder="m" value="${hole.holeWidth}"/>
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-lg-3 col-form-label" for="holeDepth">Adancime</label>
                        <div class="col-lg-9">
                            <input type="number" step="0.01" min="0" class="form-control" name="holeDepth"
                                   id="holeDepth" autocomplete="false" placeholder="m" value="${hole.holeDepth}"/>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2"></div>
            </div>
        </fieldset>
        <fieldset>
            <legend>Sapatura</legend>
            <div class="row">
                <label class="col-lg-2 col-form-label" for="SAPATURA_datePicker">Data</label>
                <div class="col-lg-3">
                    <input type="date" class="form-control" name="SAPATURA_Date" id="SAPATURA_datePicker"
                           placeholder="dd/mm/yyyy" autocomplete="false" max="${currentDate}"
                           value="${hole.date}"/>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-3">
                    <b>Sapatura executata de:</b>
                </div>
                <div class="col-lg-5">
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
                <div class="col-lg-4"></div>
            </div>
            <div class="row">
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
                <div class="col-lg-4"></div>
            </div>
            <div class="row" id="newRouteFields" style="display: none">
                <div class="form-group col-lg-4">
                    <label class="control-label" for="autoRouteDistance">Distanta parcursa de bascula</label>
                    <input type="number" step="0.01" min="0" class="form-control" name="autoRouteDistance" id="autoRouteDistance"
                           autocomplete="false" value="${hole.autoRouteDistance}"/>
                    <small id="emailHelp" class="form-text text-muted">Distanta parcursa de bascula de la locul sapaturii pana la groapa de gunoi</small>
                </div>
                <div class="col-lg-2"></div>
                <div class="form-group col-lg-4">
                    <label class="control-label" for="autoStationaryTime">Ore de stationare bascula</label>
                    <input type="number" class="form-control" name="autoStationaryTime" id="autoStationaryTime"
                           step="1" min="0" autocomplete="false" value="${hole.autoStationaryTime}"/>
                </div>
                <div class="col-lg-4"></div>
            </div>
            <input type="hidden" name="phaseEnums" value="SAPATURA">
        </fieldset>
        <c:forEach var="phase" items="${hole.phases}">
            <c:if test="${'UMPLERE'.compareTo(phase.phaseType.name()) == 0}">
                <fieldset>
                    <legend>Umplere</legend>
                    <div class="row">
                        <label class="col-lg-1 col-form-label" for="UMPLERE_datePicker">Data</label>
                        <div class="col-lg-2">
                            <input type="date" class="form-control" name="UMPLERE_Date" id="UMPLERE_datePicker"
                                   placeholder="dd/mm/yyyy" autocomplete="false" max="${currentDate}"
                                   value="${hole.date}"/>
                        </div>
                        <div class="col-lg-1"></div>
                        <label class="col-lg-2 col-form-label" for="selectPipe">Dimensiune Conducta</label>
                        <div class="col-lg-3">
                            <select name="pipe" id="selectPipe"
                                    class="browser-default custom-select">
                                <c:forEach var="pipe" items="${allPipes}">
                                    <c:choose>
                                        <c:when test="${pipe.diameter == hole.pipe.diameter}">
                                            <option value="${pipe.diameter}" selected>&straightphi; ${pipe.diameter}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${pipe.diameter}">&straightphi; ${pipe.diameter}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="row">
                                <div class="col-lg-12">
                                    <h4>Materiale</h4>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-1">
                                    <label class="control-label" for="selectMaterial">Adauga</label>
                                </div>
                                <div class="col-lg-3">
                                    <select name="selectMaterial" id="selectMaterial"
                                            class="selectpicker" data-live-search="true">
                                        <option name="materialOption-null" value="${null}" selected>Nothing selected</option>
                                        <c:forEach var="material" items="${materials}">
                                            <option name="materialOption-${material.materialId}"
                                                    value="${material.materialId}">${material.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-lg-3">
                                    <button type="button" class="btn btn-secondary" id="calculateMaterialsBtn">Adaugare Automata</button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-7">
                                    <table class="table" id="materialsTable">
                                        <thead>
                                        <tr>
                                            <td>Material</td>
                                            <td>Cantitate</td>
                                            <td>UM</td>
                                            <td></td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="materialNotice" items="${phase.materialNoticeSet}">
                                                <tr id="materialTr-${materialNotice.material.materialId}">
                                                    <td><label class="control-label" for="material-${materialNotice.material.materialId}">
                                                            ${materialNotice.material.name}</label></td>
                                                    <td><input type="number" step="0.01" min="0" class="form-control" name="material"
                                                               id="material-${materialNotice.material.materialId}" autocomplete="false" max="${hole.holeVolume}"
                                                               value="<fmt:formatNumber type = "number" maxFractionDigits="2" value="${materialNotice.quantity}"/>"/>
                                                    <td>MC</td>
                                                    <td><button type="button" class="btn btn-outline-danger"
                                                                onclick="removeMaterialTr(${materialNotice.material.materialId},'${materialNotice.material.name}')">
                                                        X</button></td>
                                                </tr>
                                                <input type="hidden" name="materialId" value="${materialNotice.material.materialId}" id="input_materialId-${materialNotice.material.materialId}">
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8">
                            <div class="row">
                                <div class="col-lg-4" id="soferDiv_UMPLERE">
                                    <h4><label class="control-label" for="selectEmployees-SOFER_UMPLERE">SOFER</label></h4>
                                    <table class="table" id="SOFER-table_UMPLERE">
                                        <tr>
                                            <td>
                                                <select name="employees_SOFER_UMPLERE" id="selectEmployees-SOFER_UMPLERE" class="selectpicker" multiple
                                                        data-live-search="true" data-actions-box="true" data-selected-text-format="count">
                                                    <c:forEach var="employee" items="${positionEmployeesMap_SOFER}">
                                                        <option value="${employee.idEmployee}">${employee.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="col-lg-4" id="mecanicDiv_UMPLERE">
                                    <h4><label class="control-label" for="selectEmployees-MECANIC_UMPLERE">MECANIC</label></h4>
                                    <table class="table" id="MECANIC-table_UMPLERE">
                                        <tr>
                                            <td>
                                                <select name="employees_MECANIC_UMPLERE" id="selectEmployees-MECANIC_UMPLERE" class="selectpicker" multiple
                                                        data-live-search="true" data-actions-box="true" data-selected-text-format="count">
                                                    <c:forEach var="employee" items="${positionEmployeesMap_MECANIC}">
                                                        <option value="${employee.idEmployee}">${employee.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="col-lg-4" id="necalificatDiv_UMPLERE">
                                    <h4><label class="control-label" for="selectEmployees-NECALIFICAT_UMPLERE">NECALIFICAT</label></h4>
                                    <table class="table" id="NECALIFICAT-table_UMPLERE">
                                        <tr>
                                            <td>
                                                <select name="employees_NECALIFICAT_UMPLERE" id="selectEmployees-NECALIFICAT_UMPLERE" class="selectpicker" multiple
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
                        <div class="col-lg-4"></div>
                    </div>
                </fieldset>
                <input type="hidden" name="phaseEnums" value="${phase.phaseType.name()}">
            </c:if>
        </c:forEach>
        <div class="row">
            <div class="col-lg-7"></div>
            <div class="col-lg-2">
                <input type="submit" class="btn btn-warning btn-lg btn-block" value="Modifica"/>
            </div>
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <a class="btn btn-lg btn-danger btn-block" href="<c:url value="/backoffice/holes/${hole.holeId}"/>" role="button">Anuleaza</a>
            </div>
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
        $("#selectEmployees-SOFER_UMPLERE").selectpicker('val', ${selectedEmployees_SOFER_UMPLERE});
        $("#selectEmployees-MECANIC_UMPLERE").selectpicker('val', ${selectedEmployees_MECANIC_UMPLERE});
        $("#selectEmployees-NECALIFICAT_UMPLERE").selectpicker('val', ${selectedEmployees_NECALIFICAT_UMPLERE});

        $("#selectMaterial").change(function (){
            var value = $(this).val();
            var materialName = $(this).find(":selected").text();
            $(this).find("option:selected").remove();
            $(this).selectpicker('refresh');
            $("#materialsTable").append('                                   <tr id="materialTr-' + value + '">\n' +
                '                                                               <td><label class="control-label" for="material-' + value + '">' + materialName + '</label></td>\n' +
                '                                                               <td><input type="number" step="0.01" min="0" class="form-control" name="material"\n' +
                '                                                                                   id="material-' + value +'" autocomplete="false"/>\n' +
                '                                                               <td>MC</td>' +
                '                                                               <td><button type="button" class="btn btn-outline-danger" onclick="removeMaterialTr(' + value +',\'' + materialName +' \')">X</button></td>' +
                '                                                               <input type="hidden" name="materialId" value="' + value + '" '+
                '                                                                 </tr>');
        });

        $("#selectPipe").change(function () {
            var values = calculateMaterials();
            var sandQuantity = values[0]; var balast = values[1];

            if (typeof ($("#material-1").val()) !== "undefined"){
                $("#material-1").val(sandQuantity);
            }
            if (typeof ($("#material-2").val()) !== "undefined"){
                $("#material-2").val(balast);
            }
        })

        $('#calculateMaterialsBtn').click(function () {
            var values = calculateMaterials();
            var sandQuantity = values[0]; var balast = values[1];

            if (typeof ($("#material-1").val()) === "undefined"){
                $("#selectMaterial option[name = 'materialOption-1']").remove();
                $("#selectMaterial").selectpicker('refresh');
                $("#materialsTable").append('                                                                    <tr id="materialTr-1">\n' +
                    '                                                                        <td><label class="control-label" for="material-1" id="labelMaterial-1">Nisip</label></td>\n' +
                    '                                                                        <td><input type="number" step="0.01" min="0" class="form-control" name="material"\n' +
                    '                                                                                   id="material-1" autocomplete="false" value="' + sandQuantity + '"/>\n' +
                    '                                                                          <td>MC</td>'+
                    '                                                               <td><button type="button" class="btn btn-outline-danger" onclick="removeMaterialTr(1,\'Nisip\')">X</button></td>' +
                    '                                                               <input type="hidden" name="materialId" value="1" '+
                    '                                                                    </tr>\n');
            }
            if (typeof ($("#material-2").val()) === "undefined"){
                $("#selectMaterial option[name = 'materialOption-2']").remove();
                $("#selectMaterial").selectpicker('refresh');
                $("#materialsTable").append('               <tr id="materialTr-2">\n' +
                    '                                                                        <td><label class="control-label" for="material-2">Balast</label></td>\n' +
                    '                                                                        <td><input type="number" step="0.01" min="0" class="form-control" name="material"\n' +
                    '                                                                                   id="material-2" autocomplete="false" value="' + balast + '"/>\n' +
                    '                                                                          <td>MC</td>'+
                    '                                                               <td><button type="button" class="btn btn-outline-danger" onclick="removeMaterialTr(2,\'Balast\')">X</button></td>' +
                    '                                                               <input type="hidden" name="materialId" value="2" '+
                    '                                                                    </tr>');
            }
        })

        $("form[name='updateHoleForm']").validate({
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
                autoStationaryTime: "Introduceti timpul de stationare",
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

    function calculateMaterials(){
        // double nisipQuantity = hole.getHoleLength() * hole.getHoleWidth() * (0.3 + hole.getPipe().getDiameterValue());
        var sandQuantity; var balast;
        var holeLength = ${hole.holeLength}; var holeWidth = ${hole.holeWidth};
        var pipeDiameterValue = parseFloat($("#selectPipe :selected").val())/1000;

        sandQuantity = (holeLength * holeWidth * (0.3 + pipeDiameterValue)).toFixed(2);
        balast = (${hole.holeVolume} - sandQuantity).toFixed(2);

        return [sandQuantity, balast];
    }

    function removeMaterialTr(materialValue, materialName) {
        $("#selectMaterial").append($('<option>', {
            value: materialValue,
            name: "materialOption-" + materialValue,
            text: materialName,
        })).selectpicker('refresh');
        $("#materialTr-" + materialValue).remove();
        $("#input_materialId-" + materialValue).remove();
    }

</script>
</body>
</html>