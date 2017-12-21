<%--
  Created by IntelliJ IDEA.
  User: Helga
  Date: 20.11.2017
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<jsp:include page="common/header.jsp"/>
<div class="container-narrow you-lot" id="content">
    <div class="row">
        <div class="col-md-8" style=" margin-left: 200px;">
            Лот: <p>${lot.name}</p>
            <p>Все ставки:</p>
            <c:if test="${not empty rates}">
                <c:forEach items="${rates}" var="singleRate">
                    <p>${singleRate.price}</p>
                    <p>${singleRate.date}</p>
                    <p>${singleRate.user.email}</p>
                    <hr>
                </c:forEach>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>