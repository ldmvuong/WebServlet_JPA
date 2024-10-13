<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 10/12/2024
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp" %>

<a href="<c:url value='/admin/category/add'/>">Add Category</a>

<table border="1" width="100%">
    <tr>
        <th>STT</th>
        <th>Image</th>
        <th>Category name</th>
        <th>Status</th>
        <th>Action</th>
    </tr>

    <c:forEach items="${listcate}" var="cate" varStatus="STT">
        <tr>
            <td>${STT.index + 1}</td>

            <td>
                <c:choose>
                    <c:when test="${cate.images.substring(0, 5) != 'https'}">
                        <c:url value="/image?fname=${cate.images}" var="imgUrl"/>
                    </c:when>
                    <c:otherwise>
                        <c:url value="${cate.images}" var="imgUrl"/>
                    </c:otherwise>
                </c:choose>
                <img height="150" width="200" src="${imgUrl}" />
            </td>

            <td>${cate.categoryname}</td>

            <td>
                <c:if test="${cate.status == 1}">
                    Hoạt động
                </c:if>
                <c:if test="${cate.status == 0}">
                    Không hoạt động
                </c:if>
            </td>

            <td>
                <a href="<c:url value='/admin/category/edit?id=${cate.categoryid}'/>" class="center">Sửa</a> |
                <a href="<c:url value='/admin/category/delete?id=${cate.categoryid}'/>" class="center">Xóa</a> |
                <a href="<c:url value='/admin/video/add?categoryId=${cate.categoryid}'/>" class="center">Thêm video</a>
            </td>
        </tr>
    </c:forEach>
</table>
