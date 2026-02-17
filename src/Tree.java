import java.util.LinkedList;

public class Tree {
    private Node root;
    private Node current;

    public Tree(Node root) {
        this.root = root;
        current=this.root;
    }

    public boolean addNode(int numberToBeAdded,Node tempNode){
        boolean isBigger;
        if (numberToBeAdded == tempNode.getNumber()) {
            return false;
        }
        isBigger = numberToBeAdded > tempNode.getNumber();

        if (isBigger) {
            if (tempNode.getBigger()==null){
                tempNode.setBigger(new Node(numberToBeAdded,tempNode,null,null));
            }else {
                addNode(numberToBeAdded,tempNode.getBigger());
            }
        } else {
            if (tempNode.getSmaller()==null){
                tempNode.setSmaller(new Node(numberToBeAdded,tempNode,null,null));
            }else {
                addNode(numberToBeAdded,tempNode.getSmaller());
            }
        }
        return true;
    }

    private void addNode (Node nodeToAddTo, int numberToBeAdded){
        if (nodeToAddTo.getNumber()>numberToBeAdded){
            nodeToAddTo.setBigger(new Node(numberToBeAdded,nodeToAddTo,null,null));
        }else {
            nodeToAddTo.setSmaller(new Node(numberToBeAdded,nodeToAddTo,null,null));
        }

    }

    public Node findNode(int number){
        Node tempNode=root;
        while (tempNode!=null){
            if (tempNode.getNumber()>number){
                tempNode=tempNode.getBigger();
            }
        }
        return null;
    }
}
