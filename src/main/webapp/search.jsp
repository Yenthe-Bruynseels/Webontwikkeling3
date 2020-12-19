<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Search</title>
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
                    <li id="actual"><a href="Controller?command=SearchContactsSinceLastPositiveTest">Search</a></li> </c:if>

                <c:if test="${user.role == 'ADMIN' || user.role == 'CUSTOMER'}">
                    <li><a href="Controller?command=PositiveTest">Register Positive Test</a></li>
                </c:if>
                <c:if test="${user.role != 'ADMIN' && user.role != 'CUSTOMER'}">
                    <li><a href="Controller?command=Register">Register</a></li>
                </c:if>
            </ul>
        </nav>


    </header>
    <main>

        <c:if test="${not empty contacts}">
            <h2 id="SearchContactsToWarn">
                Contact the following people
            </h2>
        </c:if>

        <c:if test="${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach items="${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
<c:choose>
    <c:when test="${not empty contacts}">
        <table>
            <tr>
                <th>Name</th>
                <th>Phonenumber</th>
                <th>Email</th>
            </tr>

            <c:forEach var="contact" items="${contacts}">
                <tr>
                    <td><c:out value="${contact.firstName} ${contact.lastName}"/></td>
                    <td><c:out value="${contact.phonenumber}"/></td>
                    <td><c:out value="${contact.email}"/></td>
                </tr>
            </c:forEach>
            <caption>Overview of contacts since last positive test</caption>
        </table>
    </c:when>
    <c:otherwise>
        <h2 id="SearchContactsToWarn0">Goed gedaan, er moeten geen mensen gecontacteerd worden.</h2>
    </c:otherwise>
</c:choose>

        <c:if test="${not empty users}">
        <h2 id="SearchMany">
            The following people have 25 or more contacts
        </h2>


            <table id="ManyContactsTable">
                <tr>
                    <th>Username</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Number of Contacts</th>
                </tr>

                <c:forEach var="user" items="${users}">
                    <tr>
                        <td><c:out value="${user.userid}"/></td>
                        <td><c:out value="${user.firstName} ${user.lastName}"/></td>
                        <td><c:out value="${user.email}"/></td>
                        <td><c:out value="${user.aantalContacten}"/></td>
                    </tr>
                </c:forEach>
                <caption>Overview of contacts since last positive test</caption>
            </table>
        </c:if>

    </main>



    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>

