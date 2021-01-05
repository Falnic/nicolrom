<%@ tag body-content="empty" %>
<%@ attribute name="team" required="true" type="com.nicolrom.entities.Team" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-lg-4" id="div-SOFER">
        <h4>Sofer</h4>
        <table class="table" id="table-SOFER">
            <tbody>
            <c:set var="flag" value="${false}"/>
            <c:forEach items="${team.teamDeploys}" var="teamDeploy">
                <c:if test="${teamDeploy.employee.position.name().equals('SOFER')}">
                    <c:set var="flag" value="${true}"/>
                    <tr>
                        <td>${teamDeploy.employee.name}</td>
                        <td>${teamDeploy.machinery.licensePlate}</td>
                        <td>${teamDeploy.machinery.capacity} MC</td>
                    </tr>
                </c:if>
            </c:forEach>
            <c:if test="${flag == false}">
                <tr>
                    <td>Nu s-a adaugat</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
    <div class="col-lg-4" id="div-MECANIC">
        <h4>Mecanic</h4>
        <table class="table" id="table-MECANIC">
            <tbody>
            <c:set var="flag" value="${false}"/>
            <c:forEach items="${team.teamDeploys}" var="teamDeploy">
                <c:if test="${teamDeploy.employee.position.name().equals('MECANIC')}">
                    <c:set var="flag" value="${true}"/>
                    <tr>
                        <td>${teamDeploy.employee.name}</td>
<%--                        <td>${teamDeploy.machinery.licensePlate}</td>--%>
                    </tr>
                </c:if>
            </c:forEach>
            <c:if test="${flag == false}">
                <tr>
                    <td>Nu s-a adaugat</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
    <div class="col-lg-4" id="div-NECALIFICAT">
        <h4>Necalificat</h4>
        <table class="table" id="table-NECALIFICAT">
            <tbody>
            <c:set var="flag" value="${false}"/>
            <c:forEach items="${team.teamDeploys}" var="teamDeploy">
                <c:if test="${teamDeploy.employee.position.name().equals('NECALIFICAT')}">
                    <c:set var="flag" value="${true}"/>
                    <tr>
                        <td>${teamDeploy.employee.name}</td>
                    </tr>
                </c:if>
            </c:forEach>
            <c:if test="${flag == false}">
                <tr>
                    <td>Nu s-a adaugat</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>