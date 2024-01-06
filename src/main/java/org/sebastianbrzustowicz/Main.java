package org.sebastianbrzustowicz;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        // Initialisation of the asynchronous listener to the websocket
        WebSocketListener webSocketListener = new WebSocketListener();
        webSocketListener.startListening();
        // Initialisation of the ScheduledExecutorService for the calculation thread every 2 ms
        //ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        //scheduler.scheduleAtFixedRate(new ComputationTask(), 0, 2, TimeUnit.MILLISECONDS);
    }

    static class WebSocketListener {
        // Implementation of an asynchronous listener to the websocket
        // hardcode vehicle ID here
        private final String vehicleID = "96907e09-c4ea-4b1f-8f24-b0d0c9b77687";
        // socket connection url for STOMP protocol
        private final String url_socket = "http://localhost:8080/websocket-endpoint";
        // connection status
        private final String url_status = "http://localhost:8080/topic/vehicle-status/" + vehicleID;
        // get actual data
        private final String url_data = "http://localhost:8080/topic/vehicle-data/" + vehicleID;

        public void startListening() {
            // Here implement the logic for listening for events from the websocket
            // For example, using a library to support WebSocket (e.g. Jetty WebSocket API).
            // CompletableFuture.runAsync(() -> {
            //    while (true) {
            //        // Handle websocket events asynchronously here
            //    }
            // });
            // Remember to handle errors and close down resources appropriately.
        }
    }

    static class ComputationTask implements Runnable {
        // class for computation of controller law
        // input: tasks data received from websocket and from sensors
        // output: GPIO voltage which is rotors speeds
        private int i = 0;
        @Override
        public void run() {
            // Code for handling calculations on data every 2 ms

            // Read task and sensors

            // Compute output

            // GPIO output
        }
    }
}