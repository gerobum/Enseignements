package points;

/**
 *
 * @author yvan
 */
public class Point3 extends Point2 {

    private double z;

    public Point3(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Point3 other = (Point3) obj;
        if (super.equals(obj)) {
            return Double.doubleToLongBits(this.z) == Double.doubleToLongBits(other.z);
        } else {
            return false;
        }
    }

    // Comme Point3 hérite de Point2 et que son clone utilise new
    // Le type l'objet cloné est Point2 et sa pose un problème car une
    // instance de Point3 et son clone ne sont pas de même type et donc
    // pas égaux. Ce qui rompt le contrat.
    @Override
    public Point2 clone() {
        return super.clone(); 
    }
    
    

}
