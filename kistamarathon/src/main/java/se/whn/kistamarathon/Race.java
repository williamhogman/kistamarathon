package se.whn.kistamarathon;

import java.lang.Iterable;
import java.util.Iterator;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Arrays;


import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.base.Predicates;

/**
 * Race is an immutable representation of a race at a certain point in
 * time. References to a race can be safely shared without a problem
 */
public class Race implements Iterable<Runner> {
    private Iterable<Runner> runners;

    public static Race EMPTY = new Race();

    private Race() {
        this(Iterables.<Runner>cycle());
    }

    private Race(Race base, Iterable<Runner> add) {
        this(Iterables.<Runner>concat(base.runners, add));
    }

    private Race(Iterable<Runner> runners) {
        this.runners = runners;
    }

    public Race with(Runner runner) {
        return new Race(this, Collections.<Runner>singleton(runner));
    }

    public Race withTime(int startingNo, FinishingTime finishedAt) {
        // LinkedList<Runner> copy = new LinkedList<Runner>(runners);
        // ... I wish ... well Guava it is
        LinkedList<Runner> copy = Lists.<Runner>newLinkedList(runners);

        // instead of updating the copied list we remove the
        // item and place it at the end of the list. It is
        // placed at the end of the list because searches are
        // generally done for runner who haven't finished yet,
        // placing them at the end of the list changes the
        // time complexity from O(N) to O(N - M) where M is
        // the number of runners that have finished the race.
        // Assuming these searches are done only by starting
        // number for runner who haven't finished.
        int i = 0;
        for(Runner r : runners) {
            if(r.getStartingNumber() == startingNo) {
                copy.offer(copy.remove(i).finished(finishedAt));
                break;
            }
            i++;
        }

        return new Race(copy);
    }

    public Iterator<Runner> iterator() {
        return this.runners.iterator();
    }

    public Runner[] sortedBy(SortOrder order) {
        Runner[] arr = Iterables.<Runner>toArray(runners, Runner.class);
        Arrays.sort(arr, Runner.comparatorFor(order));

        return arr;
    }
}
