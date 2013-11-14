package se.whn.kistamarathon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.Matchers.*;

/**
 * Unit test for the comparators of runner class
 */
public class RunnerComparatorTest {


    final Runner alice = new Runner("Alice", "Sweden", 99, 11);
    final Runner bob = new Runner("Bob", "Sweden", 99, 11);

    final Runner young = new Runner("Young", "Sweden", 30, 11);
    final Runner old = new Runner("Young", "Sweden", 99, 11);    

    final Runner first = new Runner("First", "Sweden", 30, 1);
    final Runner last = new Runner("Last", "Sweden", 30, 99);

    @Test
    public void nameCompareAB() {
        int res = Runner.comparatorFor(SortOrder.Name).compare(alice, bob);
        assertThat(res, is(lessThan(0)));
    }

    @Test
    public void nameCompareBA() {
        int res = Runner.comparatorFor(SortOrder.Name).compare(bob, alice);
        assertThat(res, is(greaterThan(0)));
    }

    @Test
    public void nameCompareAA() {
        int res = Runner.comparatorFor(SortOrder.Name).compare(alice, alice);
        assertThat(res, is(equalTo(0)));
    }

    @Test
    public void ageCompareYO() {
        int res = Runner.comparatorFor(SortOrder.Age).compare(young, old);
        assertThat(res, is(lessThan(0)));
    }

    @Test
    public void ageCompareOY() {
        int res = Runner.comparatorFor(SortOrder.Age).compare(old, young);
        assertThat(res, is(greaterThan(0)));
    }

    @Test
    public void ageCompareYY() {
        int res = Runner.comparatorFor(SortOrder.Age).compare(young, young);
        assertThat(res, is(equalTo(0)));
    }

    @Test
    public void StartingNoCompareFL() {
        int res = Runner.comparatorFor(SortOrder.StartingNumber).compare(first, last);
        assertThat(res, is(lessThan(0)));
    }

    @Test
    public void StartingNoCompareLF() {
        int res = Runner.comparatorFor(SortOrder.StartingNumber).compare(last, first);
        assertThat(res, is(greaterThan(0)));
    }

    @Test
    public void StartingNoCompareFF() {
        int res = Runner.comparatorFor(SortOrder.StartingNumber).compare(first, first);
        assertThat(res, is(equalTo(0)));
    }

}
