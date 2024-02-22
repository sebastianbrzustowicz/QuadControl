package dev.sebastianbrzustowicz.model;

import lombok.Data;

@Data
public class ControlData {

    private static ControlData instance;

    // desired values
    private int mode;
    private int vtol;
    private double pitchd;
    private double rolld;
    private double altituded;
    private double yawd;

    private double errorAltitude;
    private double errorRoll;
    private double errorPitch;
    private double errorYaw;

    private double altitudeKp;
    private double rollKp;
    private double pitchKp;
    private double yawKp;
    private double altitudeKi;
    private double rollKi;
    private double pitchKi;
    private double yawKi;
    private double altitudeKd;
    private double rollKd;
    private double pitchKd;
    private double yawKd;

    private double errorAltitudeTotal;
    private double errorAltitudeDelta;
    private double errorAltitudeLast;
    private double errorRollTotal;
    private double errorRollDelta;
    private double errorRollLast;
    private double errorPitchTotal;
    private double errorPitchDelta;
    private double errorPitchLast;
    private double errorYawTotal;
    private double errorYawDelta;
    private double errorYawLast;

    private double sampleTime;

    private double U1;
    private double U2;
    private double U3;
    private double U4;

    private double U1Sat;
    private double U2Sat;
    private double U3Sat;
    private double U4Sat;

    private double F1;
    private double F2;
    private double F3;
    private double F4;

    private double PWM1;
    private double PWM2;
    private double PWM3;
    private double PWM4;


    private ControlData() {
        this.mode = 1;
        this.vtol = 0;
        this.pitchd = 0;
        this.rolld = 0;
        this.altituded = 0;
        this.yawd = 0;
    }

    public static ControlData getInstance() {
        if (instance == null) {
            instance = new ControlData();
        }
        return instance;
    }

    public void saveDesiredValues(int mode, int vtol, int x, int y, int alt, int yaw) {
        this.mode = mode;
        this.vtol = vtol;
        this.pitchd = x;
        this.rolld = y;
        this.altituded = alt;
        this.yawd = yaw;
    }
}
