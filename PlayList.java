package kursach.attempt2;

import java.util.ArrayList;
import java.util.List;

public class PlayList {
    private String name;
    private List<FreeContent> contents = new ArrayList<>();

    public PlayList(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<FreeContent> getContents() {
        return contents;
    }

    void addToPlayList(FreeContent content) {
        contents.add(content);
    }

    public FreeContent getContent(int index) {
        if (index >= getContents().size()) {
            throw new IndexOutOfBoundsException("Такого контента нет");
        }
        return contents.get(index);
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "name='" + name + '\'' +
                ", contents=" + contents +
                '}';
    }
}
