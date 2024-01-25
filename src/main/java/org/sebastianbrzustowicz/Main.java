package org.sebastianbrzustowicz;

import org.sebastianbrzustowicz.service.WebSocketClientManager;
import org.sebastianbrzustowicz.thread.ComputationTask;
import org.sebastianbrzustowicz.thread.ReadAltitudeSensorTask;
import org.sebastianbrzustowicz.thread.ReadIMUSensorTask;
import org.sebastianbrzustowicz.thread.WebSocketTask;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        // Initialisation of the asynchronous listener to the websocket
        WebSocketClientManager socketManager = WebSocketClientManager.getInstance();
        String WebSocketUrl = "ws://localhost:8080/websocket-single-room";
        socketManager.createWebSocketClient(WebSocketUrl);

        // Send data to WebSocket and receive desired values
        ScheduledExecutorService schedulerWST = Executors.newScheduledThreadPool(1);
        schedulerWST.scheduleAtFixedRate(new WebSocketTask(), 0, 1000, TimeUnit.MILLISECONDS);

        // Read altitude sensor value
        //ScheduledExecutorService schedulerRAST = Executors.newScheduledThreadPool(1);
        //schedulerRAST.scheduleAtFixedRate(new ReadAltitudeSensorTask(), 50, 10000, TimeUnit.MILLISECONDS);

        // Read IMU sensor data
        //ScheduledExecutorService schedulerRIMUST = Executors.newScheduledThreadPool(1);
        //schedulerRIMUST.scheduleAtFixedRate(new ReadIMUSensorTask(), 0, 200, TimeUnit.MILLISECONDS);

        // Compute output values based on sensors data
        ScheduledExecutorService schedulerCT = Executors.newScheduledThreadPool(1);
        schedulerCT.scheduleAtFixedRate(new ComputationTask(), 100, 200, TimeUnit.MILLISECONDS);
    }
}