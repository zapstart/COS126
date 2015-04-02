public class BlobFinder {
    
    public class Blob {
        private int x, y; 
        private int mass;

        public Blob() {
            x = 0;
            y = 0;
            mass = 0;
        }
        
        public double[] get_center_mass() {
            double[] mass_center = new double[2];  
            mass_center[0] = mass / x;
            mass_center[1] = mass / y;
            return mass_center;
        }

        public void add(int i, int j) {
            x += i;
            y += j;
            mass++;
        }
        
        public int mass() {
            return mass;
        }

        public double distance(double x_1, double y_1, double x_2, double y_2) {
            return Math.sqrt((x_1 - x_2) * (x_1 - x_2) + (y_1 - y_2) * (y_1 - y_2)); 
        }
        
        public double distanceTo(Blob b) {
            double center_of_mass_x   = get_center_mass()[0];
            double center_of_mass_y   = get_center_mass()[1];
            double center_of_mass_b_x = b.get_center_mass()[0];
            double center_of_mass_b_y = b.get_center_mass()[1];

            return distance(center_of_mass_x, center_of_mass_y, center_of_mass_b_x, center_of_mass_b_y);
        }

        public String toString() {
            return(mass + " (" + get_center_mass()[0]+ ", " + get_center_mass()[1]+ ")"); 
        }
    }
    
    public BlobFinder(Picture picture, double tau) {
    }

    //public Blob[] getBeads(int P) {
    //}
}
