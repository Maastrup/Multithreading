package Default;

import java.util.Random;

public class PC {
    public void produce() throws InterruptedException {
        while(true){
            synchronized (this){
                // Waits for emptying if full.
                // While-loop makes sure that even if it is awoken,
                // but Main.buffer is still full it'll go back to sleep
                while(Main.buffer.length() == Main.buffer.capacity()) wait();

                char randChar = (char)(new Random().nextInt(26) +'a');
                Main.buffer.append(randChar);

                System.out.println("P - buffer: " + Main.buffer);

                notify();
            }
            int waittime = new Random().nextInt(1000) + 100;
            Thread.sleep(waittime);

        }
    }

    public void consume() throws InterruptedException {
        while(true){
            synchronized (this){
                // Waits for content if empty.
                // While-loop makes sure that even if it is awoken,
                // but Main.buffer is still empty it'll go back to sleep
                while(Main.buffer.length() == 0) wait();

                Main.buffer.deleteCharAt(0);

                notify();
            }

            System.out.println("C - buffer: " + Main.buffer);

            int waittime = new Random().nextInt(1000) + 100;
            Thread.sleep(waittime);
        }
    }
}
