package Niels_Lugter;

import javafx.scene.control.Button;

//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;

public class PosButton extends Button {
    double x;
    double y;
    String title;
    InstrumentPickerWindow instrumentPickerWindow;


    PosButton(String title, double x, double y) {
        this.x = x;
        this.y = y;
        this.title = title;

        //  ImageView imgButton = new ImageView("images/drummer1.png");
        //  ImageView imgButton2 = new ImageView("images/drummer2.png");
        //  imgButton.setFitWidth(150);
        //  imgButton.setFitHeight(150);
        //  button.setGraphic(imgButton);
        //  button1.setStyle("-fx-background-color: transparent");
        this.setTranslateX(x);
        this.setTranslateY(y);
        buttonPress();

    }

    void buttonPress() {
        this.setOnAction(actionEvent -> {
            instrumentPickerWindow = new InstrumentPickerWindow();

            Main.root.getChildren().add(instrumentPickerWindow);
            System.out.println(title);
//            playThatBassNote("out/production/P3_G6_Miniproject_Client/audio_files/Bass/0CBass.wav");
        });
    }

//    public static void playThatBassNote(String filepath) {
//        InputStream music;
//        try {
//            music = new FileInputStream(new File(filepath));
//            AudioStream audios = new AudioStream(music);
//            AudioPlayer.player.start(audios);
//        }
//        catch (Exception e) {
//            JOptionPane.showMessageDialog(null,"Error");
//        }
//    }
}
