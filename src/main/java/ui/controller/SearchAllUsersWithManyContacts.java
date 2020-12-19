package ui.controller;

import domain.model.Role;
import ui.authorisation.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchAllUsersWithManyContacts extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);

        request.setAttribute("users", cts.allUsersWithManyContacts());
        //request.getRequestDispatcher("search.jsp").forward(request,response);
        return "search.jsp";
    }
}
