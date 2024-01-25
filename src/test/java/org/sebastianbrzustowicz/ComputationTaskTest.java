package org.sebastianbrzustowicz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.sebastianbrzustowicz.model.ControlData;
import org.sebastianbrzustowicz.model.SensorData;
import org.sebastianbrzustowicz.service.ServoController;
import org.sebastianbrzustowicz.thread.ComputationTask;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ComputationTaskTest {

    @Test
    public void updateControlData() {
        // Set up mocks for ControlData, SensorData, and ServoController
        ControlData controlDataMock = mock(ControlData.class);
        SensorData sensorDataMock = mock(SensorData.class);
        ServoController servoControllerMock = mock(ServoController.class);

        // Create an instance of ComputationTask
        ComputationTask computationTask = new ComputationTask();

        // Set up mock expectations for controlData, sensorData, and servoController
        when(controlDataMock.getAltituded()).thenReturn(100.0);
        when(sensorDataMock.getAltitude()).thenReturn(80.0);

        // Run the ComputationTask's `run()` method
        computationTask.run();

        // Verify that controlData's errorAltitude was updated correctly
        assertEquals(-20.0, controlDataMock.getErrorAltitude());
    }

    //@Test
    //public void calculateF1() {
    //    // Set up mocks for ControlData and ServoController
    //    ControlData controlDataMock = mock(ControlData.class);
    //    ServoController servoControllerMock = mock(ServoController.class);
//
    //    // Create an instance of ComputationTask
    //    ComputationTask computationTask = new ComputationTask();
//
    //    // Set up mock expectations for controlData
    //    when(controlDataMock.getU1Sat()).thenReturn(500.0);
    //    when(controlDataMock.getU2Sat()).thenReturn(300.0);
    //    when(controlDataMock.getU3Sat()).thenReturn(400.0);
    //    when(controlDataMock.getU4Sat()).thenReturn(200.0);
//
    //    // Run the ComputationTask's `calculateF1()` method
    //    double f1 = computationTask.calculateF1();
//
    //    // Verify that F1 was calculated correctly
    //    assertEquals(1100.0, f1);
    //}
//
    //@Test
    //public void calculatePWM1() {
    //    // Set up mocks for ControlData and ServoController
    //    ControlData controlDataMock = mock(ControlData.class);
    //    ServoController servoControllerMock = mock(ServoController.class);
//
    //    // Create an instance of ComputationTask
    //    ComputationTask computationTask = new ComputationTask();
//
    //    // Set up mock expectations for controlData
    //    when(controlDataMock.getF1()).thenReturn(1000.0);
//
    //    // Run the ComputationTask's `calculatePWM1()` method
    //    double pwm1 = computationTask.calculatePWM1();
//
    //    // Verify that PWM1 was calculated correctly
    //    assertEquals(1414.3135815214732, pwm1);
    //}
}