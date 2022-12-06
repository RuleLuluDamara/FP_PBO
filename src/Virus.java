import javax.swing.ImageIcon;

public class Virus extends Abstract {

    private virusShot bomb;//Alien menjatuhkan bomb
    //constructor
    public Virus(int x, int y) {
        initVirus(x, y);
    }
    //method untuk Alien
    private void initVirus(int x, int y) {
        this.x = x;
        this.y = y;

        bomb = new virusShot(x, y);//alien mengeluarkan bomb
        var virusImg = "images/Virus.png";
        var ii = new ImageIcon(virusImg);

        setImage(ii.getImage());
    }

    public void act(int direction) {
        this.x += direction;
    }

    public virusShot getBomb() {
        return bomb;
    }

    public class virusShot extends Abstract {
        private boolean destroyed;
        public virusShot(int x, int y) {
            initBomb(x, y);
        }
        private void initBomb(int x, int y) {
            setDestroyed(true);
            this.x = x;
            this.y = y;

            var bombImg = "images/virusShot.png";
            var ii = new ImageIcon(bombImg);
            setImage(ii.getImage());
        }
        public void setDestroyed(boolean destroyed) {
            this.destroyed = destroyed;
        }
        public boolean isDestroyed() {
            return destroyed;
        }
    }
}