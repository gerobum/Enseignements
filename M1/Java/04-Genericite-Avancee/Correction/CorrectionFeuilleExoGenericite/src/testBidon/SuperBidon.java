

package testBidon;

public class SuperBidon implements Comparable<SuperBidon> {
    private final int quantité;
    public SuperBidon(int quantité) {
        this.quantité = quantité;
    }

    @Override
    public int compareTo(SuperBidon o) {
        return quantité - o.quantité;
    }

    @Override
    public String toString() {
        return quantité+" litres";
    }
    
    public int getQuantité() {
        return quantité;
    }    
}

