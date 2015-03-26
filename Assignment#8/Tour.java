public class Tour {
    private int N;
    private Node first_node;

    public class Node {
        private Point p;
        private Node next;
    }
    
    public Tour() {
        first_node = null;
        N = 0;
    }
    
    public Tour(Point a, Point b, Point c, Point d) {
    }

    public void show() {
        Node next_node = new Node();
        Node current_node = new Node();
        next_node = first_node;
        current_node = first_node;
        while (current_node.next != null) {
            next_node.current_node.next;
            System.out.println(current_node.p.toString());
            current_node = next_node;
        }
    }

    public void draw() {
        double tour_distance = 0; 
        Node next_node = new Node();
        Node current_node = new Node();
        next_node = first_node;
        current_node = first_node;
        while (current_node.next != null) {
            next_node.current_node.next;
            tour_distance += current_node.p.drawTo(next_node.p);
            current_node = next_node;
        }
          
    }

    public int size() {
        return N;
    }

    public double distance() {
        double tour_distance = 0; 
        Node next_node = new Node();
        Node current_node = new Node();
        next_node = first_node;
        current_node = first_node;
        while (current_node.next != null) {
            next_node.current_node.next;
            tour_distance += current_node.p.distanceTo(next_node.p);
            current_node = next_node;
        }
    }

    public void insertNearest(Point p) {
    }

    public void insertSmallest(Point p) {
    }
}
