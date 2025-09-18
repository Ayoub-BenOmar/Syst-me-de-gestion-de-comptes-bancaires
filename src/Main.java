import java.util.Scanner;
import controller.MainController;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static MainController mc = new MainController();

    public static void main(String[] args) {
        mc.runApplication(sc);
        sc.close();
    }
}