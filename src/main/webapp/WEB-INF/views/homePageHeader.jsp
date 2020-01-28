<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="bootstrapImports.jsp" />

<c:url var="homeURL" value="/"/>
<c:url var="logoUrl" value="/resources/pictures/logo.png"/>

<div class="row header">
    <div class="col-md-6">
        <div class="row">
            <div class="col-md-6">
                <a href="${homeURL}">
                    <img id="nicolromLogo"  src="${logoUrl}" alt="Nicol Rom" ismap>
                </a>
            </div>
        </div>
    </div>
</div>