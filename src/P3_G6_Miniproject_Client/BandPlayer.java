package P3_G6_Miniproject_Client;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class BandPlayer extends ImageView {

    int id;
    boolean taken = false;
    double x;
    double y;

    BandPlayer(String url, int id) {
        Image image = new Image(url);
        this.setImage(image);
        this.setStyle("-fx-border-color: RED;");
        this.id = id;

        this.setFitHeight(50);
        this.setFitWidth(50);




    }

    ImageView getImg(){
        return this;
    }

    public int getPlayerId() {
        return this.id;
    }





}
