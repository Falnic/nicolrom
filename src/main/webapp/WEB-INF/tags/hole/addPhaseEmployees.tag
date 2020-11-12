<%@ tag body-content="empty" %>
<%@ attribute name="positionEmployeesMap_SOFER" required="true" type="java.util.ArrayList" %>
<%@ attribute name="positionEmployeesMap_MECANIC" required="true" type="java.util.ArrayList" %>
<%@ attribute name="positionEmployeesMap_NECALIFICAT" required="true" type="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
            <div class="col-lg-4" id="soferDiv">
                <h4><label class="control-label" for="selectEmployees-SOFER">SOFER</label></h4>
                <table class="table" id="SOFER-table">
                    <tr>
                        <td>
                            <select name="employees_SOFER" id="selectEmployees-SOFER" class="selectpicker" multiple
                                    data-live-search="true" data-actions-box="true" data-selected-text-format="count">
                                <c:forEach var="employee" items="${positionEmployeesMap_SOFER}">
                                    <option value="${employee.idEmployee}">${employee.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="col-lg-4" id="mecanicDiv">
                <h4><label class="control-label" for="selectEmployees-MECANIC">MECANIC</label></h4>
                <table class="table" id="MECANIC-table">
                    <tr>
                        <td>
                            <select name="employees_MECANIC" id="selectEmployees-MECANIC" class="selectpicker" multiple
                                    data-live-search="true" data-actions-box="true" data-selected-text-format="count">
                                <c:forEach var="employee" items="${positionEmployeesMap_MECANIC}">
                                    <option value="${employee.idEmployee}">${employee.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="col-lg-4" id="necalificatDiv">
                <h4><label class="control-label" for="selectEmployees-NECALIFICAT">NECALIFICAT</label></h4>
                <table class="table" id="NECALIFICAT-table">
                    <tr>
                        <td>
                            <select name="employees_NECALIFICAT" id="selectEmployees-NECALIFICAT" class="selectpicker" multiple
                                    data-live-search="true" data-actions-box="true" data-selected-text-format="count">
                                <c:forEach var="employee" items="${positionEmployeesMap_NECALIFICAT}">
                                    <option value="${employee.idEmployee}">${employee.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
