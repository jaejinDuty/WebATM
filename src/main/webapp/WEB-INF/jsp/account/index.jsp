<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="/WEB-INF/jsp/elements/header.jsp" flush="true">
    <jsp:param name="header" value="User accounts" />
</jsp:include>
    <c:if test="${not empty msg}">
        <p>${msg}</p>
    </c:if>

    <h3>Accounts:</h3>
    <sec:authorize ifAllGranted="ROLE_ADMIN">
        <a href="/account/add/${userId}">add account</a>
    </sec:authorize>
    <c:choose>
        <c:when test="${accounts ne null}">
            <table cellpadding="0" cellspacing="10" class="report">
                <tr>
                    <th>Id</th>
                    <th>Title</th>
                    <sec:authorize ifAllGranted="ROLE_ADMIN">
                        <th>Actions</th>
                    </sec:authorize>
                </tr>
                <c:forEach var="account" items="${accounts}">
                    <tr>
                        <td><c:out value="${account.id}" /></td>
                        <td>${account.title}</td>
                        <td>
                            <sec:authorize ifAllGranted="ROLE_ADMIN">
                                <a href='<c:url value="/account/edit/${account.id}" />'>edit</a>
                                | <a href='<c:url value="/account/delete/${account.id}" />'>delete</a>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <h4>No accounts</h4>
        </c:otherwise>
    </c:choose>
<jsp:include page="/WEB-INF/jsp/elements/footer.jsp" />