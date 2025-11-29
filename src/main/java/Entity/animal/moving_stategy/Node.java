package Entity.animal.moving_stategy;

public class Node {
    private int h;//Các thông số của một node 
    private int g;
    private int f;
    private int row;
    private int col;
    private boolean is_block;   
    private Node parent;

    public Node(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }

    public int getH() {
        return h;
    }


    public void setH(int h) {
        this.h = h;
    }


    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }


    public int getRow() {
        return row;
    }


    public void setRow(int row) {
        this.row = row;
    }


    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isIs_block() {
        return is_block;
    }

    public void setIs_block(boolean is_block) {
        this.is_block = is_block;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    // Áp dụng cách tính k/cach mahanttan
    public void calculateHeuristic(Node final_node) {
        this.h = Math.abs(final_node.getRow() - getRow()) + Math.abs(final_node.getCol() - getCol());
    }

    public void setNodeData(Node current_node) {
        int g_cost = current_node.getG();
        setParent(current_node);//Tạo một Node con và đưa các thông số vào
        setG(g_cost+1);
        calculateFinalCost();
    }

    public boolean checkBetterPath(Node current_node) {
        int g_cost = current_node.getG();//Nếu như tại đó cost tốt hơn hiện tại 
        if (g_cost < getG()) {
            setNodeData(current_node);
            return true;
        }
        return false;
    }

    public void calculateFinalCost() {
        int final_cost = getG() + getH();
        setF(final_cost);
    }

    @Override
    public boolean equals(Object obj) {
        Node other = (Node) obj;
        return this.getRow() == other.getRow() && this.getCol() == other.getCol();//Khi cùng ô
    }

    @Override
    public String toString() {
        return "Node[row=" + row + ",col=" + col + "]";
    }
}
