package se.whn.kistamarathon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.*;

/**
 * Unit test for the runner class
 */
@RunWith(JUnit4.class)
public class RunnerTest {

    @Test
    public void createRunner() {
        Runner r = new Runner("Foo bar", "Sweden", 18, 1);
        assertThat(r, is(instanceOf(Runner.class)));
    }


    @Test(expected= IllegalArgumentException.class)
    public void nameNotEmptyString() {
        Runner r = new Runner("", "Sweden", 18, 1);
    }

    @Test(expected= IllegalArgumentException.class)
    public void nameNotNull() {
        Runner r = new Runner(null, "Sweden", 18, 1);
    }

    @Test(expected= IllegalArgumentException.class)
    public void countryNotEmptyString() {
        Runner r = new Runner("Foo bar", "", 18, 1);
    }

    @Test(expected= IllegalArgumentException.class)
    public void countryNotNull() {
        Runner r = new Runner("Foo bar", null, 18, 1);
    }

    @Test(expected= IllegalArgumentException.class)
    public void ageNotNegative() {
        Runner r = new Runner("Foo bar", "Sweden", -1, 1);
    }

    @Test(expected= IllegalArgumentException.class)
    public void startingNumberNotNegative() {
        Runner r = new Runner("Foo bar", "Sweden", 18, -1);
    }

    @Test(expected= IllegalArgumentException.class)
    public void startingNumberNotZero() {
        Runner r = new Runner("Foo bar", "Sweden", 18, 0);
    }

    @Test
    public void StringReprContainsName() {
        Object o = new Runner("Foo bar", "Sweden", 19, 11);
        String s = o.toString();
        assertThat(s, containsString("Foo bar"));
    }

    @Test
    public void StringReprContainsStartingNumber() {
        Object o = new Runner("Foo bar", "Sweden", 99, 11);
        String s = o.toString();
        assertThat(s, containsString("11"));
    }

    @Test
    public void StringReprContainsAge() {
        Object o = new Runner("Foo bar", "Sweden", 99, 11);
        String s = o.toString();
        assertThat(s, containsString("99"));
    }

    @Test
    public void StringReprContainsCountry() {
        Object o = new Runner("Foo bar", "Sweden", 99, 11);
        String s = o.toString();
        assertThat(s, containsString("Sweden"));
    }
}
