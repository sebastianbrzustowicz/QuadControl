package org.sebastianbrzustowicz.model;

public class ControlData {

    private static ControlData instance;

    // desired values
    private int mode;
    private int vtol;
    private int x;
    private int y;
    private int alt;
    private int yaw;


    private ControlData() {
        this.mode = 1;
        this.vtol = 0;
        this.x = 0;
        this.y = 0;
        this.alt = 0;
        this.yaw = 0;
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
        this.x = x;
        this.y = y;
        this.alt = alt;
        this.yaw = yaw;
    }


    // Getters for individual values
    public int getMode() {
        return mode;
    }

    public int getVtol() {
        return vtol;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAlt() {
        return alt;
    }

    public int getYaw() {
        return yaw;
    }
}
