<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>


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