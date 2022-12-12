import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.border.EmptyBorder;

public class Help extends JPanel {
    private Image help;
    public Help(){
        var obj2= new ImageIcon("images/help.png");
        help = obj2.getImage();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g){
        g.drawImage(help, 0, 0, getWidth(), getHeight(), null);
        helpText(g);
        Toolkit.getDefaultToolkit().sync();
    }


    public void helpText(Graphics g){
        var small = new Font("GravityBold8", Font.BOLD, 25);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString("Move Forward-----UP_KEY", Interface.BOARD_WIDTH/2 - 175, Interface.BOARD_HEIGHT/2 - 100);
        g.drawString("Move Back------DOWN_KEY", Interface.BOARD_WIDTH/2 - 175, Interface.BOARD_HEIGHT/2 - 50);
        g.drawString("Move Right----RIGHT_KEY", Interface.BOARD_WIDTH/2 - 175, Interface.BOARD_HEIGHT/2 );
        g.drawString("Move Left------LEFT_KEY", Interface.BOARD_WIDTH/2 - 175, Interface.BOARD_HEIGHT/2 + 50);
        g.drawString("Player Shot-------SPACE", Interface.BOARD_WIDTH/2 - 175, Interface.BOARD_HEIGHT/2 + 100);
    }

}

