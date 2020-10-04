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

    public GUI(Pane root) {
        this.root = root;
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

    private JsonNode getResponse(String key){
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

        return http.getBody();
    }

    public JSONObject getInfo() {
//        JsonNode response = getResponse("B07Y5W29JN");
        HashMap<String, Integer> map = new HashMap<>();

        JSONObject object = null;
        JSONObject product;
        JSONObject rev;
        JSONObject prodInf;
        try {
            String content = new String(Files.readAllBytes(Paths.get("data.json")), StandardCharsets.UTF_8);
            object = new JSONObject(content);
       //     object = response.getObject();
            product = object.getJSONObject("product");
            rev = product.getJSONObject("reviews");
            prodInf = product.getJSONObject("product_information");
            System.out.println(product);
            if (product.get("asin").equals("B07Y5W29JN")) {
                System.out.println("Es una cuchara");
                map.put("health", (int)prodInf.get("available_for_days")/3);
                map.put("defense", (int )rev.get("total_reviews")/77);
                map.put("speed",(int)prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images")*10);
                map.put("special attack", (int )product.get("total_videos")+75);
            }
            if (product.get("asin").equals("B088FDVFPT")) {
                System.out.println("Es un album");
                map.put("health", (int )prodInf.get("available_for_days")/2);
                map.put("defense", (int )rev.get("total_reviews")/16);
                map.put("speed", (int )prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images")*11);
                map.put("special attack", (int )product.get("total_videos")+45);

            }
            if (product.get("asin").equals("B088FDVFPT")) {
                //https://www.amazon.com/-/es/JYP-Twice-%C3%81lbum-Tarjetas-fotos/dp/B088FDVFPT/
                System.out.println("Es un album");
                // Sube proteccioón
                System.out.println(rev);
                map.put("health", (int )prodInf.get("available_for_days")/3);
                map.put("defense", (int )rev.get("total_reviews")/95);
                map.put("speed", (int )prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images")*9);
                map.put("special attack", (int )product.get("total_videos")+64);
            }
            if (product.get("asin").equals("B07QC6VKWB")) {
                //https://www.amazon.com/-/es/Amazon-Brand-cer%C3%A1mica-interiores-pulgadas/dp/B07QC6VKWB/
                System.out.println("Es una maceta");
                // Sube proteccioón
                System.out.println(rev);
                map.put("health", (int) prodInf.get("available_for_days") / 3);
                map.put("defense", (int) rev.get("total_reviews") / 63);
                map.put("speed", (int) prodInf.get("available_for_months")*2);
                map.put("attack", (int) product.get("total_images") * 13);
                map.put("special attack", (int) product.get("total_videos") + 83);
            }
            if (product.get("asin").equals("B07Y4ZYRQ3")) {
                //https://www.amazon.com/-/es/organizador-adhesivos-soporte-duradero-resistente/dp/B07Y4ZYRQ3/
                System.out.println("Es un cable");
                // Sube proteccioón
                System.out.println(rev);
                map.put("health", (int) prodInf.get("available_for_days") / 3);
                map.put("defense", (int) rev.get("total_reviews") / 9);
                map.put("speed", (int) prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images") * 12);
                map.put("special attack", (int) product.get("total_videos") + 91);

            }
            if (product.get("asin").equals("B00TJ9P1V6")) {
                //https://www.amazon.com/medio-Porcelana-Extender-E26-Adaptador-extensi%C3%B3n/dp/B00TJ9P1V6/
                System.out.println("Es un bombillo");
                // Sube protección
                System.out.println(rev);
                map.put("health", (int) prodInf.get("available_for_days") / 4);
                map.put("defense", (int) rev.get("total_reviews") / 2);
                map.put("speed", (int) prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images") * 17);
                map.put("special attack", (int) product.get("total_videos") + 82);
                int protection = (int) product.get("total_images") * 17;
                System.out.println(protection);
                int defense = (int) rev.get("total_reviews") / 2;
                System.out.println(defense);
                int speed = (int) prodInf.get("available_for_months");
                System.out.println(speed);
                int health = (int) prodInf.get("available_for_days") / 4;
                System.out.println(health);
                int specialAttack = (int) product.get("total_videos") + 82;
                System.out.println(specialAttack);
            }
            if (product.get("asin").equals("B07KBY2P6P")) {
                //https://www.amazon.com/-/es/Ohvera-All-Occasions-Pantalones-bolsillos/dp/B07KBY2P6P/
                System.out.println("Es un pantalon");
                // Sube proteccioón
                System.out.println(rev);
                map.put("health", (int) prodInf.get("available_for_days") / 4);
                map.put("defense", (int) rev.get("total_reviews") / 9);
                map.put("speed", (int) prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images") * 3);
                map.put("special attack", (int) product.get("total_videos") + 49);
            }
            if (product.get("asin").equals("B0872QLW8W")) {
                //https://www.amazon.com/KO-Skateboards-Monopat%C3%ADn-22-0-tibur%C3%B3n/dp/B0872QLW8W/
                System.out.println("Es una patineta");
                // Sube proteccioón
                System.out.println(rev);
                map.put("health", (int) prodInf.get("available_for_days") / 3);
                map.put("defense", (int) rev.get("total_reviews") / 4);
                map.put("speed", (int) prodInf.get("available_for_months"));
                map.put("attack", (int) product.get("total_images") * 5);
                map.put("special attack", (int) product.get("total_videos") + 97);
            }


            else if (product.get("asin").equals("B07BHTV9VQ")) {
            //https://www.amazon.com/-/es/Dixie-pulgadas-unidades-exclusivo-desechables/dp/B07BHTV9VQ/
            System.out.println("Es una plato");
            // Sube proteccioón
            System.out.println(rev);
            int protection = (int) product.get("total_images") * 7;
            System.out.println(protection);
            int defense = (int) rev.get("total_reviews") / 4;
            System.out.println(defense);
            int speed = (int) prodInf.get("available_for_months");
            System.out.println(speed);
            int health = (int) prodInf.get("available_for_days") / 5;
            System.out.println(health);
            int specialAttack = (int) product.get("total_videos") + 90;
            System.out.println(specialAttack);
        }


            else if (product.get("asin").equals("B07444854P")) {
            //https://www.amazon.com/-/es/Fila-Disruptor-II-Zapato-deportivo/dp/B07444854P/
            System.out.println("Es un zapato");
            // Sube proteccioón
            System.out.println(rev);
            int protection = (int) product.get("total_images") * 3;
            System.out.println(protection);
            int defense = (int) rev.get("total_reviews") / 90;
            System.out.println(defense);
            int speed = (int) prodInf.get("available_for_months");
            System.out.println(speed);
            int health = (int) prodInf.get("available_for_days") / 8;
            System.out.println(health);
            int specialAttack = (int) product.get("total_videos") + 59;
            System.out.println(specialAttack);
        }

            else if (product.get("asin").equals("B07RMKK1P3")) {
            //https://www.amazon.com/-/es/Anime-Poke-Pachirisu-Mu%C3%B1eca-peluche/dp/B07RMKK1P3/
            System.out.println("Es un peluche");
            // Sube proteccioón
            System.out.println(rev);
            int protection = (int) product.get("total_images") * 9;
            System.out.println(protection);
            int defense = (int) rev.get("total_reviews") / 2;
            System.out.println(defense);
            int speed = (int) prodInf.get("available_for_months");
            System.out.println(speed);
            int health = (int) prodInf.get("available_for_days") / 8;
            System.out.println(health);
            int specialAttack = (int) product.get("total_videos") + 68;
            System.out.println(specialAttack);
        }

            else if (product.get("asin").equals("B07GJBBGHG")) {
            //https://www.amazon.com/COSORI-Electric-Reminder-Touchscreen-Certified/dp/B07GJBBGHG/
            System.out.println("Es una freidora");
            // Sube proteccioón
            System.out.println(rev);
            int protection = (int) product.get("total_images") * 6;
            System.out.println(protection);
            int defense = (int) rev.get("total_reviews") /100 ;
            System.out.println(defense);
            int speed = (int) prodInf.get("available_for_months");
            System.out.println(speed);
            int health = (int) prodInf.get("available_for_days") / 2;
            System.out.println(health);
            int specialAttack = (int) product.get("total_videos") + 37;
            System.out.println(specialAttack);
        }

            else if (product.get("asin").equals("B0872QLW8W")) {
            //https://www.amazon.com/KO-Skateboards-Monopat%C3%ADn-22-0-tibur%C3%B3n/dp/B0872QLW8W/
            System.out.println("Es una patineta");
            // Sube proteccioón
            System.out.println(rev);
            int protection = (int) product.get("total_images") * 5;
            System.out.println(protection);
            int defense = (int) rev.get("total_reviews") / 4;
            System.out.println(defense);
            int speed = (int) prodInf.get("available_for_months");
            System.out.println(speed);
            int health = (int) prodInf.get("available_for_days") / 3;
            System.out.println(health);
            int specialAttack = (int) product.get("total_videos") + 97;
            System.out.println(specialAttack);

        }
            else if (product.get("asin").equals("B07N1HX72G")) {
            //https://www.amazon.com/PHOPOLLO-Dimmable-Controller-Non-Waterproof-Decoration/dp/B07N1HX72G/
            System.out.println("Son unas luces");
            // Sube proteccioón
            System.out.println(rev);
            int protection = (int) product.get("total_images") * 5;
            System.out.println(protection);
            int defense = (int) rev.get("total_reviews") / 4;
            System.out.println(defense);
            int speed = (int) prodInf.get("available_for_months");
            System.out.println(speed);
            int health = (int) prodInf.get("available_for_days") / 3;
            System.out.println(health);
            int specialAttack = (int) product.get("total_videos") + 97;
            System.out.println(specialAttack);

        }
            else if (product.get("asin").equals("B0719HYML3")) {
            //https://www.amazon.com/Biotina-Amazon-Elements-vegana-c%C3%A1psulas/dp/B0719HYML3/
            System.out.println("Es una biotina");
            // Sube proteccioón
            System.out.println(rev);
            int protection = (int) product.get("total_images") * 5;
            System.out.println(protection);
            int defense = (int) rev.get("total_reviews") / 81;
            System.out.println(defense);
            int speed = (int) prodInf.get("available_for_months");
            System.out.println(speed);
            int health = (int) prodInf.get("available_for_days") / 2;
            System.out.println(health);
            int specialAttack = (int) product.get("total_videos") + 61;
            System.out.println(specialAttack);

        }
            else if (product.get("asin").equals("B07ZX7H5XL")) {
            //https://www.amazon.com/dp/B07ZX7H5XL/
            System.out.println("Es un masajeador");
            // Sube proteccioón
            System.out.println(rev);
            int protection = (int) product.get("total_images") * 5;
            System.out.println(protection);
            int defense = (int) rev.get("total_reviews") / 4;
            System.out.println(defense);
            int speed = (int) prodInf.get("available_for_months");
            System.out.println(speed);
            int health = (int) prodInf.get("available_for_days") / 3;
            System.out.println(health);
            int specialAttack = (int) product.get("total_videos") + 97;
            System.out.println(specialAttack);
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
