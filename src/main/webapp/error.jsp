<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <meta charset="UTF-8">
    <title>error</title>
</head>
<body>
<header>
    <h1>
        <span>Cafe Campus Telenet</span>
    </h1>
    <nav>
        <ul>
            <li><a href="Controller">Home</a></li>
            <c:if test="${user.role == 'ADMIN'}">
                <li><a href="Controller?command=Overview">Users</a></li>
            </c:if>

            <c:if test="${user.role == 'ADMIN' || user.role == 'CUSTOMER'}">
                <li><a href="Controller?command=Contacts">Contacts</a></li>
            </c:if>

            <c:if test="${user.role == 'ADMIN' || user.role == 'CUSTOMER'}">
                <li><a href="Controller?command=SearchContactsSinceLastPositiveTest">Search</a></li>
            </c:if>

            <c:if test="${user.role == 'ADMIN' || user.role == 'CUSTOMER'}">
                <li><a href="Controller?command=PositiveTest">Register Positive Test</a></li>
            </c:if>
            <li><a href="Controller?command=Register">Register</a></li>
        </ul>
    </nav>
    <h2>Home</h2>

</header>
<main id="container">
    <article>
    </article>
    <div class="alert-danger">
        <p>${error}</p>
    </div>
</main>
</body>
</html>
