package dev.sebastianbrzustowicz.service;

import com.pi4j.io.gpio.*;

public class ServoController {

    private GpioPinPwmOutput[] pwmOutputs;

    private static final int NUM_SERVOS = 4;

    private static ServoController instance;

    private ServoController() {
        pwmOutputs = new GpioPinPwmOutput[NUM_SERVOS];

        GpioController gpio = GpioFactory.getInstance();
        for (int i = 0; i < NUM_SERVOS; i++) {
            pwmOutputs[i] = gpio.provisionPwmOutputPin(RaspiPin.getPinByAddress(i + 1));
        }
    }

    public static synchronized ServoController getInstance() {
        if (instance == null) {
            instance = new ServoController();
        }
        return instance;
    }

    public void setPwm(int servoNumber, int pwmValue) {
        if (servoNumber >= 0 && servoNumber < NUM_SERVOS) {
            pwmOutputs[servoNumber].setPwm(pwmValue);
        }
    }

    public void close() {
        GpioFactory.getInstance().shutdown();
    }
}