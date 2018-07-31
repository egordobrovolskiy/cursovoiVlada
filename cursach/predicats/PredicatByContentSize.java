package cursach.predicats;

import cursach.content.Content;

import java.util.function.Predicate;

public class PredicatByContentSize implements Predicate<Content> {
    private final int sizeKb;

    public PredicatByContentSize(int sizeKb) {
        this.sizeKb = sizeKb;
    }

    @Override
    public boolean test(Content content) {
        return content.getBinaryContent().getContent().length < sizeKb;
    }
}
