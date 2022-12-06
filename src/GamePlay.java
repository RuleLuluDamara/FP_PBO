import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GamePlay extends JPanel {
    private Image background;
    private List<Virus> viruses;
    private Player player;
    private ShotSinovac shotSinovac;
    private int direction = -1; //arah dan kecepatan alien
    private int deaths = 0;

    private boolean inGame = true;
    private String explode = "images/explode.png";
    private String message = "Game Over";
    private Timer timer;

    public GamePlay() {
        initBoard();
        gameInit();
    }

    private void initBoard() {
        addKeyListener(new Control());
        setFocusable(true);

        ImageIcon obj = new ImageIcon("images/sky.jpg");
        background = obj.getImage();

        timer = new Timer(Interface.DELAY, new GameCycle());
        timer.start();

        gameInit();
    }

    private void gameInit() {

        viruses = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {

                var virus = new Virus(Interface.ALIEN_INIT_X + 50 * j,
                        Interface.ALIEN_INIT_Y + 50 * i);
                viruses.add(virus);
            }
        }
        player = new Player();
        shotSinovac = new ShotSinovac();
    }

    private void drawViruses(Graphics g) {

        for (Virus virus : viruses) {

            if (virus.isVisible()) {
                g.drawImage(virus.getImage(), virus.getX(), virus.getY(), this);
            }
            if (virus.isDying()) {
                virus.die();
            }
        }
    }

    private void drawPlayer(Graphics g) {

        if (player.isVisible()) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }
        if (player.isDying()) {
            player.die();
            inGame = false;
        }
    }

    private void drawShot(Graphics g) {

        if (shotSinovac.isVisible()) {
            g.drawImage(shotSinovac.getImage(), shotSinovac.getX(), shotSinovac.getY(), this);
        }
    }

    private void drawBombing(Graphics g) {

        for (Virus a : viruses) {
            Virus.virusShot b = a.getBomb();
            if (!b.isDestroyed()) {
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        //g.setColor(Color.black);
        g.setColor(Color.green);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        if (inGame) {
            g.drawLine(0, Interface.GROUND,
                    Interface.BOARD_WIDTH, Interface.GROUND);

            drawViruses(g);
            drawPlayer(g);
            drawShot(g);
            drawBombing(g);

        } else {
            if (timer.isRunning()) {
                timer.stop();
            }
            gameOver(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void gameOver(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, Interface.BOARD_WIDTH, Interface.BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, Interface.BOARD_WIDTH / 2 - 30, Interface.BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, Interface.BOARD_WIDTH / 2 - 30, Interface.BOARD_WIDTH - 100, 50);

        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (Interface.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2,
                Interface.BOARD_WIDTH / 2);
    }

    private void update() {

        if (deaths == Interface.NUMBER_OF_VIRUS) {
            inGame = false;
            timer.stop();
            message = "Game won!";
        }

        // player
        player.act();

        // shot
        if (shotSinovac.isVisible()) {
            int shotX = shotSinovac.getX();
            int shotY = shotSinovac.getY();

            for (Virus virus : viruses) {
                int alienX = virus.getX();
                int alienY = virus.getY();

                if (virus.isVisible() && shotSinovac.isVisible()) {
                    if (shotX >= (alienX)
                            && shotX <= (alienX + Interface.ALIEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + Interface.ALIEN_HEIGHT)) {

                        var ii = new ImageIcon(explode);
                        virus.setImage(ii.getImage());
                        virus.setDying(true);
                        deaths++;
                        shotSinovac.die();
                    }
                }
            }

            int y = shotSinovac.getY();
            y -= 10;

            if (y < 0) {
                shotSinovac.die();
            } else {
                shotSinovac.setY(y);
            }
        }

        // aliens

        for (Virus virus : viruses) {

            int x = virus.getX();

            if (x >= Interface.BOARD_WIDTH - Interface.BORDER_RIGHT && direction != -1) {

                direction = -1;

                Iterator<Virus> i1 = viruses.iterator();

                while (i1.hasNext()) {
                    Virus a2 = i1.next();
                    a2.setY(a2.getY() + Interface.GO_DOWN);
                }
            }

            if (x <= Interface.BORDER_LEFT && direction != 1) {

                direction = 1;
                Iterator<Virus> i2 = viruses.iterator();

                while (i2.hasNext()) {
                    Virus a = i2.next();
                    a.setY(a.getY() + Interface.GO_DOWN);
                }
            }
        }

        Iterator<Virus> it = viruses.iterator();

        while (it.hasNext()) {
            Virus virus = it.next();

            if (virus.isVisible()) {
                int y = virus.getY();
                if (y > Interface.GROUND - Interface.ALIEN_HEIGHT) {
                    inGame = false;
                    message = "Invasion!";
                }
                virus.act(direction);
            }
        }

        // bombs
        var generator = new Random();

        for (Virus virus : viruses) {
            int shot = generator.nextInt(15);
            Virus.virusShot virusShot = virus.getBomb();

            if (shot == Interface.CHANCE && virus.isVisible() && virusShot.isDestroyed()) {
                virusShot.setDestroyed(false);
                virusShot.setX(virus.getX());
                virusShot.setY(virus.getY());
            }

            int virusX = virusShot.getX();
            int virusY = virusShot.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            if (player.isVisible() && !virusShot.isDestroyed()) {

                if (virusX >= (playerX)
                        && virusX <= (playerX + Interface.PLAYER_WIDTH)
                        && virusY >= (playerY)
                        && virusY <= (playerY + Interface.PLAYER_HEIGHT)) {

                    var ii = new ImageIcon(explode);
                    player.setImage(ii.getImage());
                    player.setDying(true);
                    virusShot.setDestroyed(true);
                }
            }

            if (!virusShot.isDestroyed()) {

                virusShot.setY(virusShot.getY() + 1);
                if (virusShot.getY() >= Interface.GROUND - Interface.BOMB_HEIGHT) {
                    virusShot.setDestroyed(true);
                }
            }
        }
    }

    private void doGameCycle() {
        update();
        repaint();
    }

    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }

    private class Control extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            player.keyPressed(e);
            int x = player.getX();
            int y = player.getY();

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {
                if (inGame) {
                    if (!shotSinovac.isVisible()) {
                        shotSinovac = new ShotSinovac(x, y);
                    }
                }
            }
        }
    }
}