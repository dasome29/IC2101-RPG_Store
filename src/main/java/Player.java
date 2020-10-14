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
    public Stats stats;
    public double money = 5000.0;
    private Text moneyText = new Text();
    public HashSet<String> equipment = new HashSet<>();

    public Player(StackPane pane) {
//        playerImage = new ImageView(getClass().getResource("player.png").toString());
//        playerImage.setTranslateX(200);
//        playerImage.setTranslateY(20);

        this.stats = new Stats(pane);

        moneyText.setTranslateX(130);
        moneyText.setTranslateY(50);
        moneyText.setFont(Font.font(null, FontWeight.BOLD, 32));
        moneyText.setStrokeWidth(2);
        moneyText.setStroke(Color.GRAY);
        changeMoney(0.0);

        this.pane = pane;
        this.pane.getChildren().addAll(moneyText);
    }

    public boolean equip(HashMap<String, Double> stats, String name) {
        if (!equipment.contains(name)) {
            this.stats.update(stats);
            equipment.add(name);
            return true;
        }
        return false;
    }

    public void changeMoney(double money) {
        this.money = this.money + money;
        moneyText.setText("$ " + String.valueOf(this.money));
    }
}

class Stats {
    private StackPane pane;

    private HashMap<String, Double> stats = new HashMap<>();
    private Text health = new Text();
    private Text defense = new Text();
    private Text attack = new Text();
    private Text speed = new Text();
    private Text specialAttack = new Text();

    private Text previewHealth = new Text();
    private Text previewDefense = new Text();
    private Text previewAttack = new Text();
    private Text previewSpeed = new Text();
    private Text previewSpecialAttack = new Text();
    private ArrayList<Text> texts = new ArrayList<>();
    public HashMap<String, Text> previews = new HashMap<>();


    public Stats(StackPane pane) {
        this.pane = pane;

        HashMap<String, Double> temp = new HashMap<>();

        stats.put("health", 0.0);
        stats.put("defense", 0.0);
        stats.put("attack", 0.0);
        stats.put("speed", 0.0);
        stats.put("special attack", 0.0);

        temp.put("health", 20.0);
        temp.put("defense", 30.0);
        temp.put("attack", 40.0);
        temp.put("speed", 35.0);
        temp.put("special attack", 40.0);

        texts.add(health);
        texts.add(defense);
        texts.add(attack);
        texts.add(speed);
        texts.add(specialAttack);

        previews.put("health", previewHealth);
        previews.put("defense", previewDefense);
        previews.put("attack", previewAttack);
        previews.put("speed", previewSpeed);
        previews.put("special attack", previewSpecialAttack);


        for (int i = 0; i < texts.size(); i++) {
            setText(texts.get(i), 50, 350 + (i * 50));
        }

        setPreviews();

        update(temp);

        this.pane.getChildren().addAll(health, defense, attack, speed, specialAttack, previewHealth, previewDefense, previewAttack, previewSpeed, previewSpecialAttack);


    }

    private void setPreviews() {
        previewAttack.setTranslateX(300);
        previewDefense.setTranslateX(300);
        previewHealth.setTranslateX(300);
        previewSpeed.setTranslateX(300);
        previewSpecialAttack.setTranslateX(300);

        previewAttack.setTranslateY(350);
        previewDefense.setTranslateY(400);
        previewHealth.setTranslateY(450);
        previewSpeed.setTranslateY(500);
        previewSpecialAttack.setTranslateY(550);

        previewAttack.setFont(Font.font(null, FontWeight.BOLD, 18));
        previewDefense.setFont(Font.font(null, FontWeight.BOLD, 18));
        previewHealth.setFont(Font.font(null, FontWeight.BOLD, 18));
        previewSpeed.setFont(Font.font(null, FontWeight.BOLD, 18));
        previewSpecialAttack.setFont(Font.font(null, FontWeight.BOLD, 18));

        previewAttack.setFill(Color.LIGHTGREEN);
        previewDefense.setFill(Color.LIGHTGREEN);
        previewHealth.setFill(Color.LIGHTGREEN);
        previewSpeed.setFill(Color.LIGHTGREEN);
        previewSpecialAttack.setFill(Color.LIGHTGREEN);
    }

    public void showPreviews(HashMap<String, Double> map) {
        for (String s :
                map.keySet()) {
            previews.get(s).setText(String.valueOf(map.get(s)));
            previews.get(s).setVisible(true);
        }
    }

    public void hidePreviews() {
        for (String s :
                previews.keySet()) {
            previews.get(s).setVisible(false);
        }
    }


    private void setText(Text text, int x, int y) {
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

    public void update(HashMap<String, Double> map) {
        for (String s :
                map.keySet()) {
            stats.put(s, map.get(s) + stats.get(s));
        }

        health.setText("Health____________: " + stats.get("health"));
        defense.setText("Defense___________: " + stats.get("defense"));
        attack.setText("Attack____________: " + stats.get("attack"));
        speed.setText("Speed_____________: " + stats.get("speed"));
        specialAttack.setText("Special Attack____: " + stats.get("special attack"));
    }
}