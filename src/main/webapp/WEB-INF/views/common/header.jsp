<%--
  Created by IntelliJ IDEA.
  User: Helga
  Date: 14.11.2017
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Auction</title>
    <link href='http://fonts.googleapis.com/css?family=Lato:400,300italic,300,700%7CPlayfair+Display:400,700italic%7CRoboto:300%7CMontserrat:400,700%7COpen+Sans:400,300%7CLibre+Baskerville:400,400italic'
          rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Raleway:400,300,500,600,700,900,800%7CRoboto:400,300,500,700,900%7COswald:400,700,300%7CMontserrat'
          rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/less" href="${contextPath}/resources/css/auction.css" media="screen">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/bootstrap.css" media="screen">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/bootstrap-theme.css" media="screen">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/font-awesome.min.css" media="screen">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/simple-line-icons.css" media="screen">
    <link href="${contextPath}/resources/css/owl.carousel.css" type="text/css" rel="stylesheet" media="screen">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/global.css" media="screen">
    <link href="${contextPath}/resources/css/settings.css" type="text/css" rel="stylesheet" media="screen">
    <link href="${contextPath}/resources/css/layers.css" type="text/css" rel="stylesheet" media="screen">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css" media="screen">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/responsive.css" media="screen">
    <link rel="stylesheet" type="text/less" href="${contextPath}/resources/css/skin.less" media="screen">

    <%-- <link rel="stylesheet" href="${contextPath}/resources/css/select2.css">--%>
    <link rel="stylesheet" href="${contextPath}/resources/css/auction.css">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="${contextPath}/resources/js/jquery.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet"/>


    <!--[if lt IE 9]>
    <script src="${contextPath}/resources/js/html5shiv.min.js"></script>
    <![endif]-->

</head>
<body>
<div id="loading">
    <div id="loading-center">
        <div id="loading-center-absolute">
            <div class="object" id="object_one"></div>
            <div class="object" id="object_two"></div>
            <div class="object" id="object_three"></div>

        </div>
    </div>

</div>
<!--Page Wrapper Start-->
<div id="wrapper" class="homepage-1">
    <!--Header Section Start-->
    <header id="header" class="header-one" style="background: black;">
        <div class="container">
            <div class="row">
                <div class="col-xs-6 col-sm-2">
                    <a href="<c:url value="/"/>" class="logo"> <img
                            src="${contextPath}/resources/images/home-1_logo.png"
                            alt="logo"/></a>
                </div>
                <div class="col-xs-6 col-sm-10 col-md-9 col-lg-8 pull-right mobile-static">
                    <nav class="navbar">

                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>

                        </div>

                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav" style="padding-left: 170px;">
                                <li><a href="<c:url value="/home"/>" class="active"><spring:message
                                        code="auction.menu.home"/> </a></li>
                                <li><a href="<c:url value="/lots/1"/>">Лоты</a></li>
                                <li><a href="<c:url value="/"/>"><spring:message code="auction.menu.about"/></a></li>
                                <c:choose>
                                    <c:when test="${loggedInUserEmail == null}">
                                        <li class="nav navbar-left"><a href="<c:url value="/login"/>"><span
                                        class="glyphicon glyphicon-users"></span> <spring:message
                                            code="auction.sing.in"/></a>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="nav navbar-left"><a><spring:message
                                                code="auction.hello"/> ${loggedInUserEmail}</a>
                                        </li>
                                        <li class="nav navbar-left">
                                            <a href="<c:url value="/logout"/>"><span
                                                    class="glyphicon glyphicon-users"></span> <spring:message
                                                    code="auction.log.out"/></a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>

                            </ul>

                        </div><!-- /.navbar-collapse -->

                    </nav>
                </div>

            </div>

        </div>

    </header>