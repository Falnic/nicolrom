<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="bootstrapImports.jsp" />

<div class="row header">
    <div class="col-sm-6">
        <div class="row">
            <div class="col-sm-6">
                <a href="${homeURL}">
                    <img id="teaLogo"  src="${logoUrl}" alt="Nicol Rom" ismap>
                </a>
            </div>
            <div class="col-sm-6">
                <div id="headerLinks">
<%--                    <sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">--%>
                        <h4 style="color: #FFFFFF; padding-left: 1%; font-family: 'Calibri';">
<%--                            Logged in as: <sec:authentication property="name"/> | <a href="${logoutUrl}">Logout</a>--%>
                        </h4>
<%--                    </sec:authorize>--%>

<%--                    <a class="headerLink" href="${productsURL}">--%>
<%--                        Products</a>--%>

<%--                    <sec:authorize access="!hasRole('ROLE_ADMIN')">--%>
<%--                        <a class="headerLink" href="${loginURL}">--%>
<%--                            Login</a>--%>
<%--                    </sec:authorize>--%>

<%--                    <a>--%>
<%--                        <img  src="${cartImageUrl}" alt="My Cart" id="cartIcon" ismap></a>--%>

<%--                    <a id="prodNr"></a>--%>

<%--                    <div id="cartPanel">--%>
<%--                        <div id="allCartData">--%>
<%--                            <div id="entry-data">--%>

<%--                            </div>--%>
<%--                            <div id = "totalTag">--%>
<%--                                total:--%>
<%--                                <span id="total-value"></span>--%>
<%--                                $--%>
<%--                            </div>--%>
<%--                        </div>--%>

<%--                        <a id="checkoutButtonID">VIEW CART</a>--%>
<%--                    </div>--%>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-3"></div>
</div>