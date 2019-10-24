package P3_G6_Miniproject_Client;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Map;

public class Instrument {
    private String type;
    private Media[] media = {};
    private boolean isPlayable = false;
    private boolean isRythmic = false;
    private boolean noteOn = false;
    private Map<String, Media> map;

    public Instrument(int id, RootUI rootUI) {

        switch (id) {
            case 0:
                this.type = "Guitar";
                break;
            case 1:
                this.type = "Drums";
                this.isRythmic = true;
                break;
            case 2:
                this.type = "Bass";
                break;
            case 3:
                this.type = "Piano";
                break;
        }

        this.media = new Media[]{
                new Media(new File("src/audio_files/" + type + "/0C" + type + ".wav").toURI().toString()),
                new Media(new File("src/audio_files/" + type + "/0D" + type + ".wav").toURI().toString()),
                new Media(new File("src/audio_files/" + type + "/0E" + type + ".wav").toURI().toString()),
                new Media(new File("src/audio_files/" + type + "/0F" + type + ".wav").toURI().toString()),
                new Media(new File("src/audio_files/" + type + "/0G" + type + ".wav").toURI().toString()),
                new Media(new File("src/audio_files/" + type + "/1A" + type + ".wav").toURI().toString()),
                new Media(new File("src/audio_files/" + type + "/1B" + type + ".wav").toURI().toString()),
                new Media(new File("src/audio_files/" + type + "/1C" + type + ".wav").toURI().toString())
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

        rootUI.setOnKeyPressed(e -> {
            for (Map.Entry<String, Media> entry : map.entrySet()) {
                if (entry.getKey().equals(e.getCode().getName()) && this.isPlayable && noteOn == false) {
                    this.playSound(entry.getValue());
                }
            }
        });
    }

    private void playSound(Media media) {
        this.noteOn = true;
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        Main.root.setOnKeyReleased(e -> {
            System.out.println(e.getCode());
            if(!this.isRythmic) {
                mediaPlayer.setVolume(0);
            }
            this.noteOn = false;
        });
    }

    private void setInstrument(String name) {
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
}