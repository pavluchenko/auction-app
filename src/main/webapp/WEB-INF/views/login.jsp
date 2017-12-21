<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" %>
<jsp:include page="common/header.jsp"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="container">
    <section>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-sm-8" style="left: 180px;top: 100px;">
                    <h3>Login</h3>
                    <div class="billig-info-wrap">
                        <div style="display: none" id="success">
                            success deliver message
                        </div>
                        <form method="POST" action="${contextPath}/login" class="form-signin" name="contactForm"
                              novalidate id="contact">

                            <div class="form-group grp-gap" ${error != null ? 'has-error' : ''}>
                                <label for="company_name">Email * </label>
                                <input required name="email" type="text" class="form-control" class="form-control"
                                       id="company_name" placeholder="" name="comName"
                                       placeholder="<spring:message code="email.placeholder"/>"
                                       autofocus="true"/>
                                <span>${message}</span>
                            </div>
                            <div class="form-group grp-gap1" ${error != null ? 'has-error' : ''}>
                                <label for="address1">Password * </label>
                                <input name="password" type="password" class="form-control" type="text" id="address1"
                                       placeholder="" name="addf"
                                       placeholder="<spring:message code="password.placeholder"/>"/>
                                <span>${error}</span>
                                <input required type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </div>

                            <div class="btn-group1">
                                <button style="display: flex;" type="submit" class="button-btn  submit-btn">
                                    <spring:message code="auction.log.in"/>
                                </button>
                            </div>
                            <h4 class="text-center"><a href="${contextPath}/registration"><spring:message
                                    code="auction.reg.page"/></a></h4>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<jsp:include page="common/footer.jsp"/>

