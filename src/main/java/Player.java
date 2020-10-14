import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
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

        moneyText.setTranslateX(130);
        moneyText.setTranslateY(50);
        moneyText.setFont(Font.font(null, FontWeight.BOLD, 32));
        moneyText.setStrokeWidth(2);
        moneyText.setStroke(Color.GRAY);
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
        moneyText.setText("$ " + String.valueOf(this.money) );
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
    private ArrayList<Text> texts = new ArrayList<>();


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

        texts.add(health);
        texts.add(defense);
        texts.add(attack);
        texts.add(speed);
        texts.add(specialAttack);


        for (int i = 0; i < texts.size(); i++) {
            setText(texts.get(i), 50, 350 + (i * 50));
        }



        update(temp);

        this.pane.getChildren().addAll(health, defense, attack, speed, specialAttack);


    }


    private void setText(Text text,int x, int y){
        text.setTranslateX(x);
        text.setTranslateY(y);

        DropShadow ds = new DropShadow();
        ds.setBlurType(BlurType.TWO_PASS_BOX);
        ds.setOffsetY(3);
        ds.setColor(Color.rgb(225, 225, 225));

        text.setEffect(ds);
        text.setCache(true);
        text.setFill(Color.rgb(82, 82, 200));
        text.setFont(Font.font(null, FontWeight.BOLD, 18));
        text.setStroke(Color.BLACK);
        text.setStrokeWidth(0.4);
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