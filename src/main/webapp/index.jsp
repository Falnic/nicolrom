<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

  <c:url var="cssUrlHead" value="/resources/style/style.css"/>
  <link rel="stylesheet" type="text/css" href="${cssUrlHead}">

  <c:url var="bkgURL2" value="/resources/pictures/Background2.jpg"/>

  <title>Nicol Rom Web App</title>
</head>
<body style="background-image: url('${bkgURL2}'); background-size: 100%">
<jsp:include page="WEB-INF/views/homePageHeader.jsp"/>
<div class="col-md-4 col-md-offset-4">
  <h1>Nicol Rom </h1>
</div>
<jsp:include page="WEB-INF/views/homePageFooter.jsp"/>
</body>
</html>