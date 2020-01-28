<%@ page import="com.nicolrom.enums.PhaseEnum" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Del Gaz Grid Holes</title>
</head>
<body>
<%--<jsp:include page="homePageHeader.jsp" />--%>


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
<%--            <tr>--%>
<%--                <input type="checkbox" id="selectall"/> Select All--%>
<%--            </tr>--%>
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
            <input type="submit" value="Adauga groapa" class="btn btn-default">
        </a>
<%--        <input type="submit" id="deleteButton" value="Delete selected" class="btn btn-default"/>--%>

        </c:if>
<%--        <c:url var="addProduct" value="/backoffice/product/add"/>--%>
<%--        <c:url var="exportProductas" value="/backoffice/product/export"/>--%>
<%--        <c:url var="importProductas" value="/backoffice/product/import"/>--%>



<%--        <a href="${addProduct}" methods="GET"><input type="submit" value="Create new hole" class="btn btn-default"></a>--%>

<%--        <input type= "submit" id="exportButton" value="Export Selected Products" class="btn btn-default"/>--%>


<%--        <p id="mesageExport" style="color:white; margin-left: 283px">message</p>--%>

<%--        <br> <br>--%>
<%--        <c:if test="${result == true}">--%>
<%--            <h3>Product deleted!</h3>--%>
<%--        </c:if>--%>
    </div>
</div>

<%--<div class="row">--%>
<%--    <div class="col-md-3 col-md-offset-3">--%>
<%--        <form method="post" action="${importProductas}" enctype="multipart/form-data">--%>
<%--            <table border="0">--%>
<%--                <tr>--%>
<%--                    <td>Add file: </td>--%>
<%--                    <td><input type="file" name="filename" size="50" accept=".csv"/></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <br>--%>
<%--                    <td><input type="submit" id="importButton" value="Upload" class="btn btn-success"/></td>--%>
<%--                </tr>--%>
<%--            </table>--%>
<%--        </form>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<c:url var="jqueyUrl" value="/resources/jquery-1.8.3.js"/>--%>
<%--<script type="text/javascript" src="${jqueyUrl}"></script>--%>

<%--<c:url var="jsUrl" value="/resources/js/myscript.js"/>--%>
<%--<script type="text/javascript" src="${jsUrl}"></script>--%>

</body>
</html>