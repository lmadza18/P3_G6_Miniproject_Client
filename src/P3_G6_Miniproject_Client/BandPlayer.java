package P3_G6_Miniproject_Client;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class BandPlayer extends ImageView {

    int id;
    //boolean taken = false;
    double x;
    double y;
    Instrument instrument;



    BandPlayer(int id) {
        this.id = id;

        this.setImage(Main.root.images[id]);

        this.setFitHeight(-50);
        this.setFitWidth(-50);

        this.instrument = new Instrument(this.id, Main.root, true);

    }

    ImageView getImg(){
        return this;
    }

    public int getPlayerId() {
        return this.id;
    }

    public void pickUpInstrument() {
        this.instrument.isPlayable = true;
    }

    public void putDownInstrument() {
        this.instrument.isPlayable = false;
    }



}
