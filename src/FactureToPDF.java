import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FactureToPDF {
    public static void generatePDF(Facture facture, String outputPath) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();

            document.add(new Paragraph("ID de la facture : " + facture.getId()));
            document.add(new Paragraph("Titre de la facture : " + facture.getTitre()));
            document.add(new Paragraph("Nom du client : " + facture.getNomClient()));
            document.add(new Paragraph("Description : " + facture.getDescription()));
            document.add(new Paragraph("Nom de l'entreprise : " + facture.getEntreprise().getNom()));
            document.add(new Paragraph("Adresse de l'entreprise : " + facture.getEntreprise().getAdresse()));
            document.add(new Paragraph("Code postal de l'entreprise : " + facture.getEntreprise().getCodePostal()));

            document.add(new Paragraph("Prix : " + facture.getPrix()));
            document.add(new Paragraph("Date de la facture : " + facture.getDateFacture()));
            document.add(new Paragraph("Signée : " + facture.isSigned()));
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
