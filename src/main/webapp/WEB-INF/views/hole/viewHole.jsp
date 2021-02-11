<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags/hole" %>

<jsp:include page="../bootstrapImports.jsp"/>
<jsp:include page="../backofficeHeader.jsp"/>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/viewHole.css"/>">

<div class="container">
    <div class="row">
        <h1>Groapa ${hole.holeId}</h1>
    </div>
    <div class="row">
        <div class="col-lg-6">
            <table class="table table-striped">
                <tr>
                    <td>Adresa</td>
                    <td>Strada ${hole.street} nr ${hole.streetNr}</td>
                </tr>
                <tr>
                    <td>Localitate</td>
                    <td>${hole.locality}</td>
                </tr>
                <tr>
                    <td>Judet</td>
                    <td>${hole.county}</td>
                </tr>
                <tr>
                    <td>District</td>
                    <td>${hole.district}</td>
                </tr>
                <c:if test="${hole.area != null && hole.area != ''}">
                <tr>
                    <td>Zona</td>
                    <td>${hole.area.type}</td>
                </tr>
                </c:if>
            </table>
        </div>
        <div class="col-lg-6">
            <table class="table table-striped">
                <tr>
                    <td>Lungime</td>
                    <td>${hole.holeLength}</td>
                    <td>m</td>
                </tr>
                <tr>
                    <td>Latime</td>
                    <td>${hole.holeWidth}</td>
                    <td>m</td>
                </tr>
                <tr>
                    <td>Adancime</td>
                    <td>${hole.holeDepth}</td>
                    <td>m</td>
                </tr>
                <tr>
                    <td>Volum</td>
                    <td><fmt:formatNumber type = "number" maxFractionDigits="2" value="${hole.holeVolume}"/></td>
                    <td>mc</td>
                </tr>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-9"></div>
        <div class="col-lg-3">
            <a class="btn btn-warning" href="<c:url value="/backoffice/holes/update?id=${hole.holeId}"/>" role="button">Modifica</a>
            <a class="btn btn-danger" id="deleteBtn" href="<c:url value="/backoffice/holes/delete?id=${hole.holeId}"/>" role="button">Sterge</a>
        </div>
    </div>
    <c:if test="${(hole.phases != null) && (hole.phases.size() != 0)}">
        <div class="row">
            <div id="tabs" class="col-lg-12">
                <ul>
                    <c:forEach var="phaseEnum" items="${allPhasesEnum}">
                        <li><a href="#tabs-${phaseEnum.name()}">${phaseEnum.name()}</a></li>
                    </c:forEach>
                </ul>
                <c:forEach var="phaseTab" items="${tabPhases}">
                    <c:set var="phase" value="${phaseTab.value}"/>
                    <c:choose>
                        <c:when test="${nextPhase.compareTo(phaseTab.key) == 0}">
                            <c:choose>
                                <c:when test="${(phaseTab.key.name() == 'UMPLERE')}">
                                        <div id="tabs-UMPLERE">
                                            <form method="post" autocomplete="off" name="addPhase-UMPLERE">
                                                <div class="row form-group">
                                                    <div class="col-lg-3">
                                                        <label class="control-label" for="phaseDatePicker">Data</label>
                                                        <input class="form-control" type="date" id="phaseDatePicker" name="phaseDate" placeholder="dd/MM/yy" max="${currentDate}">
                                                    </div>
                                                    <div class="col-lg-3">
                                                        <label class="control-label" for="selectPipe">Dimensiune Conducta</label>
                                                        <select name="pipe" id="selectPipe"
                                                                class="browser-default custom-select">
                                                            <c:forEach var="pipe" items="${allPipes}">
                                                                <option value="${pipe.diameter}">&straightphi; ${pipe.diameter}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <tag:addPhaseEmployees positionEmployeesMap_SOFER="${positionEmployeesMap_SOFER}"
                                                                               positionEmployeesMap_MECANIC="${positionEmployeesMap_MECANIC}"
                                                                               positionEmployeesMap_NECALIFICAT="${positionEmployeesMap_NECALIFICAT}"
                                                        />
                                                    </div>
                                                </div>
                                                <div id="chooseMachine_Div"></div>
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

                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row form-group">
                                                    <input type="hidden" name="nextPhase" value="${nextPhase}">
                                                    <input type="submit" class="btn btn-lg btn-primary" value="Adauga Etapa">
                                                </div>
                                            </form>
                                        </div>
                                </c:when>
<%--                                TODO: When all forms for adding new phases will be completed this code will be removed --%>
                                <c:otherwise>
                                    <div id="tabs-${phaseTab.key.name()}">
                                        <h3>TO DO</h3>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${(phaseTab.key.name() == 'SAPATURA') && (phaseTab.value != null) }">
                                    <div id="tabs-SAPATURA">
                                        <dl class="row">
                                            <dt class="col-lg-1">Data</dt>
                                            <dd class="col-lg-1"><fmt:formatDate  value="${phase.phaseDate}" pattern="dd/MM/yyyy"/></dd>
                                            <dt class="col-lg-1">Executant</dt>
                                            <dd class="col-lg-2">${hole.executor}</dd>
                                            <c:if test="${hole.autoRouteDistance != null || hole.autoStationaryTime != null}">
                                                <dt class="col-lg-2">Distanta parcursa</dt>
                                                <dd class="col-lg-1">${hole.autoRouteDistance} km</dd>
                                                <dt class="col-lg-2">Timp de stationare</dt>
                                                <dd class="col-lg-1">${hole.autoStationaryTime} ore</dd>
                                            </c:if>
                                        </dl>
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <tag:viewPhaseTeam team="${phase.team}"/>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${(phaseTab.key.name() == 'UMPLERE') && (phaseTab.value != null)}">
                                    <div id="tabs-UMPLERE">
                                        <dl class="row">
                                            <dt class="col-lg-1">Data</dt>
                                            <dd class="col-lg-1"><fmt:formatDate  value="${phase.phaseDate}" pattern="dd/MM/yyyy"/></dd>
                                            <dt class="col-lg-1">Conducta</dt>
                                            <dd class="col-lg-2">&straightphi; ${hole.pipe.diameter}</dd>
                                        </dl>
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <tag:viewPhaseTeam team="${phase.team}"/>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-4">
                                                <tag:viewHoleMaterials phase="${phase}"/>
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div id="tabs-${phaseTab.key.name()}">
                                        <h3>Etapa nu a fost introdusa</h3>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
        </div>
    </c:if>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>
<script>

    $(function () {
        $("#tabs").tabs();
        $('#deleteBtn').click(function() {
            return window.confirm("Sunteti sigur?");
        });
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

        $("form[name='addPhase-UMPLERE']").validate({
            rules: {
                phaseDate:"required",
                material : "required",
            },
            messages: {
                phaseDate: "Selectati data",
                material: "Introduceti cantitatea"
            },
            submitHandler: function(form) {
                form.submit();
            }
        });
    });

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
    }
    function appendChooseMachineHeader() {
        if ($("#chooseMachineHeaderDiv").length === 0){
            $("#chooseMachine_Div").append(
                '<div class="row" id="chooseMachineHeaderDiv">' +
                '<div class="col-lg-4">' +
                '<h5>Alege masina:</h5>' +
                '</div>' +
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
                    '   <div class="col-lg-2">' +
                    '       <label class="control-label" for="machineSelect-employee-' + employeeId + '">' + $(this).text() + '</label>' +
                    '   </div>' +
                    '   <div class="col-lg-2">' +
                    '       <select name="machineSelect_SOFER" id="machineSelect-employee-' + employeeId + '" class="selectpicker">' +
                    '       </select>' +
                    '   </div>' +
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

</script>