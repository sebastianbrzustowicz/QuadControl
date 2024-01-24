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
        double altitude = 0.0d;
        // desired values
        double rolld = 0.0d;
        double pitchd = 0.0d;
        double yawd = 0.0d;
        double altituded = 0.0d;
        // controller constans
        double k1 = 0.0d;
        double k2 = 0.0d;
        double k3 = 0.0d;

        // todo: move errors to computation singleton
        // Compute errors
        //double errorAltitude = altituded -altitude;
        //double errorRoll =     rolld - roll;
        //double errorPitch =    pitchd - pitch;
        //double errorYaw =      yawd - yaw;

        //double e_altitude_total += e_altitude;
        //double e_altitude_delta  = e_altitude - e_altitude_last;
        //double e_altitude_last   = e_altitude;
        //double e_roll_total += e_roll;
        //double e_roll_delta = e_roll - e_roll_last;
        //double e_roll_last  = e_roll;
        //double e_pitch_total += e_pitch;
        //double e_pitch_delta  = e_pitch - e_pitch_last;
        //double e_pitch_last   = e_pitch;
        //double e_yaw_total += e_yaw;
        //double e_yaw_delta  = e_yaw - e_yaw_last;
        //double e_yaw_last   = e_yaw;

        //// pid controller
        //double U1 = altitude_kp*errorAltitude + (altitude_ki*sample_time)*e_altitude_total + (altitude_kd/sample_time)*e_altitude_delta;
        //double U2 = roll_kp*errorRoll         + (roll_ki*sample_time)*e_roll_total         + (roll_kd/sample_time)*e_roll_delta;
        //double U3 = pitch_kp*errorPitch       + (pitch_ki*sample_time)*e_pitch_total       + (pitch_kd/sample_time)*e_pitch_delta;
        //double U4 = yaw_kp*errorYaw           + (yaw_ki*sample_time)*e_yaw_total           + (yaw_kd/sample_time)*e_yaw_delta;

        // allocation block
        double U1_sat = Math.min(3000, Math.max(0, U1)); //0 - 360
        double U2_sat = Math.min(1200, Math.max(-1200, U2)); //-180 - 180
        double U3_sat = Math.min(1200, Math.max(-1200, U3)); //-180 - 180
        double U4_sat = Math.min(1200, Math.max(-1200, U4)); //-180 - 180

        double F1 = U1_sat + U2_sat - U3_sat - U4_sat;
        double F2 = U1_sat + U2_sat + U3_sat + U4_sat;
        double F3 = U1_sat - U2_sat - U3_sat + U4_sat;
        double F4 = U1_sat - U2_sat + U3_sat - U4_sat;

        // Force to PWM (0, 10) ---> (1000, 2000)
        if (F1 >= 0) pwm1 = sqrt(F1*0.0022 + 0.2282)*929.4066+555.9760; else pwm1 = 1000;
        if (F2 >= 0) pwm2 = sqrt(F2*0.0022 + 0.2282)*929.4066+555.9760; else pwm2 = 1000;
        if (F3 >= 0) pwm3 = sqrt(F3*0.0022 + 0.2282)*929.4066+555.9760; else pwm3 = 1000;
        if (F4 >= 0) pwm4 = sqrt(F4*0.0022 + 0.2282)*929.4066+555.9760; else pwm4 = 1000;

        // GPIO output
        double motorA = 0.0d;
        double motorB = 0.0d;
        double motorC = 0.0d;
        double motorD = 0.0d;

    }
}