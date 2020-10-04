import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;


public class GUI {
    private Pane root;
    private Label label;
    private StackPane buyPane = new StackPane();
    private StackPane rightPane = new StackPane();
    private StackPane inventoryPane = new StackPane();
    private ScrollPane leftScrollPane = new ScrollPane(buyPane);
    private ArrayList<Item> items = new ArrayList<>();
    private MenuBar menuBar;
    private Inventory inventory;

    public GUI(Pane root) {
        this.root = root;
        label = new Label("HOLAAAAA");
        buyPane.setAlignment(Pos.TOP_LEFT);
        rightPane.setAlignment(Pos.TOP_LEFT);
        inventoryPane.setAlignment(Pos.TOP_LEFT);
        inventory = new Inventory(inventoryPane);


        menuBar = new MenuBar(this.root, buyPane, inventoryPane, leftScrollPane);
        itemGenerator();
    }

    private void itemGenerator(){
        HashMap<String, Integer> names = new HashMap<>();

        names.put("Chest", 400);
        names.put("Boots", 200);
        names.put("Speed Potion", 84);
        names.put("Health Potion", 45);
        names.put("Sword", 67);
        names.put("Apple", 23);

        for (String s :
                names.keySet()) {
            addItem(s, names.get(s));
        }
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

    private JsonNode getResponse(String key) {
        HttpResponse<JsonNode> http = null;
        try {
            http = Unirest.get("https://amazon-product-reviews-keywords.p.rapidapi.com/product/details?country=US&asin=" + key)
                    .header("x-rapidapi-host", "amazon-product-reviews-keywords.p.rapidapi.com")
                    .header("x-rapidapi-key", "203c5afef0msh42f0cc1f3c0f465p1048b2jsn6e07fdc34ead")
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return http.getBody();
    }

    public JSONObject getInfo() {
//        JsonNode response = getResponse("B07Y5W29JN");
        JSONObject object = null;
        JSONObject product;
        FileReader file;
        try {
            String content = new String(Files.readAllBytes(Paths.get("data.json")), StandardCharsets.UTF_8);
            object = new JSONObject(content);
//            object = response.getObject();
            product = object.getJSONObject("product");
            System.out.println(product.get("asin"));
            if (product.get("asin").equals("B07Y5W29JN")) {
                // Sube proteccioón
                int protection = (int) product.get("ranking") / 2;
                System.out.println("Es una cuchara");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    private void addItem(String name, int price) {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("health", 30);
        map.put("attack", -5);
        map.put("speed", 10);

        Item item = new Item(buyPane, name, String.valueOf(price));
        item.setInventory(inventory);
        item.setInfo(map);
        if (items.size() == 0) {
            item.setY(20);
        } else {
            item.setY(items.get(items.size() - 1).getY() + 70);
        }
        item.setX(20);
        items.add(item);
    }

    public void display() {
        configurePane(buyPane, leftScrollPane, 0, 70);
        configurePane(inventoryPane, leftScrollPane, 0, 70);
        configurePane(rightPane, null, 400, 0);
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
