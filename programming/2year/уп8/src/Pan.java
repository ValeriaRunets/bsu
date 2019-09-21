import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Pan extends JPanel {
    public BufferedImage img=new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_ARGB);
    Image image;

    Pan() throws IOException {
        super();
        //setSize(1500, 900);
        setOpaque(false);
        image = ImageIO.read(new File("bcg.jpg"));
        img.getGraphics().drawImage(image, 0, 0, null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }
    public void change(Hero hero, Thing[] things){
        img.getGraphics().drawImage(image, 0, 0, null);
        img.getGraphics().drawImage(hero.image, 100, hero.pos, null);
        for (int i=0; i<3; i++) {
            img.getGraphics().drawImage(things[i].image, things[i].pos, 600, null);
        }
        repaint();
    }
}
