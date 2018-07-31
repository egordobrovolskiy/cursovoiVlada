package cursach.predicats;

import cursach.content.Content;


import java.util.function.Predicate;

/**
 * Использовать FilterFabric.java
 * */

@Deprecated
public enum FilterType {
    FILTER_BY_NAME {
        @Override
        public <T> Predicate<Content> getFilter(T name) {
            return content -> content.getContentMetaData().getName().contains((CharSequence) name);
        }
    },
    FILTER_BY_AUTHOR{
        @Override
        public <T> Predicate<Content> getFilter(T author) {
            return content -> content.getContentMetaData().getAuthor().contains((CharSequence) author);
        }
    },
    FILTER_BY_SUB_NAME_OR_SUB_AUTHOR{
        @Override
        public <T> Predicate<Content> getFilter(T subName) {
            return content -> content.getContentMetaData().getName().contains((CharSequence) subName) ||
                    content.getContentMetaData().getAuthor().contains((CharSequence) subName);
        }
    },
    FILTER_BU_DURATION_MIN {
        @Override
        public <T> Predicate<Content> getFilter(T durationMin) {
            return content -> content.getContentMetaData().getDuration().getSeconds()/ SEC_IN_MIN < Long.parseLong(durationMin.toString());
        }
    },
    FILTER_BY_CONTENT_SIZE_KB {
        @Override
        public <T> Predicate<Content> getFilter(T contentSize) {
            return content -> content.getBinaryContent().getContent().length < Integer.parseInt(contentSize.toString());
        }
    };

    public static final int SEC_IN_MIN = 60;

    public abstract <T> Predicate<Content> getFilter(T filterBy);
}
