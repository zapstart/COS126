public class Tour {
    private int N;
    private Node first_node;

    public class Node {
        private Point p;
        private Node next;

        public Node() {
        }
        public Node(Point p) {
            this.p = p;
            this.next = null;
        }
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
            next_node = current_node.next;
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
            next_node = current_node.next;
            tour_distance += current_node.p.distanceTo(next_node.p);
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
            next_node = current_node.next;
            tour_distance += current_node.p.distanceTo(next_node.p);
            current_node = next_node;
        }
        return tour_distance;

    }

    public void insertNearest(Point p) {
        double smallest_distance = 0;
        Node nearest_node = new Node();
        Node next_node = new Node();
        Node current_node = new Node();
        Node new_node = new Node(p); 
        next_node = first_node;
        current_node = first_node;
        
        if (first_node != null) {
            while (current_node.next != null) {
                if (current_node.p.distanceTo(p) < smallest_distance) {
                    nearest_node = current_node;
                    smallest_distance = current_node.p.distanceTo(p); 
                }
                next_node = current_node.next;
                current_node = next_node;
            }
        }
        else {
            first_node = new Node(p); 
        }
        new_node.next = nearest_node.next; 
        nearest_node.next = new_node;
    }

   // public void insertSmallest(Point p) {
   // }

    public static void main(String[] args) {
        Tour tour = new Tour();        
        while (!StdIn.isEmpty()) {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            Point p = new Point(x, y);
            tour.insertNearest(p);
            tour.show();
        }
    }
}
