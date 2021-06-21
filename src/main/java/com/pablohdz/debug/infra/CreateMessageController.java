package com.pablohdz.debug.infra;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CreateMessageController implements HttpHandler {
    // TODO: Create file if not exists
    // TODO: 6/19/21 append the error in the file
    // TODO: 6/19/21 Response with ok is all successful

    @Override
    public void handle(HttpExchange exchange) {
        InputStream body = exchange.getRequestBody();
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(body))) {

            HashMap<String, String> dataBody = splitRequestBody(reader);
            sendResponseJson(exchange);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private HashMap<String, String> splitRequestBody(BufferedReader reader) throws IOException {
        StringBuilder request = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            request.append(responseLine.trim());
        }
        String data = request.toString();
        String data2 = data.substring(1, data.length() - 1);
        HashMap<String, String> body = new HashMap<>();
        Arrays.stream(data2
            .split(","))
            .forEach(elem -> {
                List<String> strings = Arrays.asList(elem.split(":"));
                body.put(strings.get(0), strings.get(1));
            });

        return body;
    }

    private void sendResponseText(HttpExchange exchange) throws IOException {
        String response = "This is the example response";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    private void sendResponseJson(HttpExchange exchange) throws IOException {
        String response = "{ \"message\": \"Hello from this server\"}";
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }
}
