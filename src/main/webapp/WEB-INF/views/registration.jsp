<!DOCTYPE html>
<!-- registration.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ page session="false" %>--%>

<html lang="en">
<head>
    <title>User Registration</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/5.3.0/css/bootstrap.min.css">
    <link rel="icon" type="image/png" href="reg.png">

</head>
<body>

<div class="container mt-5">
    <h2 class="text-center">User Registration</h2>
<div></div>
    <!-- Display the message if it exists -->
    <%--    <c:if test="${not empty message}">--%>
    <div class="alert ${not empty messageCode and messageCode == 99 ? 'alert-danger' : 'alert-success'} show mt-3 message-div ${not empty message ? '': 'd-none'}" role="alert">
        ${not empty message ? message : ''}
        <button type="button" class="btn btn-danger btn-sm float-end close-button">
            <span aria-hidden="true">X</span>
        </button>
    </div>
    <%--    </c:if>--%>
    <div class="card-body">
        <div class="card">
            <form action="/user/register" method="post" class="m-3">
                <div class="form-group mt-1">
                    <label for="userName">Name:</label>
                    <input type="text" class="form-control" id="userName" name="userName" required>
                </div>
                <input type="hidden" class="form-control" id="userId" name="userId">

                <div class="form-group mt-1">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>

                <div class="form-group mt-1">
                    <label for="phoneNo">Phone Number:</label>
                    <input type="text" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*?)\..*/g, '$1');" class="form-control" id="phoneNo" name="phoneNo" maxlength="11" required>
                </div>

                <div class="form-group mt-1">
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

                <button type="reset" class="btn btn-danger mt-2" id="resetButton">Reset</button>
                <button type="submit" class="btn btn-primary mt-2" id="submitButton">Register</button>
            </form>
        </div>
    </div>
</div>
<div class="container mt-5">
    <h2 class="text-center">Users List</h2>
    <div class="card-body">
        <div class="card">
            <!-- Add a container to display the list of users -->
            <div id="userListContainer" class="m-3">
                <table class="table" id="userTable">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
        </div>
    </div>

</div>
</body>


<footer>
    <!-- Include jQuery locally -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
    <%--<script type="text/javascript" src="webjars/jquery/3.6.4/jquery.min.js"></script>--%>
    <script>
        // Check if jQuery is defined
        if (typeof jQuery !== 'undefined') {
            function editUser(user) {
                // console.log(user)
                $("#userId").val(user.userId)
                $("#userName").val(user.userName)
                $("#email").val(user.email)
                if (user.status == 'Y') {
                    $("#active").prop('checked', true)
                } else {
                    $("#inactive").prop('checked', true)
                }
                // $("#status").val(user.status)
                $("#phoneNo").val(user.phoneNo)
                $("#submitButton").html("Update")

                scrollTop();
            }

            function deleteUser(userId) {
                var confirmation = confirm("Are you sure you want to delete this user?");

                // console.log(userId)
                if (confirmation) {
                    $.ajax({
                        type: "GET",
                        url: "${pageContext.request.contextPath}/user/delete",
                        data: {
                            userId: userId
                        },
                        contentType: "application/json",
                        success: function (data) {
                            console.log(data)
                            if (data.messageCode == 1) {
                                $('.message-div').removeClass("alert-danger").removeClass("alert-success").removeClass("d-none").addClass("alert-success").html(data.message).show()
                                getAllUsers()
                            } else {
                                $('.message-div').removeClass("alert-danger").removeClass("alert-success").removeClass("d-none").addClass("alert-danger").html(data.message).show()
                            }
                            scrollTop();
                        },
                        error: function (error) {
                            console.error("Error deleting user: " + JSON.stringify(error));
                        }
                    });
                }
            }

            function scrollTop() {
                // Scroll to the top of the page
                $('html, body').animate({scrollTop: 0}, 'fast');
            }

            function getAllUsers() {
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
            }

            function displayUserList(users) {
                var userListContainer = $("#userListContainer");

                // Check if the users array is not empty
                if (users && users.length > 0) {

                    let tableBody = $("#userTable tbody");
                    tableBody.empty();
                    // Iterate over each user and append to the table
                    $.each(users, function (index, user) {
                        var row = $("<tr>");
                        row.append("<td>" + user.userId + "</td>");
                        row.append("<td>" + user.userName + "</td>");
                        row.append("<td>" + user.email + "</td>");
                        row.append("<td>" + user.phoneNo + "</td>");
                        let statusHtml = '';
                        if(user.status == 'Y') {
                            statusHtml = "<span class='btn-success'>Active</span>";
                        } else {
                            statusHtml = "<span class='btn-danger'>Inactive</span>";
                        }
                        row.append("<td>" + statusHtml + "</td>");

                        // Add edit and delete buttons
                        var actions = $("<td><button class='btn btn-primary btn-sm' onclick='editUser(" + JSON.stringify(user) + ")'>Edit</button> " +
                            "<button class='btn btn-danger btn-sm' onclick='deleteUser(" + user.userId + ")'>Delete</button></td>");
                        row.append(actions);

                        tableBody.append(row);
                    });

                } else {
                    console.log("No users found.");
                }
            }

            // jQuery is loaded, you can now use it
            $(document).ready(function () {
                // Closes the message
                $('.close-button').click(function (e) {
                    $('.message-div').hide()
                })

                getAllUsers();

                $("#resetButton").click(function (e) {
                    $("#submitButton").html("Register")
                    $("#userId").empty()
                })
            });
        } else {
            // jQuery is not loaded
            console.log("jQuery is not loaded!");
        }
    </script>
</footer>
</html>
