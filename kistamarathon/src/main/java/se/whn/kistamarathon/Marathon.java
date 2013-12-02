package se.whn.kistamarathon;

import java.lang.StringBuilder;

/*
 * Controller class holding the marathon's state.
 */
public class Marathon {
    private Race raceState;
    private Counter counter;

    public Marathon() {
        raceState = Race.EMPTY;
        counter = new Counter();
    }

    private void replaceState(Race newState) {
        this.raceState = newState;
    }

    public void addRunner(String name, String country, int age) {
        int startingNo = counter.increment();
        Runner r = new Runner(name, country, age, startingNo);
        replaceState(raceState.with(r));
    }

    public void registerTime(int startingNumber, int hours, int minutes) {
        FinishingTime ft = new FinishingTime(hours, minutes);
        replaceState(raceState.withTime(startingNumber, ft));
    }

    public String generateReport(SortOrder order) {
        StringBuilder sb = new StringBuilder(128);
        
        for(Runner r : raceState.sortedBy(order)) {
            sb.append(r);
            sb.append('\n');
        }

        return sb.toString();
    }

    public int getNextStartingNumber() {
        return counter.peek();
    }
}
