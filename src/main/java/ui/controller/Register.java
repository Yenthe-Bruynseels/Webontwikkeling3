package ui.controller;

import domain.model.Person;
import domain.model.Role;
import ui.authorisation.NotAuthorizedException;
import ui.authorisation.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Register extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person user = (Person) request.getSession().getAttribute("user");
        if (user == null) {

            //request.getRequestDispatcher("register.jsp").forward(request, response);
            return "register.jsp";
        } else {
            throw new NotAuthorizedException("Alleen niet ingelogde gebruikers hebben toegang tot deze pagina");
        }
    }
}

