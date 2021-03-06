import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.HashMap;

public class Item {
    protected StackPane pane;
    private int x;
    private int y;
    protected Rectangle rectangle;
    protected ImageView itemImage;
    protected ImageView infoImage;
    private StatsPreview stats;
    protected Text name;
    public Text number;
    private Inventory inventory;
    private HashMap<String, Double> info;

    public Item(StackPane pane, String name, String number) {
        this.pane = pane;

        this.rectangle = new Rectangle(350.0D, 60.0D);
        this.name = new Text(name);
        this.number = new Text(number);
        this.name.setStyle("-fx-background-color: transparent;");
        this.rectangle.setArcHeight(60);
        this.rectangle.setArcWidth(40);
        this.rectangle.setFill(javafx.scene.paint.Color.rgb(126, 217, 244));
        this.rectangle.setStrokeWidth(3);
        this.rectangle.setStroke(Color.BLACK);
        this.itemImage = new ImageView(getClass().getResource(name + ".png").toString());
        this.infoImage = new ImageView(getClass().getResource("info.png").toString());
        imageSettings();
        setAlert();
        this.pane.getChildren().addAll(rectangle, itemImage, infoImage, this.name, this.number);

    }

    public void setX(int x) {
        this.x = x;
        rectangle.setTranslateX(this.x);
        itemImage.setTranslateX(this.x + 30);
        infoImage.setTranslateX(this.x + 290);
        stats.setX(this.x + 290);
        name.setTranslateX(this.x + 100);
        number.setTranslateX(this.x + 200);
    }

    public void setY(int y) {
        this.y = y;
        rectangle.setTranslateY(this.y);
        itemImage.setTranslateY(this.y + 5);
        infoImage.setTranslateY(this.y + 5);
        stats.setY(this.y + 5);
        name.setTranslateY(this.y + 20);
        number.setTranslateY(this.y + 20);
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
        stats.setInventory(this.inventory);
    }

    public void setInfo(HashMap<String, Double> info) {
        this.info = info;
        stats.setInfo(this.info);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name.getText();
    }

    private void imageSettings() {
        infoImage.setFitHeight(50);
        infoImage.setFitWidth(50);
        itemImage.setFitHeight(50);
        itemImage.setFitWidth(50);
        stats = new StatsPreview();
        infoImage.setOnMouseEntered(event -> {
            stats.show();
        });
        infoImage.setOnMouseExited(event -> {
            stats.hide();
        });
    }

    protected void setAlert() {
        rectangle.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Buying Item...");
            alert.setContentText("Do you want to buy this Item?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);

            alert.getButtonTypes().setAll(yesButton, noButton);
            alert.showAndWait().ifPresent(type -> {
                if (type.getButtonData() == ButtonBar.ButtonData.YES) {
                    System.out.println("You pressed Yes");
                    if (inventory != null) {
                        if (inventory.player.money - Double.parseDouble(number.getText()) > 0) {
                            inventory.addItem(name.getText(), number.getText(), info);
                            inventory.player.changeMoney(-Double.parseDouble(number.getText()));
                        }

                    }
                } else if (type.getButtonData() == ButtonBar.ButtonData.NO) {
                    System.out.println("You pressed No");
                }
            });
        });
    }
}

class StatsPreview {
    private Inventory inventory;
    private HashMap<String, Double> info;
    private int x;

    public StatsPreview() {
    }

    public void setInfo(HashMap<String, Double> info) {
        this.info = info;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
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

    private int y;

    public void show() {
        inventory.player.stats.showPreviews(info);
    }

    public void hide() {
        inventory.player.stats.hidePreviews();
    }
}
