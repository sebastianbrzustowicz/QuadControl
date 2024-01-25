package org.sebastianbrzustowicz.thread;

import org.sebastianbrzustowicz.model.OperationalData;
import org.sebastianbrzustowicz.model.SensorData;
import org.sebastianbrzustowicz.service.WebSocketClientManager;

public class WebSocketTask implements Runnable {
    // class for communication with websocket
    // input: desired values data received from websocket
    // output: sensors data sent to websocket
    WebSocketClientManager socketManager = WebSocketClientManager.getInstance();
    SensorData sensorData = SensorData.getInstance();
    OperationalData operationalData = OperationalData.getInstance();

    @Override
    public void run() {

        // Send data to WebSocket from sensors
        socketManager.sendMessage(getMessageFrame());
        //System.out.println("WS data exchange");

    }

    public String getMessageFrame() {
        // there handshake should be established between server and vehicle
        // data is sent in raw format but values stands for these variables:
        // VEHICLE                                  <- fixed prefix for vehicle message
        // 1                                        <- roll
        // 0                                        <- pitch
        // 0                                        <- yaw
        // 0                                        <- altitude
        // false                                    <- clamp
        // END                                      <- fixed ending statement of message

        double[] imuValues = sensorData.getIMUValues(); // double[]{roll, pitch, yaw}

        StringBuilder dataFrame = new StringBuilder();

        dataFrame.append("VEHICLE").append("\n");
        dataFrame.append(imuValues[0]).append("\n");
        dataFrame.append(imuValues[1]).append("\n");
        dataFrame.append(imuValues[2]).append("\n");
        dataFrame.append(sensorData.getAltitude()).append("\n");
        dataFrame.append(operationalData.isClamp()).append("\n");
        dataFrame.append("END");

        return dataFrame.toString();
    }

}
