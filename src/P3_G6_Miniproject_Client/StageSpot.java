package P3_G6_Miniproject_Client;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

/**
 * The stagespot class makes object for each spot on the stage and handles everything that is included in the stagespot
 * This includes the button and the bandplayer and all interactions the those objects
 **/

public class StageSpot extends StackPane {

    StageSpotButton stageSpotButton;
    BandPlayer bandPlayer;

    boolean taken = false;
    double x;
    double y;
    double xInit;
    double yInit;

    private int instrumentId;
    int spotId;

    public StageSpot(int id, double x, double y) {
        this.spotId = id;
        this.x = x;
        this.y = y;
        this.xInit = x;
        this.yInit = y;

        stageSpotButton = new StageSpotButton();
        this.getChildren().add(stageSpotButton);

        this.movePos(-stageSpotButton.imageSize / 2, -stageSpotButton.imageSize / 2);
        stageSpotButtonListener();

    }

    //method for moving stage spot
    private void movePos(double x, double y) {
        this.x = this.xInit + (x);
        this.y = this.yInit + (y);
        this.setTranslateX(this.x);
        this.setTranslateY(this.y);
    }

    //Method for action when stageSpotButton is pressed
    public void stageSpotButtonListener() {
        stageSpotButton.button.setOnAction(actionEvent -> {

            Main.root.getChildren().add(Main.root.instrumentPickerWindow);
            taken = true;

            instrumentPickerWindowExitListener();
            instrumentPickerWindowChooseButtonListener();

            OSC.sendMessage("GUImessage", this.spotId, this.instrumentId, "reserve");
        });
    }

    //Method for action when chooseButton in instrumentPickerWindow is pressed
    public void instrumentPickerWindowChooseButtonListener() {
        Main.root.instrumentPickerWindow.chooseButton.setOnAction(actionEvent -> {
            chooseBandPlayer();
        });
        Main.root.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                chooseBandPlayer();
            }
        });
    }

    //Method for choosing a band player
    public void chooseBandPlayer(){
        Main.root.getChildren().remove(Main.root.instrumentPickerWindow);
        this.instrumentId = Main.root.instrumentPickerWindow.switchIndex;

        displayBandPlayer(this.instrumentId, true);

        OSC.sendMessage("GUImessage", this.spotId, this.instrumentId, "take");
        takeIt();
    }


    //Method for displaying a band player
    public void displayBandPlayer(int instrumentId, boolean localPlayer) {
        taken = true;
        bandPlayer = new BandPlayer(spotId, instrumentId, localPlayer);
        bandPlayer.setFitWidth(Main.root.getWidth() / 5);
        bandPlayer.setFitHeight(Main.root.getWidth() / 5);
        movePos(-bandPlayer.getFitWidth() / 2, -this.getHeight() * 1.5);
        Platform.runLater(() ->
                this.getChildren().add(bandPlayer));
        this.stageSpotButton.setVisible(false);
    }

    //Method for removing a band player
    public void removeBandPlayer() {
        this.taken = false;
        this.movePos(-stageSpotButton.imageSize / 2, -stageSpotButton.imageSize / 2);
        Platform.runLater(() ->
                this.getChildren().remove(bandPlayer));
        if (!Main.root.playing) {
            this.stageSpotButton.setVisible(true);
        }
    }

    //Method for leaving a spot
    public void leaveIt() {
        Main.root.playing = false;
        Main.root.getChildren().remove(Main.root.leaveStageSpotButton);
        bandPlayer.putDownInstrument();
        for (StageSpot spot : Main.root.stageSpots) {
            if (!spot.taken) {
                spot.stageSpotButton.setVisible(true);
            }
        }
    }

    //Method for taking a spot
    public void takeIt() {
        Main.root.playing = true;
        Main.root.getChildren().add(Main.root.leaveStageSpotButton);
        bandPlayer.pickUpInstrument();
        for (int i = 0; i < 4; i++)
            Main.root.stageSpots[i].stageSpotButton.setVisible(false);

        leaveStageSpotButtonListener();
    }

    //Method for action when exitButton in instrumentPickerWindow is pressed
    void instrumentPickerWindowExitListener() {
        Main.root.instrumentPickerWindow.closeButton.setOnAction(actionEvent -> {
            Main.root.getChildren().remove(Main.root.instrumentPickerWindow);
            OSC.sendMessage("GUImessage", this.spotId, this.instrumentId, "release");
        });
    }

    //Method for action when the "get my ass out of here" button is clicked
    void leaveStageSpotButtonListener() {
        Main.root.leaveStageSpotButton.setOnAction(actionEvent -> {
            removeBandPlayer();
            leaveIt();
            OSC.sendMessage("GUImessage", this.spotId, this.instrumentId, "leave");
        });
    }

}
