package conversionDevises.patron;

/**
 * Pour permettre "l'héritage multiple". L'observateur abstrait est une 
 * interface. En effet, les observateurs concrets hériteront aussi de JSlider 
 * ou de JTextField.
 * @author Yvan
 */
public interface Observateur {
    void update();
    void setSujet(Sujet sujet);
}
