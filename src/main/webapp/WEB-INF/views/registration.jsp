<!-- registration.jsp -->
<!DOCTYPE html>
<html lang="en">
<head>
    <title>User Registration</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/5.3.0/css/bootstrap.min.css">

</head>
<body>

<div class="container mt-5">
    <h2 class="text-center">User Registration</h2>

    <!-- Display the message if it exists -->
    <c:if test="${not empty message}">
        <div class="alert alert-success show mt-3" role="alert">
                ${message}
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </c:if>

    <form action="/user/register" method="post" class="mt-3">
        <div class="form-group">
            <label for="userName">Name:</label>
            <input type="text" class="form-control" id="userName" name="userName" required>
        </div>
        <input type="hidden" class="form-control" id="userId" name="userId">

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="phoneNo">Phone Number:</label>
            <input type="text" class="form-control" id="phoneNo" name="phoneNo" required>
        </div>

<%--        <div class="form-group">--%>
<%--            <label for="password">Password:</label>--%>
<%--            <input type="password" class="form-control" id="password" name="password" required>--%>
<%--        </div>--%>
        <div class="form-group">
            <label>Status:</label>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="status" id="active" value="Y" checked>
                <label class="form-check-label" for="active">Active</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="status" id="inactive" value="N">
                <label class="form-check-label" for="inactive">Inactive</label>
            </div>
        </div>

        <button type="submit" class="btn btn-primary mt-2">Register</button>
    </form>
</div>
<!-- Include jQuery locally -->
<script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<%--<script type="text/javascript" src="webjars/jquery/3.6.4/jquery.min.js"></script>--%>



<!-- Your additional script -->
<script>
    // Check if jQuery is defined
    if (typeof jQuery !== 'undefined') {
        // jQuery is loaded, you can now use it
        $(document).ready(function () {
            // Your jQuery code here
            console.log("jQuery is loaded!");
        });
    } else {
        // jQuery is not loaded
        console.log("jQuery is not loaded!");
    }
</script>
</body>
</html>
