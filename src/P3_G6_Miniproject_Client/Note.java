package P3_G6_Miniproject_Client;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class Note {
    //TODO NAMING

    //Pitch pitch;
    private Media media;
    //MediaPlayer mediaPlayer;
    public boolean noteOn = false;
    private boolean isRhythmic = false;
    MediaPlayer mediaPlayer;


    Note(File audioFile) {
        mediaPlayer = new MediaPlayer(new Media(audioFile.toURI().toString()));

    }

    public void playSound(){

        mediaPlayer.seek(Duration.ZERO);

        System.out.println("something else");
        this.mediaPlayer.play();

    }

    public void stopSound(){

        //mediaPlayer.seek(Duration.ZERO);

        if (!this.isRhythmic) {
            mediaPlayer.setVolume(0);
        }
    }




    public Media getMedia() {
        return this.media;
    }


}

/*

class Pitch {
    int octave;
    char note;
    Pitch(char note, int octave) {
        this.note = note;
        this.octave = octave;
    }
    public String getPitch() {
        String s = note + String.valueOf(octave);
        System.out.println(s);
        return s;
    }


}

 */