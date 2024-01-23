package org.sebastianbrzustowicz.thread;

public class ComputationTask implements Runnable {
    // class for computation of controller law
    // input: tasks data received from websocket and from sensors
    // output: GPIO voltage which is rotors speeds
    //private int i = 0;
    @Override
    public void run() {
        System.out.println("C");
        // Code for handling calculations on data every sampling time
        // Getting sensors data
        double roll = 0.0d;
        double pitch = 0.0d;
        double yaw = 0.0d;
        // desired values
        double rolld = 0.0d;
        double pitchd = 0.0d;
        double yawd = 0.0d;
        // controller constans
        double k1 = 0.0d;
        double k2 = 0.0d;
        double k3 = 0.0d;

        // Compute output

        // pid controller

        // allocation block

        // GPIO output
        double motorA = 0.0d;
        double motorB = 0.0d;
        double motorC = 0.0d;
        double motorD = 0.0d;

    }
}