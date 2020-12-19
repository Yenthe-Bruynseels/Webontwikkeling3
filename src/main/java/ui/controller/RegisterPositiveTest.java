package ui.controller;

import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Person;
import domain.model.Role;
import domain.model.Test;
import ui.authorisation.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterPositiveTest extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role[] roles = {Role.ADMIN, Role.CUSTOMER};
        Utility.checkRole(request, roles);


        ArrayList<String> errors = new ArrayList<>();

        Test test = new Test();
        setUserid(test, request, errors);
        setDate(test,request,errors);

        if (errors.size() == 0) {
            try {
                cts.addTest(test);
                response.sendRedirect("Controller?command=AllContactsUser");
            } catch (DbException exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?command=PositiveTest";
        //request.getRequestDispatcher("positivetest.jsp").forward(request,response);
    }




    private void setUserid(Test test, HttpServletRequest request, ArrayList<String> errors) {
        Person user = (Person) request.getSession().getAttribute("user");
        String userid = user.getUserid();
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
