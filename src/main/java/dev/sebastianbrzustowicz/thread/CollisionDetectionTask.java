package dev.sebastianbrzustowicz.thread;

import dev.sebastianbrzustowicz.service.TensorFlowCollisionDetectionRequest;

import java.util.Random;

public class CollisionDetectionTask implements Runnable{

    @Override
    public void run() {

        long startTime = System.nanoTime();

        double[][] inputData = generateSampleInputData(500);

        String servingEndpoint = "http://localhost:8501/v1/models/tfmodel:predict";

        try {
            String response = TensorFlowCollisionDetectionRequest.performInference(inputData, servingEndpoint);
            System.out.println("Prediction response:\n" + response);
            // Do something on response
            // ...

        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        long executionTimeInMicroseconds = (endTime - startTime) / 1000;
        System.out.println("Execution time of collision detection request: " + executionTimeInMicroseconds + " microseconds");
    }

    private static double[][] generateSampleInputData(int size) {

        double[][] inputData = new double[1][size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {

            double threshold = 0.5;

            double noise = random.nextGaussian();

            if (i < size / 2) {
                inputData[0][i] = threshold + noise;
            } else {
                inputData[0][i] = threshold - noise;
            }
        }
        return inputData;
    }
}
