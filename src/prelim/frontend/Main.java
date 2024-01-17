package prelim.frontend;

public class Main extends Thread {
    public static void main(String[] args) {
        Main main;
        try {
          main = new Main();
          main.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
    }
}
