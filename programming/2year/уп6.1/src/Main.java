import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    private final int NUMBER = 3;

    private JPanel puzzle;
    private JPanel example;

    int x = -1;
    int y = -1;

    private JButton[][] parts;

    public Main() {
        super("Puzzle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1250, 415);
        setLayout(new BorderLayout());

        example = new JPanel();
        JLabel exampleImage = new JLabel();
        exampleImage.setIcon(new ImageIcon("test.jpg"));
        example.add(exampleImage);

        puzzle = new JPanel(new GridLayout(NUMBER, NUMBER));
        parts = getPuzzle("test.jpg");

        for (int i = 0; i < NUMBER*NUMBER; i++)
            swap((int)(Math.random() * NUMBER),
                    (int)(Math.random() * NUMBER),
                    (int)(Math.random() * NUMBER),
                    (int)(Math.random() * NUMBER),
                    false);

        for (int i = 0; i < NUMBER; i++)
            for (int j = 0; j < NUMBER; j++)
                puzzle.add(parts[i][j]);

        add(example, BorderLayout.WEST);
        add(puzzle, BorderLayout.CENTER);
    }

    private  JButton[][] getPuzzle(String imagePath) {
        try {
            BufferedImage im = ImageIO.read(new File(imagePath));
            int width = im.getWidth()/NUMBER, tileHeight = im.getHeight()/NUMBER;
            JButton[][] matr = new JButton[NUMBER][NUMBER];
            for (int i = 0; i < NUMBER; i++)
                for (int j = 0; j < NUMBER; j++) {
                    matr[i][j] = new JButton();
                    matr[i][j].setIcon(new ImageIcon(im.getSubimage(j*width, i*tileHeight, width, tileHeight)));
                    matr[i][j].setName(""+(i*NUMBER+j));
                    matr[i][j].putClientProperty(0, i);
                    matr[i][j].putClientProperty(1, j);
                    matr[i][j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JButton b = ((JButton) e.getSource());
                            if (x == -1 && y==-1) {
                                b.setEnabled(false);
                                x = (int)b.getClientProperty(0);
                                y = (int)b.getClientProperty(1);
                            }
                            else {
                                swap((int)b.getClientProperty(0), (int)b.getClientProperty(1), x, y, true);
                                check();
                                x = -1;
                                y=-1;
                            }
                        }
                    });
                }
            return matr;

        } catch (IOException e) {
            return null;
        }
    }

    private void swap(int part1X, int part1Y, int part2X, int part2Y, boolean repaint) {

        JButton part1 =  parts[part1X][part1Y];
        JButton part2 =  parts[part2X][part2Y];
        part1.setEnabled(true);
        part1.putClientProperty(0, part2X);
        part1.putClientProperty(1, part2Y);
        part2.setEnabled(true);
        part2.putClientProperty(0, part1X);
        part2.putClientProperty(1, part1Y);

        JButton temp = parts[part1X][part1Y];
        parts[part1X][part1Y] = parts[part2X][part2Y];
        parts[part2X][part2Y] = temp;

        if (repaint) {
            puzzle.removeAll();
            for (int i = 0; i < NUMBER; i++)
                for (int j = 0; j < NUMBER; j++)
                    puzzle.add(parts[i][j]);
            puzzle.revalidate();
            puzzle.repaint();
        }
    }

    void check() {
        for (int i = 0; i < NUMBER; i++)
            for (int j = 0; j < NUMBER; j++)
                if(Integer.parseInt(parts[i][j].getName()) != i*NUMBER+j)
                    return;
        JOptionPane.showMessageDialog(null, "You've solved the puzzle!", "CONGRATULATIONS!", JOptionPane.INFORMATION_MESSAGE );
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.setVisible(true);
    }
}