# FP_PBO

FINAL PROJECT OBJECT ORIENTED PROGRAMMING

Nama : Rule Lulu Damara

Kelas : PBO B

NRP : 5025211050

Final project saya akan membuat suatu game yang didasari oleh bahasa pemrograman Java dengan menggunakan Graphical User Interface yaitu bernama Divoc Invaders. Divoc Invaders merupakan sebuah game yang menceritakan Inject yaitu sebuah karakter utama yang dikontrol oleh user nantinya untuk melawan virus, bakteri, dan jamur. Virus, bakteri, dan jamur tersebut merepresentasikan penyakit yang ada dari zaman dulu sampai saat ini seperti flu, covid, dll. User akan bermain disuatu stage dimana Inject dapat menembak virus, bakteri, dan jamur yang masuk ke dalam tubuh. User mengontrol satu karakter Inject yang dapat bergerak ke-4 arah yaitu kanan, kiri, atas, dan bawah. User akan kalah jika virus, bakteri, dan jamur berhasil menabrakkan diri kepada Inject sampai dengan sehingga imun dari tubuh rusak yang Menyebabkan Game over. Pada game ini akan digunakan java.awt dan javax.swing dalam proses pembuatannya.

Terdapat beberapa aspek OOP yang saya buat sendiri, ini merupakan salah satu contoh dari setiap aspek OOP yang saya terapkan

1. Casting/Conversion
ex :

     g.drawString(String.valueOf(score), (Interface.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2 + 100,
                Interface.BOARD_HEIGHT / 2 + 50);

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

  public Menu(){
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
Ex :

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
     
    public void die() {
        visible = false;
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

External Source :

- https://zetcode.com/javagames/spaceinvaders/

