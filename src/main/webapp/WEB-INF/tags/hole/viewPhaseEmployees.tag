<%@ tag body-content="empty" %>
<%@ attribute name="phase" required="true" type="com.nicolrom.entities.Phase" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-lg-12">
        <div class="row">
            <div class="col-lg-4" id="div-SOFER">
                <h5>Sofer</h5>
                <table class="table" id="table-SOFER">
                    <tbody>
                    <c:set var="flag" value="${false}"/>
                    <c:forEach items="${phase.team.employees}" var="employee">
                        <c:if test="${employee.position.name().equals('SOFER')}">
                            <c:set var="flag" value="${true}"/>
                            <tr>
                                <td>${employee.name}</td>
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
                <h5>Mecanic</h5>
                <table class="table" id="table-MECANIC">
                    <tbody>
                    <c:set var="flag" value="${false}"/>
                    <c:forEach items="${phase.team.employees}" var="employee">
                        <c:if test="${employee.position.name().equals('MECANIC')}">
                            <c:set var="flag" value="${true}"/>
                            <tr>
                                <td>${employee.name}</td>
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
                <h5>Necalificat</h5>
                <table class="table" id="table-NECALIFICAT">
                    <tbody>
                    <c:set var="flag" value="${false}"/>
                    <c:forEach items="${phase.team.employees}" var="employee">
                        <c:if test="${employee.position.name().equals('NECALIFICAT')}">
                            <c:set var="flag" value="${true}"/>
                            <tr>
                                <td>${employee.name}</td>
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
    </div>
</div>