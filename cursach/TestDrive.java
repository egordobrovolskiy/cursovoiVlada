package cursach;

import cursach.comparators.SortType;
import cursach.content.Film;
import cursach.content.Music;
import cursach.content.Video;
import cursach.content.component.BinaryContent;
import cursach.content.component.Comment;
import cursach.content.component.ContentMetaData;
import cursach.content.component.FilmGenre;
import cursach.predicats.FilterFabric;
import cursach.predicats.FilterType;

import java.time.Duration;

import static java.lang.System.out;

public class TestDrive {
    public static void main(String[] args) {
        Video v = new Video(new ContentMetaData("Найди меня", "Самик", Duration.ofMinutes(33).plusSeconds(45)), new BinaryContent(new byte[]{4, 0, 9, 3}));
        Video v1 = new Video(new ContentMetaData("Как у нас там", "Рудольф", Duration.ofMinutes(15)), new BinaryContent(new byte[]{1, 1, 7, 5}));
        out.println(v.toString());
        v.addLike();
        v.addDislike();
        v.run();
        v.addComment(new Comment("Было очень легко!", new User("Denis", 23)));
        out.println(v.toString());
        out.println("**************************************************");

        Music m = new Music(new ContentMetaData("Хэй - хэй", "Березовец", Duration.ofMinutes(3).plusSeconds(22)), new BinaryContent(new byte[]{3, 3, 4, 5}));
        out.println(m.toString());
        m.addLike();
        m.addDislike();
        m.run();
        m.addComment(new Comment("Мне не понравилось.", new User("Егор", 30)));
        out.println(m.toString());

        out.println("***************************************************");

        PlayList playList = new PlayList("Чей - то");
        playList.addToPlayList(v);
        playList.addToPlayList(v1);
        playList.addToPlayList(m);
        out.println(playList.toString());

        playList.getContent(0).run();
        playList.getContent(0).addComment(new Comment("Ух ты!", new User("Борис", 40)));
        playList.getContent(2).addLike();
        out.println(playList.toString());

        out.println("********************************************************");

        Film f1 = new Film(new ContentMetaData("Война Z", "Режисёр2", Duration.ofHours(3).plusMinutes(12)), new BinaryContent(new byte[]{5, 4, 5, 4, 8, 3}), FilmGenre.HORROR, 350);

        Film f2 = new Film(new ContentMetaData("Война миров", "Режисёр", Duration.ofHours(2).plusMinutes(12)), new BinaryContent(new byte[]{4, 4, 4, 4}), FilmGenre.ACTION, 330);

        Film f3 = new Film(new ContentMetaData("Война", "Режисёр3", Duration.ofHours(1).plusMinutes(45)), new BinaryContent(new byte[]{4, 4, 4, 4, 3, 5, 1, 5, 8, 8}), FilmGenre.WAR, 1250);

        User u = new User("Ира", 27);
        u.replenishAccount(1500);
        out.println(u.toString());
        f2.run(u);
        out.println(u);

        out.println("************************   Изменения   *************************************");

        playList.addToPlayList(f1);
        playList.addToPlayList(f3);

        //можем добавить весь плейлист
        u.addPlayList(playList);

        // можем добавлять контент по одному, указывая имя плейлиста
        u.addContentInPlayList("Мой второй плей лист", v);
        u.addContentInPlayList("Мой второй плей лист", v1);
        u.addContentInPlayList("Мой второй плей лист", m);
        out.println(u.toString());

        out.println("********************************** From Egor **********************");
        out.println(playList.toString());

        u.addContentInPlayList("Мой второй плей лист", f2);

        u.getPlayList("Мой второй плей лист").runPlayList(u);
        out.println(u.getMyPlayLists().toString());
        out.println("****************************************");

        u.runAllPlayList();
        out.println();
        out.println("Проиграли все листы юзера: " + u.getName());
        out.println("*************************************");

        //Проверяем, что не платим повторно за фильм
        out.println("Осталось денег: " + u.getMoney());

        //лист без юзера проигрывает только бесплатные композиции, при платном выкидывает exception.
        playList.addToPlayList(f2);

        out.println("Начало проигрывания анонимного списка");
        playList.runPlayList(); // Exception обработается
        out.println("Проиграли всю халяву");


        out.println("\n((((((((((((((((((( SORT ))))))))))))))))\n");
        testSort(playList);

        out.println("\n(((((((((((((((((( FILTER )))))))))))))))\n");
        testFilter(playList);
    }


    private static void testSort(PlayList playList) {
        out.println("================ post sort name ====================");
        playList.getContents().forEach(x -> out.println(x.getClass().getSimpleName() +
                " - " + x.getContentMetaData()));
        out.println("================ after sort name ====================");
        playList.sort(SortType.SORT_BY_NAME).forEach(x -> out.println(x.getClass().getSimpleName() +
                " - " + x.getContentMetaData()));

        out.println("\n=============== post sort author ====================");
        playList.getContents().forEach(x -> out.println(x.getClass().getSimpleName() +
                " - " + x.getContentMetaData()));
        out.println("=============== after sort author ====================");
        playList.sort(SortType.SORT_BY_AUTHOR).forEach(x -> out.println(x.getClass().getSimpleName() +
                " - " + x.getContentMetaData()));

        out.println("\n=============== post sort duration ===================");
        playList.getContents().forEach(x -> out.println(x.getClass().getSimpleName() + " - " +
                x.getContentMetaData()));
        out.println("=============== after sort duration ==================");
        playList.sort(SortType.SORT_BY_DURATION).forEach(x -> out.println(x.getClass().getSimpleName() +
                " - " + x.getContentMetaData()));

        out.println("\n=============== post sort genre ======================");
        playList.getContents(Film.class).forEach(x -> out.println(x.getClass().getSimpleName() +
                " - " + x.getContentMetaData() + " Genre: " + ((Film) x).getFilmGenre()));
        out.println("=============== after sort genre =====================");
        playList.getSortListFilmByGenre().stream().map(x -> (Film) x).forEach(x -> out.println(x.getClass()
                .getSimpleName() + " - " + x.getContentMetaData() + " Genre: " + x.getFilmGenre()));
    }


    private static void testFilter(PlayList playList) {
        out.println("++++++++++++++ post filter +++++++++++++++++++++");
        playList.getContents().stream().map(x -> x.getContentMetaData().getAuthor()).forEach(out::println);

        out.println("\n++++++++++++++ filter \"Реж\" ++++++++++++++++++");

        playList.filter(FilterFabric.filterByNameOrAuthor("Реж")).stream().map(x -> x.getContentMetaData()
                .getAuthor()).forEach(out::println);

        out.println("\n++++++++++++++ filter duration \"120 min\" ++++++++++++++");

        playList.filter(FilterFabric.filterByDurationMinut(120))
                .forEach(x -> out.println(x.getClass().getSimpleName() + " - " + x.getContentMetaData()));

        out.println("\n+++++++++++ filter content size < 5 / 8 / 1024 = 0,0006103515625 kb +++++++++++++");

        playList.filter(FilterFabric.filterByContentSize(5)).forEach(x -> out.println(x.getClass().getSimpleName()
                + " - " + x.getContentMetaData() +
                        " " + x.getBinaryContent()));

        out.println("\n+++++++++++ filter FILM by genre \"WAR\" ++++++++++++");
        playList.getFilterFilmByGenre(FilmGenre.WAR).stream().map(x -> (Film) x)
                .forEach(x -> out.println(x.getClass()
                .getSimpleName() + " - " + x.getContentMetaData() + " Genre: " + x.getFilmGenre() + ", " +
                " price: " + x.getPrice()));
    }
}

//Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=33 min 45 sec }, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{size_bait=  4}, comments=[]}
//
//        Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=33 min 45 sec }, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=1}, binaryContent=BinaryContent{size_bait=  4}, comments=[Comment {User: Denis  = 'Было очень легко!'}
//        ]}
//
//        **************************************************
//        Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=3 min 22 sec }, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{size_bait=  4}, comments=[]}
//
//        Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=3 min 22 sec }, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=1}, binaryContent=BinaryContent{size_bait=  4}, comments=[Comment {User: Егор  = 'Мне не понравилось.'}
//        ]}
//
//        ***************************************************
//        PlayList{name='Чей - то', contents={class cursach.content.Video=[Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=33 min 45 sec }, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=1}, binaryContent=BinaryContent{size_bait=  4}, comments=[Comment {User: Denis  = 'Было очень легко!'}
//        ]}
//        , Video{contentMetaData=ContentMetaData{name='Как у нас там', author='Рудольф', duration=15 min 0 sec }, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{size_bait=  4}, comments=[]}
//        ], class cursach.content.Music=[Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=3 min 22 sec }, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=1}, binaryContent=BinaryContent{size_bait=  4}, comments=[Comment {User: Егор  = 'Мне не понравилось.'}
//        ]}
//        ]}}
//        PlayList{name='Чей - то', contents={class cursach.content.Video=[Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=33 min 45 sec }, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=2}, binaryContent=BinaryContent{size_bait=  4}, comments=[Comment {User: Denis  = 'Было очень легко!'}
//        , Comment {User: Борис  = 'Ух ты!'}
//        ]}
//        , Video{contentMetaData=ContentMetaData{name='Как у нас там', author='Рудольф', duration=15 min 0 sec }, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{size_bait=  4}, comments=[]}
//        ], class cursach.content.Music=[Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=3 min 22 sec }, contentStatistics=ContentStatistics{like=2, dislike=1, totalViews=1}, binaryContent=BinaryContent{size_bait=  4}, comments=[Comment {User: Егор  = 'Мне не понравилось.'}
//        ]}
//        ]}}
//        ********************************************************
//        User{name='Ира', age=27, money=1500, myPlayLists={}}
//        User{name='Ира', age=27, money=1170, myPlayLists={}}
//        ************************   Изменения   *************************************
//        User{name='Ира', age=27, money=1170, myPlayLists={Чей - то=PlayList{name='Чей - то', contents={class cursach.content.Video=[Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=33 min 45 sec }, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=2}, binaryContent=BinaryContent{size_bait=  4}, comments=[Comment {User: Denis  = 'Было очень легко!'}
//        , Comment {User: Борис  = 'Ух ты!'}
//        ]}
//        , Video{contentMetaData=ContentMetaData{name='Как у нас там', author='Рудольф', duration=15 min 0 sec }, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{size_bait=  4}, comments=[]}
//        ], class cursach.content.Film=[Film{contentMetaData=ContentMetaData{name='Война Z', author='Режисёр2', duration=192 min 0 sec }, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{size_bait=  6}, filmGenre=HORROR, comments=[], price=350}, Film{contentMetaData=ContentMetaData{name='Война', author='Режисёр3', duration=105 min 0 sec }, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{size_bait=  10}, filmGenre=WAR, comments=[], price=1250}], class cursach.content.Music=[Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=3 min 22 sec }, contentStatistics=ContentStatistics{like=2, dislike=1, totalViews=1}, binaryContent=BinaryContent{size_bait=  4}, comments=[Comment {User: Егор  = 'Мне не понравилось.'}
//        ]}
//        ]}}, Мой второй плей лист=PlayList{name='Мой второй плей лист', contents={class cursach.content.Video=[Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=33 min 45 sec }, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=2}, binaryContent=BinaryContent{size_bait=  4}, comments=[Comment {User: Denis  = 'Было очень легко!'}
//        , Comment {User: Борис  = 'Ух ты!'}
//        ]}
//        , Video{contentMetaData=ContentMetaData{name='Как у нас там', author='Рудольф', duration=15 min 0 sec }, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{size_bait=  4}, comments=[]}
//        ], class cursach.content.Music=[Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=3 min 22 sec }, contentStatistics=ContentStatistics{like=2, dislike=1, totalViews=1}, binaryContent=BinaryContent{size_bait=  4}, comments=[Comment {User: Егор  = 'Мне не понравилось.'}
//        ]}
//        ]}}}}
//        ********************************** From Egor **********************
//        PlayList{name='Чей - то', contents={class cursach.content.Video=[Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=33 min 45 sec }, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=2}, binaryContent=BinaryContent{size_bait=  4}, comments=[Comment {User: Denis  = 'Было очень легко!'}
//        , Comment {User: Борис  = 'Ух ты!'}
//        ]}
//        , Video{contentMetaData=ContentMetaData{name='Как у нас там', author='Рудольф', duration=15 min 0 sec }, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{size_bait=  4}, comments=[]}
//        ], class cursach.content.Film=[Film{contentMetaData=ContentMetaData{name='Война Z', author='Режисёр2', duration=192 min 0 sec }, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{size_bait=  6}, filmGenre=HORROR, comments=[], price=350}, Film{contentMetaData=ContentMetaData{name='Война', author='Режисёр3', duration=105 min 0 sec }, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{size_bait=  10}, filmGenre=WAR, comments=[], price=1250}], class cursach.content.Music=[Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=3 min 22 sec }, contentStatistics=ContentStatistics{like=2, dislike=1, totalViews=1}, binaryContent=BinaryContent{size_bait=  4}, comments=[Comment {User: Егор  = 'Мне не понравилось.'}
//        ]}
//        ]}}
//        {Чей - то=PlayList{name='Чей - то', contents={class cursach.content.Video=[Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=33 min 45 sec }, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=3}, binaryContent=BinaryContent{size_bait=  4}, comments=[Comment {User: Denis  = 'Было очень легко!'}
//        , Comment {User: Борис  = 'Ух ты!'}
//        ]}
//        , Video{contentMetaData=ContentMetaData{name='Как у нас там', author='Рудольф', duration=15 min 0 sec }, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=1}, binaryContent=BinaryContent{size_bait=  4}, comments=[]}
//        ], class cursach.content.Film=[Film{contentMetaData=ContentMetaData{name='Война Z', author='Режисёр2', duration=192 min 0 sec }, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{size_bait=  6}, filmGenre=HORROR, comments=[], price=350}, Film{contentMetaData=ContentMetaData{name='Война', author='Режисёр3', duration=105 min 0 sec }, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{size_bait=  10}, filmGenre=WAR, comments=[], price=1250}], class cursach.content.Music=[Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=3 min 22 sec }, contentStatistics=ContentStatistics{like=2, dislike=1, totalViews=2}, binaryContent=BinaryContent{size_bait=  4}, comments=[Comment {User: Егор  = 'Мне не понравилось.'}
//        ]}
//        ]}}, Мой второй плей лист=PlayList{name='Мой второй плей лист', contents={class cursach.content.Video=[Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=33 min 45 sec }, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=3}, binaryContent=BinaryContent{size_bait=  4}, comments=[Comment {User: Denis  = 'Было очень легко!'}
//        , Comment {User: Борис  = 'Ух ты!'}
//        ]}
//        , Video{contentMetaData=ContentMetaData{name='Как у нас там', author='Рудольф', duration=15 min 0 sec }, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=1}, binaryContent=BinaryContent{size_bait=  4}, comments=[]}
//        ], class cursach.content.Film=[Film{contentMetaData=ContentMetaData{name='Война миров', author='Режисёр', duration=132 min 0 sec }, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=2}, binaryContent=BinaryContent{size_bait=  4}, filmGenre=ACTION, comments=[], price=330}], class cursach.content.Music=[Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=3 min 22 sec }, contentStatistics=ContentStatistics{like=2, dislike=1, totalViews=2}, binaryContent=BinaryContent{size_bait=  4}, comments=[Comment {User: Егор  = 'Мне не понравилось.'}
//        ]}
//        ]}}}
//        ****************************************
//        июл 31, 2018 10:06:44 AM cursach.PlayList runPlayList
//        WARNING: ************* --== Поймали исключение: Недостаточно денег ==--
//        Контент: Film{contentMetaData=ContentMetaData{name='Война', author='Режисёр3', duration=105 min 0 sec }, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{size_bait=  10}, filmGenre=WAR, comments=[], price=1250} - не проиграл!!!!
//        User: Ира;  Money: 820
//
//        июл 31, 2018 10:06:44 AM cursach.PlayList runPlayList
//        WARNING: ************* --== Поймали исключение: Авторизируйтесь ==--
//        Контент: Film{contentMetaData=ContentMetaData{name='Война Z', author='Режисёр2', duration=192 min 0 sec }, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=1}, binaryContent=BinaryContent{size_bait=  6}, filmGenre=HORROR, comments=[], price=350} - не проиграл!!!!
//        User: anonymous;  Money: 0
//
//        июл 31, 2018 10:06:44 AM cursach.PlayList runPlayList
//        WARNING: ************* --== Поймали исключение: Авторизируйтесь ==--
//        Контент: Film{contentMetaData=ContentMetaData{name='Война', author='Режисёр3', duration=105 min 0 sec }, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{size_bait=  10}, filmGenre=WAR, comments=[], price=1250} - не проиграл!!!!
//        User: anonymous;  Money: 0
//
//        июл 31, 2018 10:06:44 AM cursach.PlayList runPlayList
//        WARNING: ************* --== Поймали исключение: Авторизируйтесь ==--
//        Контент: Film{contentMetaData=ContentMetaData{name='Война миров', author='Режисёр', duration=132 min 0 sec }, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=3}, binaryContent=BinaryContent{size_bait=  4}, filmGenre=ACTION, comments=[], price=330} - не проиграл!!!!
//        User: anonymous;  Money: 0
//
//
//        Проиграли все листы юзера: Ира
//        *************************************
//        Осталось денег: 820
//        Начало проигрывания анонимного списка
//        Проиграли всю халяву
//
//        ((((((((((((((((((( SORT ))))))))))))))))
//
//        ================ post sort name ====================
//        Video - ContentMetaData{name='Найди меня', author='Самик', duration=33 min 45 sec }
//        Video - ContentMetaData{name='Как у нас там', author='Рудольф', duration=15 min 0 sec }
//        Film - ContentMetaData{name='Война Z', author='Режисёр2', duration=192 min 0 sec }
//        Film - ContentMetaData{name='Война', author='Режисёр3', duration=105 min 0 sec }
//        Film - ContentMetaData{name='Война миров', author='Режисёр', duration=132 min 0 sec }
//        Music - ContentMetaData{name='Хэй - хэй', author='Березовец', duration=3 min 22 sec }
//        ================ after sort name ====================
//        Film - ContentMetaData{name='Война', author='Режисёр3', duration=105 min 0 sec }
//        Film - ContentMetaData{name='Война Z', author='Режисёр2', duration=192 min 0 sec }
//        Film - ContentMetaData{name='Война миров', author='Режисёр', duration=132 min 0 sec }
//        Video - ContentMetaData{name='Как у нас там', author='Рудольф', duration=15 min 0 sec }
//        Video - ContentMetaData{name='Найди меня', author='Самик', duration=33 min 45 sec }
//        Music - ContentMetaData{name='Хэй - хэй', author='Березовец', duration=3 min 22 sec }
//
//        =============== post sort author ====================
//        Video - ContentMetaData{name='Найди меня', author='Самик', duration=33 min 45 sec }
//        Video - ContentMetaData{name='Как у нас там', author='Рудольф', duration=15 min 0 sec }
//        Film - ContentMetaData{name='Война Z', author='Режисёр2', duration=192 min 0 sec }
//        Film - ContentMetaData{name='Война', author='Режисёр3', duration=105 min 0 sec }
//        Film - ContentMetaData{name='Война миров', author='Режисёр', duration=132 min 0 sec }
//        Music - ContentMetaData{name='Хэй - хэй', author='Березовец', duration=3 min 22 sec }
//        =============== after sort author ====================
//        Music - ContentMetaData{name='Хэй - хэй', author='Березовец', duration=3 min 22 sec }
//        Film - ContentMetaData{name='Война миров', author='Режисёр', duration=132 min 0 sec }
//        Film - ContentMetaData{name='Война Z', author='Режисёр2', duration=192 min 0 sec }
//        Film - ContentMetaData{name='Война', author='Режисёр3', duration=105 min 0 sec }
//        Video - ContentMetaData{name='Как у нас там', author='Рудольф', duration=15 min 0 sec }
//        Video - ContentMetaData{name='Найди меня', author='Самик', duration=33 min 45 sec }
//
//        =============== post sort duration ===================
//        Video - ContentMetaData{name='Найди меня', author='Самик', duration=33 min 45 sec }
//        Video - ContentMetaData{name='Как у нас там', author='Рудольф', duration=15 min 0 sec }
//        Film - ContentMetaData{name='Война Z', author='Режисёр2', duration=192 min 0 sec }
//        Film - ContentMetaData{name='Война', author='Режисёр3', duration=105 min 0 sec }
//        Film - ContentMetaData{name='Война миров', author='Режисёр', duration=132 min 0 sec }
//        Music - ContentMetaData{name='Хэй - хэй', author='Березовец', duration=3 min 22 sec }
//        =============== after sort duration ==================
//        Music - ContentMetaData{name='Хэй - хэй', author='Березовец', duration=3 min 22 sec }
//        Video - ContentMetaData{name='Как у нас там', author='Рудольф', duration=15 min 0 sec }
//        Video - ContentMetaData{name='Найди меня', author='Самик', duration=33 min 45 sec }
//        Film - ContentMetaData{name='Война', author='Режисёр3', duration=105 min 0 sec }
//        Film - ContentMetaData{name='Война миров', author='Режисёр', duration=132 min 0 sec }
//        Film - ContentMetaData{name='Война Z', author='Режисёр2', duration=192 min 0 sec }
//
//        =============== post sort genre ======================
//        Film - ContentMetaData{name='Война Z', author='Режисёр2', duration=192 min 0 sec } Genre: HORROR
//        Film - ContentMetaData{name='Война', author='Режисёр3', duration=105 min 0 sec } Genre: WAR
//        Film - ContentMetaData{name='Война миров', author='Режисёр', duration=132 min 0 sec } Genre: ACTION
//        =============== after sort genre =====================
//        Film - ContentMetaData{name='Война миров', author='Режисёр', duration=132 min 0 sec } Genre: ACTION
//        Film - ContentMetaData{name='Война', author='Режисёр3', duration=105 min 0 sec } Genre: WAR
//        Film - ContentMetaData{name='Война Z', author='Режисёр2', duration=192 min 0 sec } Genre: HORROR
//
//        (((((((((((((((((( FILTER )))))))))))))))
//
//        ++++++++++++++ post filter +++++++++++++++++++++
//        Самик
//        Рудольф
//        Режисёр
//        Режисёр3
//        Режисёр2
//        Березовец
//
//        ++++++++++++++ filter "Реж" ++++++++++++++++++
//        Режисёр
//        Режисёр3
//        Режисёр2
//
//        ++++++++++++++ filter duration "120 min" ++++++++++++++
//        Video - ContentMetaData{name='Найди меня', author='Самик', duration=33 min 45 sec }
//        Video - ContentMetaData{name='Как у нас там', author='Рудольф', duration=15 min 0 sec }
//        Film - ContentMetaData{name='Война', author='Режисёр3', duration=105 min 0 sec }
//        Music - ContentMetaData{name='Хэй - хэй', author='Березовец', duration=3 min 22 sec }
//
//        +++++++++++ filter content size < 5 / 8 / 1024 = 0,0006103515625 kb +++++++++++++
//        Video - ContentMetaData{name='Найди меня', author='Самик', duration=33 min 45 sec } BinaryContent{size_bait=  4}
//        Video - ContentMetaData{name='Как у нас там', author='Рудольф', duration=15 min 0 sec } BinaryContent{size_bait=  4}
//        Film - ContentMetaData{name='Война миров', author='Режисёр', duration=132 min 0 sec } BinaryContent{size_bait=  4}
//        Music - ContentMetaData{name='Хэй - хэй', author='Березовец', duration=3 min 22 sec } BinaryContent{size_bait=  4}
//
//        +++++++++++ filter FILM by genre "WAR" ++++++++++++
//        Film - ContentMetaData{name='Война', author='Режисёр3', duration=105 min 0 sec } Genre: WAR,  price: 1250
//
//        Process finished with exit code 0