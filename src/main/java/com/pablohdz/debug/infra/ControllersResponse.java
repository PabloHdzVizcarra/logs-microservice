package com.pablohdz.debug.infra;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class ControllersResponse {
    public ControllersResponse() {
    }

    public void withText(HttpExchange exchange, String message, Integer statusCode) throws IOException {
        exchange.sendResponseHeaders(statusCode, message.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(message.getBytes());
        outputStream.close();
    }

    public void withText(HttpExchange exchange, Integer statusCode) throws IOException {
        String message = "ok";
        exchange.sendResponseHeaders(statusCode, message.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(message.getBytes());
        outputStream.close();
    }

    public void withJson(HttpExchange exchange, Integer statusCode, String data) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, data.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(data.getBytes());
        outputStream.close();
    }
}
