package cursach;

import cursach.exceptions.NoUserLogIn;

import java.util.ArrayList;
import java.util.List;

public class Film implements Content {
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

    @Override
    public ContentMetaData getContentMetaData() {
        return contentMetaData;
    }

    @Override
    public ContentStatistics getContentStatistics() {
        return contentStatistics;
    }

    @Override
    public BinaryContent getBinaryContent() {
        return binaryContent;
    }

    @Override
    public List<Comment> getComments() {
        return comments;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public void run() {
        run(new User());
    }

    @Override
    public void run(User user) {
        if (User.DEFAULT_NAME.equals(user.getName())) {
            throw new NoUserLogIn("Авторизируйтесь");
        }

        if (user.isContentBue(this)) {
            contentStatistics.run();
        } else {
            {
                user.pay(price);
                contentStatistics.run();
                user.addBueContent(this);
            }
        }
    }

    public FilmGenre getFilmGenre() {
        return filmGenre;
    }

    @Override
    public void addLike() {
        contentStatistics.addLike();
    }

    @Override
    public void addDislike() {
        contentStatistics.addDislike();
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public String toString() {
        return "Film{" +
                "contentMetaData=" + contentMetaData +
                ", contentStatistics=" + contentStatistics +
                ", binaryContent=" + binaryContent +
                ", filmGenre=" + filmGenre +
                ", comments=" + comments +
                ", price=" + price +
                '}';
    }
}
