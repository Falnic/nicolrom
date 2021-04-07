<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="../bootstrapImports.jsp"/>
    <jsp:include page="../backofficeHeader.jsp"/>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/payment/viewContracts.css"/>">

    <title>Contract Nr ${contract.nr} / <fmt:formatDate pattern = "dd-MM-yyyy" value = "${contract.date}"/> </title>
</head>
<body>
<c:if test="${succes}">
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <strong>Modificarea s-a efectuat cu succes!</strong>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
</c:if>
<div class="container">
    <div class="row viewContractDiv">
        <div class="col-lg-12 text-center">
            <h1>Contract Nr ${contract.nr} / <fmt:formatDate pattern = "dd-MM-yyyy" value = "${contract.date}"/> </h1>
        </div>
    </div>
    <form method="post">
        <div class="row viewContractDiv">
            <div class="col-lg-12">
                <h3>1.Remedieri Defecte</h3>
            </div>
        </div>
        <div class="row viewContractDiv">
            <div class="col-lg-12">
                <table class="table table-bordered viewContractTable">
                    <thead>
                    <tr>
                        <th>Cod</th>
                        <th>Denumire Lucrari</th>
                        <th>UM</th>
                        <th>Pret</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="article" items="${contract.articles}">
                        <c:if test="${'MENTENANTA'.compareTo(article.type.name()) == 0}">
                            <tr id="article-${article.idArticle}">
                                <td>${article.code}</td>
                                <td>${article.description}</td>
                                <td>${article.unit}</td>
                                <td id="priceId-${article.idArticle}" ondblclick="createInput(${article.idArticle})">
                                    <fmt:formatNumber type = "number" maxFractionDigits="2" value="${article.price}"/> </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row updatePriceBtn" style="display: none">
            <div class="col-lg-11"></div>
            <div class="col-lg-1">
                <input type="submit" class="btn btn-warning btn-lg " value="Modifica"/>
            </div>
        </div>
        <div class="row viewContractDiv">
            <div class="col-lg-12">
                <h3>2.Investitii</h3>
            </div>
        </div>
        <div class="row viewContractDiv">
            <div class="col-lg-12">
                <table class="table table-bordered viewContractTable">
                    <thead>
                    <tr>
                        <th>Cod</th>
                        <th>Denumire Lucrari</th>
                        <th>UM</th>
                        <th>Pret</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="article" items="${contract.articles}">
                        <c:if test="${'INVESTITII'.compareTo(article.type.name()) == 0}">
                            <tr id="article-${article.idArticle}">
                                <td>${article.code}</td>
                                <td>${article.description}</td>
                                <td>${article.unit}</td>
                                <td id="priceId-${article.idArticle}" ondblclick="createInput(${article.idArticle})">
                                    <fmt:formatNumber type = "number" maxFractionDigits="2" value="${article.price}"/> </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row updatePriceBtn" style="display: none">
            <div class="col-lg-11"></div>
            <div class="col-lg-1">
                <input type="submit" class="btn btn-warning btn-lg " value="Modifica"/>
            </div>
        </div>
    </form>
</div>

<script src="<c:url value="/resources/js/payment/viewContract.js"/>"></script>

</body>
</html>
