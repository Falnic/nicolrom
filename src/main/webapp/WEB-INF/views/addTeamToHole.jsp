<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<jsp:include page="bootstrapImports.jsp"/>

<c:url var="addTeamToHole" value="/backoffice/holes/addTeamToHole"/>

<%--        Employee tables--%>
<div class="container">
    <div class="row text-center">
        <h3>Echipa</h3>
    </div>
    <form:form action="${addTeamToHole}" method="post">
        <c:forEach items="${employeesMap}" var="employeesMapItem">
            <div class="row">
                <div class="col-md-4">
                    <h4>${employeesMapItem.key.name()}</h4>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Nume Angajat</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="employee" items="${employeesMapItem.value}">
                            <tr>
                                <td>
                                    <input type="checkbox" name="employee" id="idEmployee" value="${employee.idEmployee}">
<%--                                    <input type="checkbox" name="employee" id="idEmployee" value="${employee}">--%>
                                    <label for="idEmployee">${employee.name}</label>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </c:forEach>
        <button type="submit" class="btn-lg btn-primary pull-right">Submit</button>
<%--        <button type="submit" class="btn-lg btn-primary pull-right" id="idAddEmployeesToTeam">Submit</button>--%>
    </form:form>
</div>

<%--        <jsp:include page="../tags/addEmployeesToTeam.jsp"/>--%>
<%--        <tag:addEmployeesToTeam addTeamToHole="${addTeamToHole}" allEmployees="${employeesMapItem.value}"--%>
<%--            position="${employeesMapItem.key.name()}"/>--%>

<c:url var="jsUrl" value="/resources/js/addHole.js"/>
<script type="text/javascript" src="${jsUrl}"></script>
