<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/viewHole.css"/>">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

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
                    <td>${hole.holeVolume}</td>
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
                                            <td>${materialNotice.quantity}</td>
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
                            <div class="col-lg-3">
                                <label class="control-label" for="phaseDate">Data</label>
                                <input class="form-control" type="text" id="phaseDate" name="phaseDate" placeholder="mm/dd/yy">
                            </div>
                        </div>
                        <div class="row form-group">
                            <div class="col-lg-7">
                                <div class="row">
                                    <c:forEach items="${employeesMap}" var="employeesMapItem">
                                        <div class="col-lg-4">
                                            <h5>${employeesMapItem.key.name()}</h5>
                                            <table class="table">
                                                <c:forEach var="employee" items="${employeesMapItem.value}">
                                                    <tr>
                                                        <td>
                                                            <input id="employeeCheck${employee.idEmployee}" type="checkbox" name="employees" value="${employee.idEmployee}">
                                                            <label for="employeeCheck${employee.idEmployee}">${employee.name}</label>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="col-lg-5">
                                <div class="row">
                                    <h5>Materiale</h5>
                                </div>
                                <div class="row">
                                    <table id="materialsTable" class="table">
                                        <tr>
                                            <td>
                                                <select name="materials" id="materialNoticeSelect"
                                                        class="browser-default custom-select">
                                                    <c:forEach var="material" items="${allMaterials}">
                                                        <option value="${material.materialId}">${material.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td>
                                                <input type="number" name="materialsQuantity" class="form-control"
                                                       placeholder="Cantitate" id="materialsQuantityInput" >
                                            </td>
                                        </tr>
                                        <tr id="addMaterialsTableRow">
                                            <td>
                                                <input id="addMaterialsRowBtn" type="button" value="Adauga Materiale"
                                                       class="btn btn-primary" onclick="addRow()"/>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
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

    function addRow(){
        $("#addMaterialsTableRow").remove();
        $("#materialsTable").append(
            '                                        <tr>\n' +
            '                                            <td>\n' +
            '                                                <select name="materials" id="materialNoticeSelect"\n' +
            '                                                        class="browser-default custom-select">\n' +
            '                                                    <c:forEach var="material" items="${allMaterials}">\n' +
            '                                                        <option value="${material.materialId}">${material.name}</option>\n' +
            '                                                    </c:forEach>\n' +
            '                                                </select>\n' +
            '                                            </td>\n' +
            '                                            <td>\n' +
            '                                                <input type="number" name="materialsQuantity" class="form-control"\n' +
            '                                                       id="materialsQuantityInput" >\n' +
            '                                            </td>\n' +
            '                                        </tr>\n' +
            '                                        <tr id="addMaterialsTableRow">\n' +
            '                                            <td>\n' +
            '                                                <input id="addMaterialsRowBtn" type="button" value="Adauga Materiale"\n' +
            '                                                       class="btn btn-primary" onclick="addRow()"/>\n' +
            '                                            </td>\n' +
            '                                        </tr>'
        );
    }

</script>