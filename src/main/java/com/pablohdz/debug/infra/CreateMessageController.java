package com.pablohdz.debug.infra;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class CreateMessageController implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) {
        System.out.println(exchange.getRequestMethod());
        System.out.println(exchange.getRequestHeaders());
        System.out.println(exchange.getResponseBody());
    }
}
