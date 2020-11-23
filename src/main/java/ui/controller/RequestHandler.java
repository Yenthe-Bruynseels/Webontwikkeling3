package ui.controller;

import domain.service.ContactTracingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RequestHandler {

    protected ContactTracingService cts;

    public abstract void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    public void setModel(ContactTracingService cts) {
        this.cts = cts;
    }




}
