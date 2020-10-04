import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    public StackPane pane;
    public ArrayList<ItemInventory> items = new ArrayList<>();
    public Player player;
    int price = 0;

    public Inventory(StackPane pane, Player player) {
        this.pane = pane;
        this.player = player;
    }

    private boolean itemExist(String name) {
        for (ItemInventory i :
                items) {
            if (i.getName().equals(name)) return true;
        }
        return false;
    }

    private ItemInventory getItem(String name) {
        for (ItemInventory i :
                items) {
            if (i.getName().equals(name)) return i;
        }
        return null;
    }

    public void addItem(String name, String price, HashMap<String, Integer> info) {
        if (itemExist(name)) {
            ItemInventory temp = getItem(name);
            temp.number.setText(String.valueOf((Integer.parseInt(temp.number.getText())) + 1));
        } else {
            ItemInventory item = new ItemInventory(this, name, Integer.parseInt(price), "1", info);
            if (items.size() == 0) {
                item.setY(20);
            } else {
                item.setY(items.get(items.size() - 1).getY() + 70);
            }
            item.setX(20);
            items.add(item);
        }

    }

}

class ItemInventory extends Item {
    private HashMap<String, Integer> info;
    private ArrayList items;
    private int price;
    private Inventory inventory;

    public ItemInventory(Inventory inventory, String name, int price, String number, HashMap<String, Integer> info) {
        super(inventory.pane, name, number);
        this.info = info;
        this.items = inventory.items;
        this.price = (int) Math.round(price * 0.5);
        this.inventory = inventory;
    }

    @Override
    protected void setAlert() {
        rectangle.setOnMouseClicked(event -> {
            Alert choose = new Alert(Alert.AlertType.CONFIRMATION);
            choose.setTitle("Buying Item...");
            choose.setContentText("Choose what you want to do with this item.");
            ButtonType equipButton = new ButtonType("Equip", ButtonBar.ButtonData.YES);
            ButtonType sellButton = new ButtonType("Sell one", ButtonBar.ButtonData.NO);
            choose.getButtonTypes().setAll(equipButton, sellButton);
            choose.showAndWait().ifPresent(type -> {

                if (type.getButtonData() == ButtonBar.ButtonData.YES) {
                    if (!inventory.player.equip(info, name.getText())){
                        Alert information = new Alert(Alert.AlertType.INFORMATION);
                        information.setTitle("Ups...");
                        information.setContentText("You already have this item equipped.");
                        information.show();
                    }else {
                        System.out.println("Equipped " + name.getText());
                        sell();
                    }
                } else if (type.getButtonData() == ButtonBar.ButtonData.NO) {
                    System.out.println("You Sold one item");
                    sell();
                    inventory.player.changeMoney(price);
                }
            });
        });
    }

    private void sell() {
        int num = Integer.parseInt(number.getText()) - 1;
        if (num <= 0) {
            pane.getChildren().removeAll(rectangle, number, name, infoImage, itemImage);
            items.remove(this);
        } else {
            number.setText(String.valueOf(num));
        }
    }
}