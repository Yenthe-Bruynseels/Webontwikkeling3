package ui.controller;

import domain.db.DbException;
import domain.db.PersonDbInMemory;
import domain.model.ContactTracingService;
import domain.model.DomainException;
import domain.model.Person;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    ContactTracingService cts = new ContactTracingService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination;
        String command = request.getParameter("command");

        if (command == null) command = "Home";


        switch (command) {
            case "Overview":
                destination = showOverview(request, response);
                break;
            case "Register":
                destination = showForm(request, response);
                break;
            case "Add":
                destination = addUser(request, response);
                break;
            default:
                destination = showHome(request, response);

        }
        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
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
            person.setPassword(password);
            request.setAttribute("passwordPreviousValue", password);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }

    private String addUser(HttpServletRequest request, HttpServletResponse response) {
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
                return showHome(request, response);
            } catch (DbException exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "register.jsp";
    }

    private String showForm(HttpServletRequest request, HttpServletResponse response) {
        return "register.jsp";
    }

    private String showOverview(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("users", cts.getPersons());
        return "personoverview.jsp";
    }

    private String showHome(HttpServletRequest request, HttpServletResponse response) {
        return "index.jsp";
    }
}
