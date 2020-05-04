<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../../bootstrapImports.jsp"/>
<jsp:include page="../../backofficeHeader.jsp"/>

<div class="container">
    <div class="row">
        <h1>Adauga Groapa </h1>
    </div>
    <form method="post" autocomplete="off">
        <div class="row">
            <div class="col-lg-3">
                <table>
                    <tr>
                        <td><label class="control-label" for="holeDatePicker">Data</label></td>
                        <td><input type="text" class="form-control" name="holeDate" id="holeDatePicker" placeholder="mm-dd-yy" autocomplete="false"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="street">Strada</label></td>
                        <td><input type="text" class="form-control" name="street" id="street" autocomplete="false"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="streetNr">Numar</label></td>
                        <td><input type="text" class="form-control" name="streetNr" id="streetNr" autocomplete="false"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="locality">Localitate</label></td>
                        <td><input type="text" class="form-control" name="locality" id="locality" autocomplete="false"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="district">Judet</label></td>
                        <td><input type="text" class="form-control" name="district" id="district" autocomplete="false"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="selectArea">Zona</label></td>
                        <td>
                            <select name="area" id="selectArea"
                                    class="browser-default custom-select">
                                <c:forEach var="area" items="${areas}">
                                    <option value="${area.areaId}">${area.type}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="holeLenght">Lungime</label></td>
                        <td><input type="number" step="0.01" min="0" class="form-control" name="holeLenght" id="holeLenght" autocomplete="false"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="holeWidth">Latime</label></td>
                        <td><input type="number" step="0.01" min="0" class="form-control" name="holeWidth" id="holeWidth" autocomplete="false"/>
                    </tr>
                    <tr>
                        <td><label class="control-label" for="holeDepth">Adancime</label></td>
                        <td><input type="number" step="0.01" min="0" class="form-control" name="holeDepth" id="holeDepth" autocomplete="false"/>
                    </tr>
                </table>
            </div>
            <div class="col-lg-9">
                <div class="row">
                    <c:forEach items="${employeesMap}" var="employeesMapItem">
                        <div class="col-lg-3">
                            <h4>${employeesMapItem.key.name()}</h4>
                            <table class="table">
                                <c:forEach var="employee" items="${employeesMapItem.value}">
                                    <tr>
                                        <td>
                                            <input id="employeeCheck${employee.idEmployee}" type="checkbox" name="employees" value="${employee.idEmployee}">
                                            <label for="employeeCheck${employee.idEmployee}">${employee.name}</label>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <input type="submit" class="btn-lg btn-primary pull-right" value="Adauga"/>
    </form>
</div>
<script>
    $( function() {
        $( "#holeDatePicker" ).datepicker();
    } );
</script>