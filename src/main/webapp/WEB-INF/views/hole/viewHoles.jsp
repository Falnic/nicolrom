<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../bootstrapImports.jsp"/>
<jsp:include page="../backofficeHeader.jsp"/>

<c:url var="addHole" value="/backoffice/holes/add"/>

<html>
    <head>
        <title>DELGAZ Grid Holes</title>
    </head>
    <body>
        <c:if test="${not empty allHoles}">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h1>Situatie defecte DELGAZ grid SA</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-4">
                        <a class="btn btn-lg btn-primary" href="${addHole}" role="button">Adauga Groapa</a>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Data </th>
                                    <th>Strada</th>
                                    <th>Nr</th>
                                    <th>Localitate</th>
                                    <th>Judet</th>
                                    <th>Etapa</th>
                                    <th>Lungime</th>
                                    <th>Latime</th>
                                    <th>Adancime</th>
                                    <th>Volum</th>
                                </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="hole" items="${allHoles}">
                                <tr ondblclick="redirectToHDP(${hole.holeId})">
                                    <td style="text-align: center">${hole.date}</td>
                                    <td style="text-align: center">${hole.street}</td>
                                    <td style="text-align: center">${hole.streetNr}</td>
                                    <td style="text-align: center">${hole.locality}</td>
                                    <td style="text-align: center">${hole.district}</td>
                                    <c:choose>
                                        <c:when test="${hole.phase == 'SAPATURA'}">
                                            <td style="text-align: center; background-color: #FF3E3E; color: white">${hole.phase}</td>
                                        </c:when>
                                        <c:when test="${hole.phase == 'UMPLERE'}">
                                            <td style="text-align: center; background-color: orange; color: white">${hole.phase}</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td style="text-align: center">${hole.phase}</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td style="text-align: center">${hole.holeLength}</td>
                                    <td style="text-align: center">${hole.holeWidth}</td>
                                    <td style="text-align: center">${hole.holeDepth}</td>
                                    <td style="text-align: center"><fmt:formatNumber type = "number" maxFractionDigits="2" value="${hole.holeVolume}"/></td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="row">
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
                                               href="<c:url value='/backoffice/holes?pgNr=${pgNr - 1}'/>">Pagina Anterioara
                                            </a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>

                                <c:forEach var="pageItem" begin="0" end="${lastPg - 1}">
                                    <c:choose>
                                        <c:when test="${pageItem == pgNr}">
                                            <li class="page-item active">
                                                <a class="page-link"
                                                   href="<c:url value='/backoffice/holes?pgNr=${pageItem}'/>">${pageItem + 1}
                                                </a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item">
                                                <a class="page-link"
                                                   href="<c:url value='/backoffice/holes?pgNr=${pageItem}'/>">${pageItem + 1}
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
                                               href="<c:url value='/backoffice/holes?pgNr=${pgNr + 1}'/>">Urmatoarea Pagina
                                            </a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </c:if>

    <script>
        function redirectToHDP(holeid) {
            window.location.href = "http://localhost:8080/nicolrom/backoffice/holes/" + holeid;
        }
    </script>
    </body>
</html>