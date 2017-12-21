<%--
  Created by IntelliJ IDEA.
  User: Helga
  Date: 03.12.2017
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" %>


<?xml version="1.0" encoding="UTF-8" ?>
<!doctype html>
<html>
<head>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>

    <link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/select2.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/auction.css">


    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet"/>

    <title>Auction</title>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar"
                    aria-expanded="false"> 
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="<c:url value="/"/>" class="navbar-brand"> <spring:message code="auction.name.app"/></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">

            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/home"/>" class="active"><spring:message code="auction.menu.home"/> </a></li>
                <li><a href="<c:url value="/lots/1"/>">Лоты</a></li>
                <li><a href="<c:url value="/"/>"><spring:message code="auction.menu.about"/></a></li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                <c:when test="${loggedInUserEmail == null}">
                <li class="nav navbar-left"><a href="<c:url value="/login"/>"><span
                        class="glyphicon glyphicon-users"></span> <spring:message code="auction.sing.in"/></a>
                    </c:when>
                    <c:otherwise>
                <li class="nav navbar-left"><a><spring:message code="auction.hello"/> ${loggedInUserEmail}</a> <a
                        href="<c:url value="/logout"/>"><span
                        class="glyphicon glyphicon-users"></span> <spring:message code="auction.log.out"/></a>
                    </c:otherwise>
                    </c:choose>
                </li>


            </ul>

        </div>
    </div>
</nav>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<jsp:include page="../common/message.jsp"/>
<div class="create_btn">

    <a class="btn btn-default btn-lg btn-block"
       href="<c:url value="/admin/user/1"/>">Users</a>
</div>

<div class="create_btn">
    <a class="btn btn-success btn-lg btn-block"
       href="<c:url value="/admin/category/1"/>">Categories</a>
</div>


<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<footer>
    <div class="footiedata">
        <div class="container">

            <form class="formlist">

            </form>

            <div class="flexylist">

                <ul class="flexcont">

                </ul>
                <ul class="flexcont1">

                </ul>

            </div>
        </div>
    </div>
    <div class="bottomsecond">
        <div class="copee"><spring:message code="footer.company.name"/></div>
    </div>
</footer>
<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
</body>
</html>
