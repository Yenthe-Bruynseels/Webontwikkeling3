<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Home</title>
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
                <li id="actual"><a href="Controller">Home</a></li>
                <li><a href="Controller?command=Overview">Overview</a></li>
                <li><a href="Controller?command=Register">Register</a></li>
            </ul>
        </nav>
        <h2>Home</h2>

    </header>
    <main><p>Welkom bij Café Campus Telenet! Corona gooit in het dagelijkse leven telkens roet in het eten, maar
        gelukkig zijn wij een café en serveren wij geen eten. Bijgevolg zal alles even goed blijven smaken, echter
        kunnen wij deze pandemie niet negeren, waardoor ook wij genoodzaakt zijn enkele maatregelen te treffen. Eén
        hiervan ziet u op deze website; u dient zich te registreren bij elk bezoek. Op deze manier kunnen wij bijhouden
        wie er wanneer in het café geweest is en bijgevolg als er iemand van onze klanten positief test ook meteen de
        juiste mensen verwittigen om zich te laten testen!</p>
        <p>Samen krijgen we Corona klein!</p>

        <c:if test="${error != null}">
            <div class="alert-danger">
                <p>${error}</p>
            </div>
        </c:if>
        <c:choose>
            <c:when test="${sessionScope.get('user') == null}">
                <form novalidate="novalidate" method="post" action="Controller?command=LogIn">
                    <!-- novalidate in order to be able to run tests correctly -->
                    <p><label for="userid">User id</label>
                        <input type="text" id="userid" name="userid"
                               required></p>
                    <p><label for="password">Password</label>
                        <input type="password" id="password" name="password"
                               required></p>
                    <p><input type="submit" id="logIn" value="Log In"></p>

                </form>
            </c:when>
            <c:otherwise>
                <p> Welcome, ${sessionScope.get('user').firstName}</p>
                <form novalidate="novalidate" method="post" action="Controller?command=LogOut">
                    <p><input type="submit" id="logOut" value="Log Out"></p>
                </form>
            </c:otherwise>
        </c:choose>
    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>