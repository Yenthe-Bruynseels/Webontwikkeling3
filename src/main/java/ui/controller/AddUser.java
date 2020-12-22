package ui.controller;

import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Person;
import domain.model.Role;
import ui.authorisation.NotAuthorizedException;
import ui.authorisation.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AddUser extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if (request.getSession().getAttribute("user") != null) {
        throw new NotAuthorizedException("Alleen niet ingelogde gebruikers mogen een nieuwe gebruiker registreren.");
    }
    else {

        ArrayList<String> errors = new ArrayList<String>();

        Person person = new Person();
        setUserid(person, request, errors);
        setFirstName(person, request, errors);
        setLastName(person, request, errors);
        setEmail(person, request, errors);
        setPassword(person, request, errors);

        if (errors.size() == 0) {
            try {
                cts.addPerson(person);
                request.getSession().setAttribute("positive", "U heeft zich succesvol geregistreerd.");
                response.sendRedirect("Controller?command=Home");
            } catch (DbException exc) {
                errors.add(exc.getMessage());
            }
        }
        //request.getSession().setAttribute("errors", errors);
        request.setAttribute("errors", errors);
        return "register.jsp";
    }
    }

    private void setUserid(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String userid = request.getParameter("userid");
        try {
            person.setUserid(userid);
            request.setAttribute("useridPreviousValue", userid);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setEmail(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String email = request.getParameter("email");
        try {
            person.setEmail(email);
            request.setAttribute("emailPreviousValue", email);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setFirstName(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String firstName = request.getParameter("firstName");
        try {
            person.setFirstName(firstName);
            request.setAttribute("firstNamePreviousValue", firstName);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setLastName(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String lastName = request.getParameter("lastName");
        try {
            person.setLastName(lastName);
            request.setAttribute("lastNamePreviousValue", lastName);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setPassword(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String password = request.getParameter("password");
        try {
            person.setPasswordHashed(password);
            request.setAttribute("passwordPreviousValue", password);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }
}
