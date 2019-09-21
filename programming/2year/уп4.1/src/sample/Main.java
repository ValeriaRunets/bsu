package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.regex.*;

import java.io.InputStream;

public class Main extends Application {
    InputStream input1 = getClass().getResourceAsStream("green.png");
    Image green = new Image(input1);

    InputStream input2 = getClass().getResourceAsStream("red.png");
    Image red = new Image(input2);
    ImageView imView=new ImageView(red);

    TextField text;

    String[] str = {"[1-9]\\d*",
            "([+-]?\\d+)",
            "(([-+]?((\\d?|[1-9]\\d*)(,\\d+)|(\\d+|[1-9]+\\d*)(,\\d*?))(e([-+]?\\d+))?))",
            "(((0[1-9]|1\\d|2[0-8])\\.02)|(0[1-9]|[1-2]\\d|30)\\.(04|06|09|11)|(0[1-9]|[1-2]\\d|30|31)\\.(01|03|05|07|08|10|12))\\.([1-9]\\d*)",
            "([0-1]\\d|2[0-3]):[0-5]\\d",
            "[a-z](\\w*[^\\s]?\\w*)@[a-zA-Z]{2,}(\\.(ru|by|com))",
    };

    @Override
    public void start(Stage primaryStage) throws Exception{
        HBox box=new HBox();
        ComboBox<String> combo=new ComboBox<>();
        box.getChildren().add(combo);
        combo.getItems().addAll(
                "натуральное число",
                "целое число",
                "число с плавающей запятой",
                "дата",
                "время",
                "e-mail"
        );

        text = new TextField("");
        box.getChildren().add(text);
        box.getChildren().add(imView);
        VBox vBox=new VBox();
        vBox.getChildren().add(box);
        Button but=new Button("OK");
        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String val = text.getText();
                int index = combo.getSelectionModel().getSelectedIndex();
                Pattern pattern = Pattern.compile(str[index]);
                Matcher matcher=pattern.matcher(val);
                boolean same=matcher.matches();
                if (same){
                    imView.setImage(green);
                }else{
                    imView.setImage(red);
                }
            }
        });
        vBox.getChildren().add(but);
        Group root = new Group();
        root.getChildren().add(vBox);
        Scene scene = new Scene(root, 450, 100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
