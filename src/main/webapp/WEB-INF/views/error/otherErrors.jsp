<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<!--[if IE 8 ]><html class="no-js oldie ie8" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="no-js oldie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html class="no-js" lang="en"> <!--<![endif]-->
<head>
    <link rel="stylesheet" href="${contextPath}/resources/css/auction.css">

    <!--- basic page needs
    ================================================== -->
    <meta charset="utf-8">
    <title>Auction Error</title>
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- mobile specific metas
    ================================================== -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <!-- CSS
  ================================================== -->
    <link rel="stylesheet" href="${contextPath}/resources/css/base.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/vendor.css">

    <!-- script
    ================================================== -->
    <script src="${contextPath}/resources/js/modernizr.js"></script>
</head>

<body>

<!-- header
================================================== -->
<header class="main-header">
    <div class="row">
        <div class="logo">
            <h1 href="/" class="not-found-error">Auction</h1>
        </div>
    </div>

</header> <!-- /header -->

<!-- navigation
================================================== -->


<!-- main content
================================================== -->
<main id="main-404-content" class="main-content-particle-js">

    <div class="content-wrap">

        <div class="shadow-overlay"></div>

        <div class="main-content">
            <div class="row">
                <div class="col-twelve">

                    <h1 class="kern-this">Error page.</h1>
                    <p>
                        Oooooops! Something is wrong, go to another page.
                    </p>

                </div> <!-- /twelve -->
            </div> <!-- /row -->
        </div> <!-- /main-content -->

        <footer>
            <div class="row">

                <div class="col-five tab-full bottom-links">
                    <ul class="links">
                        <li><a href="/">Homepage</a></li>
                        <li><a href="/login">Login</a></li>
                        <li><a href="mailto:joe@quatro.com">Report Error</a></li>
                    </ul>

                    <div class="credits">
                        <p>Sam Soluctions</p>
                    </div>
                </div>

            </div> <!-- /row -->
        </footer>

    </div> <!-- /content-wrap -->

</main> <!-- /main-404-content -->

<div id="preloader">
    <div id="loader"></div>
</div>

<!-- Java Script
================================================== -->
<script src="${contextPath}/resources/js/jquery-2.1.3.min.js"></script>
<script src="${contextPath}/resources/js/plugins.js"></script>
<script src="${contextPath}/resources/js/main.js"></script>

</body>

</html>