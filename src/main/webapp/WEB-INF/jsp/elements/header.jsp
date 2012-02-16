<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %> <!-- does not loading without this directive!!! -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- unable to use &lt;c:* without this directive!!! -->
<html>
<head>
    <!-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/img/styles.css" /> -->
    <link rel="stylesheet" type="text/css" href='<c:url value="/img/styles.css" />' />
    <link rel="stylesheet" type="text/css" href='<c:url value="/img/form.css" />' />
    <title>${param.header}</title>
</head>
<body>
<div class="menu">
    <b class="big">Menu</b>
    <ul>
        <li><a href='<c:url value="/" />'>Home</a></li>
        <li><a href='<c:url value="/users" />'>Users list</a></li>
    </ul>
</div>
<div class="container">
