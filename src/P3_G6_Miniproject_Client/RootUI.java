package P3_G6_Miniproject_Client;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class RootUI extends Pane {
    StageSpot spot1;
    StageSpot spot2;
    StageSpot spot3;
    StageSpot spot4;
    Button getMyAssOuttaHere;



    RootUI() {


    }

    public void start() {
        ImageView imageView = new ImageView("images/stage.jpg");
        imageView.setFitWidth(this.getWidth());
        imageView.setFitHeight(this.getHeight());

        spot1 = new StageSpot(this.getWidth() / 8, this.getHeight() / 8 * 6);
        spot2 = new StageSpot(this.getWidth() / 8 * 3, this.getHeight() / 8 * 5.5);
        spot3 = new StageSpot(this.getWidth() / 8 * 5, this.getHeight() / 8 * 5.5);
        spot4 = new StageSpot(this.getWidth() / 8 * 7, this.getHeight() / 8 * 6);

        getMyAssOuttaHere = new Button("get my ass outta here!");

//        PosButton button1 = new PosButton("Position1", this.getWidth() / 8, this.getHeight() / 8 * 6);
//        PosButton button2 = new PosButton("Position2", this.getWidth() / 8 * 3, this.getHeight() / 8 * 5.5);
//        PosButton button3 = new PosButton("Position3", this.getWidth() / 8 * 5, this.getHeight() / 8 * 5.5);
//        PosButton button4 = new PosButton("Position4", this.getWidth() / 8 * 7, this.getHeight() / 8 * 6);

        this.getChildren().addAll(imageView, spot1, spot2, spot3, spot4);
        instrument2 ins = new instrument2();
        ins.setUpListener(this);

        // Adding setOnKeyPressed to "this"
//        this.setOnKeyPressed(e ->{
//            Controller controller = new Controller();
//            Controller controller2 = new Controller();
//
//            switch (e.getCode()){
//                case A:
//                    controller.playSound("src/audio_files/Bass/0CBass.wav");
//                    break;
//
//                case S:
//                    controller2.playSound("src/audio_files/Bass/0DBass.wav");
//                    break;
//                case D:
//                    controller.playSound("src/audio_files/Bass/0EBass.wav");
//                    break;
//
//                case F:
//                    controller.playSound("src/audio_files/Bass/0FBass.wav");
//                    break;
//
//                case G:
//                    controller.playSound("src/audio_files/Bass/0GBass.wav");
//                    break;
                    

            //}

   //     });







    }

}
