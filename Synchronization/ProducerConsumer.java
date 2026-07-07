import java.util.LinkedList;

class ProducerConsumer {
    private LinkedList<Integer> buffer = new LinkedList<>();
    private final int capacity = 5; // buffer size

    // Producer method
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                while (buffer.size() == capacity) {
                    wait(); // wait if buffer is full
                }
                System.out.println("Producer produced: " + value);
                buffer.add(value++);
                notify(); // notify consumer
                Thread.sleep(1000);
            }
        }
    }

    // Consumer method
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (buffer.isEmpty()) {
                    wait(); // wait if buffer is empty
                }
                int val = buffer.removeFirst();
                System.out.println("Consumer consumed: " + val);
                notify(); // notify producer
                Thread.sleep(1500);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer();

        Thread producerThread = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
