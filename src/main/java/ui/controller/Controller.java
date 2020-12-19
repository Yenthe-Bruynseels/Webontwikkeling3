package ui.controller;

import domain.service.ContactTracingService;
import ui.authorisation.NotAuthorizedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        if (command != null) {
            try {
                RequestHandler handler = handlerFactory.getHandler(command,cts);
                destination = handler.handleRequest(request, response);
            } catch (NotAuthorizedException e) {
                request.setAttribute("notAuthorized", "You have insufficient rights to have a look at the requested page.");
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
            }
        }

        if (!response.isCommitted()) {
            request.getRequestDispatcher(destination).forward(request,response);
        }
    }


}
