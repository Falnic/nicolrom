<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <jsp:include page="../bootstrapImports.jsp"/>
    <jsp:include page="../backofficeHeader.jsp"/>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style/payment/viewVolumes.css"/>">

    <title>Volume</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-lg-12 text-center">
            <h1>Volume</h1>
        </div>
    </div>
    <div class="form-group row">
        <div class="col-lg-2"></div>
        <label for="startDateSearch" class="col-lg-1 col-form-label">Inceput</label>
        <div class="col-lg-3">
            <input class="form-control" type="date" id="startDateSearch" name="startDate">
        </div>
        <label for="endDateSearch" class="col-lg-1 col-form-label">Sfarsit</label>
        <div class="col-lg-3">
            <input class="form-control" type="date" id="endDateSearch" name="endDate">
        </div>
    </div>
    <div class="row">
        <div class="col-lg-2">
            <a class="btn btn-lg btn-block btn-primary" id="addVolumeBtn" href="<c:url value="/backoffice/volumes/add"/>" role="button">Adauga</a>
        </div>
        <div class="col-lg-10">
            <div class="col-lg-12">
                <div class="card viewVolumesCard">
                    <div class="card-body">
                        <h5 class="card-title">D2 - Vol 15 22 iulie - 17 august</h5>
                    </div>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="card viewVolumesCard">
                    <div class="card-body">
                        <h5 class="card-title">D3 - Vol 15 22 iulie - 17 august</h5>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
