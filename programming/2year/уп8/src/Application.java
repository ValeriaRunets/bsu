import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.time.ZonedDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.util.*;



public class Application extends JFrame {
    Timer timer=new Timer();
    Timer curTimer=new Timer();
    Pan panel;
    Hero hero;
    int score=0;
    Thing things[]=new Thing[3];
    int speed=5;
    boolean flag=false;
    int score1, score2, score3;
    private final int LEVEL1=5;
    private final int LEVEL2=8;
    private final int LEVEL3=10;
    private final int RANDOMBOUND=500;
    private final int RANDOMGAP=700;
    private final int NUMBEROFTHINGS=3;
    private final int PERIOD=70;
    private final int LEFTBORDER=-70;
    private final int HEROWIDTH=150;
    private final int HEROX=100;
    private final int THINGWIDTH=50;
    private final int HEROY=500;
    private final int THINGHEIGHT=50;
    private final int HEROJUMP=320;
    Application() throws IOException{
        panel=new Pan();
        Scanner sc = new Scanner(new File("BestScore.txt"));
        score1=sc.nextInt();
        score2=sc.nextInt();
        score3=sc.nextInt();
        JMenuBar menuBar=new JMenuBar();
        JMenu menu=new JMenu("Game");
        JMenu scoreMenu=new JMenu("Best score");
        JMenu newGame=new JMenu("New Game");
        JMenuItem item1=new JMenuItem("Level 1");
        JMenuItem item2=new JMenuItem("Level 2");
        JMenuItem item3=new JMenuItem("Level 3");
        JMenuItem score=new JMenuItem("Show");
        scoreMenu.add(score);
        newGame.add(item1);
        newGame.add(item2);
        newGame.add(item3);
        menu.add(newGame);
        menuBar.add(menu);
        menuBar.add(scoreMenu);
        setJMenuBar(menuBar);
        score.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "1 LEVEL:"+Integer.toString(score1)+'\n'+
                            "2 LEVEL:"+Integer.toString(score2)+'\n'+"3 LEVEL:"+Integer.toString(score3), "Best Scores", JOptionPane.INFORMATION_MESSAGE);
                }
            });
            item1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        speed = LEVEL1;
                        play();
                        timer.cancel();
                    }catch (Exception ex){}
                }
            });
            item2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        speed = LEVEL2;
                        play();
                        timer.cancel();
                    }catch (Exception ex){}
                }
            });
            item3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        speed = LEVEL3;
                        play();
                        timer.cancel();
                    }catch (Exception ex){}
                }
            });
            panel.setFocusable(true);
            setContentPane(panel);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
            setExtendedState(MAXIMIZED_BOTH);
            setResizable(false);
    }
    public void play() throws Exception{
        score=0;
        curTimer.cancel();
        hero=new Hero(speed);
        Action action=new Action();
        action.registerObserver(hero);
        Random random=new Random((long)ZonedDateTime.now().getMinute());
        for (int i=0; i<NUMBEROFTHINGS; i++) {
            things[i]=new Thing(random.nextInt(RANDOMBOUND)+3*RANDOMBOUND+RANDOMGAP*i, random.nextInt(speed)+speed, random.nextInt(2));
        }
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                timer.cancel();
                timer=new Timer();
                flag=false;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        panel.change(hero, things);
                        if (hero.pos<=HEROJUMP){
                            flag=true;
                        }
                        if (!flag) {
                            hero.up();
                        }else {
                            hero.down();
                        }
                        if (hero.pos>HEROY){
                            hero.pos=HEROY;
                            timer.cancel();
                        }
                    }
                }, 0, PERIOD);
            }
        });
        curTimer=new Timer();
        curTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < NUMBEROFTHINGS; i++) {
                        things[i].move();
                        panel.change(hero, things);
                        if (things[i].pos < LEFTBORDER) {
                            things[i] = new Thing(random.nextInt(RANDOMBOUND) + 3 * RANDOMBOUND + RANDOMGAP * i, random.nextInt(speed)+speed, random.nextInt(2));
                            score++;
                        }
                        if (things[i].pos < HEROX + HEROWIDTH && things[i].pos > HEROX - THINGWIDTH && hero.pos > HEROY - THINGHEIGHT) {
                            finish();
                            try {
                                FileWriter writer = new FileWriter("BestScore.txt");
                                writer.write(score1 + " ");
                                writer.write(score2 + " ");
                                writer.write(score3 + " ");
                                writer.close();
                            } catch (IOException ex) {
                            }
                            curTimer.cancel();
                        }
                    }
                }catch (Exception ex){}
            }
        }, 0, PERIOD);
    }
    public void finish(){
            if (speed==LEVEL1){
                if (score>score1){
                    JOptionPane.showMessageDialog(null, "You have the best score!", "CONGRATULATIONS!", JOptionPane.PLAIN_MESSAGE);
                    score1=score;
                }
                else{
                    JOptionPane.showMessageDialog(null, "GAME OVER", "GAME OVER", JOptionPane.PLAIN_MESSAGE);
                }
            }else if (speed==LEVEL2){
                if (score>score2){
                    JOptionPane.showMessageDialog(null, "You have the best score!", "CONGRATULATIONS!", JOptionPane.PLAIN_MESSAGE);
                    score2=score;
                }
                else{
                    JOptionPane.showMessageDialog(null, "GAME OVER", "GAME OVER", JOptionPane.PLAIN_MESSAGE);
                }
            }else if (speed==LEVEL3){
                if (score>score3){
                    JOptionPane.showMessageDialog(null, "You have the best score!", "CONGRATULATIONS!", JOptionPane.PLAIN_MESSAGE);
                    score3=score;
                }
                else{
                    JOptionPane.showMessageDialog(null, "GAME OVER", "GAME OVER", JOptionPane.PLAIN_MESSAGE);
                }
            }
            return;
    }
    public static void main(String[] args) {
        try {
            new Application();
        }catch (IOException ex){}
    }
}
