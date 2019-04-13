package Shapes;

import Games.Game;
import Utils.Direction;
import Utils.Position;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.TriangleMesh;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by brahim on 10/28/18.
 */
public class Tail extends Polygon implements Serializable {

    private static final double SIZE = Game.ELEMENT_SIZE;
    private static final double STEP = 0.25;
    private ArrayList<Node> allParts;
    private double x, y, dx, dy;

    public Tail(double x, double y, Direction direction, Color color) {
        if(direction.equals(Direction.DOWN))
            getPoints().setAll(0.0, 0.0, 0.0+Game.ELEMENT_SIZE,0.0, 0.0+Game.ELEMENT_SIZE/2, 0.0+Game.ELEMENT_SIZE );
        else{
            getPoints().setAll(0.0, 0.0 + Game.ELEMENT_SIZE, 0.0+Game.ELEMENT_SIZE,Game.ELEMENT_SIZE, 0.0+Game.ELEMENT_SIZE/2, 0.0);

        }
        setFill(color);


        this.x = x;
        this.y = y;
        this.allParts = new ArrayList<Node>();
        this.dx = Math.random() - 0.5;
        this.dy = Math.random() - 0.5;


        updatePosition();

        allParts.add(this);
        // a few unnecessary esthetic calls
        setStroke(Color.BLACK);
        setStrokeWidth(3.0);
        setStrokeLineCap(StrokeLineCap.ROUND);


    }


    public Position getPosition(){
        return new Position(x, y);
    }

    public Tail() {

    }

    public Tail(Position position, Direction direction, Color color) {

        this(position.getX(), position.getY(), direction, color);
    }



    private void updatePosition()
    {
        // these are the methods that actually position the Node on screen
        setTranslateX(x);
        setTranslateY(y);
    }
}









