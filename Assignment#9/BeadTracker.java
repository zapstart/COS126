public class BeadTracker {
    
    public static void main(String[] args) {
        
        // we do not need to store all the picture and all blobs
        // we only need to generate two Picture and BlobFinder instances
        Picture previous_picture, current_picture;
        BlobFinder previous_blobfinder, current_blobfinder; 
        BlobFinder.Blob[] previous_bead, current_bead;
        int previous_bead_num = 0;
        int current_bead_num = 0;
        int bead_ref_num = Integer.parseInt(args[0]); 
        double tau = Double.parseDouble(args[1]); 
        double delta = Double.parseDouble(args[2]); 
        
        for (int i = 3; i < args.length - 1; i++) {
            int picture_index = i - 3; 
            
            previous_picture = new Picture(args[i]);
            previous_blobfinder = new BlobFinder(previous_picture, tau);
            previous_blobfinder.find_blob_dfs(bead_ref_num);
            previous_bead = previous_blobfinder.getBeads(bead_ref_num);
            previous_bead_num = previous_bead.length;

            current_picture = new Picture(args[i + 1]);
            current_blobfinder = new BlobFinder(current_picture, tau);
            current_blobfinder.find_blob_dfs(bead_ref_num);
            current_bead = current_blobfinder.getBeads(bead_ref_num);
            current_bead_num = current_bead.length;
            
            for (int j = 0; j < previous_bead_num; j++) {
                double min_distance = previous_bead[j].distanceTo(current_bead[0]);
                for (int k = 0; k < current_bead_num; k++) {
                    if (previous_bead[j].distanceTo(current_bead[k]) <= min_distance) {
                        min_distance = previous_bead[j].distanceTo(current_bead[k]);
                    }
                }
                if (min_distance < delta) System.out.println(min_distance); 
            }
            System.out.println();
        }
    }
}
