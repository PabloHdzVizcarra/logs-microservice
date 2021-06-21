package com.pablohdz.debug.infra;

import com.pablohdz.debug.application.FileUseCases;
import com.pablohdz.debug.domain.ErrorLog;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GetMessagesController implements HttpHandler {
    // TODO: 6/21/21 create a data structure with all records in file
    // TODO: 6/21/21 send records to client
    private final ControllersResponse response;
    private final ControllerOperations operations;
    private final FileUseCases fileUseCases;

    public GetMessagesController(ControllersResponse response) {
        this.response = response;
        this.operations = new ControllerOperations();
        this.fileUseCases = new FileUseCases();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Map<String, String> query = operations
            .queryToMap(exchange.getRequestURI().getQuery());
        try {
            String filename = query.get("filename");
            fileUseCases.checkIfExistsFile(filename);
            List<ErrorLog> errorLogs = fileUseCases.getErrorsFromFile(filename);
            System.out.println(errorLogs);
        } catch (NullPointerException exception) {
            response.withText(
                exchange,
                "The filename query is missing ",
                422);
        } catch (IOException ioException) {
            response.withText(
                exchange,
                ioException.getMessage(),
                404);
        }
        response.withText(exchange, 201);
    }
}
