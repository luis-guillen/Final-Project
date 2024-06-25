package org.dacd.luis.businessunit;

import org.dacd.luis.businessunit.application.SubscribersSetup;
import org.dacd.luis.businessunit.application.SQLiteConnector;

import java.io.IOException;
import static org.dacd.luis.businessunit.application.HttpServer.startHttpServer;

public class Main {

    public static void main(String[] args) throws IOException {
        SQLiteConnector.createNewTable();
        SubscribersSetup.initializeSubscribers();
        startHttpServer();
    }
}
