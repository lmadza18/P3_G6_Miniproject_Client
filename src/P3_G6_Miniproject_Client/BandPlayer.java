package P3_G6_Miniproject_Client;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class BandPlayer extends ImageView {

    int id;
    int spotId;
    boolean me;
    double x;
    double y;
    Instrument instrument;



    BandPlayer(int spotId, int id, boolean me) {
        this.id = id;
        this.spotId = spotId;
        this.me = me;

        this.setImage(Main.root.images[id]);

        this.setFitHeight(-50);
        this.setFitWidth(-50);

        instrument = new Instrument(this.id, spotId, Main.root, me);

    }

    ImageView getImg(){
        return this;
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
