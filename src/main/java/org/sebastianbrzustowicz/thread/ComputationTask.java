package org.sebastianbrzustowicz.thread;

public class ComputationTask implements Runnable {
    // class for computation of controller law
    // input: tasks data received from websocket and from sensors
    // output: GPIO voltage which is rotors speeds
    //private int i = 0;
    @Override
    public void run() {
        System.out.println("C");
        // Code for handling calculations on data every 2 ms

        // Compute output

        // GPIO output
    }
}