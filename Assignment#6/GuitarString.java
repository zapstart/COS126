public class GuitarString {
    
    private final int sampling_rate = 44100;
    private final double energy_decay_factor = 0.996;
    RingBuffer buffer;
    private int tic_time = 0; 

    GuitarString(double frequency) {
        int capacity;

        capacity = (int)Math.ceil(sampling_rate / frequency);
        buffer = new RingBuffer(capacity);
        for (int i = 0; i < capacity; i++) {
            buffer.enqueue(0);
        }
    }
    
    GuitarString(double[] init) {
        buffer = new RingBuffer(init.length);
        for (int i = 0; i < buffer.buffer.length; i++) {
            buffer.enqueue(init[i]); 
        } 
    }

    public void pluck() {

        for (int i = 0; i < buffer.return_buffer().length; i++) {
            buffer.enqueue(Math.random() - 0.5);  
        }
    }

    public void tic() {
        double first_sample, second_sample, new_sample;

        //buffer.show_buffer(); 
        first_sample  = buffer.dequeue();
        second_sample = buffer.peek();
        new_sample = energy_decay_factor * (first_sample + second_sample) / 2;
        buffer.enqueue(new_sample);
        tic_time++;
    }

    public double sample() {
        return buffer.return_first_item();
    }

    public int time() {
        return tic_time; 
    }
    
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < N; i++) {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    } 
    
    // test 
    //public static void main(String[] args) {
    //    GuitarString guitar = new GuitarString(441);
    //    guitar.pluck();
    //    guitar.buffer.show_buffer();
    //    System.out.println("sample = \n" + guitar.sample() + "\n");
    //    guitar.tic();
    //    guitar.buffer.show_buffer();
    //    System.out.println();
    //    guitar.tic();
    //    guitar.tic();
    //    System.out.println("tic time = \n" + guitar.time());
    //}
}
