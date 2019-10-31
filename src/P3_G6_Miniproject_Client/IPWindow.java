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

public class IPWindow extends StackPane {
    StackPane background = new StackPane(); //Pane for making background unclickable
    VBox ipWindow = new VBox();
    Text text = new Text();
    TextField textField = new TextField();
    Button button = new Button("Connect");
    String ipAddress;
    IPWindow() {

        ipWindow.setAlignment(Pos.CENTER);
        //ipWindow.setSpacing(Main.root.getHeight()/120);
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



        background.setMinSize(Main.root.getWidth(), Main.root.getHeight());
        background.setStyle("-fx-background-color: #111111; -fx-opacity: 0.7;");
        ImageView bgImg = new ImageView("images/rockband.jpg");
        bgImg.setFitWidth(Main.root.getWidth());
        bgImg.setFitHeight(Main.root.getHeight());
        this.getChildren().addAll(bgImg, ipWindow);

        button.setOnAction(action -> {
            setAndRun();

        });

        Main.root.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                setAndRun();
            }
        });
    }

    public String getIpAddress() {
        return ipAddress;
    }

    private void setAndRun() {
        ipAddress = textField.getText();
        Main.root.getChildren().remove(this);
        Main.root.OSC.runOSC();
    }


}
