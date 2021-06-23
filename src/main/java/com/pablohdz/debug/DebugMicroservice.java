package com.pablohdz.debug;

import com.pablohdz.debug.application.FileUseCases;
import com.pablohdz.debug.infra.ControllersResponse;
import com.pablohdz.debug.infra.CreateMessageController;
import com.pablohdz.debug.infra.GetMessagesController;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.Map;

public class DebugMicroservice
{
    public static void main(String[] args)
    {

        try
        {
            Map<String, String> env = readEnv();
            HttpServer server = HttpServer.create(
                new InetSocketAddress(Integer.parseInt(env.get("PORT"))), 0);

            server.createContext(
                "/api/v1/create",
                new CreateMessageController(new FileUseCases(), new ControllersResponse()));

            server.createContext(
                "/api/v1/records",
                new GetMessagesController(new ControllersResponse()));

            server.setExecutor(null);
            server.start();
            System.out.println("Server is on in port " + env.get("PORT"));

        } catch (Exception exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public static Map<String, String> readEnv()
    {
        return System.getenv();
    }
}
