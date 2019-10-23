package P3_G6_Miniproject_Client;

import javafx.scene.layout.StackPane;


public class StageSpot extends StackPane {

    StageSpotButton stageSpotButton;

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
            taken = true;
            Main.root.getChildren().add(Main.root.instrumentPickerWindow);

            instrumentPickerWindowExtListener();
            instrumentPickerWindowChooseButtonListener();

//            playThatBassNote("out/production/P3_G6_Miniproject_Client/audio_files/Bass/0CBass.wav");
        });
    }

    void instrumentPickerWindowChooseButtonListener() {
        Main.root.instrumentPickerWindow.chooseButton.setOnAction(actionEvent -> {

            this.spotId = Main.root.instrumentPickerWindow.switchIndex;
            Main.root.getChildren().remove(Main.root.instrumentPickerWindow);
            Main.root.getChildren().add(Main.root.leaveStageSpotButton);

            Main.root.bandPlayers[spotId].setFitWidth(Main.root.getWidth() / 5);
            Main.root.bandPlayers[spotId].setFitHeight(Main.root.getWidth() / 5);
            movePos(-Main.root.bandPlayers[spotId].getFitWidth() / 2, -this.getHeight() * 1.5);

            takeIt();

            leaveStageSpotButtonListener();

        });
    }

    public void leaveIt() {
        Main.root.bandPlayers[spotId].taken = false;
        this.taken = false;
        Main.root.bandPlayers[spotId].putDownInstrument();
        this.getChildren().remove(Main.root.bandPlayers[spotId]);
        for (int i = 0; i < 4; i++)
            Main.root.stageSpots[i].stageSpotButton.setVisible(true);

    }

    public void takeIt() {
        Main.root.bandPlayers[spotId].taken = true;
        taken = true;

        Main.root.bandPlayers[spotId].pickUpInstrument();
        this.getChildren().add(Main.root.bandPlayers[spotId]);

        for (int i = 0; i < 4; i++)
            Main.root.stageSpots[i].stageSpotButton.setVisible(false);

    }

    void instrumentPickerWindowExtListener() {
        Main.root.instrumentPickerWindow.closeButton.setOnAction(actionEvent -> {
            Main.root.getChildren().remove(Main.root.instrumentPickerWindow);
            leaveIt();
        });
    }

    void leaveStageSpotButtonListener() {
        Main.root.leaveStageSpotButton.setOnAction(actionEvent -> {
            leaveIt();
            this.movePos(-stageSpotButton.imageSize / 2, -stageSpotButton.imageSize / 2);
            Main.root.getChildren().removeAll(Main.root.leaveStageSpotButton);
            this.getChildren().remove(Main.root.bandPlayers[spotId]);
        });
    }


}
