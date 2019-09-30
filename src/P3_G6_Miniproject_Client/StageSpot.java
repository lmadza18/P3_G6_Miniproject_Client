package P3_G6_Miniproject_Client;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class StageSpot extends Pane {

    boolean taken = false;

    double x;
    double y;

    InstrumentPickerWindow instrumentPickerWindow = new InstrumentPickerWindow();

    Button button;


    public StageSpot(double x, double y) {
        this.x = x;
        this.y = y;

        this.setTranslateX(x);
        this.setTranslateY(y);

        button = new Button("Take spot! AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        getChildren().add(button);
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
            Main.root.spot1.setVisible(false);
            Main.root.spot2.setVisible(false);
            Main.root.spot3.setVisible(false);
            Main.root.spot4.setVisible(false);
            Main.root.getChildren().remove(instrumentPickerWindow);
            Main.root.getChildren().add(Main.root.getMyAssOuttaHere);


        });
    }


}
