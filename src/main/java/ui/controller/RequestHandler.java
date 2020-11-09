package ui.controller;

import domain.service.ContactService;
import domain.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RequestHandler {

    protected UserService userService;
    protected ContactService contactService;

    public abstract void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    public void setModel(UserService userService, ContactService contactService) {
        this.userService = userService;
        this.contactService = contactService;
    }




}
