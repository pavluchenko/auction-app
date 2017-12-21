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

<jsp:include page="../common/header.jsp"/>

<div id="content">
    <section class="breadcrum-sec">
        <div class="container">
            <ol class="breadcrumb">
                <li>
                    <a href="<c:url value="/"/>"><spring:message code="auction.menu.home"/></a>
                </li>
                <li class="active">
                    <spring:message code="auction.lots.page"/>
                </li>
            </ol>
        </div>
    </section>

    <div class="row">
        <div class="col-xs-12 col-sm-9 ">
            <div class="panel-body">
                <jsp:include page="../common/message.jsp"/>
                <div style="margin-bottom: 30px;">
                    <select class="js-example-basic-single select-lots" name="states[]">
                        <option></option>
                    </select>
                </div>
                <!-- User table -->
                <div class="row">

                    <c:if test="${not empty lots}">
                        <c:forEach items="${lots}" var="singleLot">

                            <div class="col-xs-12 col-sm-3 zoom">
                                <figure>
                                    <a href="<c:url value="/lot/${singleLot.id}"/>"><img style="height: 330px;"
                                                                                         src="https://s3.amazonaws.com/helga-auction/${singleLot.photo}"
                                                                                         alt="products"/></a>
                                </figure>
                                <div class="slider-content product-content">
                                    <h3><a href="#">${singleLot.name}</a></h3>
                                    <span>$${singleLot.bayoutPrice}</span>
                                    <a href="#" class="plus-more">+</a>
                                </div>

                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
            <ul class="pagination">
                <li>
                    <c:forEach items="${lotSize}" var="lotSizeItem">
                        <a href="<c:url value="/lots/${lotSizeItem}"/>">
                            <span>${lotSizeItem}</span>
                        </a>
                    </c:forEach>
                </li>
            </ul>

        </div>
        <div class="col-xs-12 col-sm-3">
            <div class="product-category-wrap">
                <h5 style="color: black;"> PRODUCT CATEGORIES</h5>
                <ul class="product-category">
                    <c:if test="${not empty categories}">
                        <c:forEach items="${categories}" var="categoryName">
                            <li>
                                <a href="#" class="fa fa-arrow-right"></a>${categoryName}
                            </li>
                        </c:forEach>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
<script src="${contextPath}/resources/js/search.js"></script>
<jsp:include page="../common/footer.jsp"/>


<%--
<select class="js-example-basic-single select-lots" name="states[]">
    <option></option>

</select>
--%>

