<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<div class="container">
    <div class="row">
        <h1>Groapa ${hole.holeId}</h1>
    </div>
    <div class="row">
        <div class="col-lg-6">
            <table class="table table-striped">
                <tr>
                    <td>Id</td>
                    <td>${hole.holeId}</td>
                </tr>
                <tr>
                    <td>Data</td>
                    <td>${hole.date}</td>
                </tr>
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
                    <td>Suprafata</td>
                    <td>${hole.holeSurface}</td>
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
            </ul>
            <c:forEach var="phase" items="${hole.phases}">
                <div id="tabs-${phase.phaseType.ordinal()}">
                    <div class="row">
                        <div class="col-lg-7">
                            <div class="row">
                                <c:forEach items="${allPositionsEnum}" var="position">
                                    <div class="col-lg-4">
                                        <table class="table">
                                            <h4>${position}</h4>
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
        </div>
    </div>
</div>

<script>
    $(function () {
        $("#tabs").tabs();
    });
</script>