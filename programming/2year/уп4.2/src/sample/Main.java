package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.regex.*;

public class Main extends Application {
    TextArea text=new TextArea();
    Button but=new Button("Find");
    ObservableList list = FXCollections.observableArrayList();
    String str="(((0[1-9]|1\\d|2[0-8])\\.02)|(0[1-9]|[1-2]\\d|30)\\.(04|06|09|11)|(0[1-9]|[1-2]\\d|30|31)\\.(01|03|05|07|08|10|12))\\.([1-9]\\d*)";

    @Override
    public void start(Stage primaryStage) throws Exception{
        ListView<String> listView=new ListView<>(list);
        HBox box=new HBox();
        box.getChildren().add(text);
        box.getChildren().add(listView);

        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                list.clear();
                String val = text.getText();
                Matcher m = Pattern.compile(str).matcher(val);
                while (m.find()){
                    list.add(m.group(0));
                }
            }
        });

        VBox vBox=new VBox();
        vBox.getChildren().add(box);
        vBox.getChildren().add(but);
        Group root = new Group();
        root.getChildren().add(vBox);
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
