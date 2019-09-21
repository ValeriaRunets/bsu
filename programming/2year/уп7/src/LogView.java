import javafx.scene.control.ListView;
import static java.awt.event.KeyEvent.getKeyText;

public class LogView extends ListView implements Observer {
    @Override
    public void update(String key) {
        getItems().add(key);
    }
}
