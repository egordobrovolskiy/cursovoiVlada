package cursach.content;

import cursach.*;
import cursach.content.component.BinaryContent;
import cursach.content.component.Comment;
import cursach.content.component.ContentMetaData;
import cursach.content.component.ContentStatistics;

import java.util.List;

public interface Content {

    ContentMetaData getContentMetaData();

    ContentStatistics getContentStatistics();

    BinaryContent getBinaryContent();

    List<Comment> getComments();

    void run();

    void run(User user);

    void addLike();

    void addDislike();

    void addComment(Comment comment);

}
