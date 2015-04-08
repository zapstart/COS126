import java.awt.Color;

public class BlobFinder {
    private int p_width, p_height;
    private double[][] pixel_value; 
    private boolean[][] blob; 
    private Blob[][] blob_array; 
    private Luminance luminance = new Luminance(); 
    private int bead_num = 0;

    public class Blob {
        private int x, y; 
        private double mass;

        public Blob() {
            x = 0;
            y = 0;
            mass = 0;
        }
        
        public double[] get_center_mass() {
            double[] mass_center = new double[2];  
            mass_center[0] = x / mass;
            mass_center[1] = y / mass;
            return mass_center;
        }

        public void add(int i, int j) {
            x += i;
            y += j;
            mass++;
        }
        
        public double mass() {
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
        p_width = picture.width(); 
        p_height = picture.height(); 
        pixel_value = new double[p_width][p_height];  
        blob = new boolean[p_width][p_height]; 
        
        for (int i = 0; i < p_width; i++) {
            for (int j = 0; j < p_height; j++) {
                pixel_value[i][j] = luminance.lum(picture.get(i, j));
                blob[i][j] = (pixel_value[i][j] <= tau) ? true : false; 
            }
        }
    }
    
    private void find_blob_dfs() {
        blob_array = new Blob[p_width][p_height]; 
        for (int i = 0; i < p_width; i++) {
            for (int j = 0; j < p_height; j++) {
                blob_array[i][j] = new Blob();
                single_blob_dfs(blob, i, j, blob_array[i][j]);
                //if (blob_array[i][j].mass() != 0) System.out.println(blob_array[i][j].toString());  
                if (blob_array[i][j].mass() != 0) bead_num++;
            }
        }
    }
    
    private void single_blob_dfs(boolean[][] blob, int x, int y, Blob new_blob) {
        if ((x < 0) || (x > p_width - 1)) return; 
        if ((y < 0) || (y > p_height - 1)) return; 
        if (blob[x][y]) return;
        new_blob.add(x, y);
        blob[x][y] = true;
        single_blob_dfs(blob, x + 1, y, new_blob); 
        single_blob_dfs(blob, x, y + 1, new_blob); 
        single_blob_dfs(blob, x - 1, y, new_blob); 
        single_blob_dfs(blob, x, y - 1, new_blob); 
    }
    
    private Blob[] getBeads(int P) {
        Blob[] beads = new Blob[bead_num];
        for (int i = 0; i < p_width; i++) {
            for (int j = 0; j < p_height; j++) {
                if (blob_array[i][j].mass() > P) {
                    beads[--bead_num] = blob_array[i][j];
                    System.out.println(beads[bead_num]);
                }
            }
        } 
        return beads;
    }

    public static void main(String args[]) {
        Picture picture = new Picture("frame00001.jpg");
        BlobFinder blobfinder = new BlobFinder(picture, 180);
        blobfinder.find_blob_dfs();  
        blobfinder.getBeads(25);  
    }
    
    
    
    //public Blob[] getBeads(int P) {
    //}
}
