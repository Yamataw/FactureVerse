import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    interface Action {
        void execute();
    }

    static ArrayList<Facture> listFact = new ArrayList<Facture>();
    public static ArrayList<Entreprise> listEntreprise = new ArrayList<Entreprise>();


    public static void main(String[] args) {

        System.out.println("Bienvenue dans l'outil de gestion de facture, que souhaitez vous faire :");
        int curChoice = -1;
        List<Action> list = initFuncList();

        do {
            Scanner sc = new Scanner(System.in);
            sc.reset();
            System.out.println("• 1 Consulter mes factures");
            System.out.println("• 2 Générer une nouvelle facture");
            System.out.println("• 3 Créer une nouvelle entreprise");
            System.out.println("• 4 Consulter les entreprises");
            System.out.println("• 5 Signer une facture");
            System.out.println("• 6 Quitter le logiciel");
            curChoice = getUserChoice(sc);

            if (curChoice > 0 && curChoice < 7) {
                list.get(curChoice - 1).execute();
            }


        } while (curChoice != 6);

    }

    private static List<Action> initFuncList() {
        List<Action> list = new ArrayList<>();
        list.add(() -> {
            try {
                Facture.showList(listFact);
                System.out.println("Un dossier a été ouvert où vos factures PDF sont disponibles");
                Desktop.getDesktop().open(new File("."));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        list.add(() -> listFact.add(Facture.createFacture(listEntreprise)));
        list.add(() -> listEntreprise.add(Entreprise.createEntreprise()));
        list.add(() -> Entreprise.showEntreprises(listEntreprise));
        list.add(() -> Facture.test(listFact));
        list.add(Main::close);
        return list;
    }

    public static void close() {
        System.out.println("Merci d'avoir utilisé FactureVerse");
        System.exit(0);
    }

    private static int getUserChoice(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre valide.");
                try {
                    scanner.close();
                } catch (Exception e) {
                    System.out.println("Suivant");

                }

            }
        }
    }

}
