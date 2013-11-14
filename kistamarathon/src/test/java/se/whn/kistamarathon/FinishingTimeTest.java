package se.whn.kistamarathon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.Matchers.*;

/**
 * Unit test for the finishing time class.
 */
public class FinishingTimeTest {
    @Test
    public void testFinishingTime() {
        FinishingTime t = new FinishingTime(1, 20);
        assertThat(t.toString(), is(equalTo("1.20")));
    }

    @Test(expected= IllegalArgumentException.class)
    public void testNegativeHours() {
        FinishingTime t = new FinishingTime(-1, 10);
    }

    @Test(expected= IllegalArgumentException.class)
    public void testHoursTooHigh() {
        FinishingTime t = new FinishingTime(Integer.MAX_VALUE, 0);
    }

    @Test(expected= IllegalArgumentException.class)
    public void testMinutesTooHigh() {
        FinishingTime t = new FinishingTime(1, 60);
    }

    public void testNonEqual() {
        Object o = new Object();
        FinishingTime t = new FinishingTime(1, 0);
        assertThat(t.equals(o), is(equalTo(false)));
    }

    public void testEqual() {
        FinishingTime t = new FinishingTime(1, 0);
        FinishingTime t2 = new FinishingTime(1, 0);
        assertThat(t.equals(t2), is(equalTo(true)));
    }

    @Test(expected= IllegalArgumentException.class)
    public void testTooHigh(){
        FinishingTime t = new FinishingTime(71583000, 59);
    }
}
