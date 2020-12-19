package ui.controller;

import domain.model.Person;
import domain.model.Role;
import ui.authorisation.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AllContactsUser extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role[] roles = {Role.ADMIN, Role.CUSTOMER};
        Utility.checkRole(request, roles);

        Person user = (Person) request.getSession().getAttribute("user");
        request.setAttribute("contacts", cts.getContactsUser(user.getUserid()));
        //request.getRequestDispatcher("contacts.jsp").forward(request,response);
        return "contacts.jsp";
    }
}
