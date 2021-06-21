package com.pablohdz.debug.infra;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class GetMessagesController implements HttpHandler {
    private final ControllersResponse response;

    public GetMessagesController(ControllersResponse response) {
        this.response = response;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        response.withText(exchange, 201);
    }
}
