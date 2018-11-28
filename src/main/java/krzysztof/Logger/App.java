package krzysztof.Logger;

import jdk.nashorn.internal.runtime.ECMAException;
import krzysztof.Logger.DBconnector.DBconnectorPostgreSql;
import krzysztof.Logger.Server.ServerHandler;

import java.net.SecureCacheResponse;


public class App 
{
    public static void main( String[] args ) throws Exception {

        ServerHandler serverHandler = new ServerHandler();
        serverHandler.createServerAndRunIt();
    }
}
