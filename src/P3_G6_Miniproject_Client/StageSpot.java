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
    BandPlayer bandPlayer;



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
        button.setStyle("-fx-base: tranparent;");
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
            //Main.root.spot1.setVisible(false);
            Main.root.spot2.setVisible(false);
            Main.root.spot3.setVisible(false);
            Main.root.spot4.setVisible(false);
            Main.root.getChildren().remove(instrumentPickerWindow);
            Main.root.getChildren().add(Main.root.getMyAssOuttaHere);
            bandPlayer = new BandPlayer();
            this.getChildren().add(bandPlayer);
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
            Main.root.getChildren().remove(Main.root.getMyAssOuttaHere);
        });

    }


}
