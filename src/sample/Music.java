package sample;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Music {
    private static boolean firstTime = true;

    private static Media backgroundMusic = new Media(new File("src/sample/Resources/Imperialsong.mp3").toURI().toString());
    private static MediaPlayer mediaPlayer = new MediaPlayer(backgroundMusic);
    private static Media winMusic = new Media(new File("src/sample/Resources/Starwarstheme.mp3").toURI().toString());


    public static void playBackgroundMusic() {
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
    public static void playWinMusic() {
       changeMusic(winMusic);
    }
    public static void newGameMusic() {
        changeMusic(backgroundMusic);
    }

    public static void mute(boolean mute) {
        mediaPlayer.setMute(mute);
    }

    private static void changeMusic (Media music) {
        mediaPlayer.stop();
        mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public static boolean isFirstTime() {
        return firstTime;
    }

    public static void setFirstTime(boolean firstTime) {
        Music.firstTime = firstTime;
    }
}

