package cursach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    public static final String DEFAULT_NAME = "anonymous";
    public static final int DEFAULT_AGE = 0;
    private final String name;
    private final int age;
    private long money;
//    private PlayList myPlayLists = new PlayList("Мой лэйлист");
    private Map<String, PlayList> myPlayLists = new HashMap<>();
    private List<Content> contentsBue = new ArrayList<>();

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User() {
        this.name = DEFAULT_NAME;
        this.age = DEFAULT_AGE;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public long getMoney() {
        return money;
    }

    public Map<String, PlayList> getMyPlayLists() {
        return myPlayLists;
    }

    public PlayList getPlayList(String namePlayList) {
        return myPlayLists.get(namePlayList);
    }

    public void addPlayList(PlayList playList) {
        myPlayLists.put(playList.getName(), playList);
    }

    public void addContentInPlayList(String namePlayList, Content content) {
        if (myPlayLists.get(namePlayList) != null) {
            myPlayLists.get(namePlayList).addToPlayList(content);
        } else {
            PlayList newPlaylist = new PlayList(namePlayList);
            newPlaylist.addToPlayList(content);
            addPlayList(newPlaylist);
        }
    }

    public void runAllPlayList() {
        for (PlayList playList : myPlayLists.values()) {
            playList.runPlayList(this);
        }
    }

    public void pay(long price) {
        if (money < price) {
            throw new IllegalArgumentException("Недостаточно денег");
        } else {
            money = money - price;
        }
    }

    public boolean isContentBue(Content content) {
        return contentsBue.contains(content);
    }

    public void addBueContent(Content content) {
        contentsBue.add(content);
    }

    public void replenishAccount(long moneyTot) {
        money = money + moneyTot;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                ", myPlayLists=" + myPlayLists +
                '}';
    }
}
