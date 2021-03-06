package Default;

public class Main {
    static StringBuffer buffer = new StringBuffer(7);

    public static void main(String[] args) {
	    final PC pc = new PC();

	    Thread t1 = new Thread( () -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

	    Thread t2 = new Thread( () -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
