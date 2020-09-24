package Classes;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class GUI {
    private Pane root;
    private Pane leftPane = new Pane();
    private ScrollPane leftScrollPane = new ScrollPane(leftPane);
    private Pane rightPane = new Pane();
    private ScrollPane rightScrollPane = new ScrollPane(rightPane);
    private Button newItemButton = new Button("New Item");
    private ArrayList<Rectangle> items= new ArrayList<>();
    public GUI(Pane root){
        this.root = root;
        newItemButton.setOnMouseClicked(mouseEvent -> {
            System.out.println("Adding an Item.");
            try {
                addItem();
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        });
    }

    private void configurePane(Pane pane, ScrollPane scrollPane, int x){
        pane.setPrefSize(400, 800);
        scrollPane.setPrefSize(400, 600);
        scrollPane.setLayoutX(x);
        scrollPane.setLayoutY(0);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVmax(2);
        pane.setStyle("-fx-background-color: rgba(0,100,100,0.5); -fx-background-radius: 10;");
    }

    private void addItem() throws UnirestException {
//        HttpResponse<JsonNode> response = Unirest.get("https://amazon-product-reviews-keywords.p.rapidapi.com/product/details?country=US&asin=B07ZPKR714")
//                .header("x-rapidapi-host", "amazon-product-reviews-keywords.p.rapidapi.com")
//                .header("x-rapidapi-key", "203c5afef0msh42f0cc1f3c0f465p1048b2jsn6e07fdc34ead")
//                .asJson();
//        String json = response.getBody().toString();
//        System.out.println(json);
        Rectangle rectangle = new Rectangle(350.0D, 60.0D);
        rectangle.setArcHeight(60);
        rectangle.setArcWidth(40);
        rectangle.setFill(Color.rgb(222, 24, 70));
        rectangle.setStrokeWidth(3);
        rectangle.setStroke(Color.BLACK);
        if (items.size()==0){
            rectangle.setTranslateY(20);
        }else{
            rectangle.setTranslateY(items.get(items.size()-1).getTranslateY()+65);
        }
        rectangle.setTranslateX(20);
        leftPane.getChildren().addAll(rectangle);
        items.add(rectangle);
    }

    public void display(){

        configurePane(leftPane, leftScrollPane, 0);
        configurePane(rightPane, rightScrollPane, 400);
        ImageView image = new ImageView("file:/D:/Integratec/IC2101-RPG_Store/Classes/backGround.png");
        newItemButton.setTranslateX(160);
        newItemButton.setTranslateY(50);
        rightPane.getChildren().addAll(newItemButton);
        root.getChildren().addAll(leftScrollPane, rightScrollPane);
    }
}
