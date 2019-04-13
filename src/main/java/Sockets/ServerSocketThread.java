package Sockets;

import Games.MyExample;
import Snakes.Snake;
import Utils.Position;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketThread extends Thread {

    public static final int PORT = 9090;
    private static ServerSocket serverSocket;

    public Position position;
    public Position clientPosition;



    public Position getClientPosition() {
        return clientPosition;
    }


    public ServerSocketThread(Position position) throws IOException {
        if(serverSocket == null) serverSocket = new ServerSocket(PORT);
        this.position = position;

    }

    @Override
    public void run() {


            Socket clientSocket = null;
            ObjectOutputStream outToClient = null;
            ObjectInputStream inFromClient = null;
            try {
                clientSocket = serverSocket.accept();

               // System.out.println("I AM HERE ACCEPT");
                outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
                inFromClient = new ObjectInputStream(clientSocket.getInputStream());
              //System.out.println("SERVER SNAKE:: "+MyExample.getServerSnake());
                outToClient.writeObject(position);
                Position clientPosition = (Position)inFromClient.readObject();
                // System.out.println("CLIENT SNAKE"+clientSnake);
                this.clientPosition = clientPosition;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }





    }
}
