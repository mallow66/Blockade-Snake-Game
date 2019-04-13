package Shapes;

import Games.Game;
import Utils.Direction;
import Utils.Position;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeLineCap;

import java.io.Serializable;
import java.util.ArrayList;

public class Head extends Arc implements Serializable {

   // private ArrayList<Node> allParts;
    private double x, y, dx, dy;
    private Direction direction;


    public Head(Position position, Direction direction, Color color){
        this(position.getX(), position.getY(), direction, color);
    }
    public Head(double x, double y, Direction direction, Color color) {
        setType(ArcType.CHORD);

        setCenterX(0.0f);
        setCenterY(0.0f);
        setRadiusX(Game.ELEMENT_SIZE/2);
        setRadiusY(Game.ELEMENT_SIZE);
        setStartAngle(-10);
        setLength(200);
        this.direction = direction;

        setFill(color);


        this.x = x;
        this.y = y;
       // this.allParts = new ArrayList<Node>();
        this.dx = Math.random() - 0.5;
        this.dy = Math.random() - 0.5;


        updatePosition();

       // allParts.add(this);
        // a few unnecessary esthetic calls
        setStroke(Color.BLACK);
        setStrokeWidth(3.0);
        setStrokeLineCap(StrokeLineCap.ROUND);


    }


    public Position getPosition(){
        return new Position(x , y );
    }

    public Head() {

    }





    private void updatePosition()
    {
        // these are the methods that actually position the Node on screen
        if(direction.equals(Direction.UP)){
            setTranslateX(x + Game.ELEMENT_SIZE/2);
            setTranslateY(y + Game.ELEMENT_SIZE) ;
        }
        if(direction.equals(Direction.LEFT)){
            setStartAngle(80);
            setTranslateY(y + Game.ELEMENT_SIZE/2);
            setTranslateX(x + Game.ELEMENT_SIZE);
            double swap =  getRadiusX();
            setRadiusX(getRadiusY());
            setRadiusY(swap);
            setRadiusX(getRadiusX());
        }

        if(direction.equals(Direction.RIGHT)){
            setStartAngle(-100);
            setTranslateY(y + Game.ELEMENT_SIZE/2);
            setTranslateX(x );
            double swap =  getRadiusX();
            setRadiusX(getRadiusY());
            setRadiusY(swap);
            setRadiusX(getRadiusX());
        }
        if(direction.equals(Direction.DOWN)){
            setStartAngle(-190);
            setTranslateY(y );
            setTranslateX(x + Game.ELEMENT_SIZE/2);

        }



    }

    public double getMyX(){
        return x;
    }

    public double getMyY(){
        return y;
    }






}
