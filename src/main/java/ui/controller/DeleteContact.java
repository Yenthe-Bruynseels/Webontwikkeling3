package ui.controller;

import domain.model.Role;
import ui.authorisation.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteContact extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);

        int id = Integer.parseInt(request.getParameter("id"));
        cts.deleteContact(id);
        response.sendRedirect("Controller?command=AllContactsUser");
        //request.getRequestDispatcher("Controller?command=AllContactsUser").forward(request,response);
        //return "Controller?command=AllContactsUser";
    }
}
