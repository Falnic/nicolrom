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
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h1>Situatie defecte DELGAZ grid SA</h1>
                    </div>
                </div>
                <div class="row form-group">
                    <label for="orderHoles" class="col-lg-2 col-form-label">Ordoneaza dupa:</label>
                    <div class="col-lg-3">
                        <select name="orderHoles" id="orderHoles" class="browser-default custom-select">
                            <c:forEach var="orderByOption" items="${orderByOptions}">
                                <c:choose>
                                    <c:when test="${orderBy.equals(orderByOption)}">
                                        <option value="${orderByOption}" selected>${orderByOption}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${orderByOption}">${orderByOption}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-lg-2"></div>
                    <div class="input-group col-lg-5">
                        <input type="text" name="searchValue" id="searchByAddress" class="form-control" placeholder="Cauta dupa adresa" aria-label="Cauta dupa adresa" aria-describedby="basic-addon2">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" type="button" id="searchButton">Cauta</button>
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
                                    <th>District</th>
                                    <th>Etapa</th>
                                    <th>Lungime</th>
                                    <th>Latime</th>
                                    <th>Adancime</th>
                                    <th>Volum</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:choose>
                                    <c:when test="${not empty allHoles}">
                                        <c:forEach var="hole" items="${allHoles}">
                                            <tr ondblclick="redirectToHDP(${hole.holeId})">
                                                <td>${hole.date}</td>
                                                <c:choose>
                                                    <c:when test="${hole.holeNrAtSameAddress != null && hole.holeNrAtSameAddress != 0}">
                                                        <td>${hole.street} ${hole.streetNr} ${hole.locality} ${hole.county} Groapa ${hole.holeNrAtSameAddress}</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td>${hole.street} ${hole.streetNr} ${hole.locality} ${hole.county}</td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <td style="text-align: center">${hole.district}</td>
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
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td><h3>Nu s-au gasit rezultatele cautarii</h3></td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>
                    </div>
                </div>
                <c:if test="${lastPg != 0}">
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
                                                   href="<c:url value='/backoffice/holes?pgNr=${pgNr - 1}&orderBy=${orderBy}'/>">Pagina Anterioara
                                                </a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:forEach var="pageItem" begin="0" end="${lastPg - 1}">
                                        <c:choose>
                                            <c:when test="${pageItem == pgNr}">
                                                <li class="page-item active">
                                                    <a class="page-link"
                                                       href="<c:url value='/backoffice/holes?pgNr=${pageItem}&orderBy=${orderBy}'/>">${pageItem + 1}
                                                    </a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item">
                                                    <a class="page-link"
                                                       href="<c:url value='/backoffice/holes?pgNr=${pageItem}&orderBy=${orderBy}'/>">${pageItem + 1}
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
                                                   href="<c:url value='/backoffice/holes?pgNr=${pgNr + 1}&orderBy=${orderBy}'/>">Urmatoarea Pagina
                                                </a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </c:if>
            </div>

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

            $('#searchByAddress').keypress(function(event){
                var searchValue = $('#searchByAddress').val();
                if (searchValue && searchValue !== ""){
                    var keycode = (event.keyCode ? event.keyCode : event.which);
                    if(keycode == '13'){
                        window.location.href = "http://localhost:8080/Myapp1_war/backoffice/holes?searchValue=" + searchValue;
                    }
                }
            });

            $("#searchButton").click(function (){
                var searchValue = $('#searchByAddress').val();
                if (searchValue && searchValue !== ""){
                    window.location.href = "http://localhost:8080/Myapp1_war/backoffice/holes?searchValue=" + searchValue;
                }
            });
        });

        function redirectToHDP(holeid) {
            window.location.href = "http://localhost:8080/Myapp1_war/backoffice/holes/" + holeid;
        }
    </script>
    </body>
</html>