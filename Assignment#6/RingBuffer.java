//package GuitarHero;

public class RingBuffer {
    
    private int capacity;
    double[] buffer; 
    private int front;
    private int end;
    private boolean is_empty; 

    public RingBuffer(int capacity) {
        this.capacity = capacity;     
        this.buffer = new double[capacity];
        this.is_empty = true;
        this.end = 0;
        this.front = 0;
    }

    public int size() {
        if (end > front) return (end - front);
        else if (end < front) return (end + capacity - front);
        else if (end == front && is_empty) return 0;
        else return capacity; 
    }

    public boolean isEmpty() {
        return is_empty; 
    }
    
    public boolean isFull() {
        return (!is_empty && (front == end));
    }

    public void enqueue(double x) {
        if (!(!is_empty && (front == end))) {
            buffer[end] = x;
            if (end < capacity - 1) end++;
            else end = 0;
            is_empty = false;
        }
        else throw new IndexOutOfBoundsException("RingBuffer is full!!!"); 
    } 
    
    public double dequeue() {
        double buffer_old_value = 0;

        if (!is_empty) {
            if (front < capacity - 1) {
                front++;
                buffer_old_value = buffer[front - 1];
                buffer[front - 1] = -1;
            }
            else {
                front = 0;
                buffer_old_value = buffer[capacity - 1];
                buffer[capacity - 1] = -1;
            }

            if (front == end && !is_empty) {
                is_empty = true;
            }
            return buffer_old_value;
        }
        else throw new IndexOutOfBoundsException("RingBuffer is empty!!!"); 
    }

    public double peek() {
        double buffer_old_value = 0;
        
        if (!is_empty) {
            if (front < capacity - 1) {
                buffer_old_value = buffer[front];
            }
            else {
                buffer_old_value = buffer[capacity - 1];
            }

            //System.out.println("End = " + end + " Front = " + front);
            return buffer_old_value;
        }
        else throw new IndexOutOfBoundsException("RingBuffer is empty!!!");
    }

    public void show_buffer() {
        for (int i = 0; i < capacity; i++) {
            System.out.println(buffer[i]);
        }
        System.out.println();
    }
    
    public double return_first_item() {
        return buffer[end];
    }

    public double[] return_buffer() {
        return buffer;
    }
    
    // test for ringbuffer
    /*public static void main(String[] args) {
      int N = Integer.parseInt(args[0]);
      RingBuffer buffer = new RingBuffer(N);  
      for (int i = 1; i <= N; i++) {
          buffer.enqueue(i);
      }
      double t = buffer.dequeue();
      buffer.enqueue(t);
      System.out.println("Size after wrap-around is " + buffer.size());
      while (buffer.size() >= 2) {
          double x = buffer.dequeue();
          double y = buffer.dequeue();
          buffer.enqueue(x + y);
      }
      System.out.println(buffer.peek());
  }
  */
}
