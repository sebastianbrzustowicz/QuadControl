package org.sebastianbrzustowicz;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;

import java.net.URI;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class Main {

    public static void main(String[] args) {
        // Initialisation of the asynchronous listener to the websocket
        WebSocketListener webSocketListener = new WebSocketListener();
        webSocketListener.startListening();

        // Initialisation of the ScheduledExecutorService for the calculation thread every 2 ms
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new ComputationTask(), 0, 2, TimeUnit.MILLISECONDS);
    }

    static class WebSocketListener {
        private final String vehicleID = "e218e18c-9e1c-11ee-8c90-0242ac120002";
        private final String urlSocket = "http://localhost:8080/websocket-endpoint";
        private final String urlStatus = "http://localhost:8080/topic/vehicle-status/" + vehicleID;
        private final String urlData = "http://localhost:8080/topic/vehicle-data/" + vehicleID;

        public void startListening() {
            try {
                WebSocketClient client = new WebSocketClient();
                client.start();

                URI uri = new URI(urlSocket);
                ClientUpgradeRequest request = new ClientUpgradeRequest();
                client.connect(new YourSocketListener(), uri, request);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        @WebSocket
        public class YourSocketListener {

            @OnWebSocketConnect
            public void onConnect(Session session) {
                System.out.println("Connected to WebSocket!");
                // Handle connection logic, if needed
            }

            @OnWebSocketMessage
            public void onMessage(String message) {
                System.out.println("Received message: " + message);
                // Handle incoming messages
            }

            @OnWebSocketClose
            public void onClose(int statusCode, String reason) {
                System.out.println("Connection closed: " + statusCode + ", " + reason);
                // Handle close logic, if needed
            }
        }
    }

    static class ComputationTask implements Runnable {
        // class for computation of controller law
        // input: tasks data received from websocket and from sensors
        // output: GPIO voltage which is rotors speeds
        //private int i = 0;
        @Override
        public void run() {
            //System.out.println("Computations");
            // Code for handling calculations on data every 2 ms

            // Read task and sensors

            // Compute output

            // GPIO output
        }
    }
}