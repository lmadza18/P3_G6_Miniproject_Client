package P3_G6_Miniproject_Client;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class StageSpot extends Pane {

    boolean taken = false;

    double x;
    double y;

    InstrumentPickerWindow instrumentPickerWindow = new InstrumentPickerWindow();

    Button button;
    ImageView buttonImg;
    ImageView buttonHoverImg;


    static BandPlayer guitarist;
    static BandPlayer drummer;
    static BandPlayer bassist;
    static BandPlayer keyboard;

    private int spotId;

    static BandPlayer[] bandPlayers = {
            guitarist = new BandPlayer(new ImageView("images/guitarist.png"), 0),
            drummer = new BandPlayer(new ImageView("images/drummer1.png"), 1),
            bassist = new BandPlayer(new ImageView("images/bassist.jpg"), 2),
            keyboard = new BandPlayer(new ImageView("images/sprite.png"), 3),
    };

   /* static final int GUITARIST_ID = 0;
    static final int DRUMMER_ID = 1;
    static final int BASSIST_ID = 2;
    static final int KEYBOARD_ID = 3;*/


    public StageSpot(double x, double y) {
        int imageSize = (int) Math.round(Main.root.getWidth() / 10);
        int buttonSize = Math.round(imageSize / 6 * 4);
        int buttonTransform = Math.round(imageSize / 6);
        buttonImg = new ImageView("Takespot_button.png");
        buttonHoverImg = new ImageView("Takespot_button_hover.png");

        buttonImg.setFitWidth(imageSize);
        buttonImg.setFitHeight(imageSize);
        buttonHoverImg.setFitWidth(imageSize);
        buttonHoverImg.setFitHeight(imageSize);

        button = new Button(null);
        button.setStyle("-fx-background-color: transparent");
        button.setMinSize(buttonSize, buttonSize);
        button.setTranslateX(buttonTransform);
        button.setTranslateY(buttonTransform);

        getChildren().addAll(buttonImg, buttonHoverImg, button);

        //button.setPickOnBounds(true);
        buttonHoverImg.setVisible(false);

        button.setOnMouseEntered(t -> {
            buttonImg.setVisible(false);
            buttonHoverImg.setVisible(true);
        });

        button.setOnMouseExited(t -> {
            buttonImg.setVisible(true);
            buttonHoverImg.setVisible(false);
        });


        this.x = x - (imageSize / 2);
        this.y = y - (imageSize / 2);
        this.setTranslateX(this.x);
        this.setTranslateY(this.y);


        takeIt();


    }

    public void takeIt() {
        button.setOnAction(actionEvent -> {
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
        Main.root.spot1.setVisible(true);
        Main.root.spot2.setVisible(true);
        Main.root.spot3.setVisible(true);
        Main.root.spot4.setVisible(true);
    }

    void thisSpotKillsMyMojo() {
        instrumentPickerWindow.closeButton.setOnAction(actionEvent -> {
            Main.root.getChildren().remove(instrumentPickerWindow);
            leaveIt();
        });
    }

    void chooseSpot() {
        instrumentPickerWindow.chooseButton.setOnAction(actionEvent -> {
            Main.root.spot1.setVisible(false);
            Main.root.spot2.setVisible(false);
            Main.root.spot3.setVisible(false);
            Main.root.spot4.setVisible(false);

            this.spotId = instrumentPickerWindow.switchIndex;
            Main.root.getChildren().remove(instrumentPickerWindow);
            Main.root.getChildren().addAll(Main.root.getMyAssOuttaHere, bandPlayers[spotId], bandPlayers[spotId].img);
            bandPlayers[spotId].taken = true;
            getMyAssOuttaHere();

        });
    }

    void getMyAssOuttaHere() {
        Main.root.getMyAssOuttaHere.setOnAction(actionEvent -> {
            taken = false;
            Main.root.spot1.setVisible(true);
            Main.root.spot2.setVisible(true);
            Main.root.spot3.setVisible(true);
            Main.root.spot4.setVisible(true);
            Main.root.getChildren().removeAll(Main.root.getMyAssOuttaHere, bandPlayers[spotId], bandPlayers[spotId].img);
            bandPlayers[spotId].taken = false;
        });

    }


}
