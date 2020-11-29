package ui.controller;



import domain.model.Person;
import domain.model.Role;
import ui.authorisation.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchContactsSinceLastPositiveTest extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role[] roles = {Role.ADMIN, Role.CUSTOMER};
        Utility.checkRole(request, roles);

        Person user = (Person) request.getSession().getAttribute("user");
        request.setAttribute("contacts", cts.getAllContactsSincePositiveTest(user.getUserid()));
        if (user.getRole().equals(Role.ADMIN)) {
            request.getRequestDispatcher("Controller?command=SearchAllUsersWithManyContacts").forward(request,response);
        }
        else {
            request.getRequestDispatcher("search.jsp").forward(request,response);

        }
    }
}
