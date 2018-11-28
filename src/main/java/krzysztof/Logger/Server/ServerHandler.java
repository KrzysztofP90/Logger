package krzysztof.Logger.Server;

import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class ServerHandler {


    public void createServerAndRunIt() throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(7000), 0);

        server.createContext("/login", new LoginHandler());
        server.setExecutor(null);




        server.start();

    }
}
