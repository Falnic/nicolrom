<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="bootstrapImports.jsp"/>
<jsp:include page="backofficeHeader.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-lg-4 offset-lg-4">
                <div class="row">
                    <h4>Login</h4>
                </div>

            <c:if test="${not empty error}">
                <div>
                    Your login was unsuccessful. <br/>
                    Caused: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message }
                </div>
            </c:if>

            <form class="form-group" method="post" name="f" action="<c:url value='j_spring_security_check'/>">
                <div class="row">
                    <label for="username_id">Username</label>
                    <input type="text" id="username_id" name="j_username" class="form-control">
                </div>
                <div class="row">
                    <label for="password-id">Password</label>
                    <input type="password" id="password-id" name="j_password" class="form-control">
                </div>
                <div class="row">
                    <input class="btn btn-primary" name="Submit" type="Submit" value="Submit">
                </div>
            </form>
        </div>
    </div>
</div>