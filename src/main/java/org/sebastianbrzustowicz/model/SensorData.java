package org.sebastianbrzustowicz.model;

public class SensorData {

    private static SensorData instance;

    // sensor variables
    private double altitude;
    private double roll;
    private double pitch;
    private double yaw;

    private SensorData() {
        this.altitude = 0.0;
        this.roll = 0.0;
        this.pitch = 0.0;
        this.yaw = 0.0;
    }

    public static SensorData getInstance() {
        if (instance == null) {
            instance = new SensorData();
        }
        return instance;
    }

    public void saveIMUValues(double[] rpyValues) {
        this.roll = rpyValues[0];
        this.pitch = rpyValues[1];
        this.yaw = rpyValues[2];
    }

    public void saveAltitudeValue(double altitude) {
        this.altitude = altitude;
    }

    public double[] getIMUValues() {
        return new double[]{this.roll, this.pitch, this.yaw};
    }

    public double getAltitudeValue() {
        return this.altitude;
    }

}
