package Utils;

import Games.Game;
import Shapes.Egg;
import Snakes.Snake;
import javafx.scene.paint.Color;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class MyTree {


    Node root;

    private  List<Position> createdNodes = new ArrayList<>();


    private static Snake snake1, snake2;

    public Position getRoot() {
        return root.getPosition();
    }

    public MyTree(Snake snake1, Snake snake2) {

        this.snake1 = snake1;
        this.snake2 = snake2;

    }


    // an inner class to represent a node at the interface

    static class Node {



        //the position of the node
        private Position position ;
        private Node nodeUp;
        private Node nodeDown;
        private Node nodeLeft;
        private Node nodeRight;


        public String toString() {

            if (this == null) return "";
          return position.toString();
        }



        public boolean isAbove(Position position) {
            return this.getPosition().getY() < position.getY();

        }

        public boolean isBellow(Position position) {
            return this.getPosition().getY() > position.getY();
        }

        public boolean isRight(Position position) {
            return this.getPosition().getX() > position.getX();

        }

        public boolean isLeft(Position position) {
            return this.getPosition().getX() < position.getX();
        }


        public Node getNodeUp() {
            return nodeUp;
        }

        public Node getNodeDown() {
            return nodeDown;
        }

        public Node getNodeLeft() {
            return nodeLeft;
        }

        public Node getNodeRight() {
            return nodeRight;
        }




        public Node(Position position) {
            this.position = position;




        }

        public Position getPosition() {
            return position;
        }




    }



    // this function s used to add new position to the given tree (node)

    public Node add(Position position, Node node){
        if(node == null){
            if(isUsed(position))
                return null;
            createdNodes.add(position);
            return new Node(position);
        }


        if(node.isBellow(position)){
            node.nodeUp = add(position, node.nodeUp);
        }
        else if(node.isAbove(position)){
            node.nodeDown = add(position, node.nodeDown);
        }
        else if (node.isRight(position)){
            node.nodeLeft = add(position, node.nodeLeft);
        }
        else if(node.isLeft(position)){
            node.nodeRight = add(position, node.nodeRight);
        }

       return node;

    }

    //a function to have reference of the root node
    public void add(Position position) {
        this.root = add(position, root);
    }

    public   void constructTree(Position position) {


        if(isUsed(position)) return;
        add(position);
        constructTree(position.above());
        constructTree(position.bellow());
        constructTree(position.right());
        constructTree(position.left());



    }



    public  boolean isUsed(Position position) {

        return createdNodes.contains(position) || snake1.getOccupiedPositions().contains(position) || snake2.getOccupiedPositions().contains(position) || position.isExtreme();
    }




    public static int treeDept(Node n) {
        if( n == null) return 0;

        return 1 + max(treeDept(n.nodeUp), treeDept(n.nodeDown), treeDept(n.nodeRight), treeDept(n.nodeLeft));
    }

    public static int max(int n1, int n2, int n3, int n4) {
        return Math.max(Math.max(n1, n2), Math.max(n3, n4));
    }


    public static MyTree maxDeptTree(MyTree t1,MyTree t2,MyTree t3,MyTree t4){

        MyTree max = t1;
        if(treeDept(max.root)< treeDept(t2.root))
            max = t2;
        if(treeDept(max.root)< treeDept(t3.root))
            max = t3;
        if(treeDept(max.root)< treeDept(t4.root))
            max = t4;

        return max;

    }








}
