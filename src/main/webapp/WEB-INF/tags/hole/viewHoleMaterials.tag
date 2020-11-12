<%@ tag body-content="empty" %>
<%@ attribute name="phase" required="true" type="com.nicolrom.entities.Phase" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<h4>Materiale</h4>
<table class="table">
    <c:forEach var="materialNotice" items="${phase.materialNoticeSet}">
        <tr>
            <td>${materialNotice.material.name}</td>
            <td><fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${materialNotice.quantity}"/></td>
            <td>mc</td>
        </tr>
    </c:forEach>
</table>