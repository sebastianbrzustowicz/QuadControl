package dev.sebastianbrzustowicz.service;

import java.net.URI;

import dev.sebastianbrzustowicz.config.WebSocketConfig;
import dev.sebastianbrzustowicz.model.ControlData;
import dev.sebastianbrzustowicz.model.OperationalData;
import tech.gusavila92.websocketclient.WebSocketClient;

public class WebSocketClientManager {

    private static WebSocketClientManager instance;
    private WebSocketClient webSocketClient;
    ControlData controlData = ControlData.getInstance();
    OperationalData operationalData = OperationalData.getInstance();
    WebSocketConfig webSocketConfig = WebSocketConfig.getInstance();

    private WebSocketClientManager() {
        // Private constructor to prevent instantiation.
    }

    public static synchronized WebSocketClientManager getInstance() {
        if (instance == null) {
            instance = new WebSocketClientManager();
        }
        return instance;
    }

    public void createWebSocketClient() {
        URI uri;
        try {
            uri = new URI(webSocketConfig.getWebSocketUrl());
        } catch (Exception e) {
            System.out.println("Cannot create host URI resource");
            return;
        }

        webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen() {
                System.out.println("Connection to WebSocket opened");
            }

            @Override
            public void onTextReceived(String msg) {
                //System.out.println("Received message:\n" + msg);
                if (msg.startsWith("CLIENT")) {
                    String[] lines = msg.split("\n");
                    int mode = Integer.parseInt(lines[2]);
                    int vtol = Integer.parseInt(lines[3]);
                    int pitchd = Integer.parseInt(lines[4]);
                    int rolld = Integer.parseInt(lines[5]);
                    int altituded = Integer.parseInt(lines[6]);
                    int yawd = Integer.parseInt(lines[7]);
                    boolean camTrig = Boolean.parseBoolean(lines[8]);
                    boolean camTog = Boolean.parseBoolean(lines[9]);
                    int camPitch = Integer.parseInt(lines[10]);
                    boolean clamp = Boolean.parseBoolean(lines[11]);
                    controlData.saveDesiredValues(mode, vtol, pitchd, rolld, altituded, yawd);
                    operationalData.saveOperationalValues(camTrig, camTog, camPitch, clamp);
                }
            }

            @Override
            public void onBinaryReceived(byte[] data) {
            }

            @Override
            public void onPingReceived(byte[] data) {
            }

            @Override
            public void onPongReceived(byte[] data) {
            }

            @Override
            public void onException(Exception e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onCloseReceived() {
                System.out.println("Connection closed");
            }
        };

        try {
            webSocketClient.setConnectTimeout(10000);
            webSocketClient.setReadTimeout(120000);
            webSocketClient.enableAutomaticReconnection(1000);
            webSocketClient.connect();
        } catch (Exception e) {
            System.out.println("Cannot connect to host");
        }
    }

    public void sendMessage(String message) {
        try {
            webSocketClient.send(message);
        } catch (Exception e) {
            System.out.println("Cannot send message");
        }
    }
}