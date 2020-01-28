<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

  <c:url var="cssUrlHead" value="/resources/style/style.css"/>
  <link rel="stylesheet" type="text/css" href="${cssUrlHead}">

  <title>Nicol Rom Web App</title>
</head>
<body>
<jsp:include page="WEB-INF/views/homePageHeader.jsp"/>

<jsp:include page="WEB-INF/views/homePageFooter.jsp"/>
</body>
</html>