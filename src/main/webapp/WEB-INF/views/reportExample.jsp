<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${formName}</h1>


<div class="col-md-7">
    <select class="form-control input-sm mb-md readonly"
            name="reportCode" id="reportCode">
        <option value="">Please Select One</option>
        <c:forEach items="${branchList}" var="list">
            <option
                    value="${list.id}">${list.name}</option>
        </c:forEach>
    </select>
</div>

</body>
</html>
