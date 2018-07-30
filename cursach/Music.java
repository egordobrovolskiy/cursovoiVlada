package cursach;

import java.util.ArrayList;
import java.util.List;

public class Music implements Content {
    private final ContentMetaData contentMetaData;
    private ContentStatistics contentStatistics = new ContentStatistics();
    private final BinaryContent binaryContent;
    private List<Comment> comments = new ArrayList<>();

    public Music(ContentMetaData contentMetaData, BinaryContent binaryContent) {
        this.contentMetaData = contentMetaData;
        this.binaryContent = binaryContent;
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
    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void run() {
        contentStatistics.run();
    }

    @Override
    public void run(User user) {
        run();
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
    public BinaryContent getBinaryContent() {
        return binaryContent;
    }

    @Override
    public String toString() {
        return "Music{" +
                "contentMetaData=" + contentMetaData +
                ", contentStatistics=" + contentStatistics +
                ", binaryContent=" + binaryContent +
                ", comments=" + comments +
                '}' + "\n";
    }
}
