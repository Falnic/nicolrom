<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="../bootstrapImports.jsp"/>

<html>
<head>
    <title>Del Gaz Grid Holes</title>
</head>
<body>
<%--<jsp:include page="homePageHeader.jsp" />--%>

<div class="container">
    <div class="row">
        <div class="col-md-2 col-md-offset-5">
            <c:if test="${not empty allHoles}">
            <h1>Gropi</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <table id="prodTable" class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Data </th>
                    <th>Strada</th>
                    <th>Numar</th>
                    <th>Localitate</th>
                    <th>Judet</th>
                    <th>Etapa</th>
                    <th>Lungime</th>
                    <th>Latime</th>
                    <th>Adancime</th>
                    <th>Suprafata</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="hole" items="${allHoles}">
                    <tr>
                        <td style="text-align: center">${hole.holeId}</td>
                        <td style="text-align: center">${hole.date}</td>
                        <td style="text-align: center">${hole.street}</td>
                        <td style="text-align: center">${hole.streetNr}</td>
                        <td style="text-align: center">${hole.locality}</td>
                        <td style="text-align: center">${hole.district}</td>
                        <td style="text-align: center">${hole.phase}</td>
                        <td style="text-align: center">${hole.holeLength}</td>
                        <td style="text-align: center">${hole.holeWidth}</td>
                        <td style="text-align: center">${hole.holeDepth}</td>
                        <td style="text-align: center">${hole.holeSurface}</td>
    <%--                    <td><a href="<c:url value='/backoffice/hole/edit/${hole.holeId}' />" ><input type="submit" value="Edit" class="btn btn-primary"></a></td>--%>
    <%--                    <td><a href="<c:url value='/backoffice/hole/delete?holeId=${hole.holeId}'/>" methods="GET"><input type="submit" value="Delete" id="delete"+${hole.holeId} class="btn btn-danger"></a></td>--%>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

            <c:url var="addHole" value="/backoffice/holes/add"/>

            <a href="${addHole}" methods="GET">
<%--                <input type="submit" value="Adauga groapa" class="btn btn-default">--%>
                <button type="submit" class="btn-lg btn-primary pull-right">Adauga groapa</button>
            </a>
    
            </c:if>
        </div>
    </div>
</div>
</body>
</html>