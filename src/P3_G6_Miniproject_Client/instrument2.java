package P3_G6_Miniproject_Client;

import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.EventListener;
import java.util.Map;

public class instrument2 {
    private String name;
    Media[] media = {
            new Media(new File("src/audio_files/Bass/0CBass.wav").toURI().toString()),
            new Media(new File("src/audio_files/Bass/0DBass.wav").toURI().toString()),
            new Media(new File("src/audio_files/Bass/0EBass.wav").toURI().toString()),
            new Media(new File("src/audio_files/Bass/0FBass.wav").toURI().toString()),
            new Media(new File("src/audio_files/Bass/0GBass.wav").toURI().toString()),
            new Media(new File("src/audio_files/Bass/1ABass.wav").toURI().toString()),
            new Media(new File("src/audio_files/Bass/1BBass.wav").toURI().toString()),
            new Media(new File("src/audio_files/Bass/1CBass.wav").toURI().toString())
    };
    private boolean isPlayable = false;
    Map<String, Media> map;

    public instrument2(String id, RootUI rootUI) {

        if (id == "spot1") {
            setName("Guitar");
            setInstrument(name);
        } else if (id == "spot2") {
            setName("Piano");
            setInstrument(name);
        } else if (id == "spot3") {
            setName("Bass");
            setInstrument(name);
        } /*else if (id == "spot4") {
            this.name = "Drums";
        }*/

        map = Map.of(
                "A", media[0],
                "S", media[1],
                "D", media[2],
                "F", media[3],
                "G", media[4],
                "H", media[5],
                "J", media[6],
                "K", media[7]
        );

        rootUI.setOnKeyPressed(e -> {
            for (Map.Entry<String, Media> entry : map.entrySet()) {
                //System.out.println("e.getCode()", e.getCode(), "e.getCode().type", e.getCode().getClass());
                //if (entry.getKey().equals(e.getCode())) {
                System.out.println(this.isPlayable);
                if (entry.getKey() == e.getCode().getName() && this.isPlayable) {
                    this.playSound(entry.getValue());
                }
            }
            if (e.getCode().getName() == "Z") {
                this.setInstrument(this.name);
            }
        });
    }

    static void playSound(Media media) {
        System.out.println("Sound is playable?!?");
        //Media media = new Media(new File(fileName).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }

    public void setInstrument(String name) {
        this.media = new Media[]{
                new Media(new File("src/audio_files/" + name + "/0C" + name + ".wav").toURI().toString()),
                new Media(new File("src/audio_files/" + name + "/0D" + name + ".wav").toURI().toString()),
                new Media(new File("src/audio_files/" + name + "/0E" + name + ".wav").toURI().toString()),
                new Media(new File("src/audio_files/" + name + "/0F" + name + ".wav").toURI().toString()),
                new Media(new File("src/audio_files/" + name + "/0G" + name + ".wav").toURI().toString()),
                new Media(new File("src/audio_files/" + name + "/1A" + name + ".wav").toURI().toString()),
                new Media(new File("src/audio_files/" + name + "/1B" + name + ".wav").toURI().toString()),
                new Media(new File("src/audio_files/" + name + "/1C" + name + ".wav").toURI().toString())
        };
        map = Map.of(
                "A", media[0],
                "S", media[1],
                "D", media[2],
                "F", media[3],
                "G", media[4],
                "H", media[5],
                "J", media[6],
                "K", media[7]
        );
    }

    public void setPlayable(boolean isPlayable) {
        this.isPlayable = isPlayable;
    }

    public void setName(String name) {
        this.name = name;
    }
}