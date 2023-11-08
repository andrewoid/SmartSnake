package smartsnake.ui;


public class SnakeThread extends Thread {

    private final SnakeComponent component;

    public SnakeThread(SnakeComponent component) {
        this.component = component;
    }

    public void run() {
        while (!interrupted()) {
            component.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
