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
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

            addItem();

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

    private JsonNode getResponse(String key){
        System.out.print(key);
        HttpResponse<JsonNode> http = null;
        try {
            http = Unirest.get("https://amazon-product-reviews-keywords.p.rapidapi.com/product/details?country=US&asin="+key)
                    .header("x-rapidapi-host", "amazon-product-reviews-keywords.p.rapidapi.com")
                    .header("x-rapidapi-key", "203c5afef0msh42f0cc1f3c0f465p1048b2jsn6e07fdc34ead")
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return http.getBody();
    }

    public JSONObject getInfo(){
//        JsonNode response = getResponse("B07Y5W29JN");
        JSONObject object = null;
        JSONObject product;
        JSONObject rev;
        JSONObject prodInf;
        FileReader file;
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
                // Sube proteccioón
                System.out.println(rev);
                int protection = (int) product.get("total_images")*10;
                System.out.println(protection);
                int defense=(int )rev.get("total_reviews")/77;
                System.out.println(defense);
                int speed=(int )prodInf.get("available_for_months");
                System.out.println(speed);
                int health=(int )prodInf.get("available_for_days")/3;
                System.out.println(health);
                int specialAttack=(int )product.get("total_videos")+75;
                System.out.println(specialAttack);
            }
            else if (product.get("asin").equals("B088FDVFPT")) {
                System.out.println("Es un album");
                // Sube proteccioón
                System.out.println(rev);
                int protection = (int) product.get("total_images")*10;
                System.out.println(protection);
                int defense=(int )rev.get("total_reviews")/77;
                System.out.println(defense);
                int speed=(int )prodInf.get("available_for_months");
                System.out.println(speed);
                int health=(int )prodInf.get("available_for_days")/3;
                System.out.println(health);
                int specialAttack=(int )product.get("total_videos")+75;
                System.out.println(specialAttack);
            }
            else if (product.get("asin").equals("B088FDVFPT")) {
                //https://www.amazon.com/-/es/JYP-Twice-%C3%81lbum-Tarjetas-fotos/dp/B088FDVFPT/
                System.out.println("Es un album");
                // Sube proteccioón
                System.out.println(rev);
                int protection = (int) product.get("total_images")*9;
                System.out.println(protection);
                int defense=(int )rev.get("total_reviews")/95;
                System.out.println(defense);
                int speed=(int )prodInf.get("available_for_months");
                System.out.println(speed);
                int health=(int )prodInf.get("available_for_days")/3;
                System.out.println(health);
                int specialAttack=(int )product.get("total_videos")+64;
                System.out.println(specialAttack);
            }
            else if (product.get("asin").equals("B07QC6VKWB")) {
                //https://www.amazon.com/-/es/Amazon-Brand-cer%C3%A1mica-interiores-pulgadas/dp/B07QC6VKWB/
                System.out.println("Es una maceta");
                // Sube proteccioón
                System.out.println(rev);
                int protection = (int) product.get("total_images") * 13;
                System.out.println(protection);
                int defense = (int) rev.get("total_reviews") / 63;
                System.out.println(defense);
                int speed = (int) prodInf.get("available_for_months")*2;
                System.out.println(speed);
                int health = (int) prodInf.get("available_for_days") / 3;
                System.out.println(health);
                int specialAttack = (int) product.get("total_videos") + 83;
                System.out.println(specialAttack);
            }
            else if (product.get("asin").equals("B07Y4ZYRQ3")) {
                //https://www.amazon.com/-/es/organizador-adhesivos-soporte-duradero-resistente/dp/B07Y4ZYRQ3/
                System.out.println("Es un cable");
                // Sube proteccioón
                System.out.println(rev);
                int protection = (int) product.get("total_images") * 12;
                System.out.println(protection);
                int defense = (int) rev.get("total_reviews") / 9;
                System.out.println(defense);
                int speed = (int) prodInf.get("available_for_months");
                System.out.println(speed);
                int health = (int) prodInf.get("available_for_days") / 3;
                System.out.println(health);
                int specialAttack = (int) product.get("total_videos") + 91;
                System.out.println(specialAttack);
            }
            else if (product.get("asin").equals("B00TJ9P1V6")) {
                //https://www.amazon.com/medio-Porcelana-Extender-E26-Adaptador-extensi%C3%B3n/dp/B00TJ9P1V6/
                System.out.println("Es un bombillo");
                // Sube proteccioón
                System.out.println(rev);
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
            else if (product.get("asin").equals("B07KBY2P6P")) {
                //https://www.amazon.com/-/es/Ohvera-All-Occasions-Pantalones-bolsillos/dp/B07KBY2P6P/
                System.out.println("Es un pantalon");
                // Sube proteccioón
                System.out.println(rev);
                int protection = (int) product.get("total_images") * 3;
                System.out.println(protection);
                int defense = (int) rev.get("total_reviews") / 9;
                System.out.println(defense);
                int speed = (int) prodInf.get("available_for_months");
                System.out.println(speed);
                int health = (int) prodInf.get("available_for_days") / 4;
                System.out.println(health);
                int specialAttack = (int) product.get("total_videos") + 49;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    private void addItem(){
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
