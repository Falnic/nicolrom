<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

    <h1>Adauga Groapa </h1>

    <form:form commandName="hole" method="post">
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
<%--        Phase Table--%>
        <table>
            <tr>
                <td><h4>Etapa Groapa</h4></td>
            </tr>
            <tr>
                <td>Tip</td>
                <td>
                    <select name="phaseOrdinal" id="phaseTypeDropDown">
                        <c:forEach var="phase" items="${allPhases}">
                            <option value="${phase.ordinal()}">${phase.name()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td>Data:</td>
    <%--            <td><form:input path="phases.phaseDate" id="phaseDatePicker" /></td>--%>
                <td><input name="phaseDatePicker" id="phaseDatePicker"></td>
            </tr>
        </table>
<%--        Employee tables--%>
        <div class="text-center">
            <h3>Echipa</h3>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <h4>Soferi Bascule</h4>
                    <table>
                        <thead>
                        <tr>
                            <th></th>
                            <th>Nume</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="employee" items="${allEmployeesSofer}">
                                <tr>
                                    <td><input type="checkbox" value=${employee.idEmployee}></td>
                                    <td>${employee.name}</td>
                                </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="col-md-4">
                    <h4>Mecanici</h4>
                    <table>
                        <thead>
                        <tr>
                            <th></th>
                            <th>Nume</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="employee" items="${allEmployeesMecanic}">
                                <tr>
                                    <td><input type="checkbox" value=${employee.idEmployee}></td>
                                    <td>${employee.name}</td>
                                </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="col-md-4">
                    <h4>Necalificati</h4>
                    <table>
                        <thead>
                        <tr>
                            <th></th>
                            <th>Nume</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="employee" items="${allEmployeesNecalificat}">
                                <tr>
                                    <td><input type="checkbox" value=${employee.idEmployee}></td>
                                    <td>${employee.name}</td>
                                </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="text-center">
            <h3>Materiale</h3>
        </div>
        <div class="container">
            <div class="row">
                <table>
                    <thead>
                        <tr>
                            <th>Nume</th>
                            <th>Cantitate</th>
                            <th>UM</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="material" items="${allMaterials}">
                            <tr>
                                <td>${material.name}</td>
                                <td><input type="number" name="quantityInput"></td>
                                <td>mc</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <input type="submit" value="Salveaza" />
    </form:form>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $( function() {
        $( "#holeDatePicker" ).datepicker();
        $( "#phaseDatePicker" ).datepicker();
    } );
</script>