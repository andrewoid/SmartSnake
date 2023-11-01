package smartsnake.ui;


public class SnakeThread extends Thread {

    private SnakeComponent component;

    public void run() {
        while (!interrupted()) {
            component.repaint();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
