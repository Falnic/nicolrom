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
                <tr>
                    <td>Strada</td>
                    <td>${hole.street}</td>
                </tr>
                <tr>
                    <td>Numar</td>
                    <td>${hole.streetNr}</td>
                </tr>
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
                </div>
            </c:forEach>
            <c:if test="${nextPhase != null}">
                <div id="tabs-addPhase">
                    <form method="post" autocomplete="off">
                        <div class="row form-group">
                            <div class="col-lg-4">
                                <label class="control-label" for="phaseDate">Data</label>
                                <input class="form-control" type="text" id="phaseDate" name="phaseDate" placeholder="mm/dd/yy">
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
                                                    onchange="addRowSOFER()">
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
                                <h4><label class="control-label" for="selectEmployees-MECANIC">MECANIC</label></h4>
                                <table class="table" id="MECANIC-table">
                                    <tr>
                                        <td>
                                            <select name="employees" id="selectEmployees-MECANIC" class="browser-default custom-select"
                                                    onchange="addRowMECANIC()">
                                                <option value="${null}" selected>Alege Mecanic</option>
                                                <c:forEach var="employee" items="${positionEmployeesMap_MECANIC}">
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
                                                    onchange="addRowNECALIFICAT()">
                                                <option value="${null}" selected>Alege Necalificat</option>
                                                <c:forEach var="employee" items="${positionEmployeesMap_NECALIFICAT}">
                                                    <option value="${employee.idEmployee}">${employee.name}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                </table>
                            </div>



<%--                            <c:forEach items="${employeesMap}" var="employeesMapItem">--%>
<%--                                <div class="col-lg-4">--%>
<%--                                    <h5>${employeesMapItem.key.name()}</h5>--%>
<%--                                    <table class="table">--%>
<%--                                        <c:forEach var="employee" items="${employeesMapItem.value}">--%>
<%--                                            <tr>--%>
<%--                                                <td>--%>
<%--                                                    <input id="employeeCheck${employee.idEmployee}" type="checkbox" name="employees" value="${employee.idEmployee}">--%>
<%--                                                    <label for="employeeCheck${employee.idEmployee}">${employee.name}</label>--%>
<%--                                                </td>--%>
<%--                                            </tr>--%>
<%--                                        </c:forEach>--%>
<%--                                    </table>--%>
<%--                                </div>--%>
<%--                            </c:forEach>--%>
                        </div>
                        <div class="row form-group">
                            <input type="hidden" name="nextPhase" value="${nextPhase}">
                            <input type="submit" class="btn btn-lg btn-primary" value="Adauga Etapa">
                        </div>
                    </form>
                </div>
            </c:if>
        </div>
    </div>
</div>

<script>
    $(function () {
        $("#tabs").tabs();
    });

    $(function() {
        $( "#phaseDate" ).datepicker();
    });

    $(function () {
        $("#materialNoticeSelect").selectmenu();
    });

    function addRowSOFER() {
        $("#SOFER-table").append('<tr>\n' +
            '                                <td>\n' +
            '                                    <select name="employees" id="selectEmployees-SOFER" class="browser-default custom-select"\n' +
            '                                            onchange="addRowSOFER()">\n' +
            '                                        <option value="${null}" selected>Alege Sofer</option>\n' +
            '                                        <c:forEach var="employee" items="${positionEmployeesMap_SOFER}">\n' +
            '                                            <option value="${employee.idEmployee}">${employee.name}</option>\n' +
            '                                        </c:forEach>\n' +
            '                                    </select>\n' +
            '                                </td>\n' +
            '                            </tr>')
    }

    function addRowMECANIC() {
        $("#MECANIC-table").append('<tr>\n' +
            '                                <td>\n' +
            '                                    <select name="employees" id="selectEmployees-MECANIC" class="browser-default custom-select"\n' +
            '                                            onchange="addRowMECANIC()">\n' +
            '                                        <option value="${null}" selected>Alege Mecanic</option>\n' +
            '                                        <c:forEach var="employee" items="${positionEmployeesMap_MECANIC}">\n' +
            '                                            <option value="${employee.idEmployee}">${employee.name}</option>\n' +
            '                                        </c:forEach>\n' +
            '                                    </select>\n' +
            '                                </td>\n' +
            '                            </tr>')
    }

    function addRowNECALIFICAT() {
        $("#NECALIFICAT-table").append('<tr>\n' +
            '                                <td>\n' +
            '                                    <select name="employees" id="selectEmployees-NECALIFICAT" class="browser-default custom-select"\n' +
            '                                            onchange="addRowNECALIFICAT()">\n' +
            '                                        <option value="${null}" selected>Alege Necalificat</option>\n' +
            '                                        <c:forEach var="employee" items="${positionEmployeesMap_NECALIFICAT}">\n' +
            '                                            <option value="${employee.idEmployee}">${employee.name}</option>\n' +
            '                                        </c:forEach>\n' +
            '                                    </select>\n' +
            '                                </td>\n' +
            '                            </tr>')
    }

</script>