package org.sebastianbrzustowicz.thread;

import org.sebastianbrzustowicz.model.SensorData;
import org.sebastianbrzustowicz.sensor.MPU6050Reader;

public class ReadIMUSensorTask implements Runnable {
    // class for reading IMU sensor rpy values
    MPU6050Reader mpu6050Reader = new MPU6050Reader();
    SensorData sensorData = SensorData.getInstance();
    @Override
    public void run() {
        sensorData.saveIMUValues(mpu6050Reader.readData());
    }
}