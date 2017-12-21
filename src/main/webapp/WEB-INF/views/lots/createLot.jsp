<%--
  Created by IntelliJ IDEA.
  User: Helga
  Date: 10.11.17
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" %>

<jsp:include page="../common/header.jsp"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="container-narrow" id="content">
    <div class="row">
        <jsp:include page="../common/message.jsp"/>
        <div class="col-md-8 create-lot">
            <hr>
            <form:form method="post" modelAttribute="lotForm" class="form-signin" enctype="multipart/form-data">
                <h2 class="form-signin-heading" style="color: #0e111a">
                    <spring:message code="create.new.lot"/>
                </h2>
                <p><form:label path="name">
                    <spring:message code="create.lot.name"/>
                </form:label></p>
                <form:input path="name" class="form-control"/>
                <p><form:label path="description"><spring:message code="create.lot.description"/></form:label></p>
                <form:textarea path="description" class="form-control" rows="10"/>
                <p><form:label path="bayoutPrice">
                    <spring:message code="create.lot.bayout.price"/>
                </form:label></p>
                <p><form:input path="bayoutPrice" class="form-control"/></p>

                <p><form:label path="minPrice">
                    <spring:message code="create.lot.min.price"/>
                </form:label></p>
                <p><form:input path="minPrice" class="form-control"/></p>
                <p><form:label path="file">
                    <spring:message code="create.lot.photo"/>
                </form:label></p>
                <p><form:input path="file" type="file" name="file" class="form-control"/></p>

                <p><form:label path="categoryName">Category :</form:label></p>
                <p><form:select path="categoryName" items="${categories}"/></p>

                <input type="submit" value="<spring:message code="create.lot.button"/>"
                       class="button-btn small-btn green-bg">
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>