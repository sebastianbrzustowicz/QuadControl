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
    public void collisionDetection() {
        CollisionDetectionTask collisionDetectionTask = new CollisionDetectionTask();
        collisionDetectionTask.run();

        String printedOutput = outContent.toString().trim();

        String floatText = printedOutput.split("\\[\\[")[1].split("\\]")[0].trim();
        System.out.println(floatText);
        float extractedValue = Float.parseFloat(floatText);

        assertTrue(Float.isFinite(extractedValue));
    }

}