package Shapes;

import Games.Game;
import Utils.Keyboard;
import Utils.Position;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.Node;

public class Egg extends Rectangle implements Serializable { // square eggs fill space much better
    private static final double SIZE = Game.ELEMENT_SIZE;
    private static final double STEP = 0.25;
    private ArrayList<Node> allParts;
    private double x, y, dx, dy;

    public Egg(){

    }

    public Egg(Position position, Color color) {

        this(position.getX(), position.getY(), color);
    }

    public Egg(Egg egg) {
        this.x = egg.x;
        this.y = egg.y;
        this.dx = egg.dx;
        this.dy = egg.dy;
        this.allParts = egg.allParts;
    }


    public double getMyX() {
        return x;
    }

    public double getMyY() {
        return y;
    }

    public Position getPosition(){
        return new Position(x, y);
    }



    public Egg(double x, double y, Color color)
    {
        super(SIZE, SIZE, color);
        this.x = x;
        this.y = y;
        this.allParts = new ArrayList<Node>();
        this.dx = Math.random()-0.5;
        this.dy = Math.random()-0.5;


        updatePosition();

        allParts.add(this);
        // a few unnecessary esthetic calls
        setArcWidth(SIZE/3);
        setArcHeight(SIZE/2);
        setStroke(Color.BLACK);
        setStrokeWidth(3.0);
        setStrokeLineCap(StrokeLineCap.ROUND);


    }

    public ArrayList<Node> getAllParts() { return allParts; }

    public void move()
    {
        // constant accelerated move as soon as a key is pressed
        switch (Keyboard.getLastKeyCode()) {
            case LEFT:  dx= -Game.ELEMENT_SIZE; dy =0; break;
            case UP:    dy = -Game.ELEMENT_SIZE; dx=0;; break;
            case RIGHT: dx = Game.ELEMENT_SIZE; dy= 0; break;
            case DOWN:	dy = Game.ELEMENT_SIZE; dx=0 ; break;
            default: // keep horizontal&vertical speed when any other key is pressed
        }
        x = x + dx;
        y = y + dy;
        updatePosition();
    }

    private void updatePosition()
    {
        // these are the methods that actually position the Node on screen
        setTranslateX(x);
        setTranslateY(y);
    }
}