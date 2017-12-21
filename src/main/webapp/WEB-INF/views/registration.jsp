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
            <div class="row" style="margin-top: 50px;">
                <div class="col-xs-12 col-sm-8" style="left: 180px;top: 100px;">
                    <h3><spring:message code="auction.create.account"/></h3>
                    <div class="billig-info-wrap">
                        <div style="display: none" id="success">
                            success deliver message
                        </div>
                        <form:form method="POST" modelAttribute="userForm" class="form-signin" name="contactForm">
                            <spring:bind path="email">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="email" class="form-control" placeholder="Email"
                                                autofocus="true"></form:input>
                                    <form:errors path="email"></form:errors>
                                </div>
                            </spring:bind>

                            <spring:bind path="password">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="password" path="password" class="form-control"
                                                placeholder="Password"></form:input>
                                    <form:errors path="password"></form:errors>
                                </div>
                            </spring:bind>

                            <spring:bind path="passwordConfirm">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="password" path="passwordConfirm" class="form-control"
                                                placeholder="Confirm your password"></form:input>
                                    <form:errors path="passwordConfirm"></form:errors>
                                </div>
                            </spring:bind>

                            <button style="display: flex;" class="button-btn  submit-btn" type="submit"><spring:message
                                    code="auction.submit.button"/></button>
                        </form:form>

                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

<jsp:include page="common/footer.jsp"/>
