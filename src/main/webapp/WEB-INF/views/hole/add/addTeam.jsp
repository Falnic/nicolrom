<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<jsp:include page="../../bootstrapImports.jsp"/>

<c:url var="addTeamToHole" value="/backoffice/holes/add-team"/>

<%--        Employee tables--%>
<div class="container">
    <div class="row text-center">
        <h3>Echipa</h3>
    </div>
    <form:form commandName="team" action="${addTeamToHole}" method="post">
        <div class="row">
            <c:forEach items="${employeesMap}" var="employeesMapItem">
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
                                        <form:checkbox name="employees" path="employees" value="${employee.idEmployee}" label="${employee.name}"/>
<%--                                        <input type="checkbox" name="employees" id="idEmployee" value="${employee.idEmployee}">--%>
<%--                                        <label for="idEmployee">${employee.name}</label>--%>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:forEach>
        </div>
        <button type="submit" id="idSubmitButton" class="btn-lg btn-primary pull-right">Next</button>
    </form:form>
</div>
