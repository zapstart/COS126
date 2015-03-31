public class Tour {
    private int N;
    private Node first_node;

    // Node constructor 
    public class Node {
        private Point p;
        private Node next;

        public Node() {
        }
        
        public Node(Point p) { 
            this.p = p; this.next = null;
        }
    }
    
    // Tour constructor 
    public Tour() {
        first_node = null;
        N = 0;
    }
    
    // show all points in tour
    public void show() {
        Node next_node = new Node();
        Node current_node = new Node();
        next_node = first_node;
        current_node = first_node;
        
        if (N != 0) {
            while (next_node != null) {
                next_node = current_node.next;
                System.out.println(current_node.p.toString());
                current_node = next_node;
            }
        }
        else {
            System.out.println("No point in tour!!!"); 
        }
    }
    
    // draw all points and lines in tour
    public void draw() {
        Node next_node = new Node();
        Node current_node = new Node();
        next_node = first_node;
        current_node = first_node;
        
        if (N == 0) {
            System.out.println("No point in tour!!!"); 
        }
        else if (N == 1) {
            current_node.p.draw();
        }
        else {
            while (current_node.next != null) {
                next_node = current_node.next;
                current_node.p.draw();
                next_node.p.draw();
                current_node.p.drawTo(next_node.p);    
                current_node = next_node;
            }
            // draw last node and line between last and first nodes
            current_node.p.draw();
            current_node.p.drawTo(first_node.p);
        }
    }
    
    // return number of points in tour
    public int size() {
        return N;
    }

    // return totall distance in tour 
    public double distance() {
        double tour_distance = 0;
        Node next_node = new Node();
        Node current_node = new Node();
        next_node = first_node;
        current_node = first_node;
        
        if (N == 0 || N == 1) {
            return 0; 
        }
        else {
            while (current_node.next != null) {
                next_node = current_node.next;
                tour_distance += current_node.p.distanceTo(next_node.p);
                current_node = next_node;
            }
        }
        
        // add distance between last and first nodes
        tour_distance += current_node.p.distanceTo(first_node.p); 
        return tour_distance;
    }
    
    public void insertNearest(Point p) {
        double smallest_distance = 0;
        Node nearest_node = new Node();
        Node next_node = new Node();
        Node current_node = new Node();
        Node new_node = new Node(p); 
        
        if (N == 0) {
            first_node = new_node;
            N++;
        }
        else {
            next_node = first_node;
            current_node = first_node;
            
            // first node as base case for the smallest distance 
            smallest_distance = first_node.p.distanceTo(p); 
            nearest_node = first_node;
            
            while (next_node != null) {
                if (current_node.p.distanceTo(p) < smallest_distance) {
                    nearest_node = current_node;
                    smallest_distance = current_node.p.distanceTo(p); 
                }
                next_node = current_node.next;
                current_node = next_node;
            }
            N++;
            new_node.next = nearest_node.next; 
            nearest_node.next = new_node;
        }
    }

    public void insertSmallest(Point p) {
        Node nearest_node = new Node();
        Node next_node = new Node();
        Node current_node = new Node();
        Node new_node = new Node(p); 
        double smallest_distance = 0;
        double new_distance = 0;
        double current_tour_distance; 
        
        // select smallest increase pattern
        // n1 --- n2 ----> n1 -- n5 -- n2 -- n1 or n1 -- n2 -- n5 -- n1 
        //     | 
        //     n5   
        if (N == 0) {
            first_node = new_node;
            N++;
        }
        else if (N == 1){
            next_node = new_node;
            first_node.next = next_node;
            N++;
        }
        else {
            next_node = first_node;
            current_node = first_node;
            current_tour_distance = this.distance(); 

            // first node as base case for the smallest distance 
            smallest_distance = current_tour_distance - current_node.p.distanceTo(current_node.next.p) 
                                              + current_node.p.distanceTo(p) 
                                              + p.distanceTo(current_node.next.p);
            nearest_node = first_node;

            while(next_node != null) {
                if (current_node.next != null) {
                    new_distance = current_tour_distance - current_node.p.distanceTo(current_node.next.p) 
                                                         + current_node.p.distanceTo(p)
                                                         + p.distanceTo(current_node.next.p);

                    if (new_distance < smallest_distance) {
                        smallest_distance = new_distance;
                        System.out.println(smallest_distance);
                        nearest_node = current_node;  
                    }
                    next_node = current_node.next;
                    current_node = next_node;
                }
                else {
                    
                    // this case is different from other nodes, the last nodes will connected to the first node
                    if (current_tour_distance - current_node.p.distanceTo(first_node.p) 
                                              + current_node.p.distanceTo(p) 
                                              + p.distanceTo(first_node.p) < smallest_distance) {
                        
                        smallest_distance = current_tour_distance - current_node.p.distanceTo(first_node.p) 
                                              + current_node.p.distanceTo(p) 
                                              + p.distanceTo(first_node.p); 

                        nearest_node = current_node;
                    }
                    next_node = current_node.next;
                }
            }
            new_node.next = nearest_node.next;
            nearest_node.next = new_node;
            N++;
        }
    }
    
    public static void main(String[] args) {
        Tour tour = new Tour();        
        int w = StdIn.readInt(); 
        int h = StdIn.readInt();
        StdDraw.setCanvasSize(w, h);
        StdDraw.setXscale(0, w);
        StdDraw.setYscale(0, h);
        StdDraw.setPenRadius(.005);
        
        while (!StdIn.isEmpty()) {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            Point p = new Point(x, y);
            tour.insertNearest(p);
            //tour.insertSmallest(p);
        }
        
        tour.show();
        System.out.println("Tour distance = " + tour.distance());
        System.out.println("Number of points = " + tour.size());
        tour.draw();
    }
}
