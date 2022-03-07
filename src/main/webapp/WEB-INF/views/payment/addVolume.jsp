<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="../bootstrapImports.jsp"/>
    <jsp:include page="../backofficeHeader.jsp"/>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/payment/addVolume.css"/>">

    <title>Adauga Volum</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-lg-12 text-center">
            <h1>Adauga Volum</h1>
        </div>
    </div>
    <form method="post" name="addVolumeForm">
        <div class="row">
            <div class="col-lg-2">
                <label class="control-label" for="volumeNr">Nr Volum</label>
            </div>
            <div class="col-lg-3">
                <input type="text" class="form-control" name="volumeNr" id="volumeNr">
            </div>
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label class="control-label" for="contractNr">Nr Contract</label>
            </div>
            <div class="col-lg-3">
                <select name="contract" id="contractNr" class="form-control">
                    <c:forEach var="contract" items="${contracts}">
                        <c:choose>
                            <c:when test="${contract.inUse == true}">
                                <option value="${contract.idContract}" selected>${contract.nr}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${contract.idContract}">${contract.nr}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="col-lg-1"></div>
        </div>
        <div class="row">
            <div class="col-lg-2">
                <label class="control-label" for="regNr">Nr Reg</label>
            </div>
            <div class="col-lg-3">
                <input type="text" class="form-control" name="regNr" id="regNr">
            </div>
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label class="control-label" for="district">District</label>
            </div>
            <div class="col-lg-3">
                <select name="district" id="district" class="form-control">
                    <option value="" selected>Alege</option>
                    <c:forEach var="district" items="${districts}">
                        <option value="${district}">${district}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-lg-1"></div>
        </div>
        <div class="form-group row dateSearch">
            <label for="startDateSearch" class="col-lg-2 col-form-label">Inceput</label>
            <div class="col-lg-3">
                <input class="form-control" type="date" id="startDateSearch" name="startDate" max="${currentDate}">
            </div>
            <div class="col-lg-1"></div>
            <label for="endDateSearch" class="col-lg-2 col-form-label">Sfarsit</label>
            <div class="col-lg-3">
                <input class="form-control" type="date" id="endDateSearch" name="endDate" max="${currentDate}">
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>
                            <input type="checkbox" id="select-all">
                        </th>
                        <th>Data</th>
                        <th>Adresa</th>
                        <th>District</th>
                    </tr>
                    </thead>
                    <tbody id="holesTB">
                    <c:choose>
                        <c:when test="${holes.size() > 0}">
                            <c:forEach var="hole" items="${holes}">
                                <tr>
                                    <td>
                                        <input type="checkbox" id="select-Hole-${hole.id}" name="holes">
                                    </td>
                                    <td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${hole.date}"/></td>
                                    <td>${hole.address}</td>
                                    <td>${hole.district}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr id="noHolesTr">
                                <td colspan="4"><h5>Selecteaza perioada</h5></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8"></div>
            <div class="col-lg-2">
                <input type="submit" class="btn btn-primary btn-lg " value="Next"/>
            </div>
            <div class="col-lg-2">
                <a class="btn btn-lg btn-danger" id="cancelBtn" href="<c:url value="/backoffice/volumes"/>" role="button">Anuleaza</a>
            </div>
        </div>
    </form>
</div>
<script src="<c:url value="/resources/js/payment/addVolume.js"/>"></script>
</body>
</html>
