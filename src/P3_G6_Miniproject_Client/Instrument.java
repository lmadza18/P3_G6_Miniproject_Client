package P3_G6_Miniproject_Client;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Map;

public class Instrument {
    public String type;
    private Note[] notes = {};
    public boolean isPlayable = false;
    private boolean isRhythmic = false;
    //private boolean noteOn = false;
    public Map<String, Note> map;

    public Instrument(int bandPlayerId, int spotId, RootUI rootUI, boolean isMe) {

        switch (bandPlayerId) {
            case 0:
                this.type = "Guitar";
                break;
            case 1:
                this.type = "Drums";
                this.isRhythmic = true;
                break;
            case 2:
                this.type = "Bass";
                break;
            case 3:
                this.type = "Piano";
                break;
        }

        this.notes = new Note[]{
                new Note(new File("src/audio_files/" + type + "/0C" + type + ".wav")),
                new Note(new File("src/audio_files/" + type + "/0D" + type + ".wav")),
                new Note(new File("src/audio_files/" + type + "/0E" + type + ".wav")),
                new Note(new File("src/audio_files/" + type + "/0F" + type + ".wav")),
                new Note(new File("src/audio_files/" + type + "/0G" + type + ".wav")),
                new Note(new File("src/audio_files/" + type + "/1A" + type + ".wav")),
                new Note(new File("src/audio_files/" + type + "/1B" + type + ".wav")),
                new Note(new File("src/audio_files/" + type + "/1C" + type + ".wav"))
        };

        map = Map.of(
                "A", notes[0],
                "S", notes[1],
                "D", notes[2],
                "F", notes[3],
                "G", notes[4],
                "H", notes[5],
                "J", notes[6],
                "K", notes[7]
        );

        if (isMe) {
            rootUI.setOnKeyPressed(e -> {
                System.out.println("isPlayable: " + isPlayable);
                for (Map.Entry<String, Note> entry : map.entrySet()) {
                    if (entry.getKey().equals(e.getCode().getName()) && this.isPlayable && !notes[0].noteOn) {
                        OSC.sendMessage("Sound/" + this.type + "/" + entry.getKey(), spotId, bandPlayerId, "null");
                        this.playSound(entry.getValue().getMedia());
                    }
                }
            });
        }
    }

    public void playSound(Media media) {
        notes[0].noteOn = true;
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        Main.root.setOnKeyReleased(e -> {
            for (Map.Entry<String, Note> entry : map.entrySet()) {
                if (entry.getKey().equals(e.getCode().getName()) && this.isPlayable && entry.getValue().noteOn) {
                    System.out.println("RELEASING: " + entry.getKey());
                    if (!this.isRhythmic) {
                        System.out.println("SETTING VOLUME OF " + media.getSource());
                        mediaPlayer.setVolume(0);
                    }
                }
            }
            notes[0].noteOn = false;
        });
    }


}