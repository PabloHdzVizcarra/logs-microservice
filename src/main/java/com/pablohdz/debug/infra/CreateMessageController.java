package com.pablohdz.debug.infra;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CreateMessageController implements HttpHandler {
    // TODO: Create file if not exists
    // TODO: 6/19/21 append the error in the file
    // TODO: 6/19/21 Response with ok is all successful

    @Override
    public void handle(HttpExchange exchange) {
        System.out.println(exchange.getRequestMethod());
        InputStream body = exchange.getRequestBody();
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(body))) {

            StringBuilder request = new StringBuilder();
            String responseLine;
            while ((responseLine = reader.readLine()) != null) {
                request.append(responseLine.trim());
            }

            System.out.println(request);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }


}
