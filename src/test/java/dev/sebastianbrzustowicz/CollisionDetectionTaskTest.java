package dev.sebastianbrzustowicz;

import dev.sebastianbrzustowicz.thread.CollisionDetectionTask;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class CollisionDetectionTaskTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testCollisionDetection() {
        CollisionDetectionTask collisionDetectionTask = new CollisionDetectionTask();
        collisionDetectionTask.run();

        String printedOutput = outContent.toString().trim();

        String floatText = printedOutput.split("\\[\\[")[1].split("\\]")[0].trim();

        float extractedValue = Float.parseFloat(floatText);

        assertTrue(Float.isFinite(extractedValue));
    }

    @Test
    public void testExecutionTime() {
        CollisionDetectionTask collisionDetectionTask = new CollisionDetectionTask();
        collisionDetectionTask.run();

        String printedOutput = outContent.toString().trim();
        String expectedExpression1 = "Execution time of collision detection request: ";
        String expectedExpression2 = " microseconds";

        assertTrue(printedOutput.contains(expectedExpression1));
        assertTrue(printedOutput.contains(expectedExpression2));

        String longText = printedOutput.split(expectedExpression1)[1].split(expectedExpression2)[0].trim();
        float extractedValue = Float.parseFloat(longText);

        assertTrue(Float.isFinite(extractedValue));
    }

}