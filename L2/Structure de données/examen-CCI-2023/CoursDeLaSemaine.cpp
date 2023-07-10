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

#include "CoursDeLaSemaine.h"
/**
 * Constructeur CoursDeLaSemaine::CoursDeLaSemaine() qui crée une liste vide.
 *
 */
CoursDeLaSemaine::CoursDeLaSemaine() : d_tete{nullptr} {
}


CoursDeLaSemaine::~CoursDeLaSemaine() {
    while (d_tete != nullptr) {
        UnCours *courant = d_tete;
        d_tete = d_tete->d_suiv;
        delete courant;
    }
}

int CoursDeLaSemaine::dureeTotale() const {
    int duree = 0;
    UnCours *courant = d_tete;
    while (courant != nullptr) {
        duree += courant->d_fin - courant->d_deb;
        courant = courant->d_suiv;
    }
    return duree;
}

bool CoursDeLaSemaine::toutesLesMatieres() const {
    UnCours *courant = d_tete;
    bool math = false;
    bool hist = false;
    bool bio = false;
    bool fran = false;
    bool toutesLesMatieres = false;
    while (!toutesLesMatieres && courant != nullptr) {
        math = math || courant->d_mat == 'M';
        hist = hist || courant->d_mat == 'H';
        bio = bio || courant->d_mat == 'B';
        fran = fran || courant->d_mat == 'F';
        toutesLesMatieres = math && hist && bio && fran;
        courant = courant->d_suiv;
    }
}

int CoursDeLaSemaine::nbJoursLibres() const {
    if (d_tete == nullptr) {
        return 5;
    } else {
        int nbJoursLibres = 0;
        UnCours *courant = d_tete;
        UnCours *suivant = courant->d_suiv;
        while (suivant != nullptr && suivant->d_jour != 5) {
            if (suivant->d_jour - courant->d_jour > 1) {
                nbJoursLibres+= suivant->d_jour - courant->d_jour + 1;
            }
            courant = suivant;
            suivant = suivant->d_suiv;
        }
        return nbJoursLibres;
    }
}

bool CoursDeLaSemaine::horaireLibre(int debut, int fin, int jour) const {
    if (d_tete == nullptr)
        return true;

    UnCours *courant = d_tete;
    while (courant != nullptr && courant->d_jour <= jour) {
        courant = courant->d_suiv;
    }
    if (courant != nullptr && courant->d_jour == jour) {
        return courant->d_deb >= fin || courant->d_fin <= debut;
    }
    return false;
}

void CoursDeLaSemaine::ajout(int debut, int fin, int jour, char matiere) {
    UnCours *courant = d_tete;
    UnCours *precedent = nullptr;
    while (courant != nullptr && courant->d_jour < jour) {
        precedent = courant;
        courant = courant->d_suiv;
    }
    if (courant != nullptr && courant->d_jour == jour) {
        if (courant->d_deb > debut) {
            UnCours *nouveau = new UnCours{debut, fin, jour, matiere};
            if (precedent == nullptr) {
                d_tete = nouveau;
            } else {
                precedent->d_suiv = nouveau;
            }
        } else {
            UnCours *nouveau = new UnCours{debut, fin, jour, matiere};
            courant->d_suiv = nouveau;
        }
    } else {
        UnCours *nouveau = new UnCours{debut, fin, jour, matiere};
        if (precedent == nullptr) {
            d_tete = nouveau;
        } else {
            precedent->d_suiv = nouveau;
        }
    }
}