package Classes;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.awt.*;

public class Item {
    private Pane pane;
    private int x;
    private int y;

    public Item(Pane pane, int x, int y){
        this.pane = pane;
        this.x = x;
        this.y = y;
    }
}
