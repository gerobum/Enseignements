/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.uha.miage.models;

import edu.uha.miage.service.RendezVous;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author yvan
 */
public class ModelRdv implements Serializable {

    private String title, date, time;
    private boolean titleOk, dateOk, timeOk, notPassed;
    private RendezVous rendezVous;

    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("d/M/u");
    private static final DateTimeFormatter TF = DateTimeFormatter.ofPattern("H:m");

    public ModelRdv() {
        init();
    }

    public static void handle(HttpServletRequest request) {

        ModelRdv mrdv;
        mrdv = (ModelRdv) request.getAttribute("model");

        if (mrdv == null) {
            mrdv = new ModelRdv();
        }

        mrdv.title = request.getParameter("title");
        mrdv.date = request.getParameter("date");
        mrdv.time = request.getParameter("time");

        mrdv.titleOk = !mrdv.title.isBlank();

        LocalDate date = null;
        LocalTime time = null;
        try {
            date = LocalDate.parse(mrdv.date, DateTimeFormatter.ofPattern("d/M/u"));
        } catch (Exception ex) {
            mrdv.dateOk = false;
        }
        try {
            time = LocalTime.parse(mrdv.time, DateTimeFormatter.ofPattern("H:m"));
        } catch (Exception ex) {
            mrdv.timeOk = false;
        }

        if (mrdv.titleOk && mrdv.dateOk && mrdv.timeOk) {
            try {
                mrdv.rendezVous = new RendezVous(mrdv.title, date, time);
                request.setAttribute("rendezVous", mrdv.rendezVous);
                mrdv.notPassed = true;
                mrdv.init();
            } catch (DateTimeException dte) {
                mrdv.notPassed = false;
                mrdv.rendezVous = null;
            }
        }

        request.setAttribute("model", mrdv);

    }

    private void init() {
        title = "";
        date = LocalDate.now().format(DF);
        time = LocalTime.now().format(TF);
        titleOk = dateOk = timeOk = notPassed = true;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isTitleOk() {
        return titleOk;
    }

    public void setTitleOk(boolean titleOk) {
        this.titleOk = titleOk;
    }

    public boolean isDateOk() {
        return dateOk;
    }

    public void setDateOk(boolean dateOk) {
        this.dateOk = dateOk;
    }

    public boolean isTimeOk() {
        return timeOk;
    }

    public void setTimeOk(boolean timeOk) {
        this.timeOk = timeOk;
    }

    public RendezVous getRendezVous() {
        return rendezVous;
    }

    public void setRendezVous(RendezVous rendezVous) {
        this.rendezVous = rendezVous;
    }

    public boolean isNotPassed() {
        return notPassed;
    }

    public void setNotPassed(boolean notPassed) {
        this.notPassed = notPassed;
    }

}
