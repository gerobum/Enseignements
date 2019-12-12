/*
 * Copyright (C) 2019 Yvan Maillot <yvan.maillot@uha.fr>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.uha.miage.date.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class ListModel implements Serializable {

    private String nom;
    private LocalDate anniv;

    public ListModel(String nom, LocalDate anniv) {
        this.nom = nom;
        this.anniv = anniv;
    }

    public ListModel() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getAnniv() {
        return anniv;
    }

    public void setAnniv(LocalDate anniv) {
        this.anniv = anniv;
    }
    
    public int getDay() {
        return anniv.getDayOfMonth();
    }
    
    public int getMonth() {
        return anniv.getMonthValue();
    }
    
    public int getYear() {
        return anniv.getYear();
    }

}
