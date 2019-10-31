package P3_G6_Miniproject_Client;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

//**********************************************************************
//https://www.geeksforgeeks.org/what-is-javadoc-tool-and-how-to-use-it/
// https://www.jetbrains.com/help/idea/working-with-code-documentation.html
//javadoc keywords:
//        @author
//        @param
//        @see
//        @version
//        @return
//IntelliJ is smart, so it helps u quite a bit. write / * * and enter, and it makes a javadoc block for you.
//**********************************************************************

/**
 * This class is used for playing a single sample. Each instrument has an array of Note objects.
 * @see         Instrument
 */
public class Note {
    //TODO NAMING
    /**
     * @param noteOn This is set to True when the audiofile starts playing. It is set to false when the key is released.
     *               This is used for avoid triggering the playback multiple times when the key is continuously held.
     * @param isRhythmic used as rhythmic instruments should not stop playback when key is released.
     *
     */

    public boolean noteOn = false;
    private boolean isRhythmic = false;
    MediaPlayer mediaPlayer;

    /**
     *The constructor takes an File load from the Instrument class @see Instrument
     * @param audioFile the sample file for this specific note of an instrument
     */
    Note(File audioFile) {
        mediaPlayer = new MediaPlayer(new Media(audioFile.toURI().toString()));

    }

    /**
     * plays the sound
     *
     */
    public void playSound() {

        /* NOT A JAVADOC COMMENT
         * sets volume to 100, as the volume is set to 0 when key is released.
         * The 'playhead' of the sample is set to 0, as it would otherwise be at the end of the sample.
         */
        mediaPlayer.setVolume(100);

        mediaPlayer.seek(Duration.ZERO);

        System.out.println("something else");
        this.mediaPlayer.play();

    }
    /**
     * Stops the sound
     * Instead of stopping the playback using MediaPlayers method, the volume set to 0,
     * as stopping the playback can cause a click sound, as the audiowave is suddenly interrupted.
     *
     */
    public void stopSound() {

        //mediaPlayer.seek(Duration.ZERO);
        mediaPlayer.setVolume(0);


        if (!this.isRhythmic) {
            mediaPlayer.setVolume(0);
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
}