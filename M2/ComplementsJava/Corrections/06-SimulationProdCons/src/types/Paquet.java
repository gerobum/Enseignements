
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
  private Date dateConsommation, dateRetrait;
  private static final Random RANDOM = new Random();
  private static final SimpleDateFormat DF = new SimpleDateFormat("hh:mm:ss");

  public Paquet(Producteur producteur, Date dateProduction) {
    donnee = RANDOM.nextDouble();
    this.producteur = producteur;
    this.dateProduction = dateProduction;
    consommateur = null;
    dateRangement = null;
    dateConsommation = null;
    dateRetrait = null;
  }

  @Override
  public String toString() {
    return String.format("%s:(%s=>%s)|%s:(%s=>%s) [%f]", producteur, DF.format(dateProduction), DF.format(dateRangement), consommateur, DF.format(dateConsommation), DF.format(dateRetrait), donnee);
  }

  public void setConsommateur(Consommateur consommateur) {
    this.consommateur = consommateur;
  }

  public void setDateRangement(Date dateRangement) {
    this.dateRangement = dateRangement;
  }

  public void setDateConsommation(Date dateConsommation) {
    this.dateConsommation = dateConsommation;
  }

  public void setDateRetrait(Date dateRetrait) {
    this.dateRetrait = dateRetrait;
  }
}
