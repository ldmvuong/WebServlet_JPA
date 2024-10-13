<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 10/13/2024
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"  %>

<form action="<c:url value='/admin/video/insert'/>" method="post">
    <label for="title">Video Title:</label><br>
    <input type="text" id="title" name="title"><br><br>

    <label for="description">Description:</label><br>
    <textarea id="description" name="description"></textarea><br><br>

    <label for="poster">Poster URL:</label><br>
    <input type="text" id="poster" name="poster"><br><br>

    <input type="hidden" name="categoryId" value="${category.categoryid}">

    <label>Status:</label><br>
    <input type="radio" id="status_active" name="active" value="1">
    <label for="status_active">Active</label><br>
    <input type="radio" id="status_inactive" name="active" value="0">
    <label for="status_inactive">Inactive</label><br><br>

    <input type="submit" value="Submit">
</form>


