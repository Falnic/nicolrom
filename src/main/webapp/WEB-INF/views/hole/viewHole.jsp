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
                                            <form method="post" autocomplete="off">
                                                <div class="row form-group">
                                                    <div class="col-lg-3">
                                                        <label class="control-label" for="phaseDatePicker">Data</label>
                                                        <input class="form-control" type="date" id="phaseDatePicker" name="phaseDate" placeholder="dd/MM/yy">
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
                                                <tag:addPhaseEmployees positionEmployeesMap_SOFER="${positionEmployeesMap_SOFER}"
                                                                       positionEmployeesMap_MECANIC="${positionEmployeesMap_MECANIC}"
                                                                       positionEmployeesMap_NECALIFICAT="${positionEmployeesMap_NECALIFICAT}"
                                                />
                                                <div class="row form-group">
                                                    <input type="hidden" name="nextPhase" value="${nextPhase}">
                                                    <input type="submit" class="btn btn-lg btn-primary" value="Adauga Etapa">
                                                </div>
                                            </form>
                                        </div>
                                </c:when>
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
                                            <dd class="col-lg-2"><fmt:formatDate  value="${phase.phaseDate}" pattern="dd/MM/yyyy"/></dd>
                                            <dt class="col-lg-1">Executant</dt>
                                            <dd class="col-lg-2">${hole.executor}</dd>
                                            <c:if test="${hole.autoRouteDistance != null || hole.autoStationaryTime != null}">
                                                <dt class="col-lg-2">Distanta parcursa</dt>
                                                <dd class="col-lg-1">${hole.autoRouteDistance}</dd>
                                                <dt class="col-lg-2">Timp de stationare</dt>
                                                <dd class="col-lg-1">${hole.autoStationaryTime}</dd>
                                            </c:if>
                                        </dl>
                                        <tag:viewPhaseEmployees phase="${phase}"/>
                                    </div>
                                </c:when>
                                <c:when test="${(phaseTab.key.name() == 'UMPLERE') && (phaseTab.value != null)}">
                                    <div id="tabs-UMPLERE">
                                        <dl class="row">
                                            <dt class="col-lg-1">Data</dt>
                                            <dd class="col-lg-2"><fmt:formatDate  value="${phase.phaseDate}" pattern="dd/MM/yyyy"/></dd>
                                            <dt class="col-lg-1">Conducta</dt>
                                            <dd class="col-lg-2">&straightphi; ${hole.pipe.diameter}</dd>
                                        </dl>
                                        <tag:viewPhaseEmployees phase="${phase}"/>
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
    });
</script>