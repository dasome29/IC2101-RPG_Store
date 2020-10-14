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

    private StackPane buyPane = new StackPane();
    private StackPane rightPane = new StackPane();
    private StackPane inventoryPane = new StackPane();
    private ScrollPane leftScrollPane = new ScrollPane(buyPane);
    private ArrayList<Item> items = new ArrayList<>();
    private MenuBar menuBar;
    private Inventory inventory;

    private Player player = new Player(rightPane);

    public GUI(Pane root) {
        this.root = root;
        buyPane.setAlignment(Pos.TOP_LEFT);
        rightPane.setAlignment(Pos.TOP_LEFT);
        inventoryPane.setAlignment(Pos.TOP_LEFT);
        inventory = new Inventory(inventoryPane, player);
        menuBar = new MenuBar(this.root, buyPane, inventoryPane, leftScrollPane);
        itemGenerator();
    }

    private void itemGenerator() {
        String[] keys = {"B07Y5W29JN", "B088FDVFPT", "B07P9W5HJV", "B07QC6VKWB", "B07Y4ZYRQ3", "B00TJ9P1V6", "B07KBY2P6P",
                "B0872QLW8W", "B07BHTV9VQ", "B07444854P", "B07RMKK1P3", "B07GJBBGHG", "B07N1HX72G", "B0719HYML3",
                "B07ZX7H5XL"};

        for (String key : keys) {
            addItem(key);
        }
    }

    private void configurePane(Pane pane, ScrollPane scrollPane, int x, int y) {

        if (scrollPane != null) {
            pane.setPrefSize(400, 1200);
            scrollPane.setPrefSize(400, 530);
            scrollPane.setLayoutX(x);
            scrollPane.setLayoutY(y);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scrollPane.setVmax(4);
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
        System.out.print(key);
        HttpResponse<JsonNode> http = null;
        try {
            http = Unirest.get("https://amazon-product-reviews-keywords.p.rapidapi.com/product/details?country=US&asin=" + key)
                    .header("x-rapidapi-host", "amazon-product-reviews-keywords.p.rapidapi.com")
                    .header("x-rapidapi-key", "203c5afef0msh42f0cc1f3c0f465p1048b2jsn6e07fdc34ead")
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        assert http != null;
        return http.getBody();
    }

    public Item getInfo(String code) {
//        JsonNode response = getResponse(code);
        HashMap<String, Integer> map = new HashMap<>();

        String name = "";
        int price = 0;

        JSONObject object;
        JSONObject product;
        JSONObject rev;
        JSONObject prodInf;
        try {
            String content = Files.readString(Paths.get("data.json"));
            object = new JSONObject(content);
            //     object = response.getObject();
            product = object.getJSONObject("product");
            rev = product.getJSONObject("reviews");
            prodInf = product.getJSONObject("product_information");
            if (product.get("asin").equals("B07Y5W29JN")) {
                name = "Silver Boots";
                map.put("health", (int) prodInf.get("available_for_days") / 3);
                map.put("defense", (int) rev.get("total_reviews") / 77);
                map.put("speed", (int) prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images") * 10);
                map.put("special attack", (int) product.get("total_videos") + 75);
            }
            if (product.get("asin").equals("B088FDVFPT")) {
                name = "Golden Boots";
                map.put("health", (int) prodInf.get("available_for_days") / 2);
                map.put("defense", (int) rev.get("total_reviews") / 16);
                map.put("speed", (int) prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images") * 11);
                map.put("special attack", (int) product.get("total_videos") + 45);

            }
            if (product.get("asin").equals("B07P9W5HJV")) {
                name = "Green Boots";
                map.put("health", (int) prodInf.get("available_for_days") / 3);
                map.put("defense", (int) rev.get("total_reviews") / 95);
                map.put("speed", (int) prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images") * 9);
                map.put("special attack", (int) product.get("total_videos") + 64);
            }
            if (product.get("asin").equals("B07QC6VKWB")) {
                name = "Health Potion";
                map.put("health", (int) prodInf.get("available_for_days") / 3);
                map.put("defense", (int) rev.get("total_reviews") / 63);
                map.put("speed", (int) prodInf.get("available_for_months") * 2);
                map.put("attack", (int) product.get("total_images") * 13);
                map.put("special attack", (int) product.get("total_videos") + 83);
            }
            if (product.get("asin").equals("B07Y4ZYRQ3")) {
                name = "Strength Potion";
                map.put("health", (int) prodInf.get("available_for_days") / 3);
                map.put("defense", (int) rev.get("total_reviews") / 9);
                map.put("speed", (int) prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images") * 12);
                map.put("special attack", (int) product.get("total_videos") + 91);

            }
            if (product.get("asin").equals("B00TJ9P1V6")) {
                name = "Speed Potion";
                map.put("health", (int) prodInf.get("available_for_days") / 4);
                map.put("defense", (int) rev.get("total_reviews") / 2);
                map.put("speed", (int) prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images") * 17);
                map.put("special attack", (int) product.get("total_videos") + 82);
            }
            if (product.get("asin").equals("B07KBY2P6P")) {
                name = "Carbon Sword";
                map.put("health", (int) prodInf.get("available_for_days") / 4);
                map.put("defense", (int) rev.get("total_reviews") / 9);
                map.put("speed", (int) prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images") * 3);
                map.put("special attack", (int) product.get("total_videos") + 49);
            }
            if (product.get("asin").equals("B0872QLW8W")) {
                name = "Ice Sword";
                map.put("health", (int) prodInf.get("available_for_days") / 2);
                map.put("defense", (int) rev.get("total_reviews") / 3);
                map.put("speed", (int) prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images") * 4);
                map.put("special attack", (int) product.get("total_videos") + 34);
            }


            if (product.get("asin").equals("B07BHTV9VQ")) {
                name = "Fire Sword";
                map.put("health", (int) prodInf.get("available_for_days") / 5);
                map.put("defense", (int) rev.get("total_reviews") / 4);
                map.put("speed", (int) prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images") + 90);
                map.put("special attack", (int) product.get("total_videos") + 90);
            }


            if (product.get("asin").equals("B07444854P")) {
                name = "Wooden Shield";
                map.put("health", (int) prodInf.get("available_for_days") / 8);
                map.put("defense", (int) rev.get("total_reviews") / 90);
                map.put("speed", (int) prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images") * 3);
                map.put("special attack", (int) product.get("total_videos") + 59);
            }

            if (product.get("asin").equals("B07RMKK1P3")) {
                name = "Iron Shield";
                map.put("health", (int) prodInf.get("available_for_days") / 8);
                map.put("defense", (int) rev.get("total_reviews") / 2);
                map.put("speed", (int) prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images") * 9);
                map.put("special attack", (int) product.get("total_videos") + 68);
            }

            if (product.get("asin").equals("B07GJBBGHG")) {
                name = "Silver Shield";
                map.put("health", (int) prodInf.get("available_for_days") / 2);
                map.put("defense", (int) rev.get("total_reviews") / 100);
                map.put("speed", (int) prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images") * 6);
                map.put("special attack", (int) product.get("total_videos") + 37);

            }

            if (product.get("asin").equals("B07N1HX72G")) {
                name = "Golden Armour";
                map.put("health", (int) prodInf.get("available_for_days") / 3);
                map.put("defense", (int) rev.get("total_reviews") / 4);
                map.put("speed", (int) prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images") * 5);
                map.put("special attack", (int) product.get("total_videos") + 97);
            }
            if (product.get("asin").equals("B0719HYML3")) {
                name = "Silver Armour";
                map.put("health", (int) prodInf.get("available_for_days") / 2);
                map.put("defense", (int) rev.get("total_reviews") / 81);
                map.put("speed", (int) prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images") * 5);
                map.put("special attack", (int) product.get("total_videos") + 61);
            }
            if (product.get("asin").equals("B07ZX7H5XL")) {
                name = "Leather Armour";
                map.put("health", (int) prodInf.get("available_for_days") / 4);
                map.put("defense", (int) rev.get("total_reviews") / 6);
                map.put("speed", (int) prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images") * 8);
                map.put("special attack", (int) product.get("total_videos") + 82);
            }
            JSONObject priceVal = product.getJSONObject("price");

            price = (int)Math.round((Double) priceVal.get("current_price")) ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        Item item = new Item(buyPane,name, String.valueOf(price));
        item.setInfo(map);
        return item;
    }

    private void addItem(String key) {
        Item item = getInfo(key);
        item.setInventory(inventory);
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
        buyButton.setStyle("-fx-text-fill: goldenrod; -fx-font: italic 20 \"serif\"; -fx-background-color: transparent; -fx-text-alignment: right; -fx-alignment: center;-fx-border-color: lightgrey;-fx-border-width: 0.5;");
        inventoryButton.setStyle("-fx-text-fill: goldenrod; -fx-font: italic 20 \"serif\"; -fx-background-color: transparent; -fx-text-alignment: right; -fx-alignment: center;-fx-border-color: lightgrey;-fx-border-width: 0.5;");
        root.getChildren().addAll(menuBar);
    }

}
