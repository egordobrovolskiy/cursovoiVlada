package kursach.attempt2;

import java.util.ArrayList;
import java.util.List;

public class Video implements FreeContent {
    private final ContentMetaData contentMetaData;
    private ContentStatistics contentStatistics = new ContentStatistics();
    private final BinaryContent binaryContent;
    private List<Comment> comments = new ArrayList<>();

    public Video(ContentMetaData contentMetaData, BinaryContent binaryContent) {
        this.contentMetaData = contentMetaData;
        this.binaryContent = binaryContent;
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

    public void run() {
        contentStatistics.run();
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

    @Override
    public String toString() {
        return "Video{" +
                "contentMetaData=" + contentMetaData +
                ", contentStatistics=" + contentStatistics +
                ", binaryContent=" + binaryContent +
                ", comments=" + comments +
                '}' + "\n";
    }
}
