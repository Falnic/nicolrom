<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="../bootstrapImports.jsp"/>
    <jsp:include page="../backofficeHeader.jsp"/>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/payment/viewContracts.css"/>">

    <title>Contract Nr 82/21-01-2021</title>
</head>
<body>

<div class="container">
    <div class="row viewContractDiv">
        <div class="col-lg-12 text-center">
            <h1>Contract Nr 4600214/V28/1931/21-01-2021</h1>
        </div>
    </div>
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
                <tr>
                    <td>SM1</td>
                    <td>Sapaturi executate in zona verde, pamant si macadam</td>
                    <td>MC</td>
                    <td id="priceId-SM1">89.00</td>
                </tr>
                <tr>
                    <td>SM2</td>
                    <td>Desfacere carosabil cu pavaj din piatra cubica, pavele, dale din beton, borduri</td>
                    <td>MP</td>
                    <td id="priceId-SM2">26.00</td>
                </tr>
                <tr>
                    <td>SM3</td>
                    <td>Decapat carosabil din asfalt, binder, spargeri de betoan, beton stabilizant, zgura si orice combinatie dintre acestea</td>
                    <td>MC</td>
                    <td id="priceId-SM3">138.00</td>
                </tr>
                <tr>
                    <td>SM4</td>
                    <td>Asternere strat de nisip</td>
                    <td>MC</td>
                    <td id="priceId-SM4">132.00</td>
                </tr>
                <tr>
                    <td>SM5</td>
                    <td>Umplerea cu pamant</td>
                    <td>MC</td>
                    <td id="priceId-SM5">48.00</td>
                </tr>
                <tr>
                    <td>SM6</td>
                    <td>Umplerea cu balast, piatra sparta sau macadam</td>
                    <td>MC</td>
                    <td id="priceId-SM6">132.00</td>
                </tr>
                <tr>
                    <td>SM7</td>
                    <td>Sapatura la o adancime mai mare 1,5 m</td>
                    <td>MC</td>
                    <td id="priceId-SM7">45.00</td>
                </tr>
                <tr>
                    <td>SM8</td>
                    <td>Incarcarea materialelor excedentare sau a produselor de balastiera</td>
                    <td>MC</td>
                    <td id="priceId-SM8">28.00</td>
                </tr>
                <tr>
                    <td>SM9</td>
                    <td>Transportul materialelor excedentare  ≤  7 mc</td>
                    <td>KM</td>
                    <td id="priceId-SM9">149.00</td>
                </tr>
                <tr>
                    <td>SM10</td>
                    <td>Transportul materialelor excedentare  >  7 mc</td>
                    <td>KM</td>
                    <td id="priceId-SM10">178.00</td>
                </tr>
                <tr>
                    <td>SM11</td>
                    <td>Stationare utilaje</td>
                    <td>ORA</td>
                    <td id="priceId-SM11">60.00</td>
                </tr>
                <tr>
                    <td>SM12</td>
                    <td>Furnizare nisip spalat cu dimensiunile 0 - 4 mm - in balastiera</td>
                    <td>MC</td>
                    <td id="priceId-SM12">66.00</td>
                </tr>
                <tr>
                    <td>SM13</td>
                    <td>Furnizare nisip spalat cu dimensiunile 0 - 4 mm - in depozit</td>
                    <td>MC</td>
                    <td id="priceId-SM13">87.00</td>
                </tr>
                <tr>
                    <td>SM14</td>
                    <td>Furnizare balast cu dimensiunile 0 - 62 mm - in balastiera</td>
                    <td>MC</td>
                    <td id="priceId-SM14">47.00</td>
                </tr>
                <tr>
                    <td>SM15</td>
                    <td>Furnizare balast cu dimensiunile 0 - 62 mm - in depozit</td>
                    <td>MC</td>
                    <td id="priceId-SM15">67.00</td>
                </tr>
                <tr>
                    <td>SM16</td>
                    <td>Furnizare piatra sparta (concasata) cu dimensiunile 0 - 62 mm - in balastiera</td>
                    <td>MC</td>
                    <td id="priceId-SM16">58.00</td>
                </tr>
                <tr>
                    <td>SM17</td>
                    <td>Furnizare piatra sparta (concasata) cu dimensiunile 0 - 62 mm - in depozit</td>
                    <td>MC</td>
                    <td id="priceId-SM17">87.00</td>
                </tr>
                <tr>
                    <td>SM18</td>
                    <td>Transport produse de balastiera  ≤  7 mc</td>
                    <td>KM</td>
                    <td id="priceId-SM18">4.70</td>
                </tr>
                <tr>
                    <td>SM19</td>
                    <td>Transport produse de balastiera > 7 mc</td>
                    <td>KM</td>
                    <td id="priceId-SM19">5.50</td>
                </tr>
                <tr>
                    <td>SM20</td>
                    <td>Transport utilaje</td>
                    <td>KM</td>
                    <td id="priceId-SM20">5.50</td>
                </tr>
                <tr>
                    <td>SM21</td>
                    <td>Vidanjare</td>
                    <td>MC</td>
                    <td id="priceId-SM21">198.00</td>
                </tr>
                </tbody>
            </table>
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
                <tr>
                    <td>SI1</td>
                    <td>Sapaturi executate in zona verde, pamant si macadam</td>
                    <td>MC</td>
                    <td>106.00</td>
                </tr>
                <tr>
                    <td>SI2</td>
                    <td>Desfacere carosabil cu pavaj din piatra cubica, pavele, dale din beton, borduri</td>
                    <td>MP</td>
                    <td>26.00</td>
                </tr>
                <tr>
                    <td>SI3</td>
                    <td>Decapat carosabil din asfalt, binder, spargeri de betoan, beton stabilizant, zgura si orice combinatie dintre acestea</td>
                    <td>MC</td>
                    <td>147.00</td>
                </tr>
                <tr>
                    <td>SI4</td>
                    <td>Asternere strat de nisip</td>
                    <td>MC</td>
                    <td>128.00</td>
                </tr>
                <tr>
                    <td>SI5</td>
                    <td>Umplerea cu pamant</td>
                    <td>MC</td>
                    <td>48.00</td>
                </tr>
                <tr>
                    <td>SI6</td>
                    <td>Umplerea cu balast, piatra sparta sau macadam</td>
                    <td>MC</td>
                    <td>129.00</td>
                </tr>
                <tr>
                    <td>SI7</td>
                    <td>Sapatura la o adancime mai mare 1,5 m</td>
                    <td>MC</td>
                    <td>37.00</td>
                </tr>
                <tr>
                    <td>SI8</td>
                    <td>Incarcarea materialelor excedentare sau a produselor de balastiera</td>
                    <td>MC</td>
                    <td>28.00</td>
                </tr>
                <tr>
                    <td>SI9</td>
                    <td>Transportul materialelor excedentare  ≤  7 mc</td>
                    <td>KM</td>
                    <td>150.00</td>
                </tr>
                <tr>
                    <td>SI10</td>
                    <td>Transportul materialelor excedentare  >  7 mc</td>
                    <td>KM</td>
                    <td>184.00</td>
                </tr>
                <tr>
                    <td>SI11</td>
                    <td>Stationare utilaje</td>
                    <td>ORA</td>
                    <td>60.00</td>
                </tr>
                <tr>
                    <td>SI12</td>
                    <td>Furnizare nisip spalat cu dimensiunile 0 - 4 mm - in balastiera</td>
                    <td>MC</td>
                    <td>47.00</td>
                </tr>
                <tr>
                    <td>SI13</td>
                    <td>Furnizare nisip spalat cu dimensiunile 0 - 4 mm - in depozit</td>
                    <td>MC</td>
                    <td>78.00</td>
                </tr>
                <tr>
                    <td>SI14</td>
                    <td>Furnizare balast cu dimensiunile 0 - 62 mm - in balastiera</td>
                    <td>MC</td>
                    <td>37.00</td>
                </tr>
                <tr>
                    <td>SI15</td>
                    <td>Furnizare balast cu dimensiunile 0 - 62 mm - in depozit</td>
                    <td>MC</td>
                    <td>80.00</td>
                </tr>
                <tr>
                    <td>SI16</td>
                    <td>Furnizare piatra sparta (concasata) cu dimensiunile 0 - 62 mm - in balastiera</td>
                    <td>MC</td>
                    <td>58.00</td>
                </tr>
                <tr>
                    <td>SI17</td>
                    <td>Furnizare piatra sparta (concasata) cu dimensiunile 0 - 62 mm - in depozit</td>
                    <td>MC</td>
                    <td>90.00</td>
                </tr>
                <tr>
                    <td>SI18</td>
                    <td>Transport produse de balastiera  ≤  7 mc</td>
                    <td>KM</td>
                    <td>4.70</td>
                </tr>
                <tr>
                    <td>SI19</td>
                    <td>Transport produse de balastiera > 7 mc</td>
                    <td>KM</td>
                    <td>5.50</td>
                </tr>
                <tr>
                    <td>SI20</td>
                    <td>Transport utilaje</td>
                    <td>KM</td>
                    <td>5.50</td>
                </tr>
                <tr>
                    <td>SI21</td>
                    <td>Vidanjare</td>
                    <td>MC</td>
                    <td>180.00</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="<c:url value="/resources/js/payment/viewContract.js"/>"></script>

</body>
</html>
