package Snakes;

import Games.Game;
import Shapes.Egg;
import Shapes.Head;
import Shapes.Tail;
import Utils.Direction;
import Utils.MyTree;
import Utils.Position;
import javafx.scene.paint.Color;

import java.io.Serializable;

public class VerySmartSnake extends Snake implements Serializable {

    public VerySmartSnake(Head head, Tail tail) {
        super(head, tail);

    }


    public void move() {

        isStopped = false;
        MyTree treeUp = new MyTree(this, theOtherSnake);
        MyTree treeDown = new MyTree(this, theOtherSnake);
        MyTree treeLeft = new MyTree(this, theOtherSnake);
        MyTree treeRight = new MyTree(this, theOtherSnake);

        treeUp.constructTree(head.getPosition().above());
        treeDown.constructTree(head.getPosition().bellow());
        treeRight.constructTree(head.getPosition().right());
        treeLeft.constructTree(head.getPosition().left());

        MyTree max = MyTree.maxDeptTree(treeUp, treeDown, treeLeft, treeRight);

        if(max == null){
            isStopped = true;
            return;
        }
        Position nextPosition = max.getRoot();

        if(occupiedPositions.contains(nextPosition) || theOtherSnake.getOccupiedPositions().contains(nextPosition) ||
                nextPosition.getX() == -Game.ELEMENT_SIZE|| nextPosition.getY() == -Game.ELEMENT_SIZE ||
                nextPosition.getX() == Game.WINDOW_SIZE|| nextPosition.getY() == Game.WINDOW_SIZE){
            isStopped = true;
            return;
        }

        //create the new Shapes.Head;
        Head theNewHead = new Head(nextPosition, Direction.UP, Color.AQUAMARINE);

        if(this.head.getPosition().equals(nextPosition.right())){
            theNewHead = new Head(nextPosition, Direction.LEFT, Color.AQUAMARINE);
        }

        if(this.head.getPosition().equals(nextPosition.left())){
            theNewHead = new Head(nextPosition, Direction.RIGHT, Color.AQUAMARINE);
        }

        if(this.head.getPosition().equals(nextPosition.above())){
            theNewHead = new Head(nextPosition, Direction.DOWN, Color.AQUAMARINE);
        }




        //Egg theNewHead = new Egg(currentHead.getMyX() + direction.getDx(), currentHead.getMyY() + direction.getDy(), Color.AQUAMARINE);





        snakeBody.add(0, theNewHead);
        snakeBody.remove(1);

        snakeBody.add(1, new Egg(head.getPosition(), Color.BLACK));
        occupiedPositions.add(nextPosition);

        this.head.setFill(Color.BLACK);




        this.head = theNewHead;



    }








}
