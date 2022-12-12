import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel{
    private Image bgMenu;

    public Menu(){
        initMenu();
    }

    private void initMenu(){
        ImageIcon obj1 = new ImageIcon("images/bgMenu.png");
        bgMenu = obj1.getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    private void doDrawing(Graphics g) {
        g.drawImage(bgMenu, 0, 0, getWidth(), getHeight(), null);
        Toolkit.getDefaultToolkit().sync();
    }


}