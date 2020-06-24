package graphics;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Product;

public class ProductCell extends Rectangle {

    private Image image;

    public ProductCell(double x, double y, double w, double h, String imagePath) {
        super(w,h);
        setVisible(true);
        setTranslateX(x);
        setTranslateY(y);
        setImage(imagePath);
        this.setFill(new ImagePattern(image));
    }

    private void setImage(String imagePath) {
        Image image = new Image(imagePath) ;
        this.image = image;
    }
}
