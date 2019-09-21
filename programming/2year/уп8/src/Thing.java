import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Random;

public class Thing implements Observer{
    int pos;
    Image[] images=new Image[2];
    Image image;
    int speed;
    Thing(int pos, int speed, int random) throws IOException {
        images[0]= ImageIO.read(new File("thing.png"));
        images[1]= ImageIO.read(new File("thing2.png"));
        image=images[random];
        this.pos=pos;
        this.speed=speed;
    }

    @Override
    public void update() {

    }
    public void move(){
        pos-=speed;
    }
}
