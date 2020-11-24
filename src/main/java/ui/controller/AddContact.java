package ui.controller;

import domain.db.DbException;
import domain.model.Contact;
import domain.model.DomainException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AddContact extends RequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<String> errors = new ArrayList<>();

        Contact contact = new Contact();
        setFirstName(contact, request, errors);
        setLastName(contact, request, errors);
        setTimestamp(contact, request, errors);
        setPhonenumber(contact, request, errors);
        setEmail(contact, request, errors);

        if (errors.size() == 0) {
            try {
                cts.addContact(contact);
                clearPreviousValues(request);
                //return "Controller?command=Contacts";
                response.sendRedirect("Controller?command=Contacts");
            } catch (DbException exc) {
                errors.add(exc.getMessage());
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("Controller?command=Contacts").forward(request,response);
            }
        }
        else {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("Controller?command=Contacts").forward(request,response);
        }
        //return "Controller?command=Contacts";
    }

    private void clearPreviousValues(HttpServletRequest request) {
        request.removeAttribute("phonenumberPreviousValue");
        request.removeAttribute("prevHour");
        request.removeAttribute("prevDate");
        request.removeAttribute("emailPreviousValue");
        request.removeAttribute("firstNamePreviousValue");
        request.removeAttribute("lastNamePreviousValue");
    }

    private void setPhonenumber(Contact contact, HttpServletRequest request, ArrayList<String> errors) {
        String phonenumber = request.getParameter("phonenumber");
        try {
            contact.setPhonenumber(phonenumber);
            request.setAttribute("phonenumberPreviousValue", phonenumber);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setTimestamp(Contact contact, HttpServletRequest request, ArrayList<String> errors) {
        try {
            String date = request.getParameter("date");
            String hour = request.getParameter("hour");
            String timestampPattern = "yyyy-M-d HH:mm";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timestampPattern);
            LocalDateTime dateTime;
            Timestamp timestamp;

            request.setAttribute("prevHour", hour);
            request.setAttribute("prevDate", date);
            dateTime = LocalDateTime.from(formatter.parse(date + " " + hour));
            timestamp = Timestamp.valueOf(dateTime);
            contact.setTimestamp(timestamp);
        } catch (DateTimeException e) {
            errors.add("date or hour invalid");
        }
    }

    private void setEmail(Contact contact, HttpServletRequest request, ArrayList<String> errors) {
        String email = request.getParameter("email");
        try {
            contact.setEmail(email);
            request.setAttribute("emailPreviousValue", email);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setFirstName(Contact contact, HttpServletRequest request, ArrayList<String> errors) {
        String firstName = request.getParameter("firstName");
        try {
            contact.setFirstName(firstName);
            request.setAttribute("firstNamePreviousValue", firstName);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }

    private void setLastName(Contact contact, HttpServletRequest request, ArrayList<String> errors) {
        String lastName = request.getParameter("lastName");
        try {
            contact.setLastName(lastName);
            request.setAttribute("lastNamePreviousValue", lastName);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
        }
    }

}
