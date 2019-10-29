package P3_G6_Miniproject_Client;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

public class Instrument {
    public String type;
    private Note[] notes = {};
    Note note;
    public boolean isPlayable = false;
    private boolean isRhythmic = false;
    //TODO Why a noteOn here and in the Note class
    public Map<String, Note> map;

    public Instrument(int id, int spotId, RootUI rootUI, boolean me) {

        switch (id) {
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
        this.setUpListener(rootUI);
/*
        if (me) {
            rootUI.setOnKeyPressed(e -> {

                for (Map.Entry<String, Note> entry : map.entrySet()) {

                    if (entry.getKey().equals(e.getCode().getName()) && this.isPlayable &&  !notes[0].noteOn) {
                        OC.sendMessage("Sound/" + this.type + "/" + entry.getKey(), spotId, id, "null");
                        note.playSound(entry.getValue().getMedia());
                    }
                }
            });
        }


 */
    }


    public void setUpListener(RootUI rootUI) {

        rootUI.setOnKeyPressed(e -> {
            notes.

            for (Map.Entry<String, Note> entry : map.entrySet()) {
                //Key pressed by user
                String key = entry.getKey();
                //Key in map
                String mapKey = e.getCode().getName();
                //System.out.println("entry.getKey()"+entry.getKey().getClass());
                if (key.equals(mapKey) && this.isPlayable && entry.getValue().noteOn) {
                    //OC.sendMessage("Sound/" + this.type + "/" + entry.getKey(), spotId, id, "null");
                    System.out.println("something");
                    entry.getValue().playSound();


                    //map[key].playSound(entry.getValue().getMedia());
                }
            }
        });

        rootUI.setOnKeyReleased(e -> {

            notes[0].noteOn=true;

            for (Map.Entry<String, Note> entry : map.entrySet()) {
                if (entry.getKey().equals(e.getCode().getName()) && this.isPlayable && !entry.getValue().noteOn) {
                    System.out.println("RELEASING: " + entry.getKey());
                }
            }

            notes[0].noteOn = false;
        });

    }




/*
    public void playSound(Media media) {
        //TODO This is the noteOn in instrument class
        notes[0].noteOn = true;
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        Main.root.setOnKeyReleased(e -> {
            for (Map.Entry<String, Note> entry : map.entrySet()) {
                if (entry.getKey().equals(e.getCode().getName()) && this.isPlayable && !entry.getValue().noteOn) {
                    System.out.println("RELEASING: " + entry.getKey());

                    if (!this.isRhythmic) {
                        System.out.println("SETTING VOLUME OF " + media.getSource());
                        mediaPlayer.setVolume(0);
                    }
                }
            }
            notes[0].noteOn = false;
        });


        Iterator<Map.Entry<String, Note>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Note> entry = entries.next();
            String key = entry.getKey();
            Note value = entry.getValue();





    }}
*/



}