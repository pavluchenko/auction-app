<%--
  Created by IntelliJ IDEA.
  User: Helga
  Date: 04.12.2017
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<?xml version="1.0" encoding="UTF-8" ?>
<!doctype html>
<html>
<head>
    <link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/select2.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/auction.css">


    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet"/>

    <title>Auction</title>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar"
                    aria-expanded="false"> 
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="<c:url value="/"/>" class="navbar-brand"> <spring:message code="auction.name.app"/></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">

            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/home"/>" class="active"><spring:message code="auction.menu.home"/> </a></li>
                <li><a href="<c:url value="/lots/1"/>">Лоты</a></li>
                <li><a href="<c:url value="/"/>"><spring:message code="auction.menu.about"/></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                <c:when test="${loggedInUserEmail == null}">
                <li class="nav navbar-left"><a href="<c:url value="/login"/>"><span
                        class="glyphicon glyphicon-users"></span> <spring:message code="auction.sing.in"/></a>
                    </c:when>
                    <c:otherwise>
                <li class="nav navbar-left"><a><spring:message code="auction.hello"/> ${loggedInUserEmail}</a> <a
                        href="<c:url value="/logout"/>"><span
                        class="glyphicon glyphicon-users"></span> <spring:message code="auction.log.out"/></a>
                    </c:otherwise>
                    </c:choose>
                </li>


            </ul>

        </div>
    </div>
</nav>
<div class="container">

    <div class="panel panel-default">
        <jsp:include page="../common/message.jsp"/>

        <div class="panel-heading">
            <h1 class="panel-title text-uppercase">
                <span class="glyphicon glyphicon-user"></span> All users
            </h1>
        </div>

        <a class="btn btn-large btn-success" data-toggle="collapse" href="#collapseExample"
           aria-expanded="false" aria-controls="collapseExample">Create new categories</a>
        <div class="collapse" id="collapseExample">
            <form:form action="/admin/category/create" method="post" modelAttribute="categoryForm"
                       enctype="multipart/form-data">
                <form:label path="name">
                </form:label>
                <form:input path="name" class="input-block-level"/>
                <button class="btn  btn-danger" type="submit">Add category</button>
            </form:form>
        </div>
        <div class="panel-body">

            <!-- User table -->
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Category id</th>
                    <th>Category name</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <c:if test="${not empty categories}">
                    <c:forEach items="${categories}" var="singleCategory">
                        <tbody>
                        <tr class="editable-row">
                            <td data-label="id">
                                <span class="view">${singleCategory.id}</span>

                                <span class="editor">
                                        <input type="text" value="${singleCategory.id}" name="id" readonly="readonly">
                                </span>
                            </td>
                            <td data-label="name">
                                <span class="view">${singleCategory.name}</span>

                                <span class="editor">
                                        <input type="text" name="name">
                                </span>
                            </td>
                            <td class="trigger"></td>
                            <td>
                                <form:form action="/admin/category/delete/${singleCategory.id}" method="post"
                                           name="deleteForm" enctype="multipart/form-data">
                                    <button class="btn  btn-danger" type="submit">Delete</button>
                                </form:form>
                            </td>
                        </tr>
                        </tbody>
                    </c:forEach>
                </c:if>
            </table>
            <nav>
                <c:forEach items="${pagCategories}" var="categoryItem">
                    <ul class="pagination pagination-sm">
                        <li>
                            <a href="<c:url value="/admin/user/${categoryItem}"/>">
                                <span>${categoryItem}</span>
                            </a>
                        </li>
                    </ul>
                </c:forEach>
            </nav>
        </div>

    </div>

</div>

<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/admin/row-edit.js"></script>
<script>
    $(function () {
        $('.editable-row').rowEditor({
            editor: '.editor',
            view: '.view',
            trigger: '.trigger',
            apiUrl: '/admin/category/update'
        });
    });
</script>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<footer>
    <div class="footiedata">
        <div class="container">

            <form class="formlist">

            </form>

            <div class="flexylist">

                <ul class="flexcont">

                </ul>
                <ul class="flexcont1">

                </ul>

            </div>
        </div>
    </div>
    <div class="bottomsecond">
        <div class="copee"><spring:message code="footer.company.name"/></div>
    </div>
</footer>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
</body>
</html>

