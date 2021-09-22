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
public class AnnivModel implements Serializable {

    private DateModel dateModel;
    private String nom;
    private boolean nomOk;
    private List<String> messages;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public AnnivModel() {
        dateModel = new DateModel();
        this.nom = "";
        messages = new LinkedList<>();
        this.nomOk = true;
    }

    public static AnnivModel handle(HttpServletRequest request, HttpServletResponse response) {
        AnnivModel annivModel = new AnnivModel();
        
        annivModel.messages.clear();

        annivModel.dateModel = DateModel.handle(request, response);
        

        annivModel.nom = request.getParameter("who");
        
        if (annivModel.nom == null || annivModel.nom.trim().isEmpty()) {
            annivModel.nomOk = false;
            annivModel.messages.add("Il doit y avoir un nom");
        } else {
            annivModel.nomOk = true;
        }
        
        if (annivModel.isDateModelOk() && annivModel.nomOk) {
            annivModel.messages.add("Bon anniversaire");
        } else {
            annivModel.messages.addAll(annivModel.dateModel.getMessages());
        }

        return annivModel;
    }
    
    public boolean isDateModelOk() {
        return isDateOk() && isDayOk() && isMonthOk() && isYearOk();
    }
    
    public boolean isOk() {
        return isDateModelOk() && nomOk;
    }

    public LocalDate getDate() {
        return dateModel.getDate();
    }

    public void setDate(LocalDate date) {
        dateModel.setDate(date);
    }

    public String getYear() {
        return dateModel.getYear();
    }

    public void setYear(String year) {
        dateModel.setYear(year);
    }

    public String getMonth() {
        return dateModel.getMonth();
    }

    public void setMonth(String month) {
        dateModel.setMonth(month);
    }

    public String getDay() {
        return dateModel.getDay();
    }

    public void setDay(String day) {
        dateModel.setDay(day);
    }

    public boolean isYearOk() {
        return dateModel.isYearOk();
    }

    public void setYearOk(boolean yearOk) {
        dateModel.setYearOk(yearOk);
    }

    public boolean isMonthOk() {
        return dateModel.isMonthOk();
    }

    public void setMonthOk(boolean monthOk) {
        dateModel.setMonthOk(monthOk);
    }

    public boolean isDayOk() {
        return dateModel.isDayOk();
    }

    public void setDayOk(boolean dayOk) {
        dateModel.setDayOk(dayOk);
    }

    public boolean isDateOk() {
        return dateModel.isDateOk();
    }

    public void setDateOk(boolean dateOk) {
        dateModel.setDateOk(dateOk);
    }

    public DateModel getDateModel() {
        return dateModel;
    }

    public void setDateModel(DateModel dateModel) {
        this.dateModel = dateModel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isNomOk() {
        return nomOk;
    }

    public void setNomOk(boolean nomOk) {
        this.nomOk = nomOk;
    }
    
    
}
