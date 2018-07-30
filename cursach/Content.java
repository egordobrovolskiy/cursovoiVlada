package cursach;

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
