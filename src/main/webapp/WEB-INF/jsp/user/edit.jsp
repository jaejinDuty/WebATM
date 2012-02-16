<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/WEB-INF/jsp/elements/header.jsp" flush="true">
    <jsp:param name="header" value="Edit user" />
</jsp:include>

<c:if test="${not empty msg}">
    <p>${msg}</p>
</c:if>

<c:url var="formAction" value="/user/save" />
<form:form action="${formAction}" commandName="user" method="post" class="wide">
    <form:hidden path="id" />
    <fieldset>
        <legend>
            <c:choose>
                <c:when test="${not empty user.email}">
                    ${user.email}
                </c:when>
                <c:otherwise>new user</c:otherwise>
            </c:choose>
        </legend>
        <div class="clearfix req">
            <label for="email">email:</label>
            <form:input path="email" name="email" />
        </div>
        <div class="clearfix req">
            <label for="password">password:</label>
            <form:input path="password" name="password" />
        </div>
        <div class="req fm-submit">
            <input name="submit" type="submit" value="Save" />
        </div>
    </fieldset>
</form:form>

<jsp:include page="/WEB-INF/jsp/elements/footer.jsp" />