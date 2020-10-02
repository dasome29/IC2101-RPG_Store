import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    private Pane root = new Pane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {

        GUI gui = new GUI(root);
        root.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("backGround.png").toString()),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        gui.display();


        gui.getInfo();



        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toString());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("RPG Store");
//        primaryStage.getScene().setUserAgentStylesheet(getClass().getResource("style.css").toString());
        primaryStage.show();
    }
}


