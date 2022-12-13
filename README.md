# FP_PBO

FINAL PROJECT OBJECT ORIENTED PROGRAMMING

Nama : Rule Lulu Damara

Kelas : PBO B

NRP : 5025211050

Final project saya akan membuat suatu game yang didasari oleh bahasa pemrograman Java dengan menggunakan Graphical User Interface yaitu bernama Divoc Invaders. Divoc Invaders merupakan sebuah game yang menceritakan Inject yaitu sebuah karakter utama yang dikontrol oleh user nantinya untuk melawan virus, bakteri, dan jamur. Virus, bakteri, dan jamur tersebut merepresentasikan penyakit yang ada dari zaman dulu sampai saat ini seperti flu, covid, dll. User akan bermain disuatu stage dimana Inject dapat menembak virus, bakteri, dan jamur yang masuk ke dalam tubuh. User mengontrol satu karakter Inject yang dapat bergerak ke-4 arah yaitu kanan, kiri, atas, dan bawah. User akan kalah jika virus, bakteri, dan jamur berhasil menabrakkan diri kepada Inject sampai dengan sehingga imun dari tubuh rusak yang Menyebabkan Game over. Pada game ini akan digunakan java.awt dan javax.swing dalam proses pembuatannya.

Terdapat beberapa aspek OOP yang saya buat sendiri, ini merupakan salah satu contoh dari setiap aspek OOP yang saya terapkan

1. Casting/Conversion
ex :   

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString("SCORE", (Interface.BOARD_WIDTH/2 - 50),
                Interface.BOARD_HEIGHT / 2 + 60);
        g.drawString(String.valueOf(score), (Interface.BOARD_WIDTH/2 + 50),
                Interface.BOARD_HEIGHT / 2 + 60);
                

2. Encapsulation
ex : 

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    

3. Contructor 
ex :


            public Menu() {
            
                initMenu();
                
            }

            private void initMenu(){
            
                ImageIcon obj1 = new ImageIcon("images/bgMenu.png");
               
                bgMenu = obj1.getImage();
                
            }
    
    
    
4. Input-Output
ex :

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


5. Exception Handling
ex :

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



6. Abstract
ex : 

        import java.awt.Image;

        //Implement a generic Abtract Sprite
        public abstract class abstractSprite {
            private boolean visible;
            private Image image;
            private boolean dying;

            int x;
            int y;
            int dx;
            int dy;

            int width;

            public abstractSprite() {
                visible = true;
            }

            public void die() {
                visible = false;
            }

            public boolean isVisible() {
                return visible;
            }

            protected void setVisible(boolean visible) {
                this.visible = visible;
            }

            public void setImage(Image image) {
                this.image = image;
            }

            public Image getImage() {
                return image;
            }
            public int getX() {
                return x;
            }
            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }
            public void setY(int y) {
                this.y = y;
            }

            public void setDying(boolean dying) {
                this.dying = dying;
            }

            public boolean isDying() {
                return this.dying;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

        }


7. OverRidding
ex :


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



8. Array List
ex :

    public class GamePlay extends JPanel{
    
            private String data;
            
            private Image background;
            
            private Image bgGameOver;
            
            private ArrayList<Virus> viruses;
            
            private Player player;



9.  Inheritance
ex :

    public class Player extends abstractSprite {
            private int width, height;
            public Player() {
                initPlayer();
    }
    private void initPlayer() {
        var playerImg = "images/player.png";
        var ii = new ImageIcon(playerImg);

        width = ii.getImage().getWidth(null);
        height = ii.getImage().getHeight(null);
        setImage(ii.getImage());

        //int START_X = screenWidth/2 - (width/2);
        int START_X = Interface.BOARD_WIDTH/2;
        setX(START_X);

        //int START_Y = screenHeight;
        int START_Y = Interface.BOARD_HEIGHT - 10 ;
        setY(START_Y);
    }
    

10. Collection
ex : 

    public class GamePlay extends JPanel{
    
    private String data;
    
    private Image background;
    
    private Image bgGameOver;
    
    private ArrayList<Virus> viruses;
    
    private Player player;

11. Interface
ex :


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



12. Overloading
ex :


    public void act(int direction) {
            this.x += direction;
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


13. Polymorphism
ex :


    public void act(int direction) {
            this.x += direction;
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
        
        
        
14. Generics
ex :

        import java.util.Comparator;

        public class Scoreboard<T, U>{
            T score;
            U nama;

            public Scoreboard(T score, U nama){
                this.score = score;
                this.nama = nama;
            }

            public T getScore(){
                return score;
            }

            public U getName(){
                return nama;
            }

        }
        
15. GUI
        
![Menu](https://user-images.githubusercontent.com/105763198/207241610-143de635-07af-4a1c-b6a9-844b96edefc1.png)


![GamePlay](https://user-images.githubusercontent.com/105763198/207241639-49ba0262-4825-4ede-be1a-0bccd8614627.png)

External Source :

- https://zetcode.com/javagames/spaceinvaders/

