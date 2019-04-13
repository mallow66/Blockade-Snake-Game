package Sockets;
import Snakes.Snake;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MySocket {

    public static final int PORT = 9090;
    public static Snake serverSocket(Snake serverSnake) throws IOException, ClassNotFoundException {


        ServerSocket serverSocket = new ServerSocket(PORT);
        while(true) {
            Socket clientSocket = serverSocket.accept();

            ObjectOutputStream outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream());

            outToClient.writeObject(serverSnake);

            Snake clientSnake = (Snake)inFromClient.readObject();
            return clientSnake;





        }

    }


    public static Snake clientSocket(Snake clientSnake) throws IOException, ClassNotFoundException {
        Socket clientSocket = new Socket("localhost", PORT);

        ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
        ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
        outToServer.writeObject(clientSnake);
        Snake serverSnake = (Snake)inFromServer.readObject();
        return serverSnake;
    }


}
