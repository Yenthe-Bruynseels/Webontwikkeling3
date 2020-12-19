package ui.controller;

import domain.model.Role;
import ui.authorisation.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Delete extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);

        String userid = request.getParameter("userid");
        cts.deletePerson(userid);
        response.sendRedirect("Controller?command=Overview");
        //request.getRequestDispatcher("Controller?command=Overview").forward(request,response);
        return "Controller?command=Overview";
    }
}
