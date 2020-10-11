package ui.controller;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogIn extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid").toLowerCase();
        String password = request.getParameter("password");

        Person user = service.authenticate(userid, password);

        if (user == null) {
            request.setAttribute("error", "No valid userid/password");
        } else {
            request.getSession().setAttribute("user", user);
        }
        return "index.jsp";
    }
}
