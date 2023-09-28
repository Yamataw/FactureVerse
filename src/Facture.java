import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Facture {

    private String id;
    private String titre;
    private String nomClient;
    private String description;
    private Entreprise entreprise;
    private double prix;

    private Date dateFacture;
    private boolean signed = false;


    public Facture(String id, String titre, String nomClient, String description, Entreprise entreprise, double prix, Date dateFacture) {
        this.id = id;
        this.titre = titre;
        this.nomClient = nomClient;
        this.description = description;
        this.entreprise = entreprise;
        this.prix = prix;
        this.dateFacture = dateFacture;
    }

    public static Facture createFacture(List<Entreprise> entreprises) {
        if(entreprises.isEmpty()) return null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer l'ID de la facture : ");
        String id = scanner.next();
        System.out.println("Veuillez entrer le titre de la facture : ");
        String titre = scanner.next();
        System.out.println("Veuillez entrer le nom du client : ");
        String nomClient = scanner.next();
        System.out.println("Veuillez entrer le nom de l'entreprise : ");
        String nomEntreprise = scanner.next();

        System.out.println("Veuillez entrer la description de la facture : ");
        String description = scanner.next();
        System.out.println("Veuillez entrer le prix de la facture : ");
        double prix = scanner.nextDouble();
        Date dateFacture = new Date();
        Facture  fact = new Facture(id, titre, nomClient, description, Entreprise.getEntrepriseByName(entreprises,nomEntreprise), prix, dateFacture);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());
        String pdfFileName = "Facture_" + timestamp + ".pdf";

        FactureToPDF.generatePDF(fact, pdfFileName);
        return fact;
    }

    public String getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getNomClient() {
        return nomClient;
    }

    public String getDescription() {
        return description;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public double getPrix() {
        return prix;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public boolean isSigned() {
        return signed;
    }
    public void sign() {
        signed = true;
    }


    public static void test(ArrayList<Facture> listeDesFactures) {
        System.out.println("Liste des factures :");
        for (Facture facture : listeDesFactures) {
            System.out.println(facture.getTitre());
        }

        System.out.println("Veuillez saisir le nom de la facture que vous souhaitez signer : ");
        Scanner scanner = new Scanner(System.in);
        String nomFactureASigner = scanner.nextLine();
        Facture factureASigner = null;
        for (Facture facture : listeDesFactures) {
            if (facture.getTitre().equalsIgnoreCase(nomFactureASigner)) {
                factureASigner = facture;
                break;
            }
        }
        if (factureASigner != null) {
            factureASigner.sign();
            System.out.println("La facture " + factureASigner.getTitre() + " a été signée.");
        } else {
            System.out.println("Aucune facture trouvée avec le nom spécifié.");
        }

    }

    public static void showList(ArrayList<Facture> listeDesFactures ) {
        StringBuilder sb = new StringBuilder();
        sb.append("Liste des factures :\n");

        for (Facture facture : listeDesFactures) {
            sb.append("ID de la facture : ").append(facture.getId()).append("\n");
            sb.append("Titre de la facture : ").append(facture.getTitre()).append("\n");
            sb.append("Nom du client : ").append(facture.getNomClient()).append("\n");
            sb.append("Description : ").append(facture.getDescription()).append("\n");
            sb.append("Nom de l'entreprise : ").append(facture.getEntreprise().getNom()).append("\n");
            sb.append("Prix : ").append(facture.getPrix()).append("\n");
            sb.append("Date de la facture : ").append(facture.getDateFacture()).append("\n");
            sb.append("Signée : ").append(facture.isSigned()).append("\n");
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
