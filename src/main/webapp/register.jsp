<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1><span>Cafe Campus Telenet</span></h1>
        <nav>
            <ul>
                <li><a href="Controller">Home</a></li>
                <c:if test="${user.role == 'ADMIN'}">
                    <li><a href="Controller?command=Overview">Users</a></li>
                </c:if>

                <c:if test="${user.role == 'ADMIN' || user.role == 'CUSTOMER'}">
                    <li><a href="Controller?command=AllContactsUser">Contacts</a></li>
                </c:if>

                <c:if test="${user.role == 'ADMIN' || user.role == 'CUSTOMER'}">
                    <li><a href="Controller?command=SearchContactsSinceLastPositiveTest">Search</a></li>
                </c:if>

                <c:if test="${user.role == 'ADMIN' || user.role == 'CUSTOMER'}">
                    <li><a href="Controller?command=PositiveTest">Register Positive Test</a></li>
                </c:if>
                <li id="actual"><a href="Controller?command=Register">Register</a></li>
            </ul>
        </nav>
        <h2>
            Register
        </h2>

    </header>
    <main id="main">
        <c:if test="${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <form novalidate="novalidate" method="post" action="Controller?command=AddUser" id="form">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="userid">Username</label><input type="text" id="userid" name="userid"
                                                          required value="<c:out value='${useridPreviousValue}'/>"/></p>
            <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
                                                               required
                                                               value="<c:out value='${firstNamePreviousValue}'/>"/></p>
            <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName"
                                                             required
                                                             value="<c:out value='${lastNamePreviousValue}'/>"/></p>
            <p><label for="email">Email</label><input type="email" id="email" name="email" required
                                                      pattern="^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$"
                                                      value="<c:out value='${emailPreviousValue}'/>"/></p>
            <p><label for="password">Password</label><input type="password" id="password" name="password"
                                                            required value="<c:out value='${passwordPreviousValue}'/>"/>
            </p>
            <p><input type="submit" id="signUp" value="Sign Up"></p>
        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
<script src="scripts/formAuthentication.js">
</script>
</body>
</html>
