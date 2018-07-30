package cursach;

import cursach.comparators.SortType;

import java.time.Duration;

public class TestDrive {
    public static void main(String[] args) {
        Video v = new Video(new ContentMetaData("Найди меня", "Самик", Duration.ofMinutes(33).plusMillis(45)),
                new BinaryContent(new byte[]{4, 0, 9, 3}));
        Video v1 = new Video(new ContentMetaData("Как у нас там", "Рудольф", Duration.ofMinutes(15)),
                new BinaryContent(new byte[]{1, 1, 7, 5}));
        System.out.println(v.toString());
        v.addLike();
        v.addDislike();
        v.run();
        v.addComment(new Comment("Было очень легко!", new User("Denis", 23)));
        System.out.println(v.toString());
        System.out.println("**************************************************");
        Music m = new Music(new ContentMetaData("Хэй - хэй", "Березовец", Duration.ofMinutes(3).plusMillis(22)), new BinaryContent(new byte[]{3, 3, 4, 5}));
        System.out.println(m.toString());
        m.addLike();
        m.addDislike();
        m.run();
        m.addComment(new Comment("Мне не понравилось.", new User("Егор", 30)));
        System.out.println(m.toString());
        System.out.println("***************************************************");
        PlayList playList = new PlayList("Чей - то");
        playList.addToPlayList(v);
        playList.addToPlayList(v1);
        playList.addToPlayList(m);
        System.out.println(playList.toString());
        playList.getContent(0).run();
        playList.getContent(0).addComment(new Comment("Ух ты!", new User("Борис", 40)));
        playList.getContent(2).addLike();
        System.out.println(playList.toString());
        System.out.println("********************************************************");
        Film f1 = new Film(new ContentMetaData("Война Z", "Режисёр2", Duration.ofHours(3).plusMinutes(12)),
                new BinaryContent(new byte[]{5, 4, 5, 4, 8, 3}), FilmGenre.HORROR, 350);
        Film f2 = new Film(new ContentMetaData("Война миров", "Режисёр", Duration.ofHours(2).plusMinutes(12)),
                new BinaryContent(new byte[]{4, 4, 4, 4}), FilmGenre.ACTION, 330);

        Film f3 = new Film(new ContentMetaData("Война", "Режисёр3", Duration.ofHours(1).plusMinutes(45)),
                new BinaryContent(new byte[]{4, 4, 4, 4, 3, 5, 1, 5, 8, 8}), FilmGenre.WAR, 250);

        User u = new User("Ира", 27);
        u.replenishAccount(1500);
        System.out.println(u.toString());
        f2.run(u);
        System.out.println(u);

        System.out.println("************************   Изменения   *************************************");

        //можем добавить весь плейлист
        u.addPlayList(playList);

        // можем добавлять контент по одному, указывая имя плейлиста
        u.addContentInPlayList("Мой второй плей лист", v);
        u.addContentInPlayList("Мой второй плей лист", v1);
        u.addContentInPlayList("Мой второй плей лист", m);
        System.out.println(u.toString());

        System.out.println("********************************** From Egor **********************");
        System.out.println(playList.toString());

        u.addContentInPlayList("Мой второй плей лист", f2);

        u.getPlayList("Мой второй плей лист").runPlayList(u);
        System.out.println(u.getMyPlayLists().toString());
        System.out.println("****************************************");

        u.runAllPlayList();
        System.out.println("Проиграли все листы юзера: " + u.getName());
        System.out.println("*************************************");

        //Проверяем, что не платим повторно за фильм
        System.out.println("Осталось денег: " + u.getMoney());

        //лист без юзера проигрывает только бесплатные композиции, при платном выкидывает exception.
        playList.addToPlayList(f2);

        System.out.println("Начало проигрывания анонимного списка");
        playList.runPlayList(); // Exception обработается
        System.out.println("Проиграли всю халяву");

        playList.addToPlayList(f1);
        playList.addToPlayList(f3);

        System.out.println("========================== post sort name =============================");
        playList.getContents().stream().map(x -> x.getContentMetaData().getName()).forEach(System.out::println);
        System.out.println("========================== after sort name =============================");
        playList.sort(SortType.SORT_BY_NAME).stream().map(x -> x.getContentMetaData().getName()).forEach(System.out::println);

        System.out.println("========================== post sort author =============================");
        playList.getContents().stream().map(x -> x.getContentMetaData().getAuthor()).forEach(System.out::println);
        System.out.println("========================== after sort author =============================");
        playList.sort(SortType.SORT_BY_AUTHOR).stream().map(x -> x.getContentMetaData().getAuthor()).forEach(System.out::println);

        System.out.println("========================== post sort duration =============================");
        playList.getContents().stream().map(x -> x.getContentMetaData().getDuration().getSeconds()).forEach(System.out::println);
        System.out.println("========================== after sort duration =============================");
        playList.sort(SortType.SORT_BY_DURATION).stream().map(x -> x.getContentMetaData().getDuration().getSeconds()).forEach(System.out::println);

        System.out.println("========================== post sort genre =============================");
        playList.getContents(Film.class).stream().map(x -> (Film)x).map(Film::getFilmGenre).forEach(System.out::println);
        System.out.println("========================== after sort genre =============================");
        playList.getSortListFilmByGenre().stream().map(x -> (Film) x).map(Film::getFilmGenre).forEach(System.out::println);

    }
}

//Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=PT33M0.045S}, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{content=[4, 0, 9, 3]}, comments=[]}
//
//        Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=PT33M0.045S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=1}, binaryContent=BinaryContent{content=[4, 0, 9, 3]}, comments=[Comment {User: Denis  = 'Было очень легко!'}
//        ]}
//
//        **************************************************
//        Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=PT3M0.022S}, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{content=[3, 3, 4, 5]}, comments=[]}
//
//        Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=PT3M0.022S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=1}, binaryContent=BinaryContent{content=[3, 3, 4, 5]}, comments=[Comment {User: Егор  = 'Мне не понравилось.'}
//        ]}
//
//        ***************************************************
//        PlayList{name='Чей - то', contents={class cursach.Music=[Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=PT3M0.022S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=1}, binaryContent=BinaryContent{content=[3, 3, 4, 5]}, comments=[Comment {User: Егор  = 'Мне не понравилось.'}
//        ]}
//        ], class cursach.Video=[Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=PT33M0.045S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=1}, binaryContent=BinaryContent{content=[4, 0, 9, 3]}, comments=[Comment {User: Denis  = 'Было очень легко!'}
//        ]}
//        , Video{contentMetaData=ContentMetaData{name='Как у нас там', author='Рудольф', duration=PT15M}, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{content=[1, 1, 7, 5]}, comments=[]}
//        ]}}
//        PlayList{name='Чей - то', contents={class cursach.Music=[Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=PT3M0.022S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=2}, binaryContent=BinaryContent{content=[3, 3, 4, 5]}, comments=[Comment {User: Егор  = 'Мне не понравилось.'}
//        , Comment {User: Борис  = 'Ух ты!'}
//        ]}
//        ], class cursach.Video=[Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=PT33M0.045S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=1}, binaryContent=BinaryContent{content=[4, 0, 9, 3]}, comments=[Comment {User: Denis  = 'Было очень легко!'}
//        ]}
//        , Video{contentMetaData=ContentMetaData{name='Как у нас там', author='Рудольф', duration=PT15M}, contentStatistics=ContentStatistics{like=1, dislike=0, totalViews=0}, binaryContent=BinaryContent{content=[1, 1, 7, 5]}, comments=[]}
//        ]}}
//        ********************************************************
//        User{name='Ира', age=27, money=1500, myPlayLists={}}
//        User{name='Ира', age=27, money=1170, myPlayLists={}}
//        ************************   Изменения   *************************************
//        User{name='Ира', age=27, money=1170, myPlayLists={Чей - то=PlayList{name='Чей - то', contents={class cursach.Music=[Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=PT3M0.022S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=2}, binaryContent=BinaryContent{content=[3, 3, 4, 5]}, comments=[Comment {User: Егор  = 'Мне не понравилось.'}
//        , Comment {User: Борис  = 'Ух ты!'}
//        ]}
//        ], class cursach.Video=[Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=PT33M0.045S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=1}, binaryContent=BinaryContent{content=[4, 0, 9, 3]}, comments=[Comment {User: Denis  = 'Было очень легко!'}
//        ]}
//        , Video{contentMetaData=ContentMetaData{name='Как у нас там', author='Рудольф', duration=PT15M}, contentStatistics=ContentStatistics{like=1, dislike=0, totalViews=0}, binaryContent=BinaryContent{content=[1, 1, 7, 5]}, comments=[]}
//        ]}}, Мой второй плей лист=PlayList{name='Мой второй плей лист', contents={class cursach.Music=[Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=PT3M0.022S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=2}, binaryContent=BinaryContent{content=[3, 3, 4, 5]}, comments=[Comment {User: Егор  = 'Мне не понравилось.'}
//        , Comment {User: Борис  = 'Ух ты!'}
//        ]}
//        ], class cursach.Video=[Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=PT33M0.045S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=1}, binaryContent=BinaryContent{content=[4, 0, 9, 3]}, comments=[Comment {User: Denis  = 'Было очень легко!'}
//        ]}
//        , Video{contentMetaData=ContentMetaData{name='Как у нас там', author='Рудольф', duration=PT15M}, contentStatistics=ContentStatistics{like=1, dislike=0, totalViews=0}, binaryContent=BinaryContent{content=[1, 1, 7, 5]}, comments=[]}
//        ]}}}}
//        ********************************** From Egor **********************
//        PlayList{name='Чей - то', contents={class cursach.Music=[Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=PT3M0.022S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=2}, binaryContent=BinaryContent{content=[3, 3, 4, 5]}, comments=[Comment {User: Егор  = 'Мне не понравилось.'}
//        , Comment {User: Борис  = 'Ух ты!'}
//        ]}
//        ], class cursach.Video=[Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=PT33M0.045S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=1}, binaryContent=BinaryContent{content=[4, 0, 9, 3]}, comments=[Comment {User: Denis  = 'Было очень легко!'}
//        ]}
//        , Video{contentMetaData=ContentMetaData{name='Как у нас там', author='Рудольф', duration=PT15M}, contentStatistics=ContentStatistics{like=1, dislike=0, totalViews=0}, binaryContent=BinaryContent{content=[1, 1, 7, 5]}, comments=[]}
//        ]}}
//        {Чей - то=PlayList{name='Чей - то', contents={class cursach.Music=[Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=PT3M0.022S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=3}, binaryContent=BinaryContent{content=[3, 3, 4, 5]}, comments=[Comment {User: Егор  = 'Мне не понравилось.'}
//        , Comment {User: Борис  = 'Ух ты!'}
//        ]}
//        ], class cursach.Video=[Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=PT33M0.045S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=2}, binaryContent=BinaryContent{content=[4, 0, 9, 3]}, comments=[Comment {User: Denis  = 'Было очень легко!'}
//        ]}
//        , Video{contentMetaData=ContentMetaData{name='Как у нас там', author='Рудольф', duration=PT15M}, contentStatistics=ContentStatistics{like=1, dislike=0, totalViews=1}, binaryContent=BinaryContent{content=[1, 1, 7, 5]}, comments=[]}
//        ]}}, Мой второй плей лист=PlayList{name='Мой второй плей лист', contents={class cursach.Music=[Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=PT3M0.022S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=3}, binaryContent=BinaryContent{content=[3, 3, 4, 5]}, comments=[Comment {User: Егор  = 'Мне не понравилось.'}
//        , Comment {User: Борис  = 'Ух ты!'}
//        ]}
//        ], class cursach.Video=[Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=PT33M0.045S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=2}, binaryContent=BinaryContent{content=[4, 0, 9, 3]}, comments=[Comment {User: Denis  = 'Было очень легко!'}
//        ]}
//        , Video{contentMetaData=ContentMetaData{name='Как у нас там', author='Рудольф', duration=PT15M}, contentStatistics=ContentStatistics{like=1, dislike=0, totalViews=1}, binaryContent=BinaryContent{content=[1, 1, 7, 5]}, comments=[]}
//        ], class cursach.Film=[Film{contentMetaData=ContentMetaData{name='Война миров', author='Режисёр', duration=PT2H12M}, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=2}, binaryContent=BinaryContent{content=[4, 4, 4, 4]}, filmGenre=ACTION, comments=[], price=330}]}}}
//        ****************************************
//        Проиграли все листы юзера: Ира
//        *************************************
//        Осталось денег: 1170
//        Начало проигрывания анонимного списка
//        ************* --== Поймали исключение: Авторизируйтесь ==--
//        Контент: Film{contentMetaData=ContentMetaData{name='Война миров', author='Режисёр', duration=PT2H12M}, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=3}, binaryContent=BinaryContent{content=[4, 4, 4, 4]}, filmGenre=ACTION, comments=[], price=330} - не проиграл!!!!
//        Проиграли всю халяву
//        ========================== post sort name =============================
//        Хэй - хэй
//        Найди меня
//        Как у нас там
//        Война миров
//        Война Z
//        Война
//        ========================== after sort name =============================
//        Война
//        Война Z
//        Война миров
//        Как у нас там
//        Найди меня
//        Хэй - хэй
//        ========================== post sort author =============================
//        Березовец
//        Самик
//        Рудольф
//        Режисёр
//        Режисёр2
//        Режисёр3
//        ========================== after sort author =============================
//        Березовец
//        Режисёр
//        Режисёр2
//        Режисёр3
//        Рудольф
//        Самик
//        ========================== post sort duration =============================
//        180
//        1980
//        900
//        7920
//        11520
//        6300
//        ========================== after sort duration =============================
//        180
//        900
//        1980
//        6300
//        7920
//        11520
//        ========================== post sort genre =============================
//        ACTION
//        HORROR
//        WAR
//        ========================== after sort genre =============================
//        ACTION
//        WAR
//        HORROR