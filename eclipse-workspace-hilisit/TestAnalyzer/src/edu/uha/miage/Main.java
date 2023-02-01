package edu.uha.miage;

import edu.uha.miage.ui.DemoTextAnalyzer;

/**
 * TODO 1.
 * <pre>
 * Vous devez écrire les trois choses suivantes :
 *      1. l'interface edu.uha.miage.interfaces.TextAnalyzer
 *      2. sa première implémentation : edu.uha.miage.implementations.PhoneNumberAnalyzer
 *      3. sa deuxième implémentation : edu.uha.miage.implementations.EmailAddressAnalyzer
 *
 * Attention de bien respecter les noms.
 *
 *  1. l'interface TextAnalyzer
 *
 *      Dans notre application, un analyseur de texte (TextAnalyzer) est un objet qui permet
 *         1. de verifier qu'un texte est valide par une méthode isValid(String aText)
 *         2. de retourner une description de la validation.
 *
 *      Une implémentation possible est l'analyseur d'adresse mail que vous allez programmer.
 *
 *  2. l'analyseur d'adresse mail (PhoneNumberAnalyzer)
 *
 *      C'est une implémentation de TextAnalyser.
 *
 *         1. Pour rendre les choses simples, nous dirons qu'un numéro de téléphone est valide
 *            s'il est composé de 10 chiffres de 0 à 9.
 *
 *         2. Sa description, eh bien c'est le point 1.
 *
 *  3. l'analyseur d'adresse mail (EmailAddressAnalyzer)
 *
 *      C'est une implémentation de TextAnalyser.
 *
 *         1. Pour rendre les choses simples, nous dirons qu'une adresse mail est valide
 *            si elle comprend deux mots séparés d'un arobas (@)
 *
 *         2. Sa description, eh bien c'est le point 1.
 *
 *  Remarque :
 *
 *  Vous pouvez ignorez les (deux) classes du paquetage edu.uha.miage.ui
 *
 *  Elles ne servent qu'à tester votre programme. Dès qu'il est compilable, vous pouvez
 *  l'exécuter en "lançant le Main" et vous verrez une fenêtre s'ouvrir qui se présente ainsi
 *  *---------------------------------------------------------------------*
 *  |             Adresse électronique et numéro de téléphone             |
 *  *---------------------------------------------------------------------*
 *  | Entrez votre adresse mail :  ____________________________ [Envoyer] |
 *  | Entrez votre numéro de tél : ____________________________ [Appeler] |
 *  *---------------------------------------------------------------------*
 *
 *  Quand vous aurez correctement écrit l'interface et ses deux implémentations, en mettant
 *  le focus sur la fenêtre et en laissant la souris suffisamment longtemps dans un champ
 *  de saisie, votre description devrait apparaître.
 *
 *  Le bouton [Envoyer] devrait devenir cliquable dès qu'une adresse mail est correctement écrite
 *  dans le champ prévu à cet effet.
 *
 *  C'est similaire avec le bouton [Appeler].
 *
 *  À la fin de l'exercice, j'espère que vous serez convaincu qu'il serait aisé de faire un champ
 *  de texte pour valider n'importe quoi d'autre comme un numéro de sécurité sociale par exemple.
 *
 * </pre>
 */
public class Main {

    public static void main(String[] args) {
        DemoTextAnalyzer.launch();
    }
}
