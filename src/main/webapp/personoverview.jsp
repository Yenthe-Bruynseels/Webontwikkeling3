<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Overview</title>
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
                    <li id="actual"><a href="Controller?command=Overview">Users</a></li>
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
                <c:if test="${user.role != 'ADMIN' && user.role != 'CUSTOMER'}">
                    <li><a href="Controller?command=Register">Register</a></li>
                </c:if>
            </ul>
        </nav>
        <h2>
            User Overview
        </h2>

    </header>
    <main>
        <table>
            <tr>
                <th>E-mail</th>
                <th>First Name</th>
                <th>Last Name</th>
            </tr>

            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.firstName}"/></td>
                    <td><c:out value="${user.lastName}"/></td>
                    <c:if test="${sessionScope.get('user').role == 'ADMIN'}">
                        <c:if test="${user.role != 'ADMIN'}">
                            <td><a href="Controller?command=Delete&userid=<c:out value="${user.userid}"/>">Delete</a>
                            </td>
                        </c:if>
                    </c:if>
                </tr>
            </c:forEach>

            <caption>User Overview</caption>
        </table>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>