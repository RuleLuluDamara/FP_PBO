import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame{
    public GameFrame(){
        JFrame frame = new JFrame("Divoc Ivaders");
        frame.add(new GamePlay(), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}