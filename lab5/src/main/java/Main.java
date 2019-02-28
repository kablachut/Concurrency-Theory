import java.io.IOException;

public class Main {
    public static void main(String[] args)  {

        GaussExecutor executor = new GaussExecutor();
        executor.execute("out10.txt");
        System.out.println("Checker result:");
        Checker checker = new Checker();
        try {
            checker.check();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}