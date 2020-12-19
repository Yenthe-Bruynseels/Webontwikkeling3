package ui.controller;

import domain.model.Person;
import domain.model.Role;
import ui.authorisation.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PositiveTest extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role[] roles = {Role.ADMIN, Role.CUSTOMER};
        Utility.checkRole(request, roles);


        Person user = (Person) request.getSession().getAttribute("user");
        if (user.getRole().equals(Role.ADMIN)) {
            request.setAttribute("tests", cts.getAllTests());
        }

        //request.getRequestDispatcher("positivetest.jsp").forward(request,response);
        return "positivetest.jsp";
    }
}
