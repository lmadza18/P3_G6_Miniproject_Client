package P3_G6_Miniproject_Client;

import javafx.scene.input.KeyCode;

import java.io.File;
import java.util.Collections;
import java.util.Map;

/**
 * This class is used for choosing instrument and based on this loading different Strings into a map of type Note.
 * Furthermore, this class is detecting key operations by the user; keyPressed and keyReleased.
 */

public class Instrument {
    public String type;
    private Note[] notes;
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

        // Depending on the bandPlayerId a String named type is stored
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

        //An Array of type Note is initialized depending on the variable type different audiofiles are stored
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


        //A map is initialized with the array Notes
        //The map is synchronized to prevent a crash, when multiple clients
        //press the same button at once.
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

        //Sets up a listener for the rootUI
        this.setUpListener(rootUI);

    }

    //This functions is handling keyEvents by the user
    //And checks if the user pressed or releases any buttons (within the map)
    public void setUpListener(RootUI rootUI) {

        if (localPlayer) {
            //Sets on a keyPressed to the rootUi element
            rootUI.setOnKeyPressed(e -> {

                // ------------------------- When pressing 'p' you toggle pedal on/off
                if (e.getCode().equals(KeyCode.P)) {
                    pedal = !pedal;
                }
                // -------------------------

                //Loops over the map and gets the entrySet
                for (Map.Entry<String, Note> entry : map.entrySet()) {
                    //Key checked
                    String mapKey = entry.getKey();
                    //Key pressed by user
                    String key = e.getCode().getName();

                    //Compares the key pressed by the user to the map
                    //And finds the mapKey equal to the key (both are of type String)
                    //Checks the two booleans; isPlayable and noteOn
                    if (mapKey.equals(key) && this.isPlayable && !entry.getValue().noteOn) {
                        //A message is send to the server
                        OSC.sendMessage("Sound/" + this.type + "/" + entry.getKey(), spotId, bandPlayerId, "play");
                        // Play sound
                        entry.getValue().playSound();
                        //Set noteOn to true
                        map.get(mapKey).noteOn = true;
                    }
                }
            });

            //Sets a on onKeyReleased to rootUI
            rootUI.setOnKeyReleased(e -> {

                for (Map.Entry<String, Note> entry : map.entrySet()) {
                    //Key checked
                    String mapKey = entry.getKey();
                    //Key pressed by user
                    String key = e.getCode().getName();

                    //Checks if noteOn is true
                    if (mapKey.equals(key) && this.isPlayable && entry.getValue().noteOn) {
                        //  Only stop the note if pedal is off
                        if (!pedal) {
                            // Send message for stopping a sound
                            OSC.sendMessage("Sound/" + this.type + "/" + entry.getKey(), spotId, bandPlayerId, "stop");
                            // Stop sound
                            map.get(key).stopSound();
                        }
                        //Sets noteOn to false again
                        map.get(mapKey).noteOn = false;

                    }
                }
            });
        }
    }


}