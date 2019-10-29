package P3_G6_Miniproject_Client;

import javafx.application.Platform;
import javafx.scene.layout.StackPane;


public class StageSpot extends StackPane {

    StageSpotButton stageSpotButton;
    BandPlayer bandPlayer;

    boolean taken = false;
    int playerID;
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

    private void movePos(double x, double y) {
        this.x = this.xInit + (x);
        this.y = this.yInit + (y);
        this.setTranslateX(this.x);
        this.setTranslateY(this.y);
    }

    public void stageSpotButtonListener() {
        stageSpotButton.button.setOnAction(actionEvent -> {

            Main.root.getChildren().add(Main.root.instrumentPickerWindow);
            taken = true;

            instrumentPickerWindowExitListener();
            instrumentPickerWindowChooseButtonListener();

        });
    }

    public void instrumentPickerWindowChooseButtonListener() {
        Main.root.instrumentPickerWindow.chooseButton.setOnAction(actionEvent -> {

            Main.root.getChildren().remove(Main.root.instrumentPickerWindow);
            this.instrumentId = Main.root.instrumentPickerWindow.switchIndex;

            displayBandPlayer(this.instrumentId, true);

            OSC.sendMessage("GUImessage", this.spotId, this.instrumentId, "take");
            takeIt();

        });
    }

    public void displayBandPlayer(int instrumentId, boolean isMe) {
        taken = true;
        bandPlayer = new BandPlayer(spotId, instrumentId, isMe);
        bandPlayer.setFitWidth(Main.root.getWidth() / 5);
        bandPlayer.setFitHeight(Main.root.getWidth() / 5);
        movePos(-bandPlayer.getFitWidth() / 2, -this.getHeight() * 1.5);
        Platform.runLater(() ->
                this.getChildren().add(bandPlayer));
        this.stageSpotButton.setVisible(false);
    }

    public void removeBandPlayer() {
        this.taken = false;
        this.movePos(-stageSpotButton.imageSize / 2, -stageSpotButton.imageSize / 2);
        Platform.runLater(() ->
                this.getChildren().remove(bandPlayer));
        if (!Main.root.playing) {
            this.stageSpotButton.setVisible(true);
        }
    }

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

    public void takeIt() {
        Main.root.playing = true;
        Main.root.getChildren().add(Main.root.leaveStageSpotButton);
        bandPlayer.pickUpInstrument();
        for (int i = 0; i < 4; i++)
            Main.root.stageSpots[i].stageSpotButton.setVisible(false);

        leaveStageSpotButtonListener();
    }

    void instrumentPickerWindowExitListener() {
        Main.root.instrumentPickerWindow.closeButton.setOnAction(actionEvent -> {
            Main.root.getChildren().remove(Main.root.instrumentPickerWindow);
            //leaveIt();
        });
    }

    void leaveStageSpotButtonListener() {
        Main.root.leaveStageSpotButton.setOnAction(actionEvent -> {
            leaveIt();
            removeBandPlayer();
            OSC.sendMessage("GUImessage", this.spotId, this.instrumentId, "leave");
        });
    }


}
