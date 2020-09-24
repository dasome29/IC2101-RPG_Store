package Classes;

import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    private Pane root = new Pane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("RPG Store");
        GUI gui = new GUI(root);
        System.out.println(new File("Classes/backGround.png").toURI().toString());
        root.setBackground(new Background(new BackgroundImage(new Image("file:/D:/Integratec/IC2101-RPG_Store/Classes/backGround.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        gui.display();
        primaryStage.show();
    }
}
