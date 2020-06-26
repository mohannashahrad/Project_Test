package graphics;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.ResourceBundle;

public class ShowImages extends Menu implements Initializable {

    @FXML ImageView image1;
    @FXML ImageView image2;
    @FXML ImageView image3;
    @FXML ImageView image4;
    @FXML ImageView image5;
    @FXML ImageView image6;
    @FXML ImageView image7;
    @FXML ImageView image8;
    @FXML ImageView image9;
    @FXML ImageView image10;

    public ShowImages(Menu previousMenu) {
        super(previousMenu, "src/main/java/graphics/fxml/ShowImages.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        image1.setImage(new Image("file:src/main/java/graphics/fxml/images/clothing.jpg"));
        image2.setImage(new Image("file:src/main/java/graphics/fxml/images/electronics.jpg"));
        image3.setImage(new Image("file:src/main/java/graphics/fxml/images/food.jpg"));
        image4.setImage(new Image("file:src/main/java/graphics/fxml/images/gardening.jpg"));
        image5.setImage(new Image("file:src/main/java/graphics/fxml/images/health.jpg"));
        image6.setImage(new Image("file:src/main/java/graphics/fxml/images/homeProducts.jpg"));
        image7.setImage(new Image("file:src/main/java/graphics/fxml/images/sports.jpg"));
        image8.setImage(new Image("file:src/main/java/graphics/fxml/images/toys.jpg"));
        image9.setImage(new Image("file:src/main/java/graphics/fxml/images/books.jpg"));
        image10.setImage(new Image("file:src/main/java/graphics/fxml/images/shoes.jpg"));
    }
}
