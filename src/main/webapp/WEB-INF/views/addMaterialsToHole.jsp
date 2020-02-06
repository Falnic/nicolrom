<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<jsp:include page="bootstrapImports.jsp"/>
<c:url var="addMaterialsToHole" value="/backoffice/holes/addMaterialsToHole"/>

<%--            Material table--%>
<div class="container">
    <div class="row text-center">
        <h3>Materiale</h3>
    </div>
    <div class="row">
        <form:form action="${addMaterialsToHole}" method="post">
            <table class="table table-striped">
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
<%--                            <input type="hidden" name="material" value="${material}">--%>
                            <td><input type="hidden" name="materialId" value="${material.materialId}">${material.name}</td>
                            <td><input type="number" name="materialQuantity"></td>
                            <td>mc</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <button type="submit" class="btn-lg btn-primary pull-right">Submit</button>
        </form:form>
    </div>
</div>