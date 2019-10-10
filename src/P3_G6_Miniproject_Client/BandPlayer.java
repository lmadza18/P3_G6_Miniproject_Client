package P3_G6_Miniproject_Client;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class BandPlayer extends Pane {

    ImageView img;
    int id;
    boolean taken = false;
    double x;
    double y;

    BandPlayer(ImageView img, int id) {
        this.setStyle("-fx-background-color: red");

        this.img = img;
        this.id = id;

    }

    ImageView getImg(){
        return this.img;
    }

    public int getPlayerId() {
        return this.id;
    }





}
