import java.awt.*;

public interface Interface {

    //int BOARD_WIDTH = 358;
    int BOARD_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    //int BOARD_HEIGHT = 350;
    int BOARD_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    int BORDER_RIGHT = 30;
    int BORDER_LEFT = 5;
    int GROUND = BOARD_HEIGHT - 200;
    //int GROUND = 290;
    int BOMB_HEIGHT = 40;


    //Alien
    int VIRUS_HEIGTH = 60;
    int VIRUS_WIDTH = 60;
    int VIRUS_INIT_X = BOARD_WIDTH/2 - Interface.PLAYER_WIDTH*2; //koordinat awal virus
    int VIRUS_INIT_Y = 20;

    int GO_DOWN = 30;
    int NUMBER_OF_VIRUS = 28;
    int CHANCE = 5;
    int DELAY = 17;
    int PLAYER_WIDTH = 90;
    int PLAYER_HEIGHT = 90;

    int BUTTON_WIDTH = 250;
    int BUTTON_HEIGHT = 50;
    int BUTTON_INIT_X = BOARD_WIDTH/2 - 125 ;
    int START_INIT_Y = BOARD_HEIGHT/2;
    int SETTING_BUTTON_Y = START_INIT_Y +  70;
    int EXIT_BUTTON_Y = SETTING_BUTTON_Y + 70;
    int BACK_BUTTON_Y = BOARD_HEIGHT - 100;
}