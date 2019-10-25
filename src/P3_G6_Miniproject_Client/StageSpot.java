package P3_G6_Miniproject_Client;

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
    private int spotId;

    public StageSpot(int id, double x, double y) {
        this.spotId = id;
        this.x = x;
        this.y = y;
        this.xInit = x;
        this.yInit = y;
//
        //bandPlayer = new BandPlayer();
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

//            playThatBassNote("out/production/P3_G6_Miniproject_Client/audio_files/Bass/0CBass.wav");
        });
    }

    public void instrumentPickerWindowChooseButtonListener() {
        Main.root.instrumentPickerWindow.chooseButton.setOnAction(actionEvent -> {

            this.instrumentId = Main.root.instrumentPickerWindow.switchIndex;
            Main.root.getChildren().remove(Main.root.instrumentPickerWindow);
            Main.root.getChildren().add(Main.root.leaveStageSpotButton);

            displayBandPlayer(this.instrumentId);

            OC.sendMessage("GUImessage", this.spotId, this.instrumentId, "take");
            takeIt();

            leaveStageSpotButtonListener();

        });
    }

    public void displayBandPlayer(int instrumentId){
        Main.root.bandPlayersTaken[instrumentId] = true;
        bandPlayer = new BandPlayer(instrumentId);
        bandPlayer.setFitWidth(Main.root.getWidth() / 5);
        bandPlayer.setFitHeight(Main.root.getWidth() / 5);
        movePos(-bandPlayer.getFitWidth() / 2, -this.getHeight() * 1.5);
        this.getChildren().add(bandPlayer);
        this.stageSpotButton.setVisible(false);
    }

    public void leaveIt() {
        Main.root.bandPlayersTaken[instrumentId] = false;
        this.taken = false;
        this.getChildren().remove(bandPlayer);
        for (int i = 0; i < 4; i++)
            Main.root.stageSpots[i].stageSpotButton.setVisible(true);
    }

    public void takeIt() {
        taken = true;

        bandPlayer.pickUpInstrument();

        for (int i = 0; i < 4; i++)
            Main.root.stageSpots[i].stageSpotButton.setVisible(false);

    }

    void instrumentPickerWindowExitListener() {
        Main.root.instrumentPickerWindow.closeButton.setOnAction(actionEvent -> {
            Main.root.getChildren().remove(Main.root.instrumentPickerWindow);
            leaveIt();
        });
    }

    void leaveStageSpotButtonListener() {
        Main.root.leaveStageSpotButton.setOnAction(actionEvent -> {
            leaveIt();
            bandPlayer.putDownInstrument();
            this.movePos(-stageSpotButton.imageSize / 2, -stageSpotButton.imageSize / 2);
            Main.root.getChildren().removeAll(Main.root.leaveStageSpotButton);
            this.getChildren().remove(bandPlayer);
        });
    }


}
