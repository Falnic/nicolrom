<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<form:form commandName="hole" method="post">
    <div class="container">
        <h1>Adauga Groapa </h1>
            <%--        hole Table--%>
        <table>
            <tr>
                <td>Data:</td>
                <td><form:input path="date" id="holeDatePicker" /></td>
            </tr>
            <tr>
                <td>Strada:</td>
                <td><form:input path="street" /></td>
            </tr>
            <tr>
                <td>Numar:</td>
                <td><form:input path="streetNr" /></td>
            </tr>
            <tr>
                <td>Localitate:</td>
                <td><form:input path="locality" /></td>
            </tr>
            <tr>
                <td>Judet:</td>
                <td><form:input path="district" /></td>
            </tr>
            <tr>
                <td>Lungime:</td>
                <td><form:input path="holeLength" /></td>
            </tr>
            <tr>
                <td>Latime:</td>
                <td><form:input path="holeWidth" /></td>
            </tr>
            <tr>
                <td>Adancime:</td>
                <td><form:input path="holeDepth" /></td>
            </tr>
        </table>
        <button type="submit" class="btn-lg btn-primary pull-right">Salveaza</button>
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