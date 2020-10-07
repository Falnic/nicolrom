<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url var="addHole" value="/backoffice/holes/add"/>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <jsp:include page="../bootstrapImports.jsp"/>
        <jsp:include page="../backofficeHeader.jsp"/>
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
                        <div class="form-group">
                            <select name="orderHoles" id="orderHoles" class="browser-default custom-select">
                                <option value="" disabled selected>Ordoneaza dupa:</option>
                                <c:forEach var="orderByOption" items="${orderByOptions}">
                                    <option value="${orderByOption}">${orderByOption}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <a class="btn btn-lg btn-primary" href="${addHole}" role="button">Adauga Groapa</a>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Data </th>
                                    <th>Adresa</th>
                                    <th>Zona</th>
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
                                    <td>${hole.date}</td>
                                    <c:choose>
                                        <c:when test="${hole.holeNrAtSameAddress != null && hole.holeNrAtSameAddress != 0}">
                                            <td>${hole.street} ${hole.streetNr} ${hole.locality} ${hole.district} Groapa ${hole.holeNrAtSameAddress}</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>${hole.street} ${hole.streetNr} ${hole.locality} ${hole.district}</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td>${hole.holeArea}</td>
                                    <c:choose>
                                        <c:when test="${hole.phase == 'SAPATURA'}">
                                            <td style="text-align: center; background-color: #FF3E3E; color: white">${hole.phase}</td>
                                        </c:when>
                                        <c:when test="${hole.phase == 'UMPLERE'}">
                                            <td style="text-align: center; background-color: orange; color: white">${hole.phase}</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td >${hole.phase}</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td>${hole.holeLength}</td>
                                    <td>${hole.holeWidth}</td>
                                    <td>${hole.holeDepth}</td>
                                    <td><fmt:formatNumber type = "number" maxFractionDigits="2" value="${hole.holeVolume}"/></td>
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
        $(function() {
            $('#orderHoles').change(function() {
                var val = $("#orderHoles option:selected").text();

                if (${pgNr != 0}){
                    window.location.href = "http://localhost:8080/Myapp1_war/backoffice/holes?pgNr=${pgNr}&orderBy=" + val;
                } else {
                    window.location.href = "http://localhost:8080/Myapp1_war/backoffice/holes?orderBy=" + val;
                }
            });
        });

        function redirectToHDP(holeid) {
            window.location.href = "http://localhost:8080/Myapp1_war/backoffice/holes/" + holeid;
        }
    </script>
    </body>
</html>