package ui.controller;

import domain.model.Role;
import ui.authorisation.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOut extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role[] roles = {Role.ADMIN, Role.CUSTOMER};
        Utility.checkRole(request, roles);

        request.getSession().invalidate();
        response.sendRedirect("Controller?command=Home");
        //request.getRequestDispatcher("index.jsp").forward(request, response);
        //return "index.jsp";
    }
}
