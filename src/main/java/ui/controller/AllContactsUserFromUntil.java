package ui.controller;

import domain.model.Person;
import domain.model.Role;
import ui.authorisation.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AllContactsUserFromUntil extends RequestHandler{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role[] roles = {Role.ADMIN, Role.CUSTOMER};
        Utility.checkRole(request, roles);

        Person user = (Person) request.getSession().getAttribute("user");


        ArrayList<String> errors = new ArrayList<>();

        String fromDate = request.getParameter("from");
        String fromTime = request.getParameter("fromTime");

        String untilDate = request.getParameter("until");
        String untilTime = request.getParameter("untilTime");

        try {
            request.setAttribute("prevFrom", fromDate);
            request.setAttribute("prevFromTime", fromTime);
            request.setAttribute("prevUntil", untilDate);
            request.setAttribute("prevUntilTime", untilTime);

            Timestamp from = parseTimeStamp(fromDate, fromTime);
            Timestamp until = parseTimeStamp(untilDate, untilTime);

            if (from.equals(until) || from.after(until)) {
                throw new Exception("'From' must be smaller than 'until'");
            }

            request.setAttribute("contacts", cts.getContactsUserFromUntil(user.getUserid(), from, until));
            request.getRequestDispatcher("contacts.jsp").forward(request,response);
        } catch (DateTimeException e) {
            errors.add("date or hour invalid");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("contacts.jsp").forward(request, response);
        } catch (Exception e) {
            errors.add(e.getMessage());
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("contacts.jsp").forward(request, response);
        }
    }

    public Timestamp parseTimeStamp(String date, String time) {
        String timestampPattern = "yyyy-M-d HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timestampPattern);
        LocalDateTime dateTime;
        Timestamp timestamp;
        dateTime = LocalDateTime.from(formatter.parse(date + " " + time));
        timestamp = Timestamp.valueOf(dateTime);
        return timestamp;
    }
}
