<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="../bootstrapImports.jsp"/>
    <jsp:include page="../backofficeHeader.jsp"/>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/payment/viewContracts.css"/>">

    <title>Contracts</title>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h1>Contracte</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="card text-white bg-secondary viewContractsCard">
                    <div class="card-body">
                        <h5 class="card-title">Nr 82/21-01-2021</h5>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="card viewContractsCard">
                    <div class="card-body">
                        <h5 class="card-title">Nr 52/16-08-2020</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
