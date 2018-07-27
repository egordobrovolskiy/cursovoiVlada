package kursach.attempt2;

import java.time.Duration;

public class ContentMetaData {
    private final String name;
    private final String author;
    private final Duration duration;


    public ContentMetaData(String name, String author, Duration duration) {
        this.name = name;
        this.author = author;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public Duration getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "ContentMetaData{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", duration=" + duration +
                '}';
    }
}
