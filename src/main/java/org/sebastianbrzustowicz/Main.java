package org.sebastianbrzustowicz;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
        // Implementation of an asynchronous listener to the websocket
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
            i += 1;
            System.out.println("Wartość zmiennej i: " + i);
            //System.out.println("Wykonywanie obliczeń na danych...");
        }
    }
}