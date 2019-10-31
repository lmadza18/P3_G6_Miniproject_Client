package P3_G6_Miniproject_Client;

import javafx.scene.input.KeyCode;

import java.io.File;
import java.util.Collections;
import java.util.Map;

public class Instrument {
    public String type;
    private Note[] notes = {};
    public boolean isPlayable = false;
    private boolean isRhythmic = false;
    public Map<String, Note> map;
    private int bandPlayerId;
    private int spotId;
    private boolean localPlayer;
    public boolean pedal = true;

    public Instrument(int bandPlayerId, int spotId, RootUI rootUI, boolean localPlayer) {
        this.bandPlayerId = bandPlayerId;
        this.spotId = spotId;
        this.localPlayer = localPlayer;

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

        map = Collections.synchronizedMap(Map.of(
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
        if (localPlayer) {
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

        if (localPlayer) {
            rootUI.setOnKeyPressed(e -> {

                // ------------------------- When pressing 'p' you toggle pedal on/off
                if (e.getCode().equals(KeyCode.P)) {
                    pedal = !pedal;
                }
                // -------------------------

                for (Map.Entry<String, Note> entry : map.entrySet()) {
                    //Key checked
                    String key = entry.getKey();
                    //Key pressed by user
                    String mapKey = e.getCode().getName();


                    if (key.equals(mapKey) && this.isPlayable && !entry.getValue().noteOn) {
                        // Send message for playing a sound
                        OSC.sendMessage("Sound/" + this.type + "/" + entry.getKey(), spotId, bandPlayerId, "play");
                        // Play sound
                        entry.getValue().playSound();

                        //Goes into the variable,
                        map.get(key).noteOn = true;

                    }
                }
            });


            rootUI.setOnKeyReleased(e -> {

                for (Map.Entry<String, Note> entry : map.entrySet()) {

                    String key = entry.getKey();

                    if (entry.getKey().equals(e.getCode().getName()) && this.isPlayable && entry.getValue().noteOn) {
                        //  Only stop the note if pedal is off
                        if (!pedal) {
                            // Send message for stopping a sound
                            OSC.sendMessage("Sound/" + this.type + "/" + entry.getKey(), spotId, bandPlayerId, "stop");
                            // Stop sound
                            map.get(key).stopSound();
                        }
                        map.get(key).noteOn = false;

                    }
                }
            });
        }
    }


}