import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        CurrentKey key=new CurrentKey();
        LogView log=new LogView();
        KeyView lab=new KeyView();
        lab.setFont(Font.font(150));
        lab.setMinSize(200, 200);
        HBox box=new HBox();
        box.getChildren().add(lab);
        box.getChildren().add(log);
        Scene scene = new Scene(box, 450, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        key.registerObserver(lab);
        key.registerObserver(log);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getText().isEmpty()){
                    key.pressKey(event.getCode().getName());
                }else {
                    key.pressKey(event.getText());
                }
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}
