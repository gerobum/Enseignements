package page_5;

public class Main {

    public static void main(String[] args) {

        for (Jour j : Jour.values()) {
            System.out.print(j.nom + " ");
            j.questCeQuonMange();
        }

    }
}
