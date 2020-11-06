package ui.controller;

import domain.service.ContactService;
import domain.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {

    protected UserService userService;
    protected ContactService contactService;

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response);

    public void setModel(UserService userService, ContactService contactService) {
        this.userService = userService;
        this.contactService = contactService;
    }




}
