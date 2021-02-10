<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="home" value="/login"/>

<% session.invalidate(); %>
You are now logged out!!

<a href="${home}">Home</a>