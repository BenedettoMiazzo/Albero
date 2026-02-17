public class Node {
    private int number;
    private Node previous;
    private Node bigger;
    private Node smaller;

    public Node(int number, Node previous, Node bigger, Node smaller) {
        this.number = number;
        this.previous = previous;
        this.bigger = bigger;
        this.smaller = smaller;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getBigger() {
        return bigger;
    }

    public void setBigger(Node bigger) {
        this.bigger = bigger;
    }

    public Node getSmaller() {
        return smaller;
    }

    public void setSmaller(Node smaller) {
        this.smaller = smaller;
    }
}
