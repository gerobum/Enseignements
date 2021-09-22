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
public class DateModel implements Serializable {

    private LocalDate date;
    private String year, month, day;
    private boolean yearOk, monthOk, dayOk, dateOk;
    //private String yearMsg, monthMsg, dayMsg;
    private String dayOfWeek;
    private List<String> messages;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public DateModel() {
        this.year = this.month = this.day = "";
        this.messages = new LinkedList<>();
        this.dateOk = this.dayOk = this.monthOk = this.yearOk = true;
    }

    public static DateModel handle(HttpServletRequest request, HttpServletResponse response) {
        //modele.messages.clear();
        DateModel dateModel = new DateModel();
        dateModel.messages.clear();

        // Récupération des valeurs des entrées day, month et yeay saisies dans la vue
        dateModel.year = request.getParameter("year");
        dateModel.month = request.getParameter("month");
        dateModel.day = request.getParameter("day");

        int iday = -1;
        try {
            iday = Integer.parseInt(dateModel.day);
            dateModel.dayOk = true;
        } catch (Exception ex) {
            dateModel.messages.add("Le jour doit être un nombre entre 1 et 28 à 31");
            dateModel.dayOk = false;
        }
        int imonth = -1;
        try {
            imonth = Integer.parseInt(dateModel.month);
            if (imonth >= 1 && imonth <= 12) {
                dateModel.monthOk = true;
            } else {
                dateModel.monthOk = false;
            }
        } catch (Exception ex) {
            dateModel.messages.add("Le mois doit être entre 1 et 12");
            dateModel.monthOk = false;
        }
        int iyear = -1;
        try {
            iyear = Integer.parseInt(dateModel.year);
            dateModel.yearOk = iyear >= 0;
        } catch (Exception ex) {
            dateModel.messages.add("L'année doit être un entier positif");
            dateModel.yearOk = false;
        }

        try {
            dateModel.date = LocalDate.of(iyear, imonth, iday);
            dateModel.dateOk = true;
        } catch (Exception ex) {
            dateModel.dateOk = false;
        }

        if (dateModel.dayOk && dateModel.monthOk && dateModel.yearOk) {
            try {
                dateModel.date = LocalDate.of(iyear, imonth, iday);
                dateModel.dayOk = true;
            } catch (Exception ex) {
                dateModel.dayOk = false;
            }
        }

        if (dateModel.dateOk) {
            dateModel.messages.add(dateModel.date.toString());

            switch (dateModel.date.getDayOfWeek()) {
                case FRIDAY:
                    dateModel.messages.add("C'est un vendredi");
                    break;
                case MONDAY:
                    dateModel.messages.add("C'est un lundi");
                    break;
                case SATURDAY:
                    dateModel.messages.add("C'est un samedi");
                    break;
                case SUNDAY:
                    dateModel.messages.add("C'est un dimanche");
                    break;
                case THURSDAY:
                    dateModel.messages.add("C'est un jeudi");
                    break;
                case TUESDAY:
                    dateModel.messages.add("C'est un mardi");
                    break;
                case WEDNESDAY:
                    dateModel.messages.add("C'est un mercredi");
                    break;
            }

            if (dateModel.date.isLeapYear()) {
                dateModel.messages.add("C'était une année bissextile");
            }

        }

        return dateModel;
    }

    public static void main(String[] args) {
        System.out.println(LocalDate.of(2019, 11, 25));
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean isYearOk() {
        return yearOk;
    }

    public void setYearOk(boolean yearOk) {
        this.yearOk = yearOk;
    }

    public boolean isMonthOk() {
        return monthOk;
    }

    public void setMonthOk(boolean monthOk) {
        this.monthOk = monthOk;
    }

    public boolean isDayOk() {
        return dayOk;
    }

    public void setDayOk(boolean dayOk) {
        this.dayOk = dayOk;
    }

    public boolean isDateOk() {
        return dateOk;
    }

    public void setDateOk(boolean dateOk) {
        this.dateOk = dateOk;
    }
/*
    public String getYearMsg() {
        return yearMsg;
    }

    public void setYearMsg(String yearMsg) {
        this.yearMsg = yearMsg;
    }

    public String getMonthMsg() {
        return monthMsg;
    }

    public void setMonthMsg(String monthMsg) {
        this.monthMsg = monthMsg;
    }

    public String getDayMsg() {
        return dayMsg;
    }

    public void setDayMsg(String dayMsg) {
        this.dayMsg = dayMsg;
    }*/

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

}
