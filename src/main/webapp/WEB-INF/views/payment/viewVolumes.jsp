<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="../bootstrapImports.jsp"/>
    <jsp:include page="../backofficeHeader.jsp"/>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/payment/viewVolumes.css"/>">

    <title>Volume</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-lg-12 text-center">
            <h1>Volume</h1>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-lg-2"></div>
        <label for="startDateSearch" class="col-lg-1 col-form-label">Inceput</label>
        <div class="col-lg-3">
            <input class="form-control" type="date" id="startDateSearch" value="${startDate}" name="startDate"
                   max="${currentDate}">
        </div>
        <label for="endDateSearch" class="col-lg-1 col-form-label">Sfarsit</label>
        <div class="col-lg-3">
            <input class="form-control" type="date" id="endDateSearch" value="${endDate}" name="endDate"
                   max="${currentDate}">
        </div>
    </div>
    <div class="row">
        <div class="col-lg-2">
            <a class="btn btn-lg btn-block btn-primary" id="addVolumeBtn"
               href="<c:url value="/backoffice/volumes/add"/>" role="button">Adauga</a>
        </div>
        <div class="col-lg-10">
            <c:forEach var="volume" items="${volumes}">
                <div class="col-lg-12">
                    <div class="card viewVolumesCard" onclick="redirectToVolume(1)">
                        <div class="card-body">
                            <h5 class="card-title">${volume.district} - Vol ${volume.regNr} - <fmt:formatDate
                                    pattern="yyyy-MM-dd" value="${volume.startDate}"/> - <fmt:formatDate
                                    pattern="yyyy-MM-dd" value="${volume.endDate}"/></h5>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${lastPg > 1}">
                <div class="col-lg-12">
                    <nav aria-label="Search results pages">
                        <ul class="pagination justify-content-center">
                            <c:choose>
                                <c:when test="${pgNr == 0}">
                                    <li class="page-item disabled"><a class="page-link">Pagina Anterioara</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="<c:url value='/backoffice/volumes?pgNr=${pgNr - 1}&startDate=${startDate}&endDate=${endDate}'/>">Pagina
                                            Anterioara
                                        </a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach var="pageItem" begin="0" end="${lastPg - 1}">
                                <c:choose>
                                    <c:when test="${pageItem == pgNr}">
                                        <li class="page-item active">
                                            <a class="page-link"
                                               href="<c:url value='/backoffice/volumes?pgNr=${pageItem}&startDate=${startDate}&endDate=${endDate}'/>">${pageItem + 1}
                                            </a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item">
                                            <a class="page-link"
                                               href="<c:url value='/backoffice/volumes?pgNr=${pageItem}&startDate=${startDate}&endDate=${endDate}'/>">${pageItem + 1}
                                            </a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${(pgNr + 1) == lastPg}">
                                    <li class="page-item disabled"><a class="page-link">Urmatoarea Pagina</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="<c:url value='/backoffice/volumes?pgNr=${pgNr + 1}&startDate=${startDate}&endDate=${endDate}'/>">Urmatoarea
                                            Pagina
                                        </a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </nav>
                </div>
            </c:if>
        </div>
    </div>
</div>
<script src="<c:url value="/resources/js/payment/viewVolumes.js"/>"></script>

<script>
    $("#startDateSearch,#endDateSearch").change(function () {
        var startDate = $("#startDateSearch").val();
        var endDate = $("#endDateSearch").val();
        if (startDate !== "" && endDate !== "") {
            window.location.href = "http://localhost:8081/NicolRom/backoffice/volumes/?" + "pgNr=" + ${pgNr} +"&startDate=" + startDate + "&endDate=" + endDate;
        }
    })
</script>
</body>
</html>
