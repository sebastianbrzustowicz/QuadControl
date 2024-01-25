package org.sebastianbrzustowicz.thread;

import org.sebastianbrzustowicz.model.SensorData;

import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CBus;

public class ReadAltitudeSensorTask implements Runnable {
    private SensorData sensorData = SensorData.getInstance();

    @Override
    public void run() {
        try {
            // Assuming the barometer sensor is connected via I2C
            int i2cBus = I2CBus.BUS_1; // You may need to change this based on your setup
            int deviceAddress = 0x77; // Replace with the actual I2C address of your barometer module

            // Create I2C bus instance
            I2CBus bus = I2CFactory.getInstance(i2cBus);

            // Create I2C device instance
            I2CDevice barometer = bus.getDevice(deviceAddress);

            // Reading altitude from the barometer module
            double altitude = readAltitudeFromBarometer(barometer);

            // Saving altitude value to ControlData singleton
            sensorData.setAltitude(altitude);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double readAltitudeFromBarometer(I2CDevice barometer) throws Exception {
        // Logic behind specific module may vary

        byte[] data = new byte[2];
        barometer.read(0x10, data, 0, 2);

        // Convert data to altitude (replace with actual conversion)
        int rawAltitude = ((data[0] & 0xFF) << 8) | (data[1] & 0xFF);
        return rawAltitude / 100.0;
    }
}