package Games;

import Shapes.Head;
import Shapes.Tail;
import Snakes.RandomSnake;
import Snakes.SmartSnake;
import Snakes.Snake;
import Snakes.VerySmartSnake;
import Sockets.ClientSocketThread;
import Sockets.MySocket;
import Utils.Direction;
import Utils.NextDirection;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by brahim on 10/28/18.
 */
public class MyExample extends Game {

    static Snake snake2;
    static Snake snake1;

    List<Node> allNodes;

    public MyExample() throws IOException {


        Head head2= new Head(WINDOW_SIZE/1.2, WINDOW_SIZE/1.2, Direction.DOWN , Color.AQUAMARINE);
        Tail tail2= new Tail(WINDOW_SIZE/1.2, WINDOW_SIZE/1.2 + ELEMENT_SIZE,Direction.DOWN, Color.RED);

        snake2 = new Snake(head2, tail2);
        snake2.setStopped(false);

        allNodes = new ArrayList<>();
    }

    public synchronized static void setClientSnake(Snake clientSnake) {
        snake1 = clientSnake;
    }

    public synchronized  static void setServerSnake(Snake serverSnake) {
        snake2 = serverSnake;
    }

    public synchronized static Snake getServerSnake() {
        return MyExample.snake2;
    }
    public synchronized static Snake getClientSnake() {
        return MyExample.snake1;
    }

    @Override
    public Collection<Node> gameStep()  {

        // here we gonna run the thread socket.
        if(snake1 == null) {
            Head head1= new Head(WINDOW_SIZE/6, WINDOW_SIZE/6,Direction.DOWN, Color.AQUAMARINE);
            Tail tail1= new Tail(WINDOW_SIZE/6, WINDOW_SIZE/6-ELEMENT_SIZE, Direction.UP, Color.BLACK);

            System.out.println(Game.gameType);
            if(Game.gameType.equals(Game.CONTROLLED_SNAKE))
                snake1 = new Snake(head1, tail1);
            if(Game.gameType.equals(Game.RANDOM_SNAKE))
                snake1 = new RandomSnake(head1, tail1);
            if(Game.gameType.equals(Game.SMART_SNAKE))
                snake1 = new SmartSnake(head1, tail1);
            if(Game.gameType.equals(Game.VERY_SMART_SNAKE))
                snake1 = new VerySmartSnake(head1, tail1);

            snake1.setTheOtherSnake(snake2);
            snake2.setTheOtherSnake(snake1);
        }




          allNodes.removeAll(snake1.getSnakeBody());
          allNodes.removeAll(snake2.getSnakeBody());


      //  allNodes = new ArrayList<>();

       /* if((snake1.isStopped() ) && (snake1.getSnakeBody().size()> 2 )  || (snake2.isStopped() ) && (snake2.getSnakeBody().size()> 2  )  ) {
            Game.game = false;


        }*/

       if(snake1.isStopped() &&  snake1.getSnakeBody().size() > 2 && !snake2.isStopped() ){
           Game.gameSnake1 = false;
       }

       if(snake2.isStopped() && snake2.getSnakeBody().size() > 2 && !snake1.isStopped())
           Game.gameSnake2 = false;

        //allNodes = new ArrayList<>();


      // NextDirection  direction = new NextDirection();
        if(Game.gameType.equals(Game.CONTROLLED_SNAKE)){
            if(Game.client) {
                try {
                    snake1.clientMove();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(Game.server) {
                try {
                    snake2.serverMove();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            snake2.move();
            snake1.move();
        }





      //  System.out.println("Server Snake" + snake2);
       // System.out.println("Client Snake"+ snake1);






        allNodes.addAll(snake1.getSnakeBody());
        allNodes.addAll(snake2.getSnakeBody());
        //System.out.println("SERVER BODY :: "+ snake2);
        //System.out.println("CLIENT BODY"+ snake1);



        return allNodes;
    }

    public Snake snakeFromServer(){

        return null;
    }

    public Snake snakeFromClient(){

        return null;
    }


    public static void main(String [] args){
        Application.launch(args);

    }
}
