<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="/WEB-INF/jsp/elements/header.jsp" flush="true">
    <jsp:param name="header" value="Dashboard" />
</jsp:include>
    <c:if test="${not empty msg}">
        <p>${msg}</p>
    </c:if>

    <h3>Users:</h3>
    <!-- no c:if => c:else =( using c:choose -->
    <sec:authorize ifAllGranted="ROLE_ADMIN">
        <a href="/user/add">add user</a>
    </sec:authorize>
    <c:choose>
        <c:when test="${users ne null}">
            <table cellpadding="0" cellspacing="10" class="report">
                <tr>
                    <th>Id</th>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td><c:out value="${user.id}" /></td>
                        <td>${user.email}</td>
                        <td>
                            <a href='<c:url value="/user/accounts/${user.id}" />'>accounts</a>
                            <sec:authorize ifAllGranted="ROLE_ADMIN">
                                | <a href='<c:url value="/user/edit/${user.id}" />'>edit</a>
                                | <a href='<c:url value="/user/delete/${user.id}" />'>delete</a>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <jsp:include page="/WEB-INF/jsp/elements/errors.jsp" flush="true">
                <jsp:param name="errors" value="${msg}" />
            </jsp:include>
        </c:otherwise>
    </c:choose>
<jsp:include page="/WEB-INF/jsp/elements/footer.jsp" />