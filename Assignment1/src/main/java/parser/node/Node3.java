package parser.node;

public class Node3 extends Node{
    Node left;
    Node right;
    Node head;

    //constructor
    public Node3(Node left, Node head, Node right)
    {
        this.left = left;
        this.head = head;
        this.right = right;
    }

    //getter for head
    public Node getHead() {
        return head;
    }

    //getter for left
    public Node getLeft() {
        return left;
    }

    //getter for right
    public Node getRight() {
        return right;
    }
}
