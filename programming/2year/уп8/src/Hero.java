import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Hero implements Observer{
    int pos;
    Image image;
    int speed;

    Hero(int speed) throws IOException {
        image = ImageIO.read(new File("hero.png"));
        pos=500;
        this.speed=speed;
    }

    @Override
    public void update() {

    }
    public void up(){
        pos-=speed;
    }
    public void down(){
        pos+=speed;
    }
}
