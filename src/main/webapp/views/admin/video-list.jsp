<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 10/13/2024
  Time: 11:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp" %>

<a href="<c:url value='/admin/video/add'/>">Add Video</a>

<table border="1" width="100%">
    <tr>
        <th>STT</th>
        <th>Title</th>
        <th>Description</th>
        <th>Poster (URL)</th>
        <th>Views</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${listVideos}" var="video" varStatus="index">
        <tr>
            <td>${index.index + 1}</td>

            <td>${video.title}</td>
            <td>${video.description}</td>

            <!-- Display poster as text -->
            <td>${video.poster}</td>

            <td>${video.views}</td>
            <td>
                <c:if test="${video.active == true}">
                    Active
                </c:if>
                <c:if test="${video.active == false}">
                    Inactive
                </c:if>
            </td>
            <td>
                <a href="<c:url value='/admin/video/edit?id=${video.videoid}'/>" class="center">Edit</a>
                |
                <a href="<c:url value='/admin/video/delete?id=${video.videoid}'/>" class="center">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
