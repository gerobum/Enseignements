package page_5;

public enum Jour {
    LUNDI("Lundi"), MARDI("Mardi"), MERCREDI("Mercredi"),
    JEUDI("Jeudi"), VENDREDI("Vendredi") {
        @Override
        public void questCeQuonMange() {
            System.out.println("c’est spaghetti");
        }
    }, SAMEDI, DIMANCHE {
        @Override
        public void questCeQuonMange() {
            System.out.println("c’est cèleri-branche");
        }
    };
    public final String nom;

    Jour() {
        this.nom = name();
    }

    Jour(String nom) {
        this.nom = nom;
    }

    public void questCeQuonMange() {
        System.out.println("c’est ravioli");
    }
}
