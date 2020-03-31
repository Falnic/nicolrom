<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<form:form commandName="hole" method="post" autocomplete="false">
    <div class="container">
        <div class="row">
            <h1>Adauga Groapa </h1>
        </div>
        <div class="row">
            <div class="col-lg-3">
                <table>
                    <tr>
                        <td>Data:</td>
                        <td><form:input path="date" id="holeDatePicker" placeholder="mm-dd-yy" autocomplete="false"/>
                    </tr>
                    <tr>
                        <td>Strada:</td>
                        <td><form:input path="street" autocomplete="false"/>
                    </tr>
                    <tr>
                        <td>Numar:</td>
                        <td><form:input path="streetNr" autocomplete="false"/>
                    </tr>
                    <tr>
                        <td>Localitate:</td>
                        <td><form:input path="locality" autocomplete="false"/>
                    </tr>
                    <tr>
                        <td>Judet:</td>
                        <td><form:input path="district" autocomplete="false"/>
                    </tr>
                    <tr>
                        <td>Lungime:</td>
                        <td><form:input path="holeLength" />
                    </tr>
                    <tr>
                        <td>Latime:</td>
                        <td><form:input path="holeWidth" />
                    </tr>
                    <tr>
                        <td>Adancime:</td>
                        <td><form:input path="holeDepth" />
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
    </div>
</form:form>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $( function() {
        $( "#holeDatePicker" ).datepicker();
    } );
</script>