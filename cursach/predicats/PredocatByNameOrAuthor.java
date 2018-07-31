package cursach.predicats;

import cursach.content.Content;

import java.util.function.Predicate;

public class PredocatByNameOrAuthor implements Predicate<Content> {

    private final String subName;

    public PredocatByNameOrAuthor(String subName) {
        this.subName = subName;
    }

    @Override
    public boolean test(Content content) {
        return content.getContentMetaData().getName().contains(subName) ||
                content.getContentMetaData().getAuthor().contains(subName);
    }
}
