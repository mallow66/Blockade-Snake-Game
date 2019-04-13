package Sockets;

import Games.MyExample;
import Snakes.Snake;
import Utils.Position;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketThread extends Thread {

    public  static Socket clientSocket;
    public static final String HOST = "192.168.1.59";
    public static final int PORT = 9090;

    public Position position;
    public Position serverPosition;

    public static int i = 0;
    public ClientSocketThread(Position position) throws IOException {
        ClientSocketThread.clientSocket = new Socket("localhost", PORT);
        this.position = position;
    }

    public Position getServerPosition() {
        return serverPosition;
    }


    @Override
    public void run() {

        ObjectInputStream inFromServer;
        ObjectOutputStream outToServer;
        try {
             outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
             inFromServer = new ObjectInputStream(clientSocket.getInputStream());


             outToServer.writeObject(position);

             Position serverPosition = (Position) inFromServer.readObject();
          //   System.out.println("Server Snake "+ serverSnake);
             this.serverPosition = serverPosition;
            // System.out.println(i++);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
