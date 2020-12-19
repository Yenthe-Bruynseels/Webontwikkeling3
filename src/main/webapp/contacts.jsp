<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Contacts</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
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
                    <li id="actual"><a href="Controller?command=AllContactsUser">Contacts</a></li>
                </c:if>

                <c:if test="${user.role == 'ADMIN' || user.role == 'CUSTOMER'}">
                    <li><a href="Controller?command=SearchContactsSinceLastPositiveTest">Search</a></li>
                </c:if>

                <c:if test="${user.role == 'ADMIN' || user.role == 'CUSTOMER'}">
                    <li><a href="Controller?command=PositiveTest">Register Positive Test</a></li>
                </c:if>
                <c:if test="${user.role != 'ADMIN' && user.role != 'CUSTOMER'}">
                    <li><a href="Controller?command=Register">Register</a></li>
                </c:if>
            </ul>
        </nav>
        <h2>Contact Overview</h2>

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

        <c:if test="${user.role == 'ADMIN'}">
            <form novalidate="novalidate" method="post" action="Controller?command=AllContacts">
                <!-- novalidate in order to be able to run tests correctly -->
                <input id="AllContactsButton" type="submit" formaction="Controller?command=AllContacts"
                       value="See all contacts"/>
                <input type="submit" formaction="Controller?command=AllContactsUser" value="See own contacts"/>
            </form>
        </c:if>

        <c:choose>
        <c:when test="${adminAllContacts == true}">
        <h3>All Contacts</h3>
        <form method="post" action="Controller?command=AllContactsFromUntil">
            <input type="date" id="from" name="from" required value="<c:out value="${prevFrom}"/>">
            <input type="time" id="fromTime" name="fromTime" required value="<c:out value="${prevFromTime}"/>">
            <input type="date" id="until" name="until" required value="<c:out value="${prevUntil}"/>">
            <input type="time" id="untilTime" name="untilTime" required value="<c:out value="${prevUntilTime}"/>">
            <input type="submit" value="Filter">
            <input type="submit" value="Clear Filter" formaction="Controller?command=AllContacts">
            </c:when>
            <c:otherwise>
            <h3>Your Contacts</h3>
            <form method="post" action="Controller?command=AllContactsUserFromUntil">
                <input type="date" id="from" name="from" required value="<c:out value="${prevFrom}"/>">
                <input type="time" id="fromTime" name="fromTime" required value="<c:out value="${prevFromTime}"/>">
                <input type="date" id="until" name="until" required value="<c:out value="${prevUntil}"/>">
                <input type="time" id="untilTime" name="untilTime" required value="<c:out value="${prevUntilTime}"/>">
                <input type="submit" value="Filter">
                <input type="submit" value="Clear Filter" formaction="Controller?command=AllContactsUser">
                </c:otherwise>
                </c:choose>
            </form>

            <table>
                <tr>
                    <th>Date</th>
                    <c:choose>
                        <c:when test="${adminAllContacts == true}">
                            <th>Username</th>
                            <th>Contactname</th>
                        </c:when>
                        <c:otherwise>
                            <th>Name</th>
                        </c:otherwise>
                    </c:choose>
                    <th>E-mail</th>
                    <th>Phone number</th>
                    <th>Last Test result</th>
                </tr>

                <c:forEach var="contact" items="${contacts}">
                    <tr data-date="<fmt:formatDate pattern="yyyyMMdd" value="${contact.timestamp}"/>">
                        <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${contact.timestamp}"/></td>
                        <c:if test="${adminAllContacts == true}">
                            <td><c:out value="${contact.userid}"/></td>
                        </c:if>
                        <td><c:out value="${contact.firstName} ${contact.lastName}"/></td>
                        <td><c:out value="${contact.email}"/></td>
                        <td><c:out value="${contact.phonenumber}"/></td>
                        <td><c:out value="Geen idee hoe"/></td>
                        <c:if test="${user.role == 'ADMIN'}">
                            <td><a href="Controller?command=DeleteContact&id=${contact.id}">Delete</a></td>
                        </c:if>
                    </tr>
                </c:forEach>
                <caption>Contact Overview</caption>
            </table>

            <form novalidate="novalidate" method="post" action="Controller?command=AddContact">
                <!-- novalidate in order to be able to run tests correctly -->
                <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName"
                                                                   required
                                                                   value="<c:out value="${firstNamePreviousValue}"/>"/>
                </p>
                <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName"
                                                                 required
                                                                 value="<c:out value="${lastNamePreviousValue}"/>"/></p>
                <p><label for="date">Date</label><input id="date" type="date" name="date" required
                                                        value="<c:out value="${prevDate}"/>"/></p>
                <p><label for="hour">Hour</label><input id="hour" type="time" name="hour" required
                                                        value="<c:out value="${prevHour}"/>"/></p>
                <p><label for="phonenumber">Phone number</label><input id="phonenumber" name="phonenumber" required
                                                                       type="tel" placeholder="+32"
                                                                       value="<c:out value="${phonenumberPreviousValue}"/>"/>
                </p>
                <p><label for="email">Email</label><input type="email" id="email" name="email" required
                                                          value="<c:out value="${emailPreviousValue}"/>"/></p>
                <p><input type="submit" id="addContact" value="Add Contact"></p>

            </form>

    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
<script src="scripts/formAuthentication.js"></script>
=
</body>
</html>