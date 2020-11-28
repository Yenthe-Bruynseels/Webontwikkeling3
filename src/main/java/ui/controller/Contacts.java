package ui.controller;

import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Contacts extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person user = (Person) request.getSession().getAttribute("user");
        request.setAttribute("contacts", cts.getContactsUser(user.getUserid()));
        request.getRequestDispatcher("contacts.jsp").forward(request,response);
        //return "contacts.jsp";
    }
}
