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
}