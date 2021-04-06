<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="../bootstrapImports.jsp"/>
    <jsp:include page="../backofficeHeader.jsp"/>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/payment/viewVolume.css"/>">

    <title>Volum 15 D2 22 iulie - 17 august</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-12 text-center">
            <h1>D2 Volum 15</h1>
        </div>
    </div>
    <dl>
        <div class="row">
            <dt class="col-lg-1">Nr Registru</dt>
            <dd class="col-lg-2">865/20.08.2020</dd>
            <dt class="col-lg-1">Nr Contract</dt>
            <dd class="col-lg-2">4600021442/V28</dd>
            <dt class="col-lg-1">Perioada</dt>
            <dd class="col-lg-2">22 iulie - 17 august</dd>
            <dt class="col-lg-1">District</dt>
            <dd class="col-lg-2">D2</dd>
        </div>
    </dl>
    <div class="row overflow-x">
        <div class="col-lg-12">
            <table class="table">
                <thead>
                <tr>
                    <th>Data</th>
                    <th>District</th>
                    <th>Adresa</th>
                    <th>Zona</th>
                    <th>L</th>
                    <th>l</th>
                    <th>h</th>
                    <th>SM1</th>
                    <th>SM2</th>
                    <th>SM3</th>
                    <th>SM4</th>
                    <th>SM5</th>
                    <th>SM6</th>
                    <th>SM7</th>
                    <th>SM8</th>
                    <th>SM9</th>
                    <th>SM10</th>
                    <th>SM11</th>
                    <th>Total</th>
                </tr>
                <tr>
                    <th colspan="4"></th>
                    <th>M</th>
                    <th>M</th>
                    <th>M</th>
                    <th>mp</th>
                    <th>km</th>
                    <th>mc</th>
                    <th>mp</th>
                    <th>mc</th>
                    <th>mc</th>
                    <th>mc</th>
                    <th>mc</th>
                    <th>km</th>
                    <th>ora</th>
                    <th>km</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>22.07.2020</td>
                    <td>D1</td>
                    <td>str.Tractoristilor nr.24 Cluj-Napoca Cluj</td>
                    <td>x</td>
                    <td>3.10</td>
                    <td>1.80</td>
                    <td>1.50</td>
                    <td>0.00</td>
                    <td>0.30</td>
                    <td>0.00</td>
                    <td>0.00</td>
                    <td>0.00</td>
                    <td>2.01</td>
                    <td>0.00</td>
                    <td>6.36</td>
                    <td>0.00</td>
                    <td>0.00</td>
                    <td>0.00</td>
                    <td>15.40</td>
                </tr>
                <tr>
                    <td>22.07.2020</td>
                    <td>D1</td>
                    <td>str.Tractoristilor nr.24 Cluj-Napoca Cluj</td>
                    <td>x</td>
                    <td>3.10</td>
                    <td>1.80</td>
                    <td>1.50</td>
                    <td>0.00</td>
                    <td>0.30</td>
                    <td>0.00</td>
                    <td>0.00</td>
                    <td>0.00</td>
                    <td>2.01</td>
                    <td>0.00</td>
                    <td>6.36</td>
                    <td>0.00</td>
                    <td>0.00</td>
                    <td>0.00</td>
                    <td>15.40</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-2">
            <a class="btn btn-success" href="#" role="button">Genereaza Raport</a>
        </div>
        <div class="col-lg-8"></div>
        <div class="col-lg-2">
            <a class="btn btn-warning left" href="<c:url value="/backoffice/volumes/1/update"/>" role="button">Modifica</a>
            <a class="btn btn-danger right" id="deleteBtn" href="#" role="button">Sterge</a>
        </div>
    </div>
</div>
</body>
</html>
