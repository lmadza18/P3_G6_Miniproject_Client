package P3_G6_Miniproject_Client;

import javafx.scene.image.ImageView;


/**
 * This class is used to keep track which stagespots are taken,
 * and what instrument are the client chose.
 * Furthermore, if the bandplayer were created locally or by another client.
 */


public class BandPlayer extends ImageView {

    int id;
    int spotId;
    boolean localPlayer;
    double x;
    double y;
    Instrument instrument;



    BandPlayer(int spotId, int id, boolean localPlayer) {
        this.id = id;
        this.spotId = spotId;
        this.localPlayer = localPlayer;

        this.setImage(Main.root.images[id]);

        this.setFitHeight(-50);
        this.setFitWidth(-50);

        instrument = new Instrument(this.id, spotId, Main.root, localPlayer);

    }

    public int getPlayerId() {
        return this.id;
    }

    public void pickUpInstrument() {
        instrument.isPlayable = true;
    }

    public void putDownInstrument() {
        instrument.isPlayable = false;
    }



}
