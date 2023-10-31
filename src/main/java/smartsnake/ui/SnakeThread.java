package smartsnake.ui;


public class SnakeThread extends Thread {

    private SnakeComponent component;

    public void run() {
        while (!interrupted()) {
            component.repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
