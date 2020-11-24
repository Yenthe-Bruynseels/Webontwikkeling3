package ui.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteContact extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        cts.deleteContact(id);
        response.sendRedirect("Controller?command=Contacts");
        //request.getRequestDispatcher("Controller?command=Contacts").forward(request,response);
        //return "Controller?command=Contacts";
    }
}
