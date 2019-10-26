package P3_G6_Miniproject_Client;

import de.sciss.net.OSCMessage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class RootUI extends Pane {

    public OC oc;
    StageSpot[] stageSpots = new StageSpot[4];
    InstrumentPickerWindow instrumentPickerWindow;
    Button leaveStageSpotButton;
    boolean playing;

    Image[] images = {
            new Image("images/guitarist.png"),
            new Image("images/drummer1.png"),
            new Image("images/bassist.png"),
            new Image("images/sprite.png")
    };

    RootUI() {


    }

    void setMess(OSCMessage mess) {

    }
    public void start() {
        ImageView bgImg = new ImageView("images/stage.jpg");
        bgImg.setFitWidth(this.getWidth());
        bgImg.setFitHeight(this.getHeight());

        stageSpots[0] = new StageSpot(0,this.getWidth() / 8, this.getHeight() / 8 * 6);
        stageSpots[1] = new StageSpot(1,this.getWidth() / 8 * 3, this.getHeight() / 8 * 5.5);
        stageSpots[2] = new StageSpot(2,this.getWidth() / 8 * 5, this.getHeight() / 8 * 5.5);
        stageSpots[3] = new StageSpot(3,this.getWidth() / 8 * 7, this.getHeight() / 8 * 6);

        instrumentPickerWindow = new InstrumentPickerWindow();

        leaveStageSpotButton = new Button("get my ass outta here!");

        this.getChildren().addAll(bgImg, stageSpots[0], stageSpots[1], stageSpots[2], stageSpots[3]);
        oc = new OC(stageSpots);
    }

}
