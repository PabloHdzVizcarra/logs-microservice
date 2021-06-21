package com.pablohdz.debug;

import com.pablohdz.debug.application.FileUseCases;
import com.pablohdz.debug.infra.ControllersResponse;
import com.pablohdz.debug.infra.CreateMessageController;
import com.pablohdz.debug.infra.GetMessagesController;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class DebugMicroservice {
    public static void main(String[] args) {
        try {
            HttpServer server = createServer();
            server.createContext(
                "/api/v1/create",
                new CreateMessageController(new FileUseCases(), new ControllersResponse()));
            server.createContext(
                "/api/v1/records",
                new GetMessagesController(new ControllersResponse()));
            server.setExecutor(null);
            server.start();
            System.out.println("Server is on in port 8080");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static HttpServer createServer() throws IOException {
        return HttpServer
            .create(new InetSocketAddress(8080), 0);
    }
}
