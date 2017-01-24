

package testBidon;

public class Bidon extends SuperBidon {
    private final String contenu;
    public Bidon(int quantité, String contenu) {
        super(quantité);
        this.contenu = contenu;
    }
    
    @Override
    public String toString() {
        return getQuantité()+" litres "+contenu;
    }
}