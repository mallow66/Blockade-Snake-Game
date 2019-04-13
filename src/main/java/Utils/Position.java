package Utils;

import Games.Game;

import java.io.Serializable;

/**
 * Created by brahim on 10/28/18.
 */
public class Position implements Serializable {

    private double x, y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position() {

    }




    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object  p) {
        if ( p == null) return false;
        if (!(p instanceof Position)) return false;

        Position pp = (Position)p;

        if(pp.getX() == this.getX() && pp.getY() == this.getY())
            return true;
        return false;



    }

    public boolean isExtreme() {
        if(  x == -Game.ELEMENT_SIZE|| y == -Game.ELEMENT_SIZE ||
                x == Game.WINDOW_SIZE|| y == Game.WINDOW_SIZE)
            return true;
        return false;

    }


    public Position above() {
        return new Position(x, y - Game.ELEMENT_SIZE);
    }

    public Position bellow() {
        return new Position(x, y + Game.ELEMENT_SIZE);
    }

    public Position right( ) {
        return new Position(x + Game.ELEMENT_SIZE, y);
    }

    public Position left( ) {
        return new Position(x - Game.ELEMENT_SIZE, y);
    }

    public String toString() {
        return "("+x+","+y+")";
    }
}
