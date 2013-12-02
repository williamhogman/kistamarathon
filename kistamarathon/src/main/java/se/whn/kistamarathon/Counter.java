package se.whn.kistamarathon;

/**
 * Class providing a monotonially incrementing counter.
 */
public class Counter {
    int value;

    public Counter() {
        value = 1;
    }

    public int peek() {
        return value;
    }

    public int increment() {
        return value++;
    }
}
