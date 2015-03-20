import java.awt.Color;

class LFSR {
    
    public String lfsr;
    private int tap;

    public LFSR(String seed, int tap) {
        this.lfsr = seed;
        this.tap = tap;
    }
    
    public int step() {
        char tap_v, last_v; 
        int new_first_v;
        String new_lfsr;
        
        // JAVA string need use charAt() to access character 
        tap_v  = lfsr.charAt(lfsr.length() - 1 - tap);
        last_v = lfsr.charAt(0);
        new_first_v = (tap_v - '0') ^ (last_v - '0');

        // combine string and char type data
        new_lfsr = lfsr.substring(1, lfsr.length()) + Integer.toString(new_first_v).charAt(0);     
        lfsr = new_lfsr;
        return new_first_v;  
    }
    
    public int generate(int num_step) {
        int lfsr_int = 0;
        for (int i = 0; i < num_step; i++) {
            step();  
        }
        for (int index = lfsr.length() - 1; index >= 0; index--) {

            // convert string to int
            lfsr_int += Integer.parseInt(lfsr.substring(index, index + 1)) * Math.pow(2, index);  
        }
        return lfsr_int;
    }
}


public class PhotoMagic {
    private int photo_width, photo_height; 

    public Picture transform(Picture picture, LFSR lfsr) {
        int[] new_color = new int[3]; 
        photo_width  = picture.width(); 
        photo_height = picture.height(); 
        Picture new_picture = new Picture(picture.width(), picture.height());

        for (int x = 0; x < photo_width; x++) {
            for (int y = 0; y < photo_height; y++) {
                new_color[0] = lfsr.generate(y);
                new_color[1] = lfsr.generate(y + 1);
                new_color[2] = lfsr.generate(y + 2);
                Color new_Color = new Color(picture.get(x, y).getRed() ^ new_color[0], 
                                            picture.get(x, y).getGreen() ^ new_color[1],
                                            picture.get(x, y).getBlue() ^ new_color[2]);
                    
                new_picture.set(x, y, new_Color); 
            }
        }
        return new_picture;
    }
    
    public static void main(String[] args) {
        PhotoMagic photo = new PhotoMagic();
        Picture picture  = new Picture("pipe.png"); 
        LFSR lfsr = new LFSR("10100101", 5);
        
        Picture new_pic_1 = photo.transform(picture, lfsr);
        new_pic_1.show();
        Picture new_pic_2 = photo.transform(new_pic_1, lfsr);
        new_pic_2.show();

    }
}
