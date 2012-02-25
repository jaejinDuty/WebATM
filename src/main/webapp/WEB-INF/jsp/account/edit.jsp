<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/WEB-INF/jsp/elements/header.jsp" flush="true">
    <jsp:param name="header" value="Edit account" />
</jsp:include>

<c:if test="${not empty msg}">
    <p>${msg}</p>
</c:if>

<c:if test="${not empty errors}">
    <jsp:include page="/WEB-INF/jsp/elements/errors.jsp" flush="true">
        <jsp:param name="errors" value="${errors}" />
    </jsp:include>
</c:if>

<c:url var="formAction" value="/account/save" />
<form:form action="${formAction}" commandName="account" method="post" class="wide">
    <form:hidden path="id" />
    <fieldset>
        <legend>
            <c:choose>
                <c:when test="${not empty account.title}">
                    ${user.email}
                </c:when>
                <c:otherwise>new user</c:otherwise>
            </c:choose>
        </legend>
        <div class="clearfix req">
            <label for="title">title:</label>
            <form:input path="title" name="title" />
        </div>
        <div class="req fm-submit">
            <input name="submit" type="submit" value="Save" />
        </div>
    </fieldset>
</form:form>

<jsp:include page="/WEB-INF/jsp/elements/footer.jsp" />