<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<c:url var="addPhaseToHole" value="/backoffice/holes/addPhaseToHole"/>

<%--        Phase Table--%>
<form:form commandName="phase" method="post" action="${addPhaseToHole}">
    <div class="container">
        <table>
            <h4>Etapa Groapa</h4>
            <tr>
                <td>Tip</td>
                <td>
                    <form:select path="phaseType">
                        <form:options items="${allPhaseTypes}"/>
                    </form:select>
                </td>
            </tr>

            <tr>
                <td>Data:</td>
                <td><form:input path="phaseDate" id="phaseDatePicker"/></td>
            </tr>
        </table>
        <button type="submit" class="btn-lg btn-primary pull-right">Next</button>
    </div>

</form:form>


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $( function() {
        $( "#phaseDatePicker" ).datepicker();
    } );
</script>