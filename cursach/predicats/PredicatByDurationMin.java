package cursach.predicats;

import cursach.content.Content;

import java.util.function.Predicate;

public class PredicatByDurationMin implements Predicate<Content> {
    private final int durationMin;
    private final static int SEC_IN_MIN = 60;

    public PredicatByDurationMin(int durationMin) {
        this.durationMin = durationMin;
    }

    @Override
    public boolean test(Content content) {
        return content.getContentMetaData().getDuration().getSeconds()/ SEC_IN_MIN < durationMin;
    }
}
