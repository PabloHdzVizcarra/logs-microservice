package com.pablohdz.debug.infra;

import com.pablohdz.debug.application.FileUseCases;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CreateMessageController implements HttpHandler {
    private final List<String> DEFAULT_VALUES =
        Arrays.asList("message", "fileName", "serviceName");
    private final FileUseCases useCases;
    private final ControllersResponse response;
    // TODO: 6/19/21 append the error in the file
    // TODO: 6/19/21 Response with ok is all successful

    public CreateMessageController(FileUseCases useCases, ControllersResponse response) {
        this.useCases = useCases;
        this.response = response;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStream body = exchange.getRequestBody();
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(body))) {

            HashMap<String, String> dataBody = splitRequestBody(reader);
            checkValuesInRequestBody(dataBody);
            useCases.createFileUseCase(
                dataBody.get("fileName"),
                dataBody.get("message"),
                dataBody.get("serviceName"));

            response.sendResponseText(exchange, 200);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        } catch (IllegalArgumentException exception) {
            response.sendResponseText(exchange, exception.getMessage(), 422);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void checkValuesInRequestBody(HashMap<String, String> dataBody) throws IllegalArgumentException {
        for (String elem : DEFAULT_VALUES) {
            if (!dataBody.containsKey(elem)) {
                throw new IllegalArgumentException(
                    "The request not contain the required " + elem + " key"
                );
            }
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
                String key = strings
                    .get(0)
                    .trim()
                    .substring(1, strings.get(0).length() - 1);
                String value = strings
                    .get(1)
                    .trim()
                    .substring(1, strings.get(1).length() - 2);
                body.put(key, value);
            });

        return body;
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
