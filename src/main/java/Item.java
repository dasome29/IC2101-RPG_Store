import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;

public class Item {
    private StackPane pane;
    private int x;
    private int y;
    private Rectangle rectangle;
    private ImageView itemImage;
    private ImageView infoImage;
    private StatsPreview stats;

    public Item(StackPane pane) {
        this.pane = pane;
        rectangle = new Rectangle(350.0D, 60.0D);
        rectangle.setArcHeight(60);
        rectangle.setArcWidth(40);
        rectangle.setFill(javafx.scene.paint.Color.rgb(126, 217, 244));
        rectangle.setStrokeWidth(3);
        rectangle.setStroke(Color.BLACK);
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
                } else if (type.getButtonData() == ButtonBar.ButtonData.NO) {
                    System.out.println("You pressed No");
                }
            });
        });
        URL itemImagePath = getClass().getResource("A_Armor04.png");
        itemImage = new ImageView(itemImagePath.toString());
        infoImage = new ImageView(getClass().getResource("info.png").toString());
        imageSettings();
        this.pane.getChildren().addAll(rectangle, itemImage, infoImage);

    }

    public void setX(int x) {
        this.x = x;
        rectangle.setTranslateX(this.x);
        itemImage.setTranslateX(this.x + 30);
        infoImage.setTranslateX(this.x + 290);
        stats.setX(this.x + 290);
    }

    public void setY(int y) {
        this.y = y;
        rectangle.setTranslateY(this.y);
        itemImage.setTranslateY(this.y + 5);
        infoImage.setTranslateY(this.y + 5);
        stats.setY(this.y + 5);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void imageSettings() {
        infoImage.setFitHeight(50);
        infoImage.setFitWidth(50);
        itemImage.setFitHeight(50);
        itemImage.setFitWidth(50);
        stats = new StatsPreview(pane);
        infoImage.setOnMouseEntered(event -> {
            stats.show();
        });
        infoImage.setOnMouseExited(event -> {
            stats.hide();
        });
    }
}

class StatsPreview {
    private StackPane pane;
    private ImageView dialogImage;
    private Label label = new Label("Stats Here");
    private int x;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        dialogImage.setTranslateX(this.x - 180);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        dialogImage.setTranslateY(this.y + 20);
    }

    private int y;

    public StatsPreview(StackPane pane) {
        this.pane = pane;
        dialogImage = new ImageView(getClass().getResource("dialog.png").toString());
        dialogImage.setScaleX(-1);
        dialogImage.setRotate(180);
        dialogImage.setFitWidth(150);
        dialogImage.setFitHeight(100);
        dialogImage.setVisible(false);

        this.pane.getChildren().addAll(dialogImage);
    }

    public void show() {
        dialogImage.setVisible(true);
        dialogImage.toFront();
    }

    public void hide() {
        dialogImage.setVisible(false);
    }
}
