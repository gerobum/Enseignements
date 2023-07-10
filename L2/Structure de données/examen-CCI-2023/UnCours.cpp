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

#include "UnCours.h"

/* Constructeur UnCours::UnCours(int deb,int fin, int jour,char mat) qui crée un nouveau chainon pour un cours, sans suivant ni précédent. deb, fin, jour et mat sont respectivement l’heure de début, l’heure de fin, le jour et la matière correspondant au cours.
    *
    * UnCours::UnCours(int deb,int fin, int jour,char mat) {
    * 	d_deb = deb;
    * 	d_fin = fin;
    * 	d_jour = jour;
    * 	d_mat = mat;
    * 	d_prec = nullptr;
    * 	d_suiv = nullptr;
    * }
    *
    *
    *
    *
    *   UnCours(int deb,int fin, int jour, char mat); // Constructeur   */
UnCours::UnCours(int deb, int fin, int jour, char mat) :
        d_deb(deb),
        d_fin(fin),
        d_jour(jour),
        d_mat(mat),
        d_prec(nullptr),
        d_suiv(nullptr) {
}

