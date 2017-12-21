<%--
  Created by IntelliJ IDEA.
  User: Helga
  Date: 13.11.2017
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: Helga
  Date: 02.11.17
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="lot" value="${lot}"/>
<jsp:include page="../common/header.jsp"/>
<c:set var="lot" value="${lot}"/>
<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<script src="${contextPath}/resources/js/jquery.min.js"></script>

<div class="container-narrow">
    <div class="row">
        <div class="col-md-8 create-lot-col">

            <div id="main-content" class="container main-feature">
                <div class="row">

                    <form:form action="/feature/${lot.id}" method="post" modelAttribute="featureForm"
                               enctype="multipart/form-data">
                        <form:label path="name">
                            <spring:message code="create.lot.name"/>
                        </form:label>
                        <form:input path="name" class="input-block-level"/>
                        <form:label path="description">
                            Description
                        </form:label>
                        <form:input path="description" class="input-block-level"/>
                        <button class="btn  btn-danger" type="submit"><spring:message code="add.feature"/></button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp"/>

