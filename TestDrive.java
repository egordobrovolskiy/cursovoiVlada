package kursach.attempt2;

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
        Film f = new Film(new ContentMetaData("Война миров", "Режисёр", Duration.ofHours(2).plusMinutes(12)),
                new BinaryContent(new byte[]{4, 4, 4, 4}), FilmGenre.ACTION, 330);
        User u = new User("Ира", 27);
        u.replenishAccount(1500);
        System.out.println(u.toString());
        f.run(u);
        System.out.println(u);
        System.out.println("*************************************************************");
        u.getMyPlayLists().addToPlayList(v);
        u.getMyPlayLists().addToPlayList(v1);
        u.getMyPlayLists().addToPlayList(m);
        System.out.println(u.toString());


    }
}
//Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=PT33M0.045S}, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{content=[4, 0, 9, 3]}, comments=[]}
//
//Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=PT33M0.045S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=1}, binaryContent=BinaryContent{content=[4, 0, 9, 3]}, comments=[Comment {User: Denis  = 'Было очень легко!'}
//]}
//
//**************************************************
//Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=PT3M0.022S}, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{content=[3, 3, 4, 5]}, comments=[]}
//
//Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=PT3M0.022S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=1}, binaryContent=BinaryContent{content=[3, 3, 4, 5]}, comments=[Comment {User: Егор  = 'Мне не понравилось.'}
//]}
//
//***************************************************
//PlayList{name='Чей - то', contents=[Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=PT33M0.045S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=1}, binaryContent=BinaryContent{content=[4, 0, 9, 3]}, comments=[Comment {User: Denis  = 'Было очень легко!'}
//]}
//, Video{contentMetaData=ContentMetaData{name='Как у нас там', author='Рудольф', duration=PT15M}, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{content=[1, 1, 7, 5]}, comments=[]}
//, Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=PT3M0.022S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=1}, binaryContent=BinaryContent{content=[3, 3, 4, 5]}, comments=[Comment {User: Егор  = 'Мне не понравилось.'}
//]}
//]}
//PlayList{name='Чей - то', contents=[Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=PT33M0.045S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=2}, binaryContent=BinaryContent{content=[4, 0, 9, 3]}, comments=[Comment {User: Denis  = 'Было очень легко!'}
//, Comment {User: Борис  = 'Ух ты!'}
//]}
//, Video{contentMetaData=ContentMetaData{name='Как у нас там', author='Рудольф', duration=PT15M}, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{content=[1, 1, 7, 5]}, comments=[]}
//, Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=PT3M0.022S}, contentStatistics=ContentStatistics{like=2, dislike=1, totalViews=1}, binaryContent=BinaryContent{content=[3, 3, 4, 5]}, comments=[Comment {User: Егор  = 'Мне не понравилось.'}
//]}
//]}
//********************************************************
//User{name='Ира', age=27, money=1500, myPlayLists=PlayList{name='Мой лэйлист', contents=[]}}
//User{name='Ира', age=27, money=1170, myPlayLists=PlayList{name='Мой лэйлист', contents=[]}}
//*************************************************************
//User{name='Ира', age=27, money=1170, myPlayLists=PlayList{name='Мой лэйлист', contents=[Video{contentMetaData=ContentMetaData{name='Найди меня', author='Самик', duration=PT33M0.045S}, contentStatistics=ContentStatistics{like=1, dislike=1, totalViews=2}, binaryContent=BinaryContent{content=[4, 0, 9, 3]}, comments=[Comment {User: Denis  = 'Было очень легко!'}
//, Comment {User: Борис  = 'Ух ты!'}
//]}
//, Video{contentMetaData=ContentMetaData{name='Как у нас там', author='Рудольф', duration=PT15M}, contentStatistics=ContentStatistics{like=0, dislike=0, totalViews=0}, binaryContent=BinaryContent{content=[1, 1, 7, 5]}, comments=[]}
//, Music{contentMetaData=ContentMetaData{name='Хэй - хэй', author='Березовец', duration=PT3M0.022S}, contentStatistics=ContentStatistics{like=2, dislike=1, totalViews=1}, binaryContent=BinaryContent{content=[3, 3, 4, 5]}, comments=[Comment {User: Егор  = 'Мне не понравилось.'}
//]}
//]}}
