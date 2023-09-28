import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Entreprise {

    private String nom;
    private int codePostal;
    private String adresse;

    public Entreprise(String nom, int codePostal, String adresse) {
        this.adresse = adresse;
        this.nom = nom;
        this.codePostal = codePostal;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNom() {
        return nom;
    }

    public static Entreprise createEntreprise() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer le nom de l'entreprise : ");

        String nom = scanner.next();
        System.out.println("Veuillez entrer l'adresse de l'entreprise : ");
        String adresse = scanner.next();
        System.out.println("Veuillez entrer3 le code postal de l'entreprise : ");
        int codePostal = scanner.nextInt();
        return new Entreprise(nom, codePostal, adresse);
    }

    @Override
    public String toString() {
        String separator = "------------------------";
        return separator + "\n" +
                "Nom de l'entreprise: " + nom + "\n" +
                "Code Postal: " + codePostal + "\n" +
                "Adresse: " + adresse + "\n" +
                separator;
    }

    public static void showEntreprises(ArrayList<Entreprise> entreprises) {

        for (Entreprise entreprise : entreprises) {
            System.out.println(entreprise);
        }

    }

    public static Entreprise getEntrepriseByName(List<Entreprise> entreprises, String nomRecherche) {
        for (Entreprise entreprise : entreprises) {
            if (entreprise.getNom().equalsIgnoreCase(nomRecherche)) {
                return entreprise;
            }
        }

        //pas gérée = on prend la première
        return entreprises.get(0);
    }


}
