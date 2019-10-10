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
    String[] audioPaths;
    String[] keys = {
            "A", "S", "D", "F", "G", "H", "J", "k"};

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

    //Media media = new Media("src/audio_files/Bass/0CBass.wav");

    instrument2(StageSpot button){

        if(button.getId()=="spot1"){
            String name = "Guitar";
            //instrument2.playSound(name);
        }
    }

    Map<String, Media> map = Map.of(
            "A", media[0],
            "S", media[1],
            "D", media[2],
            "F", media[3],
            "G", media[4],
            "H", media[5],
            "J", media[6],
            "K", media[7]
    );


    void pickInstrument(String instrumentName) {

    }

    static void  playSound(Media media) {
        //Media media = new Media(new File(fileName).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }

    void setUpListener(RootUI rootUI) {
        //FIXME
        rootUI.setOnKeyPressed(e -> {
            for (Map.Entry<String, Media> entry : map.entrySet()) {
                //System.out.println("e.getCode()", e.getCode(), "e.getCode().type", e.getCode().getClass());
                //if (entry.getKey().equals(e.getCode())) {
                if (entry.getKey() == e.getCode().getName()) {
                    this.playSound(entry.getValue());
                }
            }

//            switch (e.getCode()){
//                case A:
//                    this.playSound("src/audio_files/Bass/0CBass.wav");
//                    break;
//            };

        });
    }
}