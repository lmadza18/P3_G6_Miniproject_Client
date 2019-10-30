package P3_G6_Miniproject_Client;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class Note {
    //TODO NAMING

    public boolean noteOn = false;
    private boolean isRhythmic = false;
    private MediaPlayer mediaPlayer;

    Note(File audioFile) {
        mediaPlayer = new MediaPlayer(new Media(audioFile.toURI().toString()));
    }

    public void playSound() {
        mediaPlayer.setVolume(100);
        mediaPlayer.seek(Duration.ZERO);
        System.out.println("something else");
        this.mediaPlayer.play();
    }

    public void stopSound() {
        mediaPlayer.setVolume(0);
        if (!this.isRhythmic) {
            mediaPlayer.setVolume(0);
        }
    }
}