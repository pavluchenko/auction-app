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
    <header id="header" class="header-one">
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
    <section class="slider-revo">
        <div id="rev_slider_14_1_wrapper" class="rev_slider_wrapper fullscreen-container" data-alias="gym1"
             style="background-color:transparent;padding:0px;">
            <!-- START REVOLUTION SLIDER 5.0.7 fullscreen mode -->
            <div id="rev_slider_14_1" class="rev_slider fullscreenbanner" style="display:none;" data-version="5.0.7">
                <ul>
                    <!-- SLIDE  -->
                    <li data-index="rs-44" data-transition="slideoververtical" data-slotamount="default"
                        data-easein="default" data-easeout="default" data-masterspeed="1500"
                        data-thumb="h${contextPath}/resources/images/home_1_slider_1.jpg"
                        data-rotate="0" data-fstransition="fade" data-fsmasterspeed="1500" data-fsslotamount="7"
                        data-saveperformance="off" data-title="Intro" data-description="">
                        <!-- MAIN IMAGE -->
                        <img src="${contextPath}/resources/images/home_1_slider_1.jpg"
                             alt="" data-bgposition="center center" data-bgfit="cover" data-bgrepeat="no-repeat"
                             class="rev-slidebg" data-no-retina>

                        <!-- LAYERS -->

                        <!-- LAYER NR. 1 -->
                        <div class="tp-caption Gym-Display   tp-resizeme main-heading"
                             id="slide-44-layer-1"
                             data-x="['center','center','center','center']" data-hoffset="['0','0','0','0']"
                             data-y="['middle','middle','middle','middle']" data-voffset="['-120','-120','-120','-100']"
                             data-fontsize="['100','100','100','100']"
                             data-lineheight="['20','20','20','20']"
                             data-width="none"
                             data-height="none"
                             data-whitespace="nowrap"
                             data-transform_idle="o:1;"

                             data-transform_in="y:[100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;opacity:0;s:2000;e:Power4.easeInOut;"
                             data-transform_out="y:[100%];s:1000;e:Power2.easeInOut;s:1000;e:Power2.easeInOut;"
                             data-mask_in="x:0px;y:[100%];s:inherit;e:inherit;"
                             data-mask_out="x:inherit;y:inherit;s:inherit;e:inherit;"
                             data-start="500"
                             data-splitin="none"
                             data-splitout="none"
                             data-responsive_offset="on"

                             style="z-index: 5; white-space: nowrap; ">
                            <spring:message code="favorable.rates"/>
                        </div>

                        <!-- LAYER NR. 3 -->
                        <div class="tp-caption Gym-Display   tp-resizeme main-sub-heading"
                             id="slide-44-layer-3"
                             data-x="['center','center','center','center']" data-hoffset="['0','0','0','0']"
                             data-y="['middle','middle','middle','middle']" data-voffset="['0','0','0','0']"
                             data-fontsize="['100','100','100','100']"
                             data-width="none"
                             data-height="none"
                             data-whitespace="nowrap"
                             data-transform_idle="o:1;"

                             data-transform_in="y:[100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;opacity:0;s:2000;e:Power4.easeInOut;"
                             data-transform_out="y:[100%];s:1000;e:Power2.easeInOut;s:1000;e:Power2.easeInOut;"
                             data-mask_in="x:0px;y:[100%];s:inherit;e:inherit;"
                             data-mask_out="x:inherit;y:inherit;s:inherit;e:inherit;"
                             data-start="750"
                             data-splitin="none"
                             data-splitout="none"
                             data-responsive_offset="on"

                             style="z-index: 7; white-space: nowrap;">
                            <spring:message code="auction.demo"/>
                        </div>

                        <!-- LAYER NR. 4 -->
                        <div class="tp-caption Gym-Subline   tp-resizeme "
                             id="slide-44-layer-4"
                             data-x="['center','center','center','center']" data-hoffset="['0','0','0','0']"
                             data-y="['middle','middle','middle','middle']" data-voffset="['130','130','130','0']"
                             data-fontsize="['18','18','18','18']"
                             data-lineheight="['18','18','18','18']"
                             data-fontweight="['100','100','100','700']"
                             data-width="none"
                             data-height="none"
                             data-whitespace="nowrap"
                             data-transform_idle="o:1;"

                             data-transform_in="y:[100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;opacity:0;s:2000;e:Power4.easeInOut;"
                             data-transform_out="y:[100%];s:1000;e:Power2.easeInOut;s:1000;e:Power2.easeInOut;"
                             data-mask_in="x:0px;y:[100%];s:inherit;e:inherit;"
                             data-mask_out="x:inherit;y:inherit;s:inherit;e:inherit;"
                             data-start="1000"
                             data-splitin="none"
                             data-splitout="none"
                             data-responsive_offset="on"

                             style="z-index: 8; white-space: nowrap;">

                            <!-- LAYER NR. 6 -->
                            <div class="tp-caption rev-btn rev-maxround rev-hiddenicon rev-bordered  "
                                 id="slide-44-layer-6"
                                 data-x="['center','center','center','center']" data-hoffset="['0','0','0','0']"
                                 data-y="['bottom','bottom','bottom','bottom']" data-voffset="['280','280','280','21']"
                                 data-width="none"
                                 data-height="none"
                                 data-whitespace="nowrap"
                                 data-transform_idle="o:1;"
                                 data-transform_hover="o:1;rX:0;rY:0;rZ:0;z:0;s:300;e:Power1.easeInOut;"
                                 data-style_hover="c:rgba(255, 255, 255, 1.00);bg:rgba(114, 168, 0, 0);bc:rgba(139, 192, 39, 1.00);cursor:pointer;"

                                 data-transform_in="y:[100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;opacity:0;s:2000;e:Power4.easeInOut;"
                                 data-transform_out="y:[175%];s:1000;e:Power2.easeInOut;s:1000;e:Power2.easeInOut;"
                                 data-mask_out="x:inherit;y:inherit;"
                                 data-start="1650"
                                 data-splitin="none"
                                 data-splitout="none"
                                 data-actions='[{"event":"click","action":"jumptoslide","slide":"rs-46","delay":""}]'
                                 data-responsive_offset="on"
                                 data-responsive="off"

                                 style="z-index: 12; white-space: nowrap; font-size: 15px; line-height: 15px; font-weight: 600; color: rgba(255, 255, 255, 1.00);font-family:Raleway;padding:12px 35px 12px 35px;border-color:rgba(255, 255, 255, 0.25);border-style:solid;border-width:2px;border-radius:30px 30px 30px 30px;letter-spacing:1px;">
                                <a href="<c:url value="/lot/${singleLot.id}"/>" class="join-btn"><spring:message
                                        code="get.rate"/></a>
                            </div>

                    </li>
                    <!-- SLIDE  -->
                    <li data-index="rs-45" data-transition="slideoververtical" data-slotamount="default"
                        data-easein="default" data-easeout="default" data-masterspeed="1500"
                        data-thumb="${contextPath}/resources/images/home_1_slider_1.jpg"
                        data-rotate="0" data-saveperformance="off" data-title="Service" data-description="">
                        <!-- MAIN IMAGE -->
                        <img src="${contextPath}/resources/images/home_1_slider_1.jpg"
                             alt="" data-bgposition="left center" data-bgfit="cover" data-bgrepeat="no-repeat"
                             class="rev-slidebg" data-no-retina>
                        <!-- LAYERS -->

                        <!-- LAYER NR. 1 -->
                        <div class="tp-caption Gym-Display   tp-resizeme"
                             id="slide-45-layer-1"
                             data-x="['left','center','center','center']" data-hoffset="['0','0','0','0']"
                             data-y="['middle','middle','middle','middle']" data-voffset="['-182','-182','-182','-182']"
                             data-fontsize="['100','100','100','200']"
                             data-width="none"
                             data-height="none"
                             data-whitespace="nowrap"
                             data-transform_idle="o:1;"

                             data-transform_in="y:[100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;opacity:0;s:2000;e:Power4.easeInOut;"
                             data-transform_out="y:[100%];s:1500;e:Power2.easeInOut;s:1500;e:Power2.easeInOut;"
                             data-mask_in="x:0px;y:[100%];s:inherit;e:inherit;"
                             data-mask_out="x:inherit;y:inherit;s:inherit;e:inherit;"
                             data-start="500"
                             data-splitin="none"
                             data-splitout="none"
                             data-responsive_offset="on"

                             style="z-index: 5; white-space: nowrap;">
                            <spring:message code="get.rate.new"/>
                        </div>

                        <!-- LAYER NR. 3 -->
                        <div class="tp-caption Gym-Display   tp-resizeme"
                             id="slide-45-layer-3"
                             data-x="['left','center','center','center']" data-hoffset="['0','0','0','0']"
                             data-y="['middle','middle','middle','middle']" data-voffset="['-50','-50','-50','-127']"
                             data-fontsize="['100','100','100','100']"
                             data-width="none"
                             data-height="none"
                             data-whitespace="nowrap"
                             data-transform_idle="o:1;"

                             data-transform_in="y:[100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;opacity:0;s:2000;e:Power4.easeInOut;"
                             data-transform_out="y:[100%];s:1500;e:Power2.easeInOut;s:1500;e:Power2.easeInOut;"
                             data-mask_in="x:0px;y:[100%];s:inherit;e:inherit;"
                             data-mask_out="x:inherit;y:inherit;s:inherit;e:inherit;"
                             data-start="750"
                             data-splitin="none"
                             data-splitout="none"
                             data-responsive_offset="on"

                             style="z-index: 7; white-space: nowrap;">
                            <spring:message code="auction.demo"/>
                        </div>

                        <!-- LAYER NR. 4 -->
                        <div class="tp-caption Gym-Subline   tp-resizeme"
                             id="slide-45-layer-4"
                             data-x="['left','center','center','center']" data-hoffset="['0','0','0','0']"
                             data-y="['middle','middle','middle','middle']" data-voffset="['71','71','71','1']"
                             data-fontsize="['18','18','18','15']"
                             data-lineheight="['30','30','30','20']"
                             data-fontweight="['300','300','300','400']"
                             data-width="none"
                             data-height="none"
                             data-whitespace="nowrap"
                             data-transform_idle="o:1;"

                             data-transform_in="y:[100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;opacity:0;s:2000;e:Power4.easeInOut;"
                             data-transform_out="y:[100%];s:1500;e:Power2.easeInOut;s:1500;e:Power2.easeInOut;"
                             data-mask_in="x:0px;y:[100%];s:inherit;e:inherit;"
                             data-mask_out="x:inherit;y:inherit;s:inherit;e:inherit;"
                             data-start="1000"
                             data-splitin="none"
                             data-splitout="none"
                             data-responsive_offset="on"

                             style="z-index: 8; white-space: nowrap; font-size: 20px; font-weight: 300;">

                        </div>

                        <!-- LAYER NR. 8 -->
                        <div class="tp-caption rev-btn rev-maxround rev-hiddenicon rev-bordered  "
                             id="slide-45-layer-8"
                             data-x="['left','center','center','center']" data-hoffset="['216','0','0','0']"
                             data-y="['bottom','bottom','bottom','bottom']" data-voffset="['280','280','280','21']"
                             data-width="none"
                             data-height="none"
                             data-whitespace="nowrap"
                             data-transform_idle="o:1;"
                             data-transform_hover="o:1;rX:0;rY:0;rZ:0;z:0;s:300;e:Power1.easeInOut;"
                             data-style_hover="c:rgba(255, 255, 255, 1.00);bg:rgba(114, 168, 0, 0);bc:rgba(139, 192, 39, 1.00);cursor:pointer;"

                             data-transform_in="y:[100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;opacity:0;s:2000;e:Power4.easeInOut;"
                             data-transform_out="y:[175%];s:1000;e:Power2.easeInOut;s:1000;e:Power2.easeInOut;"
                             data-mask_out="x:inherit;y:inherit;"
                             data-start="1650"
                             data-splitin="none"
                             data-splitout="none"
                             data-actions='[{"event":"click","action":"jumptoslide","slide":"rs-46","delay":""}]'
                             data-responsive_offset="on"
                             data-responsive="off"

                             style="z-index: 12; white-space: nowrap; font-size: 15px; line-height: 15px; font-weight: 600; color: rgba(255, 255, 255, 1.00);font-family:Raleway;padding:12px 35px 12px 35px;border-color:rgba(255, 255, 255, 0.25);border-style:solid;border-width:2px;border-radius:30px 30px 30px 30px;letter-spacing:1px;">
                            <a href="trainer-detail.html" class="join-btn"><spring:message code="get.rate"/></a>
                        </div>

                    </li>
                    <!-- SLIDE  -->
                    <li data-index="rs-46" data-transition="slideoververtical" data-slotamount="7" data-easein="default"
                        data-easeout="default" data-masterspeed="1500"
                        data-thumb="${contextPath}/resources/images/home_1_slider_1.jpg"
                        data-rotate="0" data-saveperformance="off" data-title="Contact" data-description="">
                        <!-- MAIN IMAGE -->
                        <img src="${contextPath}/resources/images/home_1_slider_1.jpg"
                             alt="" data-bgposition="center center" data-bgfit="cover" data-bgrepeat="no-repeat"
                             class="rev-slidebg" data-no-retina>
                        <!-- LAYERS -->

                        <!-- LAYER NR. 2 -->
                        <div class="tp-caption Gym-Display   tp-resizeme"
                             id="slide-46-layer-2"
                             data-x="['right','center','center','center']" data-hoffset="['0','0','0','0']"
                             data-y="['middle','middle','middle','middle']" data-voffset="['-182','-182','-182','-139']"
                             data-fontsize="['100','100','100','200']"
                             data-width="none"
                             data-height="none"
                             data-whitespace="nowrap"
                             data-transform_idle="o:1;"

                             data-transform_in="y:[100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;opacity:0;s:2000;e:Power4.easeInOut;"
                             data-transform_out="y:[100%];s:1500;e:Power2.easeInOut;s:1500;e:Power2.easeInOut;"
                             data-mask_in="x:0px;y:[100%];s:inherit;e:inherit;"
                             data-mask_out="x:inherit;y:inherit;s:inherit;e:inherit;"
                             data-start="500"
                             data-splitin="none"
                             data-splitout="none"
                             data-responsive_offset="on"

                             style="z-index: 6; white-space: nowrap;">
                            <spring:message code="favorable.rates"/>
                        </div>

                        <!-- LAYER NR. 4 -->
                        <div class="tp-caption Gym-Display   tp-resizeme"
                             id="slide-46-layer-4"
                             data-x="['right','center','center','center']" data-hoffset="['0','0','0','0']"
                             data-y="['middle','middle','middle','middle']" data-voffset="['-50','-50','-50','-87']"
                             data-fontsize="['100','100','100','200']"
                             data-width="none"
                             data-height="none"
                             data-whitespace="nowrap"
                             data-transform_idle="o:1;"

                             data-transform_in="y:[100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;opacity:0;s:2000;e:Power4.easeInOut;"
                             data-transform_out="y:[100%];s:1500;e:Power2.easeInOut;s:1500;e:Power2.easeInOut;"
                             data-mask_in="x:0px;y:[100%];s:inherit;e:inherit;"
                             data-mask_out="x:inherit;y:inherit;s:inherit;e:inherit;"
                             data-start="750"
                             data-splitin="none"
                             data-splitout="none"
                             data-responsive_offset="on"

                             style="z-index: 8; white-space: nowrap;">
                            <spring:message code="auction.demo"/>
                        </div>

                        <!-- LAYER NR. 5 -->
                        <div class="tp-caption Gym-Subline   tp-resizeme"
                             id="slide-46-layer-5"
                             data-x="['right','center','center','center']" data-hoffset="['0','0','0','0']"
                             data-y="['middle','middle','middle','middle']" data-voffset="['71','71','71','21']"
                             data-fontsize="['18','18','18','15']"
                             data-lineheight="['30','30','30','20']"
                             data-fontweight="['300','300','300','400']"
                             data-width="none"
                             data-height="none"
                             data-whitespace="nowrap"
                             data-transform_idle="o:1;"

                             data-transform_in="y:[100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;opacity:0;s:2000;e:Power4.easeInOut;"
                             data-transform_out="y:[100%];s:1500;e:Power2.easeInOut;s:1500;e:Power2.easeInOut;"
                             data-mask_in="x:0px;y:[100%];s:inherit;e:inherit;"
                             data-mask_out="x:inherit;y:inherit;s:inherit;e:inherit;"
                             data-start="1000"
                             data-splitin="none"
                             data-splitout="none"
                             data-responsive_offset="on"

                             style="z-index: 9; white-space: nowrap; font-size: 20px; font-weight: 300;">
                        </div>

                        <!-- LAYER NR. 7 -->
                        <div class="tp-caption rev-btn rev-maxround rev-hiddenicon rev-bordered  "
                             id="slide-46-layer-7"
                             data-x="['right','center','center','center']" data-hoffset="['0','0','0','0']"
                             data-y="['bottom','bottom','bottom','bottom']" data-voffset="['280','280','280','21']"
                             data-width="none"
                             data-height="none"
                             data-whitespace="nowrap"
                             data-transform_idle="o:1;"
                             data-transform_hover="o:1;rX:0;rY:0;rZ:0;z:0;s:300;e:Power1.easeInOut;"
                             data-style_hover="c:rgba(255, 255, 255, 1.00);bg:rgba(114, 168, 0, 0);bc:rgba(139, 192, 39, 1.00);cursor:pointer;"

                             data-transform_in="y:[100%];z:0;rX:0deg;rY:0;rZ:0;sX:1;sY:1;skX:0;skY:0;opacity:0;s:2000;e:Power4.easeInOut;"
                             data-transform_out="y:[175%];s:1000;e:Power2.easeInOut;s:1000;e:Power2.easeInOut;"
                             data-mask_out="x:inherit;y:inherit;"
                             data-start="1650"
                             data-splitin="none"
                             data-splitout="none"
                             data-actions='[{"event":"click","action":"jumptoslide","slide":"rs-46","delay":""}]'
                             data-responsive_offset="on"
                             data-responsive="off"

                             style="z-index: 12; white-space: nowrap; font-size: 15px; line-height: 15px; font-weight: 600; color: rgba(255, 255, 255, 1.00);font-family:Raleway;padding:12px 35px 12px 35px;border-color:rgba(255, 255, 255, 0.25);border-style:solid;border-width:2px;border-radius:30px 30px 30px 30px;letter-spacing:1px;">
                            <a href="trainer-detail.html" class="join-btn"><spring:message code="get.rate"/></a>
                        </div>

                    </li>
                </ul>

                <div class="tp-bannertimer tp-bottom" style="visibility: hidden !important;"></div>
            </div>
        </div><!-- END REVOLUTION SLIDER -->

    </section>
    <div id="content">
        <jsp:include page="common/message.jsp"/>
        <section class="cool-products">
            <div class="container">
                <div class="head-global">
                    <h2 class="h2"><spring:message code="cool.products"/></h2>
                </div>
                <div class="row">
                    <c:if test="${not empty allLots}">
                        <c:forEach items="${allLots}" var="singleLot">
                            <div class="col-xs-12 col-sm-3 zoom">
                                <figure>
                                    <a href="#"><img src="https://s3.amazonaws.com/helga-auction/${singleLot.photo}"
                                                     alt="products"/></a>
                                </figure>
                                <div class="slider-content product-content">
                                    <h3><a href="<c:url value="/lot/${singleLot.id}"/>">${singleLot.name}</a></h3>
                                    <span>$${singleLot.bayoutPrice}</span>
                                    <a href="<c:url value="/lot/${singleLot.id}"/>" class="plus-more">+</a>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </section>
    </div>
    <jsp:include page="common/footer.jsp"/>
