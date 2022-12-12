import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main extends JPanel implements Interface  {

    private JButton play, help, exit, close;
    private Image bgMenu;
    Font GravityBold8;

    JFrame frame = new JFrame("Space Invaders");
    JFrame frame2 = new JFrame("Space Invaders");
    JFrame frame3 = new JFrame("How To Play!");
    JPanel panel1 = new JPanel();


    public Main(){
        JPanel button = new JPanel();

        play = new JButton("PLAY");
        play.addActionListener(new ButtonListener());
        play.setSize(200,80);
        play.setBounds(0, 0 , 200, 80);
        play.setBackground(new Color(0,0,51));
        play.setForeground(Color.WHITE);
        play.setFont(Font.getFont("GravityBold8"));
        play.getFont();
//        start = new JButton("START");
//        start.addActionListener(new HelpButton());
//        //start.setBounds(0, 0 , 200, 80);
//        start.setBackground(new Color(0,0,51));
//        //start.setForeground(Color.WHITE);
//        start.setSize(200,80);
//        start = new JButton("START");
//        start.addActionListener(new ButtonListener());
//        start.setBounds(0, 0,200, 80);
//        start.setBackground(new Color(0,0,51));
//        //start.setForeground(Color.BLACK);
//        //start.setOpaque(true);

        help = new JButton("HOW TO PLAY!");
        help.addActionListener(new HelpButton());
        help.setBounds(0, 120 , 200, 80);
        help.setBackground(new Color(0,0,51));
        help.setForeground(Color.WHITE);

        exit = new JButton("EXIT");
        exit.addActionListener(new CloseMenu());
        exit.setBounds(0, 240 , 200, 80);
        exit.setBackground(new Color(0,0,51));
        exit.setForeground(Color.WHITE);

        button.setVisible(true);
        button.setBounds(BUTTON_INIT_X, BOARD_HEIGHT/2, 600, 600);
        button.setLayout(null);
        button.setOpaque(false);

        button.add(play);
        button.add(help);
        button.add(exit);

        frame2.setTitle("Space Invaders");
        frame2.add(button);
        frame2.add(new Menu(), BorderLayout.CENTER);
        frame2.setLocationRelativeTo(null);
        frame2.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame2.setUndecorated(true);
        frame2.setAlwaysOnTop(true);
        frame2.setVisible(true);
        frame2.setResizable(false);
    }

    public void closeIntro() {
        frame2.dispose();
        frame3.dispose();
    }

    public void closeHelp() {
        frame3.dispose();
        new Main();
    }

    public void endMenu() {
        frame2.dispose();
    }
    public void closeMenu() {
        frame.dispose();
        frame2.dispose();
        frame3.dispose();
    }


    public static void main(String[] args) {
        //new GameFrame();
        new Main();
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            // Buat font baru untuk HUD
            try
            {
                GravityBold8 = Font.createFont(Font.TRUETYPE_FONT, new File("GravityBold8.ttf")).deriveFont(20f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("GravityBold8.ttf")));
            } catch(IOException | FontFormatException E)
            {

            }
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            frame.getContentPane().add(new GamePlay());
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setUndecorated(true);
            frame.setAlwaysOnTop(true);
            frame.setVisible(true);
            closeIntro();

        }
    }
    private class CloseMenu implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            closeMenu();
        }
    }
    private class CloseHelp implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            closeHelp();
        }
    }

    private class HelpButton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            endMenu();
            JButton close = new JButton("BACK TO MENU");
            close.addActionListener(new CloseHelp());
            //close.setBounds(BOARD_WIDTH/2, BOARD_HEIGHT - 150 ,200, 100);
            close.setBounds(0, 240 , 200, 80);
            close.setBackground(Color.WHITE);
            close.setForeground(Color.BLACK);
            close.setOpaque(true);

            JPanel button2 = new JPanel();
            button2.setVisible(true);
            button2.setBounds(BUTTON_INIT_X, BOARD_HEIGHT/2, 600, 600);
            button2.setLayout(null);
            button2.setOpaque(false);
            button2.add(close);

            frame3.add(button2);
            frame3.add(new Help(), BorderLayout.CENTER);
            frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame3.setExtendedState(Frame.MAXIMIZED_BOTH);
            frame3.setResizable(false);
            frame3.setLocationRelativeTo(null);
            frame3.setUndecorated(true);
            frame3.setAlwaysOnTop(true);
            frame3.setVisible(true);
        }
    }

}

//    External Source :
//
//    https://zetcode.com/javagames/spaceinvaders/
//
//    github : tatilattanzi/java-space-invaders
//
//    github : labibs30/FP_PBO_A