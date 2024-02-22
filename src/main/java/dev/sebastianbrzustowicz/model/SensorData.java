package dev.sebastianbrzustowicz.model;

public class SensorData {

    private static SensorData instance;

    // sensor variables
    private double altitude;
    private double roll;
    private double pitch;
    private double yaw;

    private SensorData() {
        this.altitude = 3.1415;
        this.roll = 3.1415;
        this.pitch = 3.1415;
        this.yaw = 3.1415;
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

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double[] getIMUValues() {
        return new double[]{this.roll, this.pitch, this.yaw};
    }

    public double getAltitude() {
        return this.altitude;
    }

    public double getRoll() {
        return this.roll;
    }

    public double getPitch() {
        return this.pitch;
    }

    public double getYaw() {
        return this.yaw;
    }

}
