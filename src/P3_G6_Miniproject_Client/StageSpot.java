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
        for (int i = 0; i < 4; i++)
            Main.root.stageSpots[i].stageSpotButton.setVisible(true);
    }

    void thisSpotKillsMyMojo() {
        instrumentPickerWindow.closeButton.setOnAction(actionEvent -> {
            Main.root.getChildren().remove(instrumentPickerWindow);
            leaveIt();
        });
    }

    void chooseSpot() {
        instrumentPickerWindow.chooseButton.setOnAction(actionEvent -> {
            taken = false;
            for (int i = 0; i < 4; i++)
                Main.root.stageSpots[i].stageSpotButton.setVisible(false);

            this.spotId = instrumentPickerWindow.switchIndex;
            Main.root.getChildren().remove(instrumentPickerWindow);
            Main.root.getChildren().addAll(Main.root.leaveStageSpot);

            this.getChildren().add(Main.root.bandPlayers[spotId]);

            Main.root.bandPlayers[spotId].setFitWidth(Main.root.getWidth() / 5);
            Main.root.bandPlayers[spotId].setFitHeight(Main.root.getWidth() / 5);
            movePos(-Main.root.bandPlayers[spotId].getFitWidth() / 2, -this.getHeight() * 1.5);

            Main.root.bandPlayers[spotId].taken = true;
            Main.root.bandPlayers[spotId].taken = true;
            Main.root.bandPlayers[spotId].pickUpInstrument();
            leaveStageSpot();

        });
    }

    void leaveStageSpot() {
        Main.root.leaveStageSpot.setOnAction(actionEvent -> {
            taken = false;
            Main.root.bandPlayers[spotId].taken = false;
            taken = false;
            for (int i = 0; i < 4; i++)
                Main.root.stageSpots[i].stageSpotButton.setVisible(true);
            this.movePos(-stageSpotButton.imageSize / 2, -stageSpotButton.imageSize / 2);
            Main.root.getChildren().removeAll(Main.root.leaveStageSpot);
            Main.root.bandPlayers[spotId].putDownInstrument();
            this.getChildren().removeAll(Main.root.bandPlayers[spotId]);
        });

    }


}
