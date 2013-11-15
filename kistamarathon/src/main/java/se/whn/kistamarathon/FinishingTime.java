package se.whn.kistamarathon;

public class FinishingTime implements Comparable<FinishingTime> {
    public final static FinishingTime NOT_FINISHED = new FinishingTime();

    private static int MAX_HOURS = (Integer.MAX_VALUE / 60) - 1;
    private static int MAX_MINUTES = 59;

    int time;

    private FinishingTime() {
        this.time = Integer.MAX_VALUE;
    }

    public FinishingTime(int hours, int minutes) {

        if(hours < 0) {
            throw new IllegalArgumentException("hours can't be negative");
        }

        if(hours > MAX_HOURS) {
            throw new IllegalArgumentException("hours is too high");
        }

        if(minutes < 0) {
            throw new IllegalArgumentException("minutes can't be negative");            
        }

        if(minutes > MAX_MINUTES){
            throw new IllegalArgumentException("minutes is too high, can't be more than 59");
        }

        this.time = (hours * 60) + minutes;
        if(this.time == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Time cannot be that high");
        }
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof FinishingTime) {
            FinishingTime t = (FinishingTime) o;
            return t.time == this.time;
        }
        return false;
    }

    @Override
    public int compareTo(FinishingTime t) {
        return this.time - t.time;
    }

    @Override
    public String toString() {
        if(this == NOT_FINISHED) {
            return "--";
        }
        return String.format("%d.%d", this.time / 60,this.time % 60);
    }
}
