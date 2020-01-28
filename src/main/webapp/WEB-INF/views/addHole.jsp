<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Adauga Groapa </h1>

<form:form commandName="hole" method="post">
    <table>
        <tr>
            <td>Data:</td>
            <td><form:input path="date" /></td>
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
            <td>Etapa:</td>
            <td>
                <select name="phaseOrdinal" id="dropDown">
                    <c:forEach var="phase" items="${allPhases}">
                        <option value="${phase.ordinal()}">${phase.name()}</option>
                    </c:forEach>
                </select>
            </td>
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
        <tr>
            <td colspan="2">
                <input type="submit" value="Save Changes" />
            </td>
        </tr>
    </table>
</form:form>
