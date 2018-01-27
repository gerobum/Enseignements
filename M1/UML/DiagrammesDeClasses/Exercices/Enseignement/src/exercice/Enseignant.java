/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercice;

import java.util.Date;
import java.util.Set;

/**
 *
 * @author yvan
 */
public class Enseignant {
    private String nom;
    private String prénom;
    private Date dateNaissance;
    private Set<Cours> cours;
    private Cours responsabilite;
}
