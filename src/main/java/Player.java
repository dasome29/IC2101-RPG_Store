import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import javafx.scene.image.ImageView;
import java.util.HashMap;

public class Player {
    private ImageView playerImage;
    private StackPane pane;
    public Player(StackPane pane){
        playerImage = new ImageView(getClass().getResource("player.png").toString());

        playerImage.setTranslateX(200);
        playerImage.setTranslateY(20);

        this.pane = pane;
        this.pane.getChildren().addAll(playerImage);
    }
}

class Stats{
    private StackPane pane;

    private HashMap<String, Integer> stats = new HashMap<>();
    private Text health;
    private Text defense;
    private Text attack;
    private Text speed;
    private Text specialAttack;



    public Stats(StackPane pane){
        this.pane = pane;

        stats.put("health", 20);
        stats.put("defense", 30);
        stats.put("attack", 40);
        stats.put("speed", 35);
        stats.put("special attack", 40);



        this.pane.getChildren().addAll(health, defense, attack, speed, specialAttack);


    }

    private void update(){
        health.setText("Health____________: " + stats.get("health"));
        defense.setText("Defense___________: " + stats.get("defense"));
        attack.setText("Attack____________: " + stats.get("attack"));
        speed.setText("Speed_____________: " + stats.get("speed"));
        specialAttack.setText("Special Attack____: " + stats.get("special attack"));
    }

    public void changeStat(String key, int value){
        stats.put(key, value);
        update();
    }

}