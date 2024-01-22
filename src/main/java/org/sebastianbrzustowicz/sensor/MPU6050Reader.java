package org.sebastianbrzustowicz.sensor;

import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;

import java.io.IOException;

public class MPU6050Reader {

    private static final int MPU6050_ADDRESS = 0x68; // Adres MPU6050

    private I2CBus i2cBus;
    private I2CDevice mpu6050;

    public MPU6050Reader() {
        try {
            // I2C bus init and MPU6050 device
            //i2cBus = I2CFactory.getInstance(I2CBus.BUS_1);
            mpu6050 = i2cBus.getDevice(MPU6050_ADDRESS);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double[] readData() {
        double[] rpyValues = new double[3];

        try {
            while (true) {
                byte[] buffer = new byte[14];
                mpu6050.read(0x3B, buffer, 0, 14);

                int accelX = (buffer[0] << 8) | (buffer[1] & 0xFF);
                int accelY = (buffer[2] << 8) | (buffer[3] & 0xFF);
                int accelZ = (buffer[4] << 8) | (buffer[5] & 0xFF);

                int gyroX = (buffer[8] << 8) | (buffer[9] & 0xFF);
                int gyroY = (buffer[10] << 8) | (buffer[11] & 0xFF);
                int gyroZ = (buffer[12] << 8) | (buffer[13] & 0xFF);

                double roll = Math.atan2(accelY, accelZ) * (180 / Math.PI);
                double pitch = Math.atan2(-accelX, Math.sqrt(accelY * accelY + accelZ * accelZ)) * (180 / Math.PI);
                double yaw = Math.toDegrees(Math.atan2(gyroY, gyroX));

                rpyValues[0] = roll;
                rpyValues[1] = pitch;
                rpyValues[2] = yaw;

                Thread.sleep(2);

                return rpyValues;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            return new double[]{0.0, 0.0, 0.0};
        }
    }

    public static void main(String[] args) {
        MPU6050Reader reader = new MPU6050Reader();
        reader.readData();
    }
}