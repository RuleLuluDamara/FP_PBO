import java.awt.*;

public interface Interface {

    //int BOARD_WIDTH = 358;
    int BOARD_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    //int BOARD_HEIGHT = 350;
    int BOARD_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    int BORDER_RIGHT = 30;
    int BORDER_LEFT = 5;
    int GROUND = BOARD_HEIGHT - 100;
    //int GROUND = 290;
    int BOMB_HEIGHT = 40;

    int ALIEN_HEIGHT = 60;
    int ALIEN_WIDTH = 60;
    int ALIEN_INIT_X = 150; //jarak antar alien
    int ALIEN_INIT_Y = 20;


    int GO_DOWN = 15;
    int NUMBER_OF_VIRUS = 28;
    int CHANCE = 5;
    int DELAY = 17;
    int PLAYER_WIDTH = 90;
    int PLAYER_HEIGHT = 90;
}