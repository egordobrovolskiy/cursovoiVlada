package cursach;

import cursach.comparators.SortType;

import java.util.*;

public class PlayList {
    private String name;
//    private List<Content> contents = new ArrayList<>();

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
                System.out.println("************* --== Поймали исключение: " + e.getMessage() + " ==--");
                System.out.println("Контент: " + content.toString() + " - не проиграл!!!!");
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

    @Override
    public String toString() {
        return "PlayList{" +
                "name='" + name + '\'' +
                ", contents=" + contents +
                '}';
    }
}
