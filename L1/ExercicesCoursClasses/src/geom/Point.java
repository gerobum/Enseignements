package geom;
// Une classe Point avec

public class Point {

    // Des attributs x et y de type double, pour les coordonnées cartésiennes.
    private double x, y;
    // Des attributs rho et theta de type double, pour les coordonnées polaires.
    private double rho, theta;
    
    // La possibilité de le construire par défaut (0,0).
    public Point() {
        this(0, 0);
    }
    // La possibilité de le construire en donnant les coordonnées cartésiennes.
    public Point(double x, double y) {
        this(x, y, false);
    }
    /* La possibilité de le construire en donnant les coordonnées polaires ou 
    cartésiennes selon qu’un paramètre soit vrai ou faux. */
    public Point(double rhooux, double thetaouy, boolean polaire) {
        if (polaire) {
            rho = rhooux;
            theta = thetaouy;
            cfromp();
        } else {
            x = rhooux;
            y = thetaouy;
            pfromc();
        }
    }
    
    private void pfromc() {
       rho = Math.sqrt(x * x + y * y);
        theta = Math.atan2(y, x);
    }

    private void cfromp() {
        theta = theta % 2 * Math.PI;
        x = rho * Math.cos(theta);
        y = rho * Math.sin(theta);
    }

    // Tous les setters et les getters
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        // Attention à l'intégrité de données
        pfromc();
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        // Attention à l'intégrité de données
        pfromc();
    }

    public double getRho() {
        return rho;
    }

    public void setRho(double rho) {
        this.rho = rho;
        // Attention à l'intégrité de données
        cfromp();
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
        // Attention à l'intégrité de données
        cfromp();
    }

    // Une méthode pour lui appliquer une translation.
    public void translater(double dx, double dy) {
        x += dx;
        y += dy;
        pfromc();
    }
    // Pas demandé, mais pourquoi pas.
    public void translater(Vecteur v) {
        translater(v.getX(), v.getY());
    }
    // Une méthode pour lui appliquer une rotation par rapport à l’origine.
    public void tourner(double dtheta) {
        theta += dtheta;
        cfromp();
    }
    // Une méthode pour l’afficher sur la sortie standard
    public void afficher(boolean polaire) {
        if (polaire) {
            System.out.println("[" + rho + ":" + theta + "]");
        } else {
            System.out.println("(" + x + ", " + y + ")");
        }
    }
    // Une méthode pour l’afficher sur la sortie standard
    public void afficher() {
        afficher(false);
    }
    /* Une méthode qui indique si ce point est à gauche d’un vecteur formé par 
    deux points passés en paramètres */
    public boolean aGauche(Point a, Point b) {
        Vecteur ap = new Vecteur(a, this);
        Vecteur ab = new Vecteur(a, b);

        return ap.determinant(ab) < 0;
    }
}
