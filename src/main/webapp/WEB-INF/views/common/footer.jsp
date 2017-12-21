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
<footer id="footer" class="footer-one">
    <div class="container">
        <div class="primary-footer">
            <div class="row">


                <div class="col-xs-12 col-sm-6 col-md-4 class-time footer-l">

                </div>
                <div class="col-xs-12 col-sm-6 col-md-3 class-time timing-box footer-l">
                </div>

            </div>
        </div>

        <div class="bottom-footer">
            <div class="row">
                <div class="col xs-12 col-sm-6 col-lg-7 copy-right">
                    <p>
                        <spring:message code="copyright"/>
                    </p>
                </div>
            </div>
        </div>

    </div>

</footer>
<!--Footer Section End-->

</div>
<!--Page Wrapper End-->
<script type="text/javascript" src="${contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/less.js"></script>

<!-- revolution Js -->
<script type="text/javascript" src="${contextPath}/resources/js/jquery.themepunch.tools.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/jquery.themepunch.revolution.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/jquery.revolution.js"></script>
<!-- revolution Js-->
<script type="text/javascript" src="${contextPath}/resources/js/owl.carousel.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/site.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>

</body>
</html>