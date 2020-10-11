package ui.controller;

import domain.db.DbException;
import domain.db.PersonDbInMemory;
import domain.model.ContactTracingService;
import domain.model.DomainException;
import domain.model.Person;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    ContactTracingService cts = new ContactTracingService();
    private HandlerFactory handlerFactory = new HandlerFactory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination = "index.jsp";
        String command = request.getParameter("command");
/*

        if (command == null) command = "Home";


        switch (command) {
            case "Overview":
                destination = showOverview(request, response);
                break;
            case "Register":
                destination = showForm(request, response);
                break;
            case "Add":
                destination = addUser(request, response);
                break;
            default:
                destination = showHome(request, response);

        }
*/

        if (command != null) {
            try {
                RequestHandler handler = handlerFactory.getHandler(command, cts);
                destination = handler.handleRequest(request, response);
            } catch (Exception exc) {
                request.setAttribute("error", exc.getMessage());
                destination = "error.jsp";
            }
        }

        request.getRequestDispatcher(destination).forward(request, response);
    }


}
