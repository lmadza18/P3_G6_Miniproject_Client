package P3_G6_Miniproject_Client;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Map;

public class instrument2 {
    String[] audioPaths;
    String[] keys = {
            "A","S"};


    Map<String,String> map = Map.of(
        "A","src/audio_files/Bass/0CBass.wav",
                "b","src/audio_files/Bass/0DBass.wav"
    );

    void pickInstrument(String instrumentName){

    }

    void playSound(String fileName){
        // FIXME y new media??
        Media media = new Media(new File(fileName).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }

    void setUpListener(RootUI rootUI){
        //FIXME
        rootUI.setOnKeyPressed(e ->{
            for (Map.Entry<String, String> entry : map.entrySet()) {
                //System.out.println("e.getCode()", e.getCode(), "e.getCode().type", e.getCode().getClass());
                    //if (entry.getKey().equals(e.getCode())) {
                if (entry.getKey() == e.getCode().getName()) {
                    System.out.println("PLAYED");
                    this.playSound(entry.getValue());
                }
                System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
            }

//            switch (e.getCode()){
//                case A:
//                    this.playSound("src/audio_files/Bass/0CBass.wav");
//                    break;
//            };

        });
    }
}