package P3_G6_Miniproject_Client;

import javafx.scene.layout.StackPane;


public class StageSpot extends StackPane {

    StageSpotButton stageSpotButton;
    InstrumentPickerWindow instrumentPickerWindow = new InstrumentPickerWindow();

    boolean taken = false;

    double x;
    double y;
    Double xInit;
    Double yInit;

    private int spotId;

    public StageSpot(double x, double y) {
        this.x = x;
        this.y = y;
        this.xInit = x;
        this.yInit = y;
//
        stageSpotButton = new StageSpotButton();
        this.getChildren().add(stageSpotButton);


        this.movePos(-stageSpotButton.imageSize / 2, -stageSpotButton.imageSize / 2);

        this.setStyle("-fx-border-color: RED;");

        takeIt();

    }

    private void movePos(double x, double y) {
        this.x = this.xInit + (x);
        this.y = this.yInit + (y);
        this.setTranslateX(this.x);
        this.setTranslateY(this.y);
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
            movePos(-this.getWidth(), -this.getHeight() * 2);

            Main.root.bandPlayers[spotId].taken = true;
            getMyAssOuttaHere();

        });
    }

    void getMyAssOuttaHere() {
        Main.root.getMyAssOuttaHere.setOnAction(actionEvent -> {
            taken = false;
            Main.root.bandPlayers[spotId].taken = false;
            Main.root.spot1.stageSpotButton.setVisible(true);
            Main.root.spot2.stageSpotButton.setVisible(true);
            Main.root.spot3.stageSpotButton.setVisible(true);
            Main.root.spot4.stageSpotButton.setVisible(true);
            this.movePos(-stageSpotButton.imageSize / 2, -stageSpotButton.imageSize / 2);
            Main.root.getChildren().removeAll(Main.root.getMyAssOuttaHere);

            this.getChildren().removeAll(Main.root.bandPlayers[spotId]);
        });

    }


}
