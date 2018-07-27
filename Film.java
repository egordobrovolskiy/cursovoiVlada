package kursach.attempt2;

import java.util.ArrayList;
import java.util.List;

public class Film {
    private final ContentMetaData contentMetaData;
    private ContentStatistics contentStatistics = new ContentStatistics();
    private final BinaryContent binaryContent;
    private final FilmGenre filmGenre;
    private List<Comment> comments = new ArrayList<>();
    private final long price;

    public Film(ContentMetaData contentMetaData, BinaryContent binaryContent, FilmGenre filmGenre, long price) {
        this.contentMetaData = contentMetaData;
        this.binaryContent = binaryContent;
        this.filmGenre = filmGenre;
        this.price = price;
    }

    public ContentMetaData getContentMetaData() {
        return contentMetaData;
    }

    public ContentStatistics getContentStatistics() {
        return contentStatistics;
    }

    public BinaryContent getBinaryContent() {
        return binaryContent;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public long getPrice() {
        return price;
    }

    public void run(User user) {
        if (user.getMoney() < price) {
            throw new IllegalArgumentException("Недостаточно денег");
        } else {
            user.pay(price);
            contentStatistics.run();
        }
    }

    public void addLike() {
        contentStatistics.addLike();
    }

    public void addDislike() {
        contentStatistics.addDislike();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

}
