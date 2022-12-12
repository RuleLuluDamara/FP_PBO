import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class GamePlay extends JPanel{
    private String data = "";
    private Image background;
    private Image bgGameOver;
    private List<Virus> viruses;
    private Player player;
    private ShotSinovac shotSinovac;
    private int direction = 1; //arah dan kecepatan alien
    private int deaths = 0;
    private int score = 0;
    private boolean inGame = true;
    private boolean inGameEnd = false;
    private String explode = "images/explode.png";
    private String message = "images/gameover.png";
    private Timer timer;

    public GamePlay() {
        initGamePlay();
    }
    private void initGamePlay(){
        addKeyListener(new Control());
        setFocusable(true);

        ImageIcon obj = new ImageIcon("images/sky.png");
        background = obj.getImage();

        timer = new Timer(Interface.DELAY, new GameCycle());
        timer.start();

        gameInit();
    }

    private void gameInit() {

        viruses = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                var virus = new Virus(Interface.VIRUS_INIT_X + 70 * j,
                        Interface.VIRUS_INIT_Y + 70 * i);
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

        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        if (inGame) {
//            g.setColor(new Color(78, 43, 0));
//            g.fillRect(0, Interface.GROUND,
//                    Interface.BOARD_WIDTH, Interface.GROUND);
//            g.setColor(new Color(78, 43, 0));
//            g.drawRect(0, Interface.GROUND,
//                    Interface.BOARD_WIDTH, Interface.GROUND);
//            g.drawLine(0, Interface.GROUND,
//                    Interface.BOARD_WIDTH, Interface.GROUND
            drawViruses(g);
            drawPlayer(g);
            drawShot(g);
            drawBombing(g);

        } else {
            if (timer.isRunning()) {
                timer.stop();
            }
            gameOver(g);
            saveScore();
        }
        Toolkit.getDefaultToolkit().sync();
    }

    private void gameOver(Graphics g){

        ImageIcon obj = new ImageIcon(message);
        bgGameOver = obj.getImage();

        g.drawImage(bgGameOver, 0, 0 , getWidth(), getHeight(), null);

        var small = new Font("GravityBold8", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString("SCORE", (Interface.BOARD_WIDTH/2 - 50),
                Interface.BOARD_HEIGHT / 2 + 60);
        g.drawString(String.valueOf(score), (Interface.BOARD_WIDTH/2 + 50),
                Interface.BOARD_HEIGHT / 2 + 60);
    }

    private void update() {

        if (deaths == Interface.NUMBER_OF_VIRUS) {
            inGame = false;
            timer.stop();
            message = "images/youwin.png";
        }

        // player
        player.act();

        // shot
        if (shotSinovac.isVisible()) {
            int shotX = shotSinovac.getX();
            int shotY = shotSinovac.getY();

            for (Virus virus : viruses) {
                int virusX = virus.getX();
                int virusY = virus.getY();

                if (virus.isVisible() && shotSinovac.isVisible()) {
                    if (shotX >= (virusX)
                            && shotX <= (virusX + Interface.VIRUS_WIDTH)
                            && shotY >= (virusY)
                            && shotY <= (virusY + Interface.VIRUS_HEIGTH)) {

                        var ii = new ImageIcon(explode);
                        virus.setImage(ii.getImage());
                        virus.setDying(true);
                        deaths++;
                        score += 8;
                        shotSinovac.die();
                    }
                }
            }

            int y = shotSinovac.getY();
            y -= 20;

            if (y < 0) {
                shotSinovac.die();
            } else {
                shotSinovac.setY(y);
            }
        }

        // virus

        for (Virus virus : viruses) {

            int x = virus.getX();

            if (x >= Interface.BOARD_WIDTH - Interface.BORDER_RIGHT - 30 && direction != -1) {

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
                if (y > Interface.GROUND - Interface.VIRUS_HEIGTH) {
                    inGame = false;
                    message = "images/pandemic.png";
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
//            else if (e.getKeyCode() == KeyEvent.VK_ESCAPE && escapeCounter < 1) {
//                inGame = false;
//                keyStat = 1;
//            } else if (inGame && e.getKeyCode() == KeyEvent.VK_ENTER) {
//                inGame = true;
//                gameReset();
//                inGame = true;
//                gameReset();
//                inGame = true;
//                keyStat = 1;
//            }
        }
    }

    public void saveScore() {
        //String data = Integer.parseInt(score);

        data =""+ score;
        File scoreDat = new File("scoreDat.txt");

        if(!scoreDat.exists()) {
            try {
                scoreDat.createNewFile();
            }catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        FileWriter writeFile = null;
        BufferedWriter writer = null;
        try {
            writeFile = new FileWriter(scoreDat);
            writer = new BufferedWriter(writeFile);
            writer.write(this.data);
        }catch(Exception e) {
            //errors
        }
        finally {
            try {
                if(writer != null){
                    writer.close();
                }
            }catch(Exception e) {

            }
        }
    }
}

//for (type variableName : arrayName) {
//        // code block to be executed
//        }