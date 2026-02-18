//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int number;
        Node node= new Node((int)(Math.random()*10),null,null,null);
        Tree tree = new Tree(node);
        for (int i = 0; i < 100; i++) {
            tree.addNode((int)(Math.random()*10));


        }
        tree.printTreeMatrix();
        tree.printTree();


    }
}