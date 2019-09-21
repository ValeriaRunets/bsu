import javafx.scene.control.Label;
import static java.awt.event.KeyEvent.getKeyText;

public class KeyView extends Label implements Observer {
    @Override
    public void update(String key) {
        setText(key);
    }
}
