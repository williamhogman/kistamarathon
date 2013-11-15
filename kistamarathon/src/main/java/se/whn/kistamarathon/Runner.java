package se.whn.kistamarathon;

import java.util.Comparator;

public class Runner {
    private String name, country;
    private int age, startingNumber;
    private FinishingTime finishedAt;

    public Runner(String name, String country, int age, int startingNumber) {
        if(name == "") {
            throw new IllegalArgumentException("Name cannot be the empty string");
        }

        if(name == null) {
            throw new IllegalArgumentException("Name can't be null");
        }

        if(country == "") {
            throw new IllegalArgumentException("Country cannot be the empty string");
        }

        if(country == null) {
            throw new IllegalArgumentException("Country can't be null");            
        }

        if(age < 0) {
            throw new IllegalArgumentException("Age can't be negative");
        }

        if(startingNumber < 1) {
            throw new IllegalArgumentException("Starting number has to be a postive and non-zero");
        }
        
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
        if(finishedAt == FinishingTime.NOT_FINISHED) {
            throw new IllegalArgumentException("Finshed at has to be an actual time");
        }
        return new Runner(this, finishedAt);
    }

    @Override
    public String toString() {
        return String.format("%d %s %s %d", this.startingNumber, this.name, this.country, this.age);
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
