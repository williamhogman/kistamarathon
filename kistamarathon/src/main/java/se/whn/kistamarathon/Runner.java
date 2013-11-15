package se.whn.kistamarathon;

import java.util.Comparator;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public class Runner {
    private String name, country;
    private int age, startingNumber;
    private FinishingTime finishedAt;

    public Runner(String name, String country, int age, int startingNumber) {
        checkArgument(name != null, "Name can't be null");
        checkArgument(!name.equals(""), "Name can't be the empty string");

        checkArgument(country != null, "Country can't be null");
        checkArgument(!country.equals(""), "Country can't be the empty string");

        checkArgument(age >= 0, "Age cannot be negative");
        checkArgument(startingNumber > 0, 
                      "Starting number has to be a postive, non-zero integer");

        this.name = name;
        this.country = country;
        this.startingNumber = startingNumber;
        this.age = age;
        this.finishedAt = FinishingTime.NOT_FINISHED;
    }

    private Runner(Runner base, FinishingTime finishedAt) {
        this.name = base.name;
        this.country = base.country;
        this.startingNumber = base.startingNumber;
        this.age = base.age;
        this.finishedAt = finishedAt;
    }

    public Runner finished(FinishingTime finishedAt) {
        checkState(this.finishedAt == FinishingTime.NOT_FINISHED,
                   "The runner has already finished");
        checkArgument(finishedAt != null,
                      "Finished at can't be null");
        checkArgument(finishedAt != FinishingTime.NOT_FINISHED,
                      "Finshed at has to be an actual time");
        return new Runner(this, finishedAt);
    }


    @Override
    public String toString() {
        return String.format("%d %s %s %d %s", 
                             this.startingNumber, this.name, 
                             this.country, this.age,
                             this.finishedAt);
    }

    public static Comparator<Runner> comparatorFor(SortOrder order) {
        switch(order) {
        case StartingNumber:
            return Runner.StartingNumberComparator.INSTANCE;
        case Name:
            return Runner.NameComparator.INSTANCE;
        case Age:
            return Runner.AgeComparator.INSTANCE;
        case FinishingTime:
            return Runner.FinishingTimeComparator.INSTANCE;
        }
        throw new AssertionError("Fell through switch statement covering all possible cases");
    }

    private static class NameComparator implements Comparator<Runner> {
        private static final NameComparator INSTANCE = new NameComparator();
        public int compare(Runner a, Runner b) {
            return a.name.compareTo(b.name);
        }
    }

    private static class AgeComparator implements Comparator<Runner> {
        private static final AgeComparator INSTANCE = new AgeComparator();
        public int compare(Runner a, Runner b) {
            return Integer.compare(a.age, b.age);
        }
    }

    private static class StartingNumberComparator implements Comparator<Runner> {
        private static final StartingNumberComparator INSTANCE = new StartingNumberComparator();
        public int compare(Runner a, Runner b) {
            return Integer.compare(a.startingNumber, b.startingNumber);
        }
    }

    private static class FinishingTimeComparator implements Comparator<Runner> {
        private static final FinishingTimeComparator INSTANCE = new FinishingTimeComparator();
        public int compare(Runner a, Runner b) {
            return a.finishedAt.compareTo(b.finishedAt);
        }
    }
    
}
