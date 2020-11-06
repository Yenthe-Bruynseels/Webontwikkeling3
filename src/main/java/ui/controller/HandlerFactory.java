package ui.controller;

import domain.service.ContactService;
import domain.service.UserService;

public class HandlerFactory {

    public RequestHandler getHandler(String handlerName, UserService userService, ContactService contactService) {
        RequestHandler handler = null;
        try {
            Class handlerClass = Class.forName("ui.controller." + handlerName);
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setModel(userService, contactService);
        } catch (Exception e) {
            throw new RuntimeException("Deze pagina bestaat niet!!!!");
        }

        return handler;
    }

}
