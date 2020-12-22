package ui.controller;

import domain.model.Person;
import domain.model.Role;
import ui.authorisation.NotAuthorizedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogIn extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person currentUser = (Person) request.getSession().getAttribute("user");
        if (currentUser == null) {


            String userid = request.getParameter("userid").trim().toLowerCase();
            String password = request.getParameter("password");

            Person user = cts.authenticate(userid, password);

            if (user == null) {
                request.setAttribute("error", "No valid username/password");
            } else {
                request.getSession().setAttribute("user", user);
                response.sendRedirect("Controller?command=Home");
            }
            return "Controller?command=Home";
        } else {
            throw new NotAuthorizedException("Alleen niet ingelogde gebruikers kunnen zich inloggen.");
        }
    }
}
