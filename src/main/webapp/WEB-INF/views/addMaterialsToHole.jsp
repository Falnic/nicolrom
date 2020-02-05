<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<c:url var="addMaterialsToHole" value="/backoffice/holes/addMaterialsToHole"/>

<%--            Material table--%>
<div class="container">
    <div class="row text-center">
        <h3>Materiale</h3>
    </div>
    <form:form commandName="phase_material" method="post" action="${addMaterialsToHole}">

    </form:form>
    <div class="row">
        <table>
            <thead>
            <tr>
                <th>Nume</th>
                <th>Cantitate</th>
                <th>UM</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="material" items="${allMaterials}">
                <tr>
                    <td><input type="hidden" name="materialId" value="${material.materialId}">${material.name}</td>
                    <td><input type="number" name="quantityInput"></td>
                    <td>mc</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>