import javax.swing.ImageIcon;

public class ShotSinovac extends abstractSprite {

    private int width;
        public ShotSinovac() {
    }

    public ShotSinovac(int x, int y) {
        initShot(x, y);
    }
    private void initShot(int x, int y) {

        var shotImg = "images/sinovac.png";
        var ii = new ImageIcon(shotImg);

        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());

        int H_SPACE = width + 15;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);
    }
}