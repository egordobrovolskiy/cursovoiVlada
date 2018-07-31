package cursach.comparators;

import cursach.content.Content;

import java.util.Comparator;

public enum SortType {

    SORT_BY_NAME {
        @Override
        public Comparator<Content> getComparator() {
            return Comparator.comparing(o -> o.getContentMetaData().getName());
        }
    },

    SORT_BY_AUTHOR {
        @Override
        public Comparator<Content> getComparator() {
            return Comparator.comparing(o -> o.getContentMetaData().getAuthor());
        }
    },

    SORT_BY_DURATION {
        @Override
        public Comparator<Content> getComparator() {
            return Comparator.comparing(o -> o.getContentMetaData().getDuration());
        }
    },

    SORT_BY_SIZE_CONTENT {
        @Override
        public Comparator<Content> getComparator() {
            return Comparator.comparingInt(o -> o.getBinaryContent().getContent().length);
        }
    },

    SORT_BY_VIEWS {
        @Override
        public Comparator<Content> getComparator() {
            return (o1, o2) -> (int) (o1.getContentStatistics().getTotalViews() - o2.getContentStatistics().getTotalViews());
        }
    },

    SORT_BY_LIKE {
        @Override
        public Comparator<Content> getComparator() {
            return (o1, o2) -> (int) (o1.getContentStatistics().getLike() - o2.getContentStatistics().getLike());
        }
    },

    SORT_BY_DISLIKE {
        @Override
        public Comparator<Content> getComparator() {
            return (o1, o2) -> (int) (o1.getContentStatistics().getDislike() - o2.getContentStatistics().getDislike());
        }
    },

    SORT_BY_COMMENT {
        @Override
        public Comparator<Content> getComparator() {
            return Comparator.comparingInt(o -> o.getComments().size());
        }
    };


    public abstract Comparator<Content> getComparator();
}
