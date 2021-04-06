<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <c:forEach var="contract" items="${contracts}">
            <div class="row" onclick="redirectToContract(${contract.idContract})">
                <div class="col-lg-12">
                    <c:choose>
                        <c:when test="${contract.inUse == true}">
                            <div class="card text-white bg-secondary viewContractsCard">
                                <div class="card-body">
                                    <h5 class="card-title">${contract.nr} / <fmt:formatDate pattern = "dd-MM-yyyy" value = "${contract.date}"/></h5>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="card viewContractsCard">
                                <div class="card-body">
                                    <h5 class="card-title">${contract.nr} / <fmt:formatDate pattern = "dd-MM-yyyy" value = "${contract.date}"/></h5>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </c:forEach>
    </div>
    <script src="<c:url value="/resources/js/payment/viewContracts.js"/>"></script>
</body>
</html>
