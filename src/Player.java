import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class Player extends Abstract {

    private int width, height;

    public Player() {
        initPlayer();
    }
    private void initPlayer() {

        var playerImg = "images/player31.png";
        var ii = new ImageIcon(playerImg);

        width = ii.getImage().getWidth(null);
        height = ii.getImage().getHeight(null);

        setImage(ii.getImage());

        //int START_X = screenWidth/2 - (width/2);
        int START_X = Interface.BOARD_WIDTH/2;
        setX(START_X);

        //int START_Y = screenHeight;
        int START_Y = Interface.GROUND;
        setY(START_Y);
    }

    public void act() {
        x += dx;
        y += dy;

        if (x <= 0) {
            x = 0;
        } //set boarder kiri

        if (y <= Interface.BOARD_HEIGHT/2 - 200) {
            y = Interface.BOARD_HEIGHT/2 - 200;
        } //set boarder atas

        if (x >= Interface.BOARD_WIDTH -  width - 5) {
            x = Interface.BOARD_WIDTH -  width - 5;
        } //set boarder kanan

        if (y >= Interface.BOARD_HEIGHT - 2 * height - 20) {
            y = Interface.BOARD_HEIGHT - 2 * height - 20;
        } //set boarder bawah
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -10;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 10;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -10;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 10;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}