package graphics;

import controller.ProductManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerRequestsMenu extends Menu implements Initializable {

    ProductManager productManager = new ProductManager();

    public CustomerRequestsMenu(Menu previousMenu) {
        super(previousMenu, "src/main/java/graphics/fxml/CustomerRequestsMenu.fxml");
    }

    @FXML
    private void addComment(){
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Comment Product");
        dialog.setHeaderText(null);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField titleField = new TextField();
        titleField.setPromptText("Title");
        TextField commentField = new TextField();
        commentField.setPromptText("Content");
        TextField productIdField = new TextField();
        productIdField.setPromptText("Product Id");
        VBox content = new VBox();
        content.setAlignment(Pos.CENTER_LEFT);
        content.setSpacing(10);
        content.getChildren().addAll(new Label("Type the product's id :"),productIdField);
        content.getChildren().addAll(new Label("Type your comment :"),titleField,commentField);
        dialog.getDialogPane().setContent(content);
        dialog.showAndWait();
        try {
            String title = titleField.getText();
            String body = commentField.getText();
            productManager.addComment(Integer.parseInt(productIdField.getText()),title,body);
        } catch (Exception e) {
            showError(e.getMessage(), 200);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
