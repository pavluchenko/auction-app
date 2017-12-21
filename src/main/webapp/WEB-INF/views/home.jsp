<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" %>

<jsp:include page="common/header.jsp"/>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div id="content">
    <section class="breadcrum-sec">
        <div class="container">
            <ol class="breadcrumb">
                <li>
                    <a href="<c:url value="/"/>"><spring:message code="auction.menu.home"/></a>
                </li>
                <li class="active">
                    <spring:message code="auction.listing.page"/>
                </li>
            </ol>
        </div>
    </section>
    <div class="row ">
        <jsp:include page="common/message.jsp"/>
        <div class="col-md-8 homepage">
            <hr>
            <div class="create_btn" style="margin-bottom: 10px;">

                <a class="button-btn small-btn green-bg"
                   href="<c:url value="/lot/create"/>"><spring:message code="create.new.lot"/></a>
            </div>

            <div class="create_btn" style="margin-bottom: 10px;">
                <a class="button-btn small-btn green-bg"
                   href="<c:url value="/lots/1"/>"><spring:message code="lot.show.all"/></a>

            </div>


            <div class="row">

                <c:if test="${not empty allLotsFromUser}">
                    <c:forEach items="${allLotsFromUser}" var="singleLot">
                        <div class="col-xs-12 col-sm-3 zoom">
                            <figure>
                                <a href="<c:url value="/lot/${singleLot.id}"/>"><img style="height: 260px;"
                                                                                     src="https://s3.amazonaws.com/helga-auction/${singleLot.photo}"
                                                                                     alt="products"/>
                                </a>
                            </figure>
                            <div class="slider-content product-content">
                                <h3><a href="#">${singleLot.name}</a></h3>
                                <span>$${singleLot.bayoutPrice}</span>
                                <a href="#" class="plus-more">+</a>
                                <div>
                                    <a class="button-btn small-btn green-bg"
                                       href="<c:url value="lot/edit/${singleLot.id}"/>"><spring:message
                                            code="lot.edit.button"/></a>
                                </div>
                                <div>
                                    <form:form action="lot/delete/${singleLot.id}" method="post" name="deleteForm"
                                               enctype="multipart/form-data">
                                        <button class="button-btn small-btn red-bg" type="submit"><spring:message
                                                code="lot.delete.button"/></button>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>
