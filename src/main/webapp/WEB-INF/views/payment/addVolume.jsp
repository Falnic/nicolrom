<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="../bootstrapImports.jsp"/>
    <jsp:include page="../backofficeHeader.jsp"/>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/payment/addVolume.css"/>">

    <title>Adauga Volum</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-lg-12 text-center">
            <h1>Adauga Volum</h1>
        </div>
    </div>
    <form method="post" name="addVolumeForm">
        <div class="row">
            <div class="col-lg-2">
                <label class="control-label" for="volumeNr">Nr Volum</label>
            </div>
            <div class="col-lg-3">
                <input type="text" class="form-control" name="volumeNr" id="volumeNr">
            </div>
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label class="control-label" for="contractNr">Nr Contract</label>
            </div>
            <div class="col-lg-3">
                <select name="contract" id="contractNr" class="form-control">
                    <option value="4600021442" selected>4600021442</option>
                    <option value="5834578524">5834578524</option>
                </select>
            </div>
            <div class="col-lg-1"></div>
        </div>
        <div class="row">
            <div class="col-lg-2">
                <label class="control-label" for="regNr">Nr Reg</label>
            </div>
            <div class="col-lg-3">
                <input type="text" class="form-control" name="regNr" id="regNr">
            </div>
            <div class="col-lg-1"></div>
            <div class="col-lg-2">
                <label class="control-label" for="district">District</label>
            </div>
            <div class="col-lg-3">
                <select name="district" id="district" class="form-control">
                    <option value="D1" selected>D1</option>
                    <option value="D2">D2</option>
                </select>
            </div>
            <div class="col-lg-1"></div>
        </div>
        <div class="form-group row dateSearch">
            <label for="startDateSearch" class="col-lg-2 col-form-label">Inceput</label>
            <div class="col-lg-3">
                <input class="form-control" type="date" id="startDateSearch" name="startDate">
            </div>
            <div class="col-lg-1"></div>
            <label for="endDateSearch" class="col-lg-2 col-form-label">Sfarsit</label>
            <div class="col-lg-3">
                <input class="form-control" type="date" id="endDateSearch" name="endDate">
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <table class="table">
                    <thead>
                    <tr>
                        <th>
                            <input type="checkbox" id="select-all">
                        </th>
                        <th>Data</th>
                        <th>Adresa</th>
                        <th>District</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <input type="checkbox" id="select-Hole-1" name="holes">
                        </td>
                        <td>03.03.2020</td>
                        <td>Str Lalelelor nr 28 Cluj-Napoca Cluj</td>
                        <td>D2</td>
                    </tr>
                    <tr>
                        <td>
                            <input type="checkbox" id="select-Hole-2" name="holes">
                        </td>
                        <td>04.03.2020</td>
                        <td>Str Einstain nr 2 Cluj-Napoca Cluj</td>
                        <td>D1</td>
                    </tr>
                    <tr>
                        <td>
                            <input type="checkbox" id="select-Hole-3" name="holes">
                        </td>
                        <td>29.03.2020</td>
                        <td>Str Eminescu nr 13 Cluj-Napoca Cluj</td>
                        <td>D1</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8"></div>
            <div class="col-lg-2">
                <input type="submit" class="btn btn-primary btn-lg " value="Salveaza"/>
            </div>
            <div class="col-lg-2">
                <a class="btn btn-lg btn-danger" id="cancelBtn" href="<c:url value="/backoffice/volumes"/>" role="button">Anuleaza</a>
            </div>
        </div>
    </form>
</div>

</body>
</html>
