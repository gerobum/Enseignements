/*
 * Creative commons CC BY-NC-SA 2021 Yvan Maillot <yvan.maillot@uha.fr>
 *
 *     Share - You can copy and redistribute the material in any medium or format
 * 
 *     Adapt - You can remix, transform, and build upon the material 
 * 
 * Under the following terms :
 * 
 *     Attribution - You must give appropriate credit, provide a link to the license, 
 *     and indicate if changes were made. You may do so in any reasonable manner, 
 *     but not in any way that suggests the licensor endorses you or your use. 
 * 
 *     NonCommercial — You may not use the material for commercial purposes. 
 * 
 *     ShareAlike — If you remix, transform, or build upon the material, 
 *     you must distribute your contributions under the same license as the original. 
 * 
 * Notices:    You do not have to comply with the license for elements of 
 *             the material in the public domain or where your use is permitted 
 *             by an applicable exception or limitation. 
 * 
 * No warranties are given. The license may not give you all of the permissions 
 * necessary for your intended use. For example, other rights such as publicity, 
 * privacy, or moral rights may limit how you use the material. 
 * 
 * See <https://creativecommons.org/licenses/by-nc-sa/4.0/>.
 */
//
// Created by yvan on 23/03/23.
//

#ifndef EXAMEN_CCI_2023_COURSDELASEMAINE_H
#define EXAMEN_CCI_2023_COURSDELASEMAINE_H

#include "UnCours.h"

class CoursDeLaSemaine {
private:
    UnCours *d_tete;        // Pointeur sur le premier cours
public:
    CoursDeLaSemaine();        // Constructeur
    ~CoursDeLaSemaine();       // Destructeur
    int dureeTotale() const;   // Durée totale des cours
    bool toutesLesMatieres() const; // Vrai si toutes les matières sont présentes
    int nbJoursLibres() const; // Nombre de jours sans cours
    bool horaireLibre(int debut, int fin, int jour) const; // Vrai si l'horaire est libre
    void ajout(int debut, int fin, int jour, char matiere); // Ajout d'un cours
};



#endif //EXAMEN_CCI_2023_COURSDELASEMAINE_H
