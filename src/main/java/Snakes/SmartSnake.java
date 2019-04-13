package Snakes;

import Games.Game;
import Shapes.Egg;
import Shapes.Head;
import Shapes.Tail;
import Utils.Direction;
import Utils.Position;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SmartSnake extends Snake implements Serializable {

    public static Direction currentDirection;

    public SmartSnake(Head head, Tail tail) {
        super(head, tail);
        currentDirection = Direction.DOWN;
    }

    public void move() {
        isStopped = false;
        List<Position> intersection;
        List<Position> possibleNextPositions = new ArrayList<>(3);
        List<Position> allOccupiedPositions = new ArrayList<>();
        allOccupiedPositions.addAll(occupiedPositions);
        allOccupiedPositions.addAll(theOtherSnake.getOccupiedPositions());
        Position nextPosition=null;
        // System.out.println(currentDirection.toString());
        int index;
        switch (currentDirection) {
            case DOWN:
                System.out.println("DOWN");
                possibleNextPositions.add(0,new Position(head.getMyX(), head.getMyY()+ Game.ELEMENT_SIZE));
                possibleNextPositions.add(1,new Position(head.getMyX()+ Game.ELEMENT_SIZE,head.getMyY()));
                possibleNextPositions.add(2,new Position(head.getMyX()- Game.ELEMENT_SIZE,head.getMyY()));


                //the possible next position that are not occupied::
               intersection =  possibleNextPositions.stream().filter(position -> !allOccupiedPositions.contains(position))
                       .filter(position -> !position.isExtreme())
                       .collect(Collectors.toList());

               //if there is not next possible solution that is occupied
               if(intersection.isEmpty()){
                   index = new Random().nextInt(possibleNextPositions.size());
                   nextPosition = possibleNextPositions.get(index);
               }
               else{
                   index = new Random().nextInt(intersection.size());
                   nextPosition = intersection.get(index);
               }

                if(head.getMyY() > nextPosition.getY()) currentDirection = Direction.UP;
                if(head.getMyY() < nextPosition.getY()) currentDirection = Direction.DOWN;
                if(head.getMyX() > nextPosition.getX()) currentDirection = Direction.LEFT;
                if(head.getMyX() < nextPosition.getX()) currentDirection = Direction.RIGHT;
                break;

            case UP:
                System.out.println("UP");
                possibleNextPositions.add(0,new Position(head.getMyX(), head.getMyY()- Game.ELEMENT_SIZE));
                possibleNextPositions.add(1,new Position(head.getMyX()+ Game.ELEMENT_SIZE,head.getMyY()));
                possibleNextPositions.add(2,new Position(head.getMyX()- Game.ELEMENT_SIZE,head.getMyY()));

                intersection =  possibleNextPositions.stream().filter(position -> !allOccupiedPositions.contains(position))
                        .filter(position -> !position.isExtreme())
                        .collect(Collectors.toList());
                //if there is not next possible solution that is occupied
                if(intersection.isEmpty()){
                    index = new Random().nextInt(possibleNextPositions.size());
                    nextPosition = possibleNextPositions.get(index);
                }
                else{
                    index = new Random().nextInt(intersection.size());
                    nextPosition = intersection.get(index);
                }

                if(head.getMyY() > nextPosition.getY()) currentDirection = Direction.UP;
                if(head.getMyY() < nextPosition.getY()) currentDirection = Direction.DOWN;
                if(head.getMyX() > nextPosition.getX()) currentDirection = Direction.LEFT;
                if(head.getMyX() < nextPosition.getX()) currentDirection = Direction.RIGHT;

                break;

            case RIGHT:
                System.out.println("RIGHT");
                possibleNextPositions.add(0,new Position(head.getMyX()+ Game.ELEMENT_SIZE,head.getMyY()));
                possibleNextPositions.add(1,new Position(head.getMyX(), head.getMyY()+ Game.ELEMENT_SIZE));
                possibleNextPositions.add(2,new Position(head.getMyX(), head.getMyY()- Game.ELEMENT_SIZE));


                intersection =  possibleNextPositions.stream().filter(position -> !allOccupiedPositions.contains(position))
                        .filter(position -> !position.isExtreme())
                        .collect(Collectors.toList());



                //if there is not next possible solution that is occupied
                if(intersection.isEmpty()){
                    index = new Random().nextInt(possibleNextPositions.size());
                    nextPosition = possibleNextPositions.get(index);
                }
                else{
                    index = new Random().nextInt(intersection.size());
                    nextPosition = intersection.get(index);
                }

                if(head.getMyY() > nextPosition.getY()) currentDirection = Direction.UP;
                if(head.getMyY() < nextPosition.getY()) currentDirection = Direction.DOWN;
                if(head.getMyX() > nextPosition.getX()) currentDirection = Direction.LEFT;
                if(head.getMyX() < nextPosition.getX()) currentDirection = Direction.RIGHT;
                break;

            case LEFT:
                System.out.println("LEFT");
                possibleNextPositions.add(0,new Position(head.getMyX()- Game.ELEMENT_SIZE,head.getMyY()));
                possibleNextPositions.add(1,new Position(head.getMyX(), head.getMyY()- Game.ELEMENT_SIZE));
                possibleNextPositions.add(2,new Position(head.getMyX(), head.getMyY()+ Game.ELEMENT_SIZE));


                intersection =  possibleNextPositions.stream().filter(position -> !allOccupiedPositions.contains(position))
                        .filter(position -> !position.isExtreme())
                        .collect(Collectors.toList());

                //if there is not next possible solution that is occupied
                if(intersection.isEmpty()){
                    index = new Random().nextInt(possibleNextPositions.size());
                    nextPosition = possibleNextPositions.get(index);
                }
                else{
                    index = new Random().nextInt(intersection.size());
                    nextPosition = intersection.get(index);
                }

                if(head.getMyY() > nextPosition.getY()) currentDirection = Direction.UP;
                if(head.getMyY() < nextPosition.getY()) currentDirection = Direction.DOWN;
                if(head.getMyX() > nextPosition.getX()) currentDirection = Direction.LEFT;
                if(head.getMyX() < nextPosition.getX()) currentDirection = Direction.RIGHT;
                break;

            default: System.out.println("default");

        }

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

        snakeBody.add(1, new Egg(head.getPosition(), Color.RED));
        occupiedPositions.add(nextPosition);
        this.head.setFill(Color.RED);
        this.head = theNewHead;



    }
}
