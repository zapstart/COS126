public class MarkovModel {
    
    private String text;
    private int kgrams_length;
    
    /* 
    * class_name: Freqchar
    *             data type that stores kgram and its next character frequency & number 
    * 
    * private_class_member: 
    *               String kgrams            : kgram name  
    *               int[] char_freq          : number of characters  
    *               double[] char_freq_value : frequency of characters 
    * 
    * member_method:  
    *                update_freq(char )
    *                return_freq(char )
    *                return_freq_value(char )
    *                return_freq_value_array()
    *                show_kgrams_inf() 
    */ 
    private class Freqchar {
        private String kgrams; 

        private int[] char_freq = new int[128]; 
        
        private double[] char_freq_value = new double[128]; 
        
        // Freqchar constructor
        public Freqchar(String kgrams) {
            this.kgrams = kgrams;
            for (int i = 0; i < char_freq.length; i++) {
                char_freq[i] = 0;
                char_freq_value[i] = 0.0; 
            }
        } 
        
        // update number and frequency of character next to kgram
        public void update_freq(char next_char) {
            double sum = 0;

            char_freq[(int) next_char]++;   
            for (int i = 0; i < char_freq.length; i++) {
                sum += char_freq[i];
            }
            for (int i = 0; i < char_freq.length; i++) {
                char_freq_value[i] = char_freq[i] / sum;
            }
        }
        
        // return number of character next to kgram
        public int return_freq(char character) {
            int char_index = (int) character;
            return char_freq[char_index];
        }
        
        // return frequency of character next to kgram 
        public double return_freq_value(char character) {
            int char_index = (int) character;
            return char_freq_value[char_index];
        }
        
        // return the next character frequency array 
        public double[] return_freq_value_array() {
            return char_freq_value;
        }
        
        // show all the next character number  
        public void show_kgrams_inf() {
            System.out.println("kgram = " + kgrams); 
            for (int i = 0; i < char_freq.length; i++) {
                System.out.println((char)i + " = " + char_freq[i]); 
            }
        }
    }
    
    private ST<String, Freqchar> st; 
    
    // MarkovModel constructor
    public MarkovModel(String text, int k) {
        st = new ST<String, Freqchar>();
        String kgrams;
        
        this.text = text;
        this.kgrams_length = k; 
        
        // create text.length() Freqchar instance
        for (int i = 0; i < text.length(); i++) {
            if (i + k <= text.length()) { 
                kgrams = text.substring(i, i + k);
            }
            else {
                kgrams = text.substring(i, text.length()) + text.substring(0, i + k - text.length());
            }
            Freqchar freq_char = new Freqchar(kgrams); 
            st.put(kgrams, freq_char);
        }
    }
    
    // update kgram's next character number and frequency 
    public void update_kgrams(String kgrams) {
        String sub_string;
        Freqchar freq_char = new Freqchar(kgrams);
        
        for (int i = 0; i < text.length(); i++) {
            
            // kgram does not cross the end of text
            if (i + kgrams_length <= text.length()) {
                sub_string = text.substring(i, i + kgrams_length); 
                if (st.contains(sub_string)) {
                    if (sub_string.equals(kgrams)) {
                        freq_char.update_freq(i + kgrams_length == text.length() ? text.charAt(0) : text.charAt(i + kgrams_length)); 
                    }
                }
                else {
                    throw new RuntimeException("Wrong substring!!!");
                }
            }
            // kgram crosses the end of text
            else {
                sub_string = text.substring(i, text.length()) + text.substring(0, i + kgrams_length - text.length());
                if (st.contains(sub_string)) {
                    if (sub_string.equals(kgrams)) {
                        freq_char.update_freq(text.charAt(i + kgrams_length - text.length() - 1));
                    }
                }
                else {
                    throw new RuntimeException("Wrong substring!!!");
                }
            }
        }
        st.put(kgrams, freq_char);
    }
   
    // update all kgram in text
    public void Update() {
        for (int i = 0; i < text.length(); i++) {
            if (i + kgrams_length <= text.length()) {
                update_kgrams(text.substring(i, i + kgrams_length)); 
            }
            else {
                update_kgrams(text.substring(i, text.length()) + text.substring(0, i + kgrams_length - text.length())); 
            }
        }
    }
    
    // show kgram length
    public int order() {
        return kgrams_length; 
    }
    
    // count number of kgram in text
    public int freq(String kgram) {
        String sub_string;
        int kgram_freq = 0;
        
        if (kgram.length() < kgrams_length) {
            throw new RuntimeException("Input kgram is shorter than k!!!");
        }
        else {
            for (int i = 0; i < text.length(); i++) {
                if (i + kgrams_length <= text.length()) { 
                    sub_string = text.substring(i, i + kgrams_length);
                }
                else {
                    sub_string = text.substring(i, text.length()) + text.substring(0, i + kgrams_length - text.length());
                }
                if (kgram.equals(sub_string)) {
                    kgram_freq++;
                }
            }
        }
        return kgram_freq;
    }
    
    // get number of character c next to kgram 
    public int freq(String kgram, char c) {
        Freqchar freq_char = new Freqchar(kgram);

        if (st.contains(kgram)) {
            freq_char = st.get(kgram);
            return freq_char.return_freq(c); 
        }
        else {
            throw new RuntimeException("Wrong kgram!!!");
        } 
    }

    // generate random character accoring to freq_value_array 
    public char rand(String kgram) {
        double[] freq_char_value;
        int index = 0;

        freq_char_value = st.get(kgram).return_freq_value_array();
        index = StdRandom.discrete(freq_char_value);
        return (char)index;
    }
    
    // generate random text according to MarkovModel  
    public String gen(String kgram, int T) {
        String new_kgram = kgram;
        String gen_text = kgram;
        String rand_char;
         
        for (int i = 0; i < T - kgram.length(); i++) {
            rand_char = Character.toString(rand(new_kgram));
            new_kgram = new_kgram.substring(1) + rand_char;
            gen_text += rand_char;
        }
        return gen_text;
    }
    
    public static void main(String[] args) {
        String test; 
        String text = "gagggagaggcgagaaa";
        MarkovModel m_model = new MarkovModel(text, 2);
        int gag = 0;
        int gaa = 0;
        
        m_model.Update(); 
        for (int j = 0; j < 1000000; j++) { 
            test = m_model.gen("ga", 3); 
            System.out.println(test);
            if (test.substring(0, 3).equals("gaa")) {
                gaa++;
            }
            else if (test.substring(0, 3).equals("gag")) {
                gag++;
            }
        }
        System.out.println(gaa + "  " + gag);
    }
}
