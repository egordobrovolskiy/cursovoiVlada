package cursach.predicats;

import cursach.content.Content;

import java.util.function.Predicate;

public class FilterFabric {
    public static Predicate<Content> filterByNameOrAuthor(String name) {
        return new PredocatByNameOrAuthor(name);
    }
    public static Predicate<Content> filterByDurationMinut(int minut) {
        return new PredicatByDurationMin(minut);
    }
    public static Predicate<Content> filterByContentSize(int size) {
        return new PredicatByContentSize(size);
    }
}
