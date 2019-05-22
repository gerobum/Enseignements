package agenda;

/*
2 Horaire (~14pts)

Un horaire est constitué d’heures (entre 0 et 23) et de minutes (entre
0 et 59). La classe Horaire à créer doit garantir que ses instances ont
toujours des heures entre 0 et 23 et des minutes entre 0 et 59.
*/
public class Horaire {
/* 
2.1 Attributs (~1pt)
    
Dans un paquetage agenda, écrire une classe Horaire qui possède les
attributs privés heure et minute de type int.
*/
    private int heure, minute;

/*
2.2 Constructeurs (~3pts)
La responsabilité de cette classe est de garantir la cohérence de ses at-
tributs. Les heures doivent rester dans [0, 23] et les minutes dans [0, 59].
*/
/*
1. (~0.5pt) Écrire le constructeur par défaut qui crée l’horaire 0
heure 0 minute (00h00).
*/    
    public Horaire() {
        this(0);
    }
/*
2. (~2pts) Écrire un constructeur qui crée un horaire à partir d’un
seul paramètre entier n qui correspond à un nombre total de mi-
nutes (éventuellement supérieur à 59, éventuellement négatif).
Par exemple, si ce paramètre vaut 48 (pour 48 minutes), l’horaire
créé est 0 heure 48 minutes (00h48), si ce paramètre vaut 92 (pour
92 minutes), l’horaire créé est 1 heure 32 minutes (01h32)
Le calcul est le suivant :
si n est positif alors
(a) le nombre de minutes est le reste de la division, par 60, de n.
(b) le nombre d’heures est le reste de la division, par 24, de la
division entière, par 60, de n.
D’autres exemples : 0 => 00h00, 1 => 00h01, 34 => 00h34,
1000 => 16h40, 2000 => 09h20, 4000 => 18h40, 14578 => 02h58,
2147483647 => 02h07.
Il est même possible que le nombre total de minutes soit négatif.
Dans ce cas, cela représente le nombre de minutes avant minuit.
Par exemple, si ce paramètre vaut -48 (pour 48 minutes avant mi-
nuit), l’horaire créé est 23h12, si ce paramètre vaut -92, l’horaire
créé est 22h28.
Le calcul est le suivant :
si n est négatif alors
(a) heure = 23 - ((-(n + 1)/ 60)% 24);
(b) minute = 59 - (-(n + 1)% 60);
D’autres exemples : -0 => 00h00, -1 => 23h59, -34 => 23h26,
-1000 => 07h20, -2000 => 14h40, -4000 => 05h20, -14578 => 21h02,
-2147483647 => 21h53.
    
*/   
    public Horaire(int total) {
        if (total < 0) {
            heure = 23 - ((-(total + 1) / 60) % 24);
            minute = 59 - (-(total + 1) % 60);
        } else {
            heure = (total / 60) % 24;
            minute = total % 60;
        }
    }
/*
3. (~0.5pt) Écrire un constructeur à deux paramètres entiers, h et m
qui permet de construire un horaire en donnant des heures et
des minutes. Par exemple, new Horaire(9, 40) doit créer l’horaire
09h40.
Mais, comme il n’est pas possible d’empêcher d’appeler ce
constructeur avec n’importe quel entier pour h et m, il faut don-
ner une interprétation à des appels comme new Horaire(36, 98).
Il s’agit en fait de considérer tout simplement le nombre total de
minutes que comptent 36 heures et 98 minutes (soit 2258). C’est
vrai quelles que soient les valeurs de h et m, qu’elles soient posi-
tives ou négatives.
Quelques exemples :
— new Horaire(8, 30) => 08h30
— new Horaire(23, 59) => 23h59
— new Horaire(0, 0) => 00h00
— new Horaire(0, -1) => 23h59
— new Horaire(-1, 0) => 23h00
— new Horaire(36, 98) => 13h38
— new Horaire(-38, 72) => 11h12
— new Horaire(38, -72) => 12h48
— new Horaire(-1478, -98) => 08h22  
*/
    public Horaire(int h, int m) {
        this(h * 60 + m);
    }
/*
2.3 Méthodes (~10pts)
*/
/*
(~0.5pt) Écrire une méthode public void afficher() qui affiche un
horaire sur la sortie standard (System.out.println()).    
*/  
    public void afficher() {
        System.out.println(String.format("%02dh%02d", heure, minute));
    }
/*
(~1pt) Écrire une méthode public boolean estEnMêmeTemps(Horaire
h) qui retourne vrai si cet horaire se déroule en même temps que h,
c’est-à-dire à la même heure et à la même minute.
    
*/     
    public boolean estEnMemeTemps(Horaire h) {
        return heure == h.heure && minute == h.minute;
    }
    
    
/*
(~1.5pts) Écrire une méthode public void augmenterDUneMinute qui
augmente cet horaire d’une minute, sans créer de nouvel horaire.
    
*/   
    public void augmenterDUneMinute() {
        minute++;
        if (minute == 60) {
            minute = 0;
            heure = (heure + 1) % 24;
        }
    }
    
/*
(~2pts) Écrire une méthode public void diminuerDUneMinute qui di-
minue cet horaire d’une minute, sans créer de nouvel horaire.    
*/   
    public void diminuerDUneMinute() {
        minute--;
        if (minute == -1) {
            minute = 59;
            heure--;
            if (heure == -1) {
                heure = 23;
            }
        }
    }
    
    
/*
(~1pt) Écrire une méthode public int totalMinutes() qui retourne
le nombre total de minutes de cet horaire.    
*/   
    public int totalMinutes() {
        return heure*60+minute;
    }  
/*
(~2pts) Écrire une méthode public int écart(Horaire h) qui re-
tourne le nombre de minutes qui sépare cet horaire de h.
Remarque : cet écart peut-être positif, négatif ou nul.    
*/
    public int écart(Horaire h) {
        return totalMinutes() - h.totalMinutes();
    }
  
/*
(~2pts) Écrire une méthode public Horaire prochain(int durée) qui
retourne un nouvel horaire dont l’écart avec l’horaire sur lequel elle
s’applique (this) est égal à durée.
    
*/     
    public Horaire prochain(int durée) {
        return new Horaire(totalMinutes()+durée);
    }
    
}
