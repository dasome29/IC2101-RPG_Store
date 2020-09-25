import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class GUI {
    private Pane root;
    private Label label;
    private StackPane buyPane = new StackPane();
    private StackPane rightPane = new StackPane();
    private StackPane inventoryPane = new StackPane();
    private ScrollPane leftScrollPane = new ScrollPane(buyPane);
    private Button newItemButton = new Button("New Item");
    private ArrayList<Item> items = new ArrayList<>();
    private MenuBar menuBar;
    public GUI(Pane root) {
        this.root = root;
        label = new Label("HOLAAAAA");
        buyPane.setAlignment(Pos.TOP_LEFT);
        rightPane.setAlignment(Pos.TOP_LEFT);
        newItemButton.setOnMouseClicked(mouseEvent -> {
            System.out.println("Adding an Item.");
            try {
                addItem();
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        });
        menuBar = new MenuBar(this.root, buyPane, inventoryPane, leftScrollPane);
    }

    private void configurePane(Pane pane, ScrollPane scrollPane, int x, int y) {

        if (scrollPane != null) {
            pane.setPrefSize(400, 800);
            scrollPane.setPrefSize(400, 600);
            scrollPane.setLayoutX(x);
            scrollPane.setLayoutY(y);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scrollPane.setVmax(2);
            scrollPane.setStyle("-fx-border-width: 3;-fx-border-radius: 4;");
            pane.setStyle("-fx-background-color: transparent;");
        } else {
            pane.setPrefSize(400, 600);
            pane.setTranslateX(x);
            pane.setTranslateY(y);
            pane.setStyle("-fx-background-color: rgba(0,100,100,0.5);-fx-border-color: lightgrey;-fx-border-width: 3;");
        }


    }

    private void addItem() throws UnirestException {
//        HttpResponse<JsonNode> response = Unirest.get("https://amazon-product-reviews-keywords.p.rapidapi.com/product/details?country=US&asin=B07ZPKR714")
//                .header("x-rapidapi-host", "amazon-product-reviews-keywords.p.rapidapi.com")
//                .header("x-rapidapi-key", "203c5afef0msh42f0cc1f3c0f465p1048b2jsn6e07fdc34ead")
//                .asJson();
//        String json = response.getBody().toString();
//        System.out.println(json);
        Item rectangle = new Item(buyPane);
        if (items.size() == 0) {
            rectangle.setY(20);
        } else {
            rectangle.setY(items.get(items.size() - 1).getY() + 70);
        }
        rectangle.setX(20);
        items.add(rectangle);
    }

    public void display() {

        configurePane(buyPane, leftScrollPane, 0, 70);
        configurePane(inventoryPane, leftScrollPane, 0, 70);
        configurePane(rightPane, null, 400, 0);
        newItemButton.setTranslateX(160);
        newItemButton.setTranslateY(50);
        rightPane.getChildren().addAll(newItemButton);

        root.getChildren().addAll(leftScrollPane, rightPane);
    }
}

class MenuBar {
    private Pane root;
    private StackPane menuBar;
    private StackPane buyPane;
    private StackPane inventoryPane;
    private Button buyButton;
    private Button inventoryButton;

    public MenuBar(Pane root, StackPane buyPane, StackPane inventoryPane, ScrollPane scrollPane) {
        this.root = root;
        this.buyPane = buyPane;
        this.inventoryPane = inventoryPane;
        menuBar = new StackPane();
        menuBar.setAlignment(Pos.TOP_LEFT);
        menuBar.setTranslateX(0);
        menuBar.setTranslateY(0);
        buyButton = new Button("Buy");
        inventoryButton = new Button("Inventory");
        buyButton.setTranslateX(80);
        buyButton.setTranslateY(15);
        inventoryButton.setTranslateX(230);
        inventoryButton.setTranslateY(15);
        Glow glow = new Glow(15);
        menuBar.getChildren().addAll(buyButton, inventoryButton);

        buyButton.setOnMouseClicked(event -> {
            scrollPane.setContent(buyPane);
        });
        buyButton.setOnMouseEntered(event -> {
            buyButton.setEffect(glow);
        });
        buyButton.setOnMouseExited(event -> {
            buyButton.setEffect(null);
        });
        inventoryButton.setOnMouseClicked(event -> {
            scrollPane.setContent(inventoryPane);
        });
        inventoryButton.setOnMouseEntered(event -> {
            inventoryButton.setEffect(glow);
        });
        inventoryButton.setOnMouseExited(event -> {
            inventoryButton.setEffect(null);
        });
        menuBar.setStyle("-fx-background-color: rgba(0,100,100,0.5);-fx-border-color: lightgrey;-fx-border-width: 3;");
        menuBar.setPrefSize(400, 70);
        buyButton.setStyle("-fx-text-fill: goldenrod; -fx-font: italic 20 \"serif\"; -fx-background-color: transparent; -fx-text-alignment: right; -fx-alignment: center;-fx-border-color: lightgrey;-fx-border-width: 1;");
        inventoryButton.setStyle("-fx-text-fill: goldenrod; -fx-font: italic 20 \"serif\"; -fx-background-color: transparent; -fx-text-alignment: right; -fx-alignment: center;-fx-border-color: lightgrey;-fx-border-width: 1;");
        root.getChildren().addAll(menuBar);
    }

}
