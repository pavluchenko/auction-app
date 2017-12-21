<%--
  Created by IntelliJ IDEA.
  User: Helga
  Date: 15.11.2017
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<jsp:include page="../common/header.jsp"/>

<div class="container-narrow" id="content">
    <div class="row">
        <jsp:include page="../common/message.jsp"/>
        <div class="col-md-8 homepage">
            <hr>
            <form:form method="post" modelAttribute="lotForm" class="form-signin">
                <h2 class="form-signin-heading" style="color: #0e111a"><spring:message code="update.new.lot"/></h2>
                <p><form:label path="name">Name :</form:label></p>

                <input class="form-control" id="name" name="name" type="text" value="${lot.name}"/>

                <p><form:label path="description">Description : </form:label></p>
                <input class="form-control" id="description" name="description" class="input-block-level"
                       rows="10" type="text" value="${lot.description}"/>

                <p><form:label path="bayoutPrice">Bayout price : </form:label></p>
                <input class="form-control" id="bayoutPrice" name="bayoutPrice" class="input-block-level"
                       rows="10" type="text" value="${lot.bayoutPrice}"/>

                <p><form:label path="minPrice">Min price : </form:label></p>
                <input class="form-control" id="minPrice" name="minPrice" class="input-block-level"
                       rows="10" type="text" value="${lot.minPrice}"/>

                <p><form:label path="categoryName">Category :</form:label></p>
                <p><form:select class="form-control" path="categoryName" items="${categories}"/></p>


                <input type="submit" value="Update" class="button-btn small-btn green-bg">
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>

