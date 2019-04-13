package Shapes;

import Utils.Direction;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Eye extends Circle {




    private double x, y;


    public Eye(Head head) {

        setFill(Color.GREEN);


        this.x = head.getMyX();
        this.y = head.getMyY();


        setCenterX(x);
        setCenterY(y);
        setRadius(100.0f);

        //updatePosition();
    }


    public void updatePosition(){
        setTranslateX(x);
        setTranslateX(y);
    }

}
