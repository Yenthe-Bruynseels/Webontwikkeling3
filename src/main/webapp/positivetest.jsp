<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Positive Test</title>
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
                    <li><a href="Controller?command=SearchContactsSinceLastPositiveTest">Search</a></li> </c:if>

                <c:if test="${user.role == 'ADMIN' || user.role == 'CUSTOMER'}">
                    <li id="actual"><a href="Controller?command=PositiveTest">Register Positive Test</a></li>
                </c:if>
                <c:if test="${user.role != 'ADMIN' && user.role != 'CUSTOMER'}">
                    <li><a href="Controller?command=Register">Register</a></li>
                </c:if>
            </ul>
        </nav>
        <h2>
            Register Positive Test
        </h2>

    </header>
    <main>
        <c:if test="${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <form novalidate="novalidate" method="post" action="Controller?command=RegisterPositiveTest" id="form">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="date">Date</label><input id="date" type="date" name="date" required value="${prevDate}"/></p>
            <p><input type="submit" id="RegisterPositiveTest" value="Register Test"></p>

        </form>


        <c:if test="${user.role == 'ADMIN'}">
            <table>
                <tr>
                    <th>Username</th>
                    <th>Date</th>
                </tr>

                <c:forEach var="test" items="${tests}">
                    <tr>
                        <td><c:out value="${test.userid}"/></td>
                        <td><c:out value="${test.date}"/></td>
                    </tr>
                </c:forEach>
                <caption>Test Overview</caption>
            </table>
        </c:if>


    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
<script src="scripts/formAuthentication.js"></script>
</body>
</html>

