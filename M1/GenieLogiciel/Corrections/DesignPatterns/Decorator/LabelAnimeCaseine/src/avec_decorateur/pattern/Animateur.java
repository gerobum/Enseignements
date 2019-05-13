package avec_decorateur.pattern;




import javax.swing.JLabel;
/**
 * Un animateur de JLabel, au sens abstrait, doit être capable d'animer
 * ce JLabel. 
 * 
 * Plusieurs animateurs peuvent animer le même JLabel. C'est d'ailleurs bien 
 * le but du pattern Decorateur, ajouter des responsabilités, en l'occurrence
 * des animations, à un objet, en l'occurrence à un JLabel
 * 
 * La méthode getJLabel() doit retourner le JLabel sur le lequel s'applique 
 * l'animation.
 */
// TODO établir une généralisation si nécessaire
public abstract class Animateur {
    
    public abstract void animer();
    public abstract JLabel getJLabel();
    
}
