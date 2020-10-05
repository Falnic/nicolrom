<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../bootstrapImports.jsp"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/viewHole.css"/>">
<jsp:include page="../backofficeHeader.jsp"/>

<div class="container">
    <div class="row">
        <h1>Groapa ${hole.holeId}</h1>
    </div>
    <div class="row">
        <div class="col-lg-6">
            <table class="table table-striped">
                <c:choose>
                    <c:when test="${hole.holeNrAtSameAddress != null && hole.holeNrAtSameAddress != 0}">
                        <tr>
                            <td>Adresa</td>
                            <td>Strada ${hole.street} nr ${hole.streetNr} Groapa ${hole.holeNrAtSameAddress}</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td>Adresa</td>
                            <td>Strada ${hole.street} nr ${hole.streetNr}</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                <tr>
                    <td>Localitate</td>
                    <td>${hole.locality}</td>
                </tr>
                <tr>
                    <td>Judet</td>
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
    <div class="row">
        <div id="tabs" class="col-lg-12">
            <ul>
                <c:forEach var="phase" items="${hole.phases}">
                    <li><a href="#tabs-${phase.phaseType.ordinal()}">${phase.phaseType.name()}</a></li>
                </c:forEach>
                <c:if test="${nextPhase != null}">
                    <li><a href="#tabs-addPhase" id="a-addPhase">${nextPhase.name()}</a></li>
                </c:if>
            </ul>
            <c:forEach var="phasePositionsEntry" items="${phasePositionsMap}">
                <c:set var="phase" value="${phasePositionsEntry.key}"/>
                <div id="tabs-${phase.phaseType.ordinal()}">
                    <div class="row">
                        <div class="col-lg-3">
                            <table class="table">
                                <tbody>
                                    <tr>
                                        <td><h5>Data:</h5></td>
                                        <td><h5><fmt:formatDate  value="${phase.phaseDate}" pattern="dd/MM/yyyy"/></h5></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <c:if test="${phase.phaseType.name().equals('UMPLERE') && hole.pipe != null}">
                            <div class="col-lg-3">
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <td>Conducta:</td>
                                        <td>&straightphi; ${hole.pipe.diameter}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </c:if>
                        <div class="col-lg-3">
                            <table class="table">
                                <tbody>
                                <tr>
                                    <td>Executant:</td>
                                    <td>${hole.executor}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-7">
                            <div class="row">
                                <c:forEach items="${phasePositionsEntry.value}" var="position">
                                    <div class="col-lg-4">
                                        <h5>${position}</h5>
                                        <table class="table">
                                            <tbody>
                                                <c:forEach items="${phase.team.employees}" var="employee">
                                                    <c:if test="${employee.position.equals(position)}">
                                                        <tr>
                                                            <td>${employee.name}</td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <c:if test="${phase.materialNoticeSet.size() != 0}">
                            <div class="col-lg-5">
                                <h4>Materiale</h4>
                                <table class="table">
                                    <c:forEach var="materialNotice" items="${phase.materialNoticeSet}">
                                        <tr>
                                            <td>${materialNotice.material.name}</td>
                                            <td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${materialNotice.quantity}"/></td>
                                            <td>mc</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </c:if>
                    </div>
                    <c:if test="${hole.autoRouteDistance != null || hole.autoStationaryTime != null}">
                        <div class="row">
                            <div class="col-lg-3">
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <td>Distanta parcursa:</td>
                                        <td>${hole.autoRouteDistance}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="col-lg-3">
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <td>Timp de stationare:</td>
                                        <td>${hole.autoStationaryTime}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:if>
                </div>
            </c:forEach>
            <c:if test="${nextPhase != null}">
                <div id="tabs-addPhase">
                    <form method="post" autocomplete="off" id="addPhaseForm">
                        <div class="row form-group">
                            <div class="col-lg-4">
                                <label class="control-label" for="phaseDatePicker">Data</label>
                                <input class="form-control" type="date" id="phaseDatePicker" name="phaseDate" placeholder="mm/dd/yy">
                            </div>
                            <c:if test="${nextPhase.name().equals('UMPLERE')}">
                                <div class="col-lg-4">
                                    <label class="control-label" for="selectPipe">Dimensiune Conducta</label>
                                    <select name="pipe" id="selectPipe"
                                            class="browser-default custom-select">
                                        <c:forEach var="pipe" items="${allPipes}">
                                            <option value="${pipe.diameter}">&straightphi; ${pipe.diameter}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </c:if>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-4">
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
                            <div class="col-lg-4">
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
                            <div class="col-lg-4">
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
                        </div>
                        <div class="row form-group">
                            <input type="hidden" name="nextPhase" value="${nextPhase}">
                            <input type="submit" class="btn btn-lg btn-primary" value="Adauga Etapa" onclick="submitPhase()">
                        </div>
                    </form>
                </div>
            </c:if>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>

<script>

    $(function () {
        $("#tabs").tabs();
        $('#deleteBtn').click(function() {
            return window.confirm("Sunteti sigur?");
        });
        $("#addPhaseForm").validate({
            rules: {
                phaseDate:"required",
                employees: "required"
            },
            messages: {
                phaseDate: "Selectati data",
                employees: "Alegeti angajat"
            },
            submitHandler: function(form) {
                form.submit();
            }
        });
    });

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
</script>