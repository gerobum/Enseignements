package code;

import annotations.Creation;
import annotations.Date;
import annotations.Revision;
import annotations.Version;
import static types.Jour.*;
import static types.Mois.*;

/*
 * 7.2 Création et révisions
 * On aimerait qu’une classe puisse être munie d’un préambule de création et 
 * d’autant de préambules de révision qu’elle a subi de révisions.
 * 
 * 6. Écrire quelques exemples de classe pour tester les annotations 
 *    Création et Révision.
 */
@Revision(
        auteur="CMoi",
        date=@Date(j=_9,m=NOVEMBRE,a=2015),
        version=@Version)
@Revision(
        auteur="Ma pomme",
        date=@Date(j=_25,m=OCTOBRE,a=2014),
        version=@Version)
@Revision(
        auteur="Bibi",
        date=@Date(j=_11,m=MAI,a=2011),
        version=@Version)
@Creation(
        auteur="Yvan",
        date=@Date(j=_1,m=FEVRIER,a=2010),
        version=@Version)

public class Annoté {
    void uneMethode() {
        
    }
}
