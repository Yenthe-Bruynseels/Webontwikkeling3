package ui.controller;

import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogIn extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userid = request.getParameter("userid").trim().toLowerCase();
        String password = request.getParameter("password");

        Person user = userService.authenticate(userid, password);

        if (user == null) {
            request.setAttribute("error", "No valid username/password");
        } else {
            request.getSession().setAttribute("user", user);
        }
        request.getRequestDispatcher("index.jsp").forward(request,response);
        //return "index.jsp";
    }
}
