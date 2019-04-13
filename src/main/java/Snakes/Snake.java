package Snakes;

import Games.Game;
import Games.MyExample;
import Shapes.Egg;
import Shapes.Eye;
import Shapes.Head;
import Shapes.Tail;
import Sockets.ClientSocketThread;
import Sockets.ServerSocketThread;
import Utils.Direction;
import Utils.NextDirection;
import Utils.Position;
import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by brahim on 10/28/18.
 */
public   class Snake  implements Serializable {
    public static ServerSocket serverSocket;
    public static final int PORT = 9091;
    public static final String HOST ="localhost";
    public static Socket clientSocket;

    protected Head head;
    protected List<Node> snakeBody;
    protected Tail tail;
    protected List<Position> occupiedPositions;

    protected Snake theOtherSnake;

    protected boolean isStopped;

    public Snake() {

    }

    public Snake(Head head, Tail tail) {
        this.head = head;
        this.tail = tail;

        isStopped = true;

        snakeBody = new ArrayList<>();
        snakeBody.add(0,head);
        snakeBody.add(tail);

        //add the occupied positions at start
        occupiedPositions = new ArrayList<>();
        occupiedPositions.add(head.getPosition());
        occupiedPositions.add(tail.getPosition());
    }

    public Head getHead(){
        return this.head;
    }

    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }

    public Snake getTheOtherSnake() {
        return theOtherSnake;
    }

    public void setTheOtherSnake(Snake theOtherSnake) {
        this.theOtherSnake = theOtherSnake;
    }

    public boolean isStopped() {
        return isStopped;
    }

    public List<Position> getOccupiedPositions() {
        return occupiedPositions;
    }

    public synchronized List<Node> getSnakeBody() {
        return snakeBody;
    }

    public void move() {
        NextDirection direction = new NextDirection();
        isStopped = false;


        Head currentHead= this.getHead();
        Position nextPosition = new Position(currentHead.getMyX() + direction.getDx(), currentHead.getMyY() + direction.getDy());



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


        snakeBody.add(1, new Egg(head.getPosition(), Color.RED));

        occupiedPositions.add(nextPosition);

        currentHead.setFill(Color.RED);

        this.head = theNewHead;




    }

    public void serverMove() throws IOException, InterruptedException {


        NextDirection direction = new NextDirection();
        isStopped = false;


        Head currentHead= this.getHead();
        Position nextPosition = new Position(currentHead.getMyX() + direction.getDx(), currentHead.getMyY() + direction.getDy());

       ServerSocketThread t = new ServerSocketThread(nextPosition);
       t.start();
       t.join();

       theOtherSnake.move(t.getClientPosition());




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

        snakeBody.add(0, theNewHead);
        snakeBody.remove(1);


        snakeBody.add(1, new Egg(head.getPosition(), Color.RED));

        occupiedPositions.add(nextPosition);

        currentHead.setFill(Color.RED);

        this.head = theNewHead;




    }

    public void clientMove() throws IOException, InterruptedException {


        NextDirection direction = new NextDirection();
        isStopped = false;

        Head currentHead= this.getHead();
        Position nextPosition = new Position(currentHead.getMyX() + direction.getDx(), currentHead.getMyY() + direction.getDy());

        ClientSocketThread t = new ClientSocketThread(nextPosition);

        t.start();
        t.join();

        theOtherSnake.move(t.getServerPosition());



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

        snakeBody.add(0, theNewHead);
        snakeBody.remove(1);


        snakeBody.add(1, new Egg(head.getPosition(), Color.RED));

        occupiedPositions.add(nextPosition);

        currentHead.setFill(Color.RED);

        this.head = theNewHead;



    }


    public void move(Position nextPosition) {
        Head currentHead= this.getHead();
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


        snakeBody.add(1, new Egg(head.getPosition(), Color.RED));

        occupiedPositions.add(nextPosition);

        currentHead.setFill(Color.RED);

        this.head = theNewHead;

    }


    @Override
    public String toString(){
        return head.getPosition().toString()+" -->"+ snakeBody.size();
    }


}
