package kursach.attempt2;

public class ContentStatistics {
    private long like = 0;
    private long dislike = 0;
    private long totalViews = 0;

    public long getLike() {
        return like;
    }

    public long getDislike() {
        return dislike;
    }

    public long getTotalViews() {
        return totalViews;
    }
    public void run(){
        totalViews = totalViews + 1;
    }

    public void addLike() {
        like = like + 1;
    }

    public void addDislike() {
        dislike = dislike + 1;
    }

    @Override
    public String toString() {
        return "ContentStatistics{" +
                "like=" + like +
                ", dislike=" + dislike +
                ", totalViews=" + totalViews +
                '}';
    }
}
