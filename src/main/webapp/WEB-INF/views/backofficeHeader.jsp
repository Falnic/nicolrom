<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="logout" value="/logout"/>
<c:url var="backofficeHome" value="/backoffice/holes"/>
<c:url var="contractsUrl" value="/backoffice/contracts"/>
<c:url var="viewVolumesUrl" value="/backoffice/volumes"/>

<%@ page session="false" %>
<html>
    <head>
        <title>Nicol Rom Backoffice</title>
    </head>

    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <nav class="navbar navbar-expand-lg navbar-light bg-light">
                        <a class="navbar-brand" href="${backofficeHome}">Nicol Rom Backoffice</a>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse">
                            <ul class="navbar-nav col-lg-9">
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            Situatii de plata
                                        </a>
                                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="${viewVolumesUrl}">Volume</a>
                                            <a class="dropdown-item" href="${contractsUrl}">Contracte</a>
                                        </div>
                                    </li>
                                </sec:authorize>
                            </ul>
                            <ul class="navbar-nav col-lg-3">
                                <li class="nav-item active">
                                    <a class="nav-link" href="#">
                                        User: <sec:authentication property="name"/>
                                        <span class="sr-only">(current)</span>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="<c:url value='/logout'/>">Log out</a>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </body>
</html>


