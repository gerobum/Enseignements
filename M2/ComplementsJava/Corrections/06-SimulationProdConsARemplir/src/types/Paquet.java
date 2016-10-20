package types;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import pc.Consommateur;
import pc.Producteur;

public class Paquet {

    private final double donnee;
    private final Producteur producteur;
    private Consommateur consommateur;
    private final Date dateProduction;
    private Date dateRangement;
    private Date dateDemandeConsommation, dateConsommation;
    private static final Random RANDOM = new Random();
    private static final SimpleDateFormat DF = new SimpleDateFormat("hh:mm:ss");

    public Paquet(Producteur producteur) {
        donnee = RANDOM.nextDouble();
        this.producteur = producteur;
        this.dateProduction = new Date();
        consommateur = null;
        dateRangement = null;
        dateDemandeConsommation = null;
        dateConsommation = null;
    }

    private String formatedDateOrNull(Date d) {
        if (d == null) {
            return "?";
        } else {
            return DF.format(d);
        }
    }

    @Override
    public String toString() {
        return String.format("%s:(%s=>%s)|%s:(%s=>%s) %s [%f]", 
                producteur, 
                formatedDateOrNull(dateProduction), 
                formatedDateOrNull(dateRangement), 
                consommateur, 
                formatedDateOrNull(dateDemandeConsommation), 
                formatedDateOrNull(dateConsommation), 
                getArrows(), 
                donnee);
    }

    private String getArrows() {
        return String.format("%c=%c", 
                (dateRangement.getTime() - dateProduction.getTime() < 10 ? '>' : '|'),
                (dateConsommation.getTime() - dateDemandeConsommation.getTime() < 10 ? '>' : '|'));
    }

    public void setConsommateur(Consommateur consommateur) {
        this.consommateur = consommateur;
    }

    public void setDateRangement(Date dateRangement) {
        this.dateRangement = dateRangement;
    }

    public void setDateDemandeConsommation(Date dateDemandeConsommation) {
        this.dateDemandeConsommation = dateDemandeConsommation;
    }

    public void setDateConsommation(Date dateConsommation) {
        this.dateConsommation = dateConsommation;
    }
}
