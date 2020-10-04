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
    private StackPane pane;
    private ArrayList<ItemInventory> items = new ArrayList<>();

    public Inventory(StackPane pane) {
        this.pane = pane;
    }

    private boolean itemExist(String name) {
        for (ItemInventory i :
                items) {
            if (i.getName().equals(name)) return true;
        }
        return false;
    }

    private ItemInventory getItem(String name){
        for (ItemInventory i :
                items) {
            if (i.getName().equals(name)) return i;
        }
        return null;
    }

    public void addItem(String name, HashMap<String, Integer> info) {
        if (itemExist(name)){
            ItemInventory temp = getItem(name);
            temp.number.setText( String.valueOf((Integer.parseInt(temp.number.getText()))+1));
        }
        else{
            ItemInventory item = new ItemInventory(pane, name, "1", info);
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

    public ItemInventory(StackPane pane, String name, String number, HashMap<String, Integer> info) {
        super(pane, name, number);
        this.info = info;
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
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                if (type.getButtonData() == ButtonBar.ButtonData.YES) {
                    System.out.println("You Equipped this Item");
                } else if (type.getButtonData() == ButtonBar.ButtonData.NO) {
                    System.out.println("You Sold one item");
                    sell();
                }
            });
        });
    }

    private void sell() {
        int num = Integer.parseInt(number.getText()) - 1;
        if (num <= 0) {
            pane.getChildren().removeAll(rectangle, number, name, infoImage, itemImage);
        }
        else {
            number.setText(String.valueOf(num));
        }
    }
}