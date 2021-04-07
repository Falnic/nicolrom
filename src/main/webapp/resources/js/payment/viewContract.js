function createInput(articleId) {
    var tdselector = "#priceId-" + articleId;
    var trselector = "#article-" + articleId;
    var articlePrice = parseInt($(tdselector).text());
    $(tdselector).remove();
    $(trselector).append("<td><input type=\"number\" step=\"0.01\" min=\"0\" class=\"form-control\" name=\"articlePrice\"\n" +
        "                                       id=\"price-" + articleId + "\" value=\""+ articlePrice +"\" />" +
        "<input type='hidden' name='articleId' value='"+articleId+"'></td>")

    $(".updatePriceBtn").show();
}