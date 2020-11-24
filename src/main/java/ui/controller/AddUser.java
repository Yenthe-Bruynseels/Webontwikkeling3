package ui.controller;

import domain.model.DomainException;
import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AddUser extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> errors = new ArrayList<String>();

        Person person = new Person();
        setUserid(person, request, errors);
        setFirstName(person, request, errors);
        setLastName(person, request, errors);
        setEmail(person, request, errors);
        setPassword(person, request, errors);

        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            //return "register.jsp";
        }

        Person thePerson = cts.getPerson(request.getParameter("userid").trim().toLowerCase());
        if (thePerson != null) {
            errors.add("User already exists.");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("register.jsp").forward(request,response);
            //return "register.jsp";
        } else {
            cts.addPerson(person);
            response.sendRedirect("Controller?command=Overview");
            //request.getRequestDispatcher("Controller?command=Overview").forward(request,response);
            //return "Controller?command=Overview";
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
