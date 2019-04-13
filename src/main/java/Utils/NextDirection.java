package Utils;

import Games.Game;
import Utils.Keyboard;
import javafx.scene.input.KeyCode;

/**
 * Created by brahim on 10/28/18.
 */
public class NextDirection {

    public static KeyCode history = KeyCode.ENTER;

    private double dx, dy;

    public NextDirection() {
       // dy = -Game.ELEMENT_SIZE;
        //dx=0;

        switch(Keyboard.getLastKeyCode()){
            case LEFT:
                if(history.equals(KeyCode.RIGHT)) {
                    dx = Game.ELEMENT_SIZE;
                    dy=0;
                    history = KeyCode.RIGHT;
                }
                else {
                    dx= -Game.ELEMENT_SIZE;
                    dy =0;
                    history = KeyCode.LEFT;
                }
                break;
            case RIGHT:
                if(history.equals(KeyCode.LEFT)) {
                    dx= -Game.ELEMENT_SIZE;
                    dy = 0;
                    history = KeyCode.LEFT;
                }
                else {
                    dx = Game.ELEMENT_SIZE;
                    dy= 0;
                    history = KeyCode.RIGHT;
                }
                break;
            case UP :
                if(history.equals(KeyCode.DOWN)) {
                    dy = Game.ELEMENT_SIZE;
                    dx=0;
                    history = KeyCode.DOWN;
                }
                else {
                    dy = -Game.ELEMENT_SIZE;
                    dx=0;
                    history = KeyCode.UP;
                }

                break;
            case DOWN:
                if(history.equals(KeyCode.UP)){
                    dy = -Game.ELEMENT_SIZE;
                    dx=0;
                    history = KeyCode.UP;
                }
                else{
                    dy = Game.ELEMENT_SIZE;
                    dx=0;
                    history = KeyCode.DOWN;
                }
                 break;
            default:
        }
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }
}
