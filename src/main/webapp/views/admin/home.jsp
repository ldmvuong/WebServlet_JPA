<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 10/12/2024
  Time: 9:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp" %>
<html>
<head>
    <title>Trang chu cua admin</title>
</head>
<body>
<%
    if (request.getParameter("logout") != null) {
        session.invalidate(); // xóa session
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>
<c:choose>
    <c:when test="${sessionScope.account == null}">
        <div class="col-sm-6">
            <ul class="list-inline right-topbar pull-right">
                <li><a href="${pageContext.request.contextPath }/login">Đăng nhập</a>
                    | <a href="${pageContext.request.contextPath }/register">Đăng ký</a></li>
                <li><i class="search fa fa-search search-button"></i></li>
            </ul>
        </div>
    </c:when>
    <c:otherwise>
        <div class="col-sm-6">
            <ul class="list-inline right-topbar pull-right">
                <li><a href="${pageContext.request.contextPath
}/member/myaccount">${sessionScope.account.fullname}</a> | <a href="?logout=true">Đăng Xuất</a></li>
                <li><i class="search fa fa-search search-button"></i></li>
            </ul>
        </div>
    </c:otherwise></c:choose>
</body>
</html>
