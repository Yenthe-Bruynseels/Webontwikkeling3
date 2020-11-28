package ui.controller;

import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

public class RegisterPositiveTest extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> errors = new ArrayList<>();

        Test test = new Test();
        setUserid(test, request, errors);
        setDate(test,request,errors);

        if (errors.size() == 0) {
            try {
                cts.addTest(test);
                response.sendRedirect("Controller?command=Contacts");
            } catch (DbException exc) {
                errors.add(exc.getMessage());
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("positivetest.jsp").forward(request,response);
            }
        }
        else {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("positivetest.jsp").forward(request,response);
        }
    }

    private void setUserid(Test test, HttpServletRequest request, ArrayList<String> errors) {
        String userid = request.getParameter("userid");
        try {
            test.setUserid(userid);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setDate(Test test, HttpServletRequest request, ArrayList<String> errors) {
        String dateText = request.getParameter("date");
        try {
            Date date = Date.valueOf(dateText);
            System.out.println();
            test.setDate(date);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        } catch (IllegalArgumentException exc) {
            errors.add("Date can't be empty.");
        }
    }
}
