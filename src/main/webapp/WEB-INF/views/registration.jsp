<!DOCTYPE html>
<!-- registration.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ page session="false" %>--%>

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
    <c:if test="${not empty message}}">
        <div class="alert alert-success show mt-3 message-div" role="alert">
                ${message}
            <button type="button" class="btn btn-danger btn-sm float-end close-button">
                <span aria-hidden="true">X</span>
            </button>
        </div>
    </c:if>
    <div class="card-body">
        <div class="card">
            <form action="/user/register" method="post" class="m-3">
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
    </div>
</div>
<!-- Add a container to display the list of users -->
<div id="userListContainer" class="mt-3"></div>
</body>


<footer>
    <!-- Include jQuery locally -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
    <%--<script type="text/javascript" src="webjars/jquery/3.6.4/jquery.min.js"></script>--%>
    <script>
        // Check if jQuery is defined
        if (typeof jQuery !== 'undefined') {
            // jQuery is loaded, you can now use it
            $(document).ready(function () {
                // Closes the message
                $('.close-button').click(function (e) {
                    $('.message-div').hide()
                })

                // Fetch the list of users using AJAX
                $.ajax({
                    type: "GET",
                    url: "${pageContext.request.contextPath}/user/list",
                    contentType: "application/json",
                    success: function (users) {
                        // Update the HTML to display the list of users
                        displayUserList(users);
                    },
                    error: function (error) {
                        console.error("Error fetching user list: " + JSON.stringify(error));
                    }
                });

                function displayUserList(users) {
                    var userListContainer = $("#userListContainer");
                    userListContainer.empty();

                    // Check if the users array is not empty
                    if (users && users.length > 0) {
                        // Create a table
                        var table = $("<table class='table'>");

                        // Add table header
                        var header = $("<thead><tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Status</th><th>Actions</th></tr></thead>");
                        table.append(header);

                        // Create a table body
                        var tbody = $("<tbody>");

                        // Iterate over each user and append to the table
                        $.each(users, function (index, user) {
                            var row = $("<tr>");
                            row.append("<td>" + user.userId + "</td>");
                            row.append("<td>" + user.userName + "</td>");
                            row.append("<td>" + user.email + "</td>");
                            row.append("<td>" + user.phoneNo + "</td>");
                            row.append("<td>" + user.status + "</td>");

                            // Add edit and delete buttons
                            var actions = $("<td><button class='btn btn-primary btn-sm' onclick='editUser(" + user.userId + ")'>Edit</button> " +
                                "<button class='btn btn-danger btn-sm' onclick='deleteUser(" + user.userId + ")'>Delete</button></td>");
                            row.append(actions);

                            tbody.append(row);
                        });

                        // Append table body to the table
                        table.append(tbody);

                        // Append the table to the container
                        userListContainer.append(table);
                    } else {
                        userListContainer.text("No users found.");
                    }
                }
            });
        } else {
            // jQuery is not loaded
            console.log("jQuery is not loaded!");
        }
    </script>
</footer>
</html>
