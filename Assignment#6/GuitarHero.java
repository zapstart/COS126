public class GuitarHero {
    final double base_f = 440;
    double[] concert = new double[37];
    String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' "; 
    
    public GuitarHero() {
        for (int i = 0; i < 37; i++) {
            concert[i] = 440 * Math.pow(2, (i - 24.0 / 12.0));
        }
    }
    
    public static void main(String[] args) {
        
        GuitarHero guitar_hero = new GuitarHero();

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                for (int i = 0; i < guitar_hero.keyboard.length(); i++) {
                    if (guitar_hero.keyboard.charAt(i) == key) { 
                        GuitarString string = new GuitarString(guitar_hero.concert[i]);
                        string.pluck();
                    }
                    break;
                }
            }
        }
    }
}
