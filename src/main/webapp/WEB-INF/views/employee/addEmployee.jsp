<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<div class="container">
    <div class="row">
        <h3>Adauga angajat</h3>
    </div>
    <div class="row">
        <form:form action="add" method="post" modelAttribute="employee">
            <table>
                <tr>
                    <td><form:label path="name">Nume </form:label></td>
                    <td><form:input path="name"/></td>
                </tr>
                <tr>
                    <td><form:label path="position">Pozitie</form:label></td>
                    <td><form:select path="position" items="${positions}"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Adauga"></td>
                </tr>
            </table>
        </form:form>
    </div>
</div>

