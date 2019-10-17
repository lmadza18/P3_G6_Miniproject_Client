package P3_G6_Miniproject_Client;

import javafx.scene.layout.Pane;


public class StageSpot extends Pane {

    StageSpotButton stageSpotButton;
    InstrumentPickerWindow instrumentPickerWindow = new InstrumentPickerWindow();

    boolean taken = false;

    double x;
    double y;

    private int spotId;

    public StageSpot(double x, double y) {
//
        stageSpotButton = new StageSpotButton();
        this.getChildren().add(stageSpotButton);

        this.x = x - (stageSpotButton.imageSize / 2);
        this.y = y - (stageSpotButton.imageSize / 2);
        this.setTranslateX(this.x);
        this.setTranslateY(this.y);

        takeIt();

    }

    public void takeIt() {
        stageSpotButton.button.setOnAction(actionEvent -> {
            taken = true;
            instrumentPickerWindow = new InstrumentPickerWindow();
            Main.root.getChildren().add(instrumentPickerWindow);

            thisSpotKillsMyMojo();
            chooseSpot();

//            playThatBassNote("out/production/P3_G6_Miniproject_Client/audio_files/Bass/0CBass.wav");
        });
    }

    public void leaveIt() {
        taken = false;
        Main.root.spot1.stageSpotButton.setVisible(true);
        Main.root.spot2.stageSpotButton.setVisible(true);
        Main.root.spot3.stageSpotButton.setVisible(true);
        Main.root.spot4.stageSpotButton.setVisible(true);
    }

    void thisSpotKillsMyMojo() {
        instrumentPickerWindow.closeButton.setOnAction(actionEvent -> {
            Main.root.getChildren().remove(instrumentPickerWindow);
            leaveIt();
        });
    }

    void chooseSpot() {
        instrumentPickerWindow.chooseButton.setOnAction(actionEvent -> {
            Main.root.spot1.stageSpotButton.setVisible(false);
            Main.root.spot2.stageSpotButton.setVisible(false);
            Main.root.spot3.stageSpotButton.setVisible(false);
            Main.root.spot4.stageSpotButton.setVisible(false);

            this.spotId = instrumentPickerWindow.switchIndex;
            Main.root.getChildren().remove(instrumentPickerWindow);
            Main.root.getChildren().addAll(Main.root.getMyAssOuttaHere);
            this.getChildren().add(Main.root.bandPlayers[spotId]);
            Main.root.bandPlayers[spotId].taken = true;
            getMyAssOuttaHere();

        });
    }

    void getMyAssOuttaHere() {
        Main.root.getMyAssOuttaHere.setOnAction(actionEvent -> {
            taken = false;
            Main.root.spot1.stageSpotButton.setVisible(true);
            Main.root.spot2.stageSpotButton.setVisible(true);
            Main.root.spot3.stageSpotButton.setVisible(true);
            Main.root.spot4.stageSpotButton.setVisible(true);
            Main.root.getChildren().removeAll(Main.root.getMyAssOuttaHere, Main.root.bandPlayers[spotId]);
            Main.root.bandPlayers[spotId].taken = false;
        });

    }


}
