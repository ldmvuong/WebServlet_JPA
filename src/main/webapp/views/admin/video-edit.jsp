<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 10/13/2024
  Time: 11:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp" %>

<form action="<c:url value='/admin/video/update'/>" method="post">
    <input type="hidden" id="videoid" name="id" value="${video.videoid}">

    <label for="title">Video Title:</label><br>
    <input type="text" id="title" name="title" value="${video.title}"><br><br>

    <label for="description">Description:</label><br>
    <textarea id="description" name="description">${video.description}</textarea><br><br>

    <label for="poster">Poster URL:</label><br>
    <input type="text" id="poster" name="poster" value="${video.poster}"><br><br>

    <label>Status:</label><br>
    <input type="radio" id="status_active" name="active" value="true" ${video.active ? 'checked' : ''}>
    <label for="status_active">Active</label><br>
    <input type="radio" id="status_inactive" name="active" value="false" ${!video.active ? 'checked' : ''}>
    <label for="status_inactive">Inactive</label><br><br>

    <input type="submit" value="Update">
</form>

