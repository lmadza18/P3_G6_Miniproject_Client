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




instrument2(StageSpot button){
        if(button.getId()=="spot1"){
            String name = "Guitar";
            instrument2.playSound(name);
        }





}
    Map<String, String> map = Map.of(
            "A", "src/audio_files/Bass/0CBass.wav",
            "S", "src/audio_files/Bass/0DBass.wav",
            "D", "src/audio_files/Bass/0EBass.wav",
            "F", "src/audio_files/Bass/0FBass.wav",
            "G", "src/audio_files/Bass/0GBass.wav",
            "H", "src/audio_files/Bass/1ABass.wav",
            "J", "src/audio_files/Bass/1BBass.wav",
            "K", "src/audio_files/Bass/1CBass.wav"


    );

    void pickInstrument(String instrumentName) {

    }

    static void  playSound(String fileName) {
        Media media = new Media(new File(fileName).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }

    void setUpListener(RootUI rootUI) {
        //FIXME
        rootUI.setOnKeyPressed(e -> {
            for (Map.Entry<String, String> entry : map.entrySet()) {
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