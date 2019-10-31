package P3_G6_Miniproject_Client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * This class the first screen in the program where your need to enter the IP address you want to join.
 **/

public class IPWindow extends StackPane {
    //StackPane background = new StackPane(); /
    VBox ipWindow = new VBox();
    Text text = new Text();
    TextField textField = new TextField();
    Button button = new Button("Connect");

    IPWindow() {

        //settings for GUI
        ipWindow.setAlignment(Pos.CENTER);
        int margin = (int) (Main.root.getHeight() / 60);
        VBox.setMargin(text, new Insets(margin / 2, margin, margin / 2, margin));
        VBox.setMargin(textField, new Insets(margin / 2, margin, margin / 2, margin));
        VBox.setMargin(button, new Insets(margin / 2, margin, margin / 2, margin));

        String text = "Insert the IP address you want to connect with";
        this.text.setText(text);
        this.text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        this.text.setFill(Color.BROWN);

        ipWindow.getChildren().addAll(this.text, textField, button);
        ipWindow.setMaxSize(Main.root.getWidth() / 3, Main.root.getHeight() / 7);
        ipWindow.setStyle("-fx-background-color: #AAAAAA; -fx-border-color: #444444;");


        ImageView bgImg = new ImageView("images/rockband.jpg"); //the background image
        bgImg.setFitWidth(Main.root.getWidth());
        bgImg.setFitHeight(Main.root.getHeight());
        this.getChildren().addAll(bgImg, ipWindow);

        //listener for when the button is clicked
        button.setOnAction(action -> {
            setAndRun();
        });

        //listener for when the enter is pressed
        Main.root.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                setAndRun();
            }
        });
    }

    //method for starting server when ip address have been written
    private void setAndRun() {
        OSC.hostName = textField.getText();
        Main.root.OSC.runOSC();
        Main.root.getChildren().remove(this);
    }

}
