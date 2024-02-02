package org.sebastianbrzustowicz.thread;

import org.sebastianbrzustowicz.model.ControlData;
import org.sebastianbrzustowicz.model.SensorData;
//import org.sebastianbrzustowicz.service.ServoController;

public class ComputationTask implements Runnable {
    // class for computation of control law
    // input: tasks data received from websocket and from sensors
    // output: GPIO voltage which is rotors speeds

    ControlData controlData = ControlData.getInstance();
    SensorData sensorData = SensorData.getInstance();
    //ServoController servoController = ServoController.getInstance();

    @Override
    public void run() {
        // Code for handling calculations on data every sampling tim
        System.out.println("Computation task");
        // setting timer
        long startTime = System.nanoTime();

        // Compute errors: error, integral, and derivative for rpy and altitude
        controlData.setErrorAltitude(controlData.getAltituded() - sensorData.getAltitude());
        controlData.setErrorRoll(controlData.getRolld() - sensorData.getRoll());
        controlData.setErrorPitch(controlData.getPitchd() - sensorData.getPitch());
        controlData.setErrorYaw(controlData.getYawd() - sensorData.getYaw());

        controlData.setErrorAltitudeTotal(controlData.getErrorAltitudeTotal() + controlData.getErrorAltitude());
        controlData.setErrorAltitudeDelta(controlData.getErrorAltitude() - controlData.getErrorAltitudeLast());
        controlData.setErrorAltitudeLast(controlData.getErrorAltitude());

        controlData.setErrorRollTotal(controlData.getErrorRollTotal() + controlData.getErrorRoll());
        controlData.setErrorRollDelta(controlData.getErrorRoll() - controlData.getErrorRollLast());
        controlData.setErrorRollLast(controlData.getErrorRoll());

        controlData.setErrorPitchTotal(controlData.getErrorPitchTotal() + controlData.getErrorPitch());
        controlData.setErrorPitchDelta(controlData.getErrorPitch() - controlData.getErrorPitchLast());
        controlData.setErrorPitchLast(controlData.getErrorPitch());

        controlData.setErrorYawTotal(controlData.getErrorYawTotal() + controlData.getErrorYaw());
        controlData.setErrorYawDelta(controlData.getErrorYaw() - controlData.getErrorYawLast());
        controlData.setErrorYawLast(controlData.getErrorYaw());

        // pid controller
        // U1 = AltitudeKp*ErrorAltitude + (AltitudeKi*SampleTime)*ErrorAltitudeTotal + (AltitudeKd/SampleTime)*ErrorAltitudeDelta
        controlData.setU1(controlData.getAltitudeKp()*controlData.getErrorAltitude() + (controlData.getAltitudeKi()*controlData.getSampleTime())*controlData.getErrorAltitudeTotal() + (controlData.getAltitudeKd()/controlData.getSampleTime())*controlData.getErrorAltitudeDelta());
        // U2 = RollKp*ErrorRoll + (RollKi*SampleTime)*ErrorRollTotal + (RollKd/SampleTime)*ErrorRollDelta
        controlData.setU2(controlData.getRollKp()*controlData.getErrorRoll() + (controlData.getRollKi()*controlData.getSampleTime())*controlData.getErrorRollTotal() + (controlData.getRollKd()/controlData.getSampleTime())*controlData.getErrorRollDelta());
        // U3 = PitchKp*ErrorPitch + (PitchKi*SampleTime)*ErrorPitchTotal + (PitchKd/SampleTime)*ErrorPitchDelta
        controlData.setU3(controlData.getPitchKp()*controlData.getErrorPitch() + (controlData.getPitchKi()*controlData.getSampleTime())*controlData.getErrorPitchTotal() + (controlData.getPitchKd()/controlData.getSampleTime())*controlData.getErrorPitchDelta());
        // U4 = YawKp*ErrorYaw + (YawKi*SampleTime)*ErrorYawTotal + (YawKd/SampleTime)*ErrorYawDelta
        controlData.setU4(controlData.getYawKp()*controlData.getErrorYaw() + (controlData.getYawKi()*controlData.getSampleTime())*controlData.getErrorYawTotal() + (controlData.getYawKd()/controlData.getSampleTime())*controlData.getErrorYawDelta());

        // saturation block
        // U1_sat = Math.min(3000, Math.max(0, U1)); //0 - 360
        controlData.setU1Sat(Math.min(3000, Math.max(0, controlData.getU1())));
        // U2_sat = Math.min(1200, Math.max(-1200, U2)); //-180 - 180
        controlData.setU2Sat(Math.min(1200, Math.max(0, controlData.getU2())));
        // U3_sat = Math.min(1200, Math.max(-1200, U3)); //-180 - 180
        controlData.setU3Sat(Math.min(1200, Math.max(0, controlData.getU3())));
        // U4_sat = Math.min(1200, Math.max(-1200, U4)); //-180 - 180
        controlData.setU4Sat(Math.min(1200, Math.max(0, controlData.getU4())));

        // allocation block
        // F1 = U1_sat + U2_sat - U3_sat - U4_sat;
        controlData.setF1(controlData.getU1Sat() + controlData.getU2Sat() - controlData.getU3Sat() - controlData.getU4Sat());
        // F2 = U1_sat + U2_sat + U3_sat + U4_sat;
        controlData.setF2(controlData.getU1Sat() + controlData.getU2Sat() + controlData.getU3Sat() + controlData.getU4Sat());
        // F3 = U1_sat - U2_sat - U3_sat + U4_sat;
        controlData.setF3(controlData.getU1Sat() - controlData.getU2Sat() - controlData.getU3Sat() + controlData.getU4Sat());
        // F4 = U1_sat - U2_sat + U3_sat - U4_sat;
        controlData.setF4(controlData.getU1Sat() - controlData.getU2Sat() + controlData.getU3Sat() - controlData.getU4Sat());

        // Force to PWM conversion (0, 10) ---> (1000, 2000)

        controlData.setPWM1(controlData.getF1() >= 0 ? Math.sqrt(controlData.getF1()*0.0022 + 0.2282)*929.4066+555.9760 : 1000);
        controlData.setPWM2(controlData.getF2() >= 0 ? Math.sqrt(controlData.getF2()*0.0022 + 0.2282)*929.4066+555.9760 : 1000);
        controlData.setPWM3(controlData.getF3() >= 0 ? Math.sqrt(controlData.getF3()*0.0022 + 0.2282)*929.4066+555.9760 : 1000);
        controlData.setPWM4(controlData.getF4() >= 0 ? Math.sqrt(controlData.getF4()*0.0022 + 0.2282)*929.4066+555.9760 : 1000);

        // GPIO output
        //servoController.setPwm(0, (int) controlData.getPWM1());
        //servoController.setPwm(1, (int) controlData.getPWM2());
        //servoController.setPwm(2, (int) controlData.getPWM3());
        //servoController.setPwm(3, (int) controlData.getPWM4());

        //servoController.setPwm(0, 0);
        //servoController.setPwm(1, 0);
        //servoController.setPwm(2, 0);
        //servoController.setPwm(3, 0);

        long endTime = System.nanoTime();

        long executionTimeInMicroseconds = (endTime - startTime) / 1000;

        System.out.println("Execution time of computations: " + executionTimeInMicroseconds + " microseconds");
    }
}