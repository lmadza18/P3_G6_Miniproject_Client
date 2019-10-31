package P3_G6_Miniproject_Client;

import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Instrument {
    public String type;
    private Note[] notes = {};
    public boolean isPlayable = false;
    private boolean isRhythmic = false;
    public Map<String, Note> map;
    private int bandPlayerId;
    private int spotId;
    private boolean isMe;
    public boolean pedal = true;

    public Instrument(int bandPlayerId, int spotId, RootUI rootUI, boolean isMe) {
        this.bandPlayerId = bandPlayerId;
        this.spotId = spotId;
        this.isMe = isMe;

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

        map = Collections.synchronizedMap( Map.of(
                "A", notes[0],
                "S", notes[1],
                "D", notes[2],
                "F", notes[3],
                "G", notes[4],
                "H", notes[5],
                "J", notes[6],
                "K", notes[7]
        ));
        this.setUpListener(rootUI);
        //TODO OC MESSAGE
        //OC.sendMessage("Sound/" + this.type + "/" + entry.getKey(), spotId, id, "null");
/*
        if (isMe) {
            rootUI.setOnKeyPressed(e -> {
                for (Map.Entry<String, Note> entry : map.entrySet()) {
                    if (entry.getKey().equals(e.getCode().getName()) && this.isPlayable && !notes[0].noteOn) {
                        OSC.sendMessage("Sound/" + this.type + "/" + entry.getKey(), spotId, bandPlayerId, "null");
                        this.playSound(entry.getValue().getMedia());
                    }
                }
            });
        }

 */
    }


    public void setUpListener(RootUI rootUI) {

        if (isMe) {
            rootUI.setOnKeyPressed(e -> {

                // ------------------------- When pressing 'p' you toggle pedal on/off
                if (e.getCode().equals(KeyCode.P)){
                    pedal = !pedal;
                }
                // -------------------------

                for (Map.Entry<String, Note> entry : map.entrySet()) {
                    //Key checked
                    String key = entry.getKey();
                    //Key pressed by user
                    String mapKey = e.getCode().getName();


                    if (key.equals(mapKey) && this.isPlayable && !entry.getValue().noteOn) {
                        String operation = "pedal";
                        if (!pedal){
                            operation = "none";
                        }
                        OSC.sendMessage("Sound/" + this.type + "/" + entry.getKey(), spotId, bandPlayerId, operation);
                        System.out.println("something");
                        entry.getValue().playSound();

                        // Copies what's in the map for the current key
                        //Note note = entry.getValue();
                        //note.noteOn=true;
                        // Replaces what's in the map
                        //map.put(key,note);a

                        //Goes into the variable,
                        map.get(key).noteOn = true;

                    }
                }
            });


            rootUI.setOnKeyReleased(e -> {

                for (Map.Entry<String, Note> entry : map.entrySet()) {

                    String key = entry.getKey();

                    if (entry.getKey().equals(e.getCode().getName()) && this.isPlayable && entry.getValue().noteOn) {
                        System.out.println("RELEASING: " + entry.getKey());

                        //  Only stop the note if pedal is off
                        if (!pedal) {
                            map.get(key).stopSound();
                        }
                        map.get(key).noteOn = false;

                    }
                }
            });
        }
    }



}