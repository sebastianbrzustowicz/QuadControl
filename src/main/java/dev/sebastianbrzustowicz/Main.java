package dev.sebastianbrzustowicz;

import dev.sebastianbrzustowicz.service.TensorFlowCollisionDetectionRequest;
import dev.sebastianbrzustowicz.service.WebSocketClientManager;
import dev.sebastianbrzustowicz.thread.CollisionDetectionTask;
import dev.sebastianbrzustowicz.thread.ComputationTask;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        // Turning off some Spring initial information (if application is distributed with Spring framework)
        //SpringApplication application = new SpringApplication(Main.class);
        //application.setBannerMode(Banner.Mode.OFF);
        //application.setLogStartupInfo(false);
        //ConfigurableApplicationContext context = application.run(args);


        //System.out.println(context.getParentBeanFactory());
        // Initialisation of the asynchronous listener to the websocket
        WebSocketClientManager socketManager = WebSocketClientManager.getInstance();
        socketManager.createWebSocketClient();

        // Send data to WebSocket and receive desired values
        //ScheduledExecutorService schedulerWST = Executors.newScheduledThreadPool(1);
        //schedulerWST.scheduleAtFixedRate(new WebSocketTask(), 0, 1000, TimeUnit.MILLISECONDS);

        // Read altitude sensor value
        //ScheduledExecutorService schedulerRAST = Executors.newScheduledThreadPool(1);
        //schedulerRAST.scheduleAtFixedRate(new ReadAltitudeSensorTask(), 50, 10000, TimeUnit.MILLISECONDS);

        // Read IMU sensor data
        //ScheduledExecutorService schedulerRIMUST = Executors.newScheduledThreadPool(1);
        //schedulerRIMUST.scheduleAtFixedRate(new ReadIMUSensorTask(), 0, 200, TimeUnit.MILLISECONDS);

        // Compute output values based on sensors data
        ScheduledExecutorService schedulerCT = Executors.newScheduledThreadPool(1);
        schedulerCT.scheduleAtFixedRate(new ComputationTask(), 0, 1000, TimeUnit.MILLISECONDS);

        ScheduledExecutorService schedulerCDT = Executors.newScheduledThreadPool(1);
        schedulerCDT.scheduleAtFixedRate(new CollisionDetectionTask(), 0, 1000, TimeUnit.MILLISECONDS);

    }

}