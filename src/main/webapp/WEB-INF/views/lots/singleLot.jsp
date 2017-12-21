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

<style>
    body {
        background-color: #f5f5f5;
    }

    #main-content {
        max-width: 940px;
        padding: 2em 3em;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
        -moz-border-radius: 5px;
        border-radius: 5px;
    }
</style>

<jsp:include page="../common/header.jsp"/>

<c:set var="lot" value="${lot}"/>
<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/auction.css" media="screen">

<div id="content">
    <section class="breadcrum-sec">
        <div class="container">
            <ol class="breadcrumb">
                <li>
                    <a href="<c:url value="/"/>"><spring:message code="auction.menu.home"/></a>
                </li>
                <li>
                    <a href="<c:url value="lots/1"/>"> <spring:message code="auction.lots.page"/></a>
                </li>
                <li class="active">
                    <spring:message code="auction.lot.page"/> ${lot.name}
                </li>
            </ol>
        </div>
    </section>
    <div class="row">
        <div class="col-md-8 col-single-lot">

            <div id="main-content" class="container main-single-lot">
                <div class="row">

                </div>
                <div class="row">
                    <div class="col-md-12">
                        <table id="conversation" class="table table-striped">
                            <thead>
                            <tr>
                                <th>
                                    Lot ${lot.name}
                                    <c:if test="${lot.user.email != loggedInUserEmail}">
                                        <c:if test="${subscription == null}">
                                            <form:form action="/subscribe/${lot.user.id}" method="post"
                                                       enctype="multipart/form-data">
                                                <div class="create_btn btn-single" align="right">
                                                    <button class="btn  btn-success" type="submit">Подписаться</button>
                                                </div>
                                            </form:form>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${lot.user.email != loggedInUserEmail}">
                                        <c:if test="${subscription != null}">
                                            <form:form action="/unsubscribe" method="post"
                                                       enctype="multipart/form-data">
                                                <div class="create_btn btn-single" align="right">
                                                    <button class="btn  btn-danger" type="submit">Отписаться</button>
                                                </div>
                                            </form:form>
                                        </c:if>
                                    </c:if>
                                </th>
                            </tr>
                            </thead>
                            <tbody id="greetings">
                            </tbody>
                        </table>
                    </div>
                    <p></p>
                    <p>  ${lot.name}</p>
                    <p><spring:message code="lot.description"/> ${lot.description}</p>
                    <p><spring:message code="lot.category"/> ${lot.category.name}</p>
                    <p><spring:message code="lot.creator"/> ${lot.user.email}</p>
                    <p><spring:message code="lot.bayout.price"/> ${lot.bayoutPrice}</p>
                    <p><spring:message code="lot.min.price"/>${lot.minPrice}</p>
                    <p><spring:message code="lot.photo"/>
                        <img class="indexPhoto" src="${lot.photo}">
                    </p>
                    <p>
                        Rates:
                    <p>
                        <c:if test="${not empty lot.features}">
                            <c:forEach items="${lot.features}" var="featureName">
                                <c:out value="${featureName.name}"/>
                                <c:out value="${featureName.description}"/>,
                            </c:forEach>
                        </c:if>
                    </p>
                    </p>
                    <c:if test="${not empty rate}">
                        <p class="text-success"><spring:message code="lot.last.rate"/><span
                                id="ratePrice"> ${rate.price}</span></p>
                    </c:if>
                    <c:if test="${lot.disable != true}">
                        <div>
                            <form:form method="POST" id="rateForm" modelAttribute="rateForm" class="form-signin"
                                       action="/rate/add/${lot.id}/${loggedInUserEmail}">
                                <div class="col-md-4">
                                    <spring:bind path="price">
                                        <form:input type="number" id="price" path="price" class="form-control"
                                                    placeholder="Price" name="price"
                                                    autofocus="true" data-id="${lot.id}"
                                                    data-item="${loggedInUserEmail}"></form:input>
                                        <form:errors path="price"></form:errors>
                                    </spring:bind>
                                </div>
                                <div class="col-md-4 you-lot">
                                    <button id="send" type="submit" class="button-btn small-btn green-bg" name="add">
                                        <spring:message code="rate.add"/></button>
                                </div>
                            </form:form>
                        </div>
                    </c:if>
                    <c:if test="${lot.disable == true}">
                        <p>Аукцион закрыт</p>
                    </c:if>
                    <div class="create_btn">
                        <a class="btn btn-large btn-success"
                           href="<c:url value="/rate/all/${lot.id}"/>"><spring:message code="rate.show.all"/></a>
                    </div>
                </div>
                <c:if test="${lot.user.email == loggedInUserEmail}">
                    <div class="create_btn">
                        <a class="btn btn-large btn-success" style="margin-top: 20px; margin-left: -15px;"
                           data-toggle="collapse" href="#collapseExample"
                           aria-expanded="false" aria-controls="collapseExample"><spring:message
                                code="add.feature"/></a>
                        <div class="collapse" id="collapseExample">
                            <form:form action="/feature/${lot.id}" method="post" modelAttribute="featureForm"
                                       enctype="multipart/form-data">
                                <p><form:label path="name">
                                    <spring:message code="create.lot.name"/>
                                </form:label></p>
                                <form:input path="name" class="form-control"/>
                                <p><form:label path="description">
                                    Description
                                </form:label></p>
                                <form:input path="description" class="form-control"/>
                                <button class="button-btn small-btn green-bg" type="submit"><spring:message
                                        code="add.feature"/></button>
                            </form:form>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<script src="${contextPath}/resources/js/websocket.js"></script>


<jsp:include page="../common/footer.jsp"/>

