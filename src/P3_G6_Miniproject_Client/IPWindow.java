package P3_G6_Miniproject_Client;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class IPWindow extends StackPane {
    StackPane background = new StackPane(); //Pane for making background unclickable
    VBox ipWindow = new VBox();
    Text text = new Text();
    TextField textField = new TextField();
    Button button = new Button("Click to get text");
    String ipAddress;
    boolean connected;

    IPWindow() {

        String text = "Hello how are you";
        this.text.setText(text);

        //Setting the color
        this.text.setFill(Color.BROWN);

        ipWindow.getChildren().addAll(this.text, textField, button);
        //ipWindow.setMinSize(Main.root.getWidth()/2, Main.root.getHeight()/2);
        ipWindow.setStyle("-fx-background-color: #AAAAAA;");

        background.setMinSize(Main.root.getWidth(), Main.root.getHeight());
        background.setStyle("-fx-background-color: #111111;");
        this.getChildren().addAll(background, ipWindow);

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
        System.out.println(textField.getText());
        ipAddress = textField.getText();
        System.out.println(ipAddress);
        Main.root.getChildren().remove(this);
        Main.root.OSC.runOSC();
    }


}
