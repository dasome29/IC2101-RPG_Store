import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import javafx.scene.image.ImageView;
import java.util.HashMap;
import java.util.HashSet;

public class Player {
    private ImageView playerImage;
    private StackPane pane;
    private Stats stats;
    public int money = 5000;
    private Text moneyText = new Text();
    public HashSet<String> equipment = new HashSet<>();
    public Player(StackPane pane){
//        playerImage = new ImageView(getClass().getResource("player.png").toString());
//        playerImage.setTranslateX(200);
//        playerImage.setTranslateY(20);

        this.stats = new Stats(pane);

        moneyText.setTranslateX(200);
        moneyText.setTranslateY(50);

        changeMoney(0);

        this.pane = pane;
        this.pane.getChildren().addAll(moneyText);
    }
    public boolean equip(HashMap<String, Integer> stats, String name){
        if (!equipment.contains(name)){
            this.stats.update(stats);
            equipment.add(name);
            return true;
        }
        return false;
    }
    public void changeMoney(int money){
        this.money = this.money + money;
        moneyText.setText(String.valueOf(this.money) );
    }
}

class Stats{
    private StackPane pane;

    private HashMap<String, Integer> stats = new HashMap<>();
    private Text health = new Text();
    private Text defense = new Text();
    private Text attack = new Text();
    private Text speed = new Text();
    private Text specialAttack = new Text();



    public Stats(StackPane pane){
        this.pane = pane;

        HashMap<String, Integer> temp = new HashMap<>();

        stats.put("health", 0);
        stats.put("defense", 0);
        stats.put("attack", 0);
        stats.put("speed", 0);
        stats.put("special attack", 0);

        temp.put("health", 20);
        temp.put("defense", 30);
        temp.put("attack", 40);
        temp.put("speed", 35);
        temp.put("special attack", 40);

        health.setTranslateX(180);
        defense.setTranslateX(180);
        attack.setTranslateX(180);
        speed.setTranslateX(180);
        specialAttack.setTranslateX(180);


        health.setTranslateY(350);
        defense.setTranslateY(400);
        attack.setTranslateY(450);
        speed.setTranslateY(500);
        specialAttack.setTranslateY(550);






        update(temp);

        this.pane.getChildren().addAll(health, defense, attack, speed, specialAttack);


    }



    public void update(HashMap<String, Integer> map){
        for (String s :
                map.keySet()) {
            stats.put(s, map.get(s) + stats.get(s));
        }

        health.       setText("Health____________: " + stats.get("health"));
        defense.      setText("Defense___________: " + stats.get("defense"));
        attack.       setText("Attack____________: " + stats.get("attack"));
        speed.        setText("Speed_____________: " + stats.get("speed"));
        specialAttack.setText("Special Attack____: " + stats.get("special attack"));
    }
}