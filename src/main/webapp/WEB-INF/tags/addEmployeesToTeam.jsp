<%--<%@attribute name="addTeamToHole" required="true" %>--%>
<%--<%@attribute name="allEmployees" required="true" %>--%>
<%--<%@attribute name="position" required="false" %>--%>

<form:form commandName="team_employee" action="${addTeamToHole}" method="post">
    <div class="row">
        <div class="col-md-4">
            <h4>${position}</h4>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th><form:label path="employee">Nume Angajat</form:label></th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="employee" items="${allEmployees}">
                        <tr>
                            <td>
                                    <%--                                    <form:checkbox path="employee" id="idEmployee" value="${employee.name}"--%>
                                    <input type="checkbox" id="idEmployee" value="${employee.idEmployee}">
                                    <label for="idEmployee">${employee.name}</label>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <button type="submit" class="btn-lg btn-primary pull-right" id="idAddEmployeesToTeam">Next</button>
</form:form>

<c:url var="jsUrl" value="/resources/js/addHole.js"/>
<script type="text/javascript" src="${jsUrl}"></script>