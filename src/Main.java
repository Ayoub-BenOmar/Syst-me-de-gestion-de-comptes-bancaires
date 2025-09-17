import java.util.Scanner;
import controller.MainController;

public class Main {
    public static void main(String[] arges){
        Scanner sc = new Scanner(System.in);
        MainController mc = new MainController();
        boolean running = true;

        while(running){
            System.out.println("\n===> MENU BANQUE <===");
            System.out.println("1. Créer un compte");
            System.out.println("2. Consulter un compte");
            System.out.println("3. Quitter");
            System.out.print("Choisissez une option: ");

            int choice = sc.nextInt();

            switch (choice){
                case 1:
                    boolean running2 = true;
                    while(running2){
                        System.out.println("<========================>");
                        System.out.println("1. Créer un compte épargne");
                        System.out.println("2. Créer un compte courant");
                        System.out.println("3. Retour au menu précédent");
                        System.out.print("Choisissez une option: ");

                        switch (sc.nextInt()){
                            case 1:
                                mc.creeCompteEpargne();
                                break;
                            case 2:
                                mc.creeCompteCourant();
                                break;
                            case 3:
                                running2 = false;
                                break;
                            default:
                                System.out.println("Veuillez entrer un choix");
                        }
                    }
                    break;
                case 2:



            }
        }
        sc.close();
    }
}