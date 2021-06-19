package com.pablohdz.debug.infra;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class CreateMessageController implements HttpHandler {
    // TODO: Read data Json to Request
    // TODO: Create file if not exists
    // TODO: 6/19/21 append the error in the file
    // TODO: 6/19/21 Response with ok is all successful

    @Override
    public void handle(HttpExchange exchange) {
        System.out.println(exchange.getRequestMethod());
        System.out.println(exchange.getRequestHeaders());
        System.out.println(exchange.getResponseBody());
    }


}
