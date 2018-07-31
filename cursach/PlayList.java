package cursach;

import cursach.comparators.SortType;
import cursach.predicats.Filter;
import cursach.predicats.FilterType;

import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class PlayList {
    private String name;
//    private List<Content> contents = new ArrayList<>();

    private Logger logger = Logger.getLogger(PlayList.class.getName());

    private Map<Class<? extends Content>, List<Content>> contents = new HashMap<>();

    public PlayList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Content> getContents() {
        List<Content> result = new ArrayList<>();
        for (List<Content> list : contents.values()) {
            result.addAll(list);
        }
        return result;
    }

    public List<Content> getContents(Class<? extends Content> clazz) {
        return contents.get(clazz);
    }

    void addToPlayList(Content content) {
        if (contents.get(content.getClass()) == null) {
            List<Content> list = new ArrayList<>();
            list.add(content);
            contents.put(content.getClass(), list);
        } else {
            contents.get(content.getClass()).add(content);
        }
    }

    public Content getContent(int index) {
        if (index >= getContents().size()) {
            throw new IndexOutOfBoundsException("Такого контента нет");
        }
        return getContents().get(index);
    }


    public void runPlayList() {
        runPlayList(new User());

    }

    public void runPlayList(User user) {
        for (Content content : getContents()) {

            try {
                content.run(user);
            } catch (Exception e) {
                logger.warning("************* --== Поймали исключение: " + e.getClass().getSimpleName()
                        + "; " + "message: " + e.getMessage() + " ==--" + "\n"
                        + "Контент: " + content.toString() + " - не проиграл!!!!" + "\n"
                        + "User: " + user.getName() + "; " + " Money: " + user.getMoney() + "\n");
            }
        }
    }

    public List<Content> sort(SortType sortType) {
        List<Content> result = getContents();
        result.sort(sortType.getComparator());
        return result;
    }

    public List<Content> getSortListFilmByGenre() {
        List<Content> list = contents.get(Film.class);
        list.sort(Comparator.comparing(o -> ((Film) o).getFilmGenre()));
        return list;
    }

    public List<Content> filter(Predicate<Content> predicate) {
        return Filter.filter(getContents(), predicate);
    }

    public List<Content> getFilterFilmByGenre(FilmGenre genre) {
        List<Content> result = contents.get(Film.class);
        return Filter.filter(result, content -> genre.equals(((Film)content).getFilmGenre()));
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "name='" + name + '\'' +
                ", contents=" + contents +
                '}';
    }
}
