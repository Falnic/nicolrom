$(function () {
    $("#tabs").tabs();
});

$(function() {
    $( "#phaseDate" ).datepicker();
});

$(function () {
    $("#materialNoticeSelect").selectmenu();
});

function addRow(){
    $("#addMaterialsTableRow").remove();
    $("#materialsTable").append(
        '                                        <tr>\n' +
        '                                            <td>\n' +
        '                                                <select name="materials" id="materialNoticeSelect"\n' +
        '                                                        class="browser-default custom-select">\n' +
        '                                                    <c:forEach var="material" items="${allMaterials}">\n' +
        '                                                        <option value="${material.materialId}">${material.name}</option>\n' +
        '                                                    </c:forEach>\n' +
        '                                                </select>\n' +
        '                                            </td>\n' +
        '                                            <td>\n' +
        '                                                <input type="number" name="materialsQuantity" class="form-control"\n' +
        '                                                       id="materialsQuantityInput" >\n' +
        '                                            </td>\n' +
        '                                        </tr>\n' +
        '                                        <tr id="addMaterialsTableRow">\n' +
        '                                            <td>\n' +
        '                                                <input id="addMaterialsRowBtn" type="button" value="Adauga Materiale"\n' +
        '                                                       class="btn btn-primary" onclick="addRow()"/>\n' +
        '                                            </td>\n' +
        '                                        </tr>'
    );
}