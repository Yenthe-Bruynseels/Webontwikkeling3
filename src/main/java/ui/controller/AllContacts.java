package ui.controller;

import domain.model.Person;
import domain.model.Role;
import ui.authorisation.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AllContacts extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);
        request.setAttribute("adminAllContacts", true);
        request.setAttribute("contacts", cts.getAllContacts());
        return "contacts.jsp";
        //request.getRequestDispatcher("contacts.jsp").forward(request,response); //Geen redirect, aangezien deze contacts anders zijn dan die uit de handler AllContactsUser
    }
}
