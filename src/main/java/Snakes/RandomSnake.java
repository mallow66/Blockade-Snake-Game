package Snakes;

import Games.Game;
import Shapes.Egg;
import Shapes.Head;
import Shapes.Tail;
import Snakes.Snake;
import Utils.Direction;
import Utils.Position;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomSnake extends Snake implements Serializable {


    private static Direction currentDirection;


    public RandomSnake(Head head, Tail tail) {
        super(head, tail);
        currentDirection = Direction.DOWN;
    }

    public RandomSnake(){

    }


    public void move() {
        isStopped = false;
        List<Position> possibleNextPositions = new ArrayList<>(3);
        Position nextPosition=null;
       // System.out.println(currentDirection.toString());
        int index;
        switch (currentDirection) {
            case DOWN:
                System.out.println("DOWN");
                possibleNextPositions.add(0,new Position(head.getMyX(), head.getMyY()+ Game.ELEMENT_SIZE));
                possibleNextPositions.add(1,new Position(head.getMyX()+ Game.ELEMENT_SIZE,head.getMyY()));
                possibleNextPositions.add(2,new Position(head.getMyX()- Game.ELEMENT_SIZE,head.getMyY()));

                index = new Random().nextInt(possibleNextPositions.size());

                if(index == 0) currentDirection = Direction.DOWN; else if (index == 1) currentDirection = Direction.RIGHT; else currentDirection = Direction.LEFT;
                nextPosition = possibleNextPositions.get(index);
                System.out.println("DOWN: "+currentDirection.toString()+","+ index);
                break;

            case UP:
                System.out.println("UP");
                possibleNextPositions.add(0,new Position(head.getMyX(), head.getMyY()- Game.ELEMENT_SIZE));
                possibleNextPositions.add(1,new Position(head.getMyX()+ Game.ELEMENT_SIZE,head.getMyY()));
                possibleNextPositions.add(2,new Position(head.getMyX()- Game.ELEMENT_SIZE,head.getMyY()));

                index = new Random().nextInt(possibleNextPositions.size());

                if(index == 0) currentDirection = Direction.UP; else if (index == 1) currentDirection = Direction.RIGHT; else currentDirection = Direction.LEFT;
                nextPosition = possibleNextPositions.get(index);
                System.out.println("UP: "+currentDirection.toString()+","+ index);
                break;

            case RIGHT:
                System.out.println("RIGHT");
                possibleNextPositions.add(0,new Position(head.getMyX()+ Game.ELEMENT_SIZE,head.getMyY()));
                possibleNextPositions.add(1,new Position(head.getMyX(), head.getMyY()+ Game.ELEMENT_SIZE));
                possibleNextPositions.add(2,new Position(head.getMyX(), head.getMyY()- Game.ELEMENT_SIZE));

                index = new Random().nextInt(possibleNextPositions.size());

                if(index == 0) currentDirection = Direction.RIGHT; else if (index == 1) currentDirection = Direction.DOWN; else currentDirection = Direction.UP;
                nextPosition = possibleNextPositions.get(index);
                System.out.println("RIGHT: "+currentDirection.toString()+","+ index);
                break;

            case LEFT:
                System.out.println("LEFT");
                possibleNextPositions.add(0,new Position(head.getMyX()- Game.ELEMENT_SIZE,head.getMyY()));
                possibleNextPositions.add(1,new Position(head.getMyX(), head.getMyY()- Game.ELEMENT_SIZE));
                possibleNextPositions.add(2,new Position(head.getMyX(), head.getMyY()+ Game.ELEMENT_SIZE));

                index = new Random().nextInt(possibleNextPositions.size());

                if(index == 0) currentDirection = Direction.LEFT; else if (index == 1) currentDirection = Direction.UP; else currentDirection = Direction.DOWN;
                nextPosition = possibleNextPositions.get(index);
                System.out.println("LEFT: "+currentDirection.toString()+","+ index);
                break;

             default: System.out.println("default");

        }

        if(occupiedPositions.contains(nextPosition) || theOtherSnake.getOccupiedPositions().contains(nextPosition) ||
                nextPosition.getX() == -Game.ELEMENT_SIZE|| nextPosition.getY() == -Game.ELEMENT_SIZE ||
                nextPosition.getX() == Game.WINDOW_SIZE|| nextPosition.getY() == Game.WINDOW_SIZE
                 ){
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
