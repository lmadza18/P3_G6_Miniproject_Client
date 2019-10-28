package P3_G6_Miniproject_Client;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Note {

    //Pitch pitch;
    private Media media;
    //MediaPlayer mediaPlayer;
    public boolean noteOn = false;

    Note(File audioFile) {
        this.media = new Media(audioFile.toURI().toString());
    }




    /*/////
    public void playNote() {
        this.mediaPlayer.stop();
        this.mediaPlayer = new MediaPlayer(media);
        this.mediaPlayer.play();
    }
     *//////

    public Media getMedia() {
        return this.media;
    }
}





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