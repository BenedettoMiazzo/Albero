import java.util.LinkedList;

public class Tree {
    private Node root;


    public Node getRoot() {
        return root;
    }

    public Tree(Node root) {
        this.root = root;
    }

    public boolean addNode(int numberToBeAdded){
        return addNode(numberToBeAdded,root);
    }

    public int getNumberOfNodes(){
        return Node.keyCounter;
    }

    private boolean addNode(int numberToBeAdded,Node tempNode){
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


    ///

    public void printTree() {
        printTree(root, "", true);
    }

    private void printTree(Node node, String prefix, boolean isLast) {
        if (node == null) {
            return;
        }

        // Stampa il prefisso + ramo
        System.out.println(prefix + (isLast ? "└── " : "├── ") + node.getNumber());

        // Calcola il nuovo prefisso per i figli
        String newPrefix = prefix + (isLast ? "    " : "│   ");

        // Se ha entrambi i figli
        if (node.getSmaller() != null && node.getBigger() != null) {
            printTree(node.getBigger(), newPrefix, false);
            printTree(node.getSmaller(), newPrefix, true);

        }
        // Solo sinistro
        else if (node.getSmaller() != null) {
            printTree(node.getSmaller(), newPrefix, true);
        }
        // Solo destro
        else if (node.getBigger() != null) {
            printTree(node.getBigger(), newPrefix, true);
        }
    }

    public void printTreeUntil(int numberToBeFound) {
        printTreeUntil(root, "", true,numberToBeFound);
    }

    private void printTreeUntil(Node node, String prefix, boolean isLast,int numberToBeFound) {
        if (node == null) {
            return;
        }


        // Stampa il prefisso + ramo
        System.out.println(prefix + (isLast ? "└── " : "├── ") + node.getNumber());

        // Calcola il nuovo prefisso per i figli
        String newPrefix = prefix + (isLast ? "    " : "│   ");

        // Se ha entrambi i figli
        if (node.getSmaller() != null && node.getBigger() != null) {
            printTreeUntil(node.getSmaller(), newPrefix, false,numberToBeFound);
            printTreeUntil(node.getBigger(), newPrefix, true,numberToBeFound);
        }
        // Solo sinistro
        else if (node.getSmaller() != null) {
            printTreeUntil(node.getSmaller(), newPrefix, true,numberToBeFound);
        }
        // Solo destro
        else if (node.getBigger() != null) {
            printTreeUntil(node.getBigger(), newPrefix, true,numberToBeFound);
        }
    }

    public void printTreeMatrix() {

        if (root == null) return;

        // =========================
        // 1️⃣ Calcolo altezza (iterativo)
        // =========================
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        int height = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            height++;

            for (int i = 0; i < size; i++) {
                Node n = queue.poll();
                if (n.getSmaller() != null) queue.add(n.getSmaller());
                if (n.getBigger() != null) queue.add(n.getBigger());
            }
        }

        // =========================
        // 2️⃣ Creazione matrice
        // =========================
        int rows = height * 2;
        int cols = (int) Math.pow(2, height) * 2;

        char[][] matrix = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = ' ';
            }
        }

        // =========================
        // 3️⃣ Riempimento iterativo
        // =========================
        class Frame {
            Node node;
            int row, col, offset;
            Frame(Node n, int r, int c, int o) {
                node = n; row = r; col = c; offset = o;
            }
        }

        LinkedList<Frame> stack = new LinkedList<>();
        stack.push(new Frame(root, 0, cols / 2, cols / 4));

        while (!stack.isEmpty()) {
            Frame f = stack.pop();
            Node node = f.node;

            if (node == null) continue;

            // Scrive numero
            String value = String.valueOf(node.getNumber());
            for (int i = 0; i < value.length(); i++) {
                matrix[f.row][f.col + i] = value.charAt(i);
            }

            // Figlio destro
            if (node.getBigger() != null) {
                matrix[f.row + 1][f.col + f.offset / 2] = '\\';
                stack.push(new Frame(
                        node.getBigger(),
                        f.row + 2,
                        f.col + f.offset,
                        f.offset / 2
                ));
            }

            // Figlio sinistro
            if (node.getSmaller() != null) {
                matrix[f.row + 1][f.col - f.offset / 2] = '/';
                stack.push(new Frame(
                        node.getSmaller(),
                        f.row + 2,
                        f.col - f.offset,
                        f.offset / 2
                ));
            }
        }

        // =========================
        // 4️⃣ Stampa
        // =========================
        for (int i = 0; i < rows; i++) {
            System.out.println(new String(matrix[i]));
        }
    }



}
