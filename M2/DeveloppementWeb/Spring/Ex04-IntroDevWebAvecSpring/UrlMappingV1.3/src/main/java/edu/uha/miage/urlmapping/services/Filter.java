package edu.uha.miage.urlmapping.services;

import java.io.Serializable;

/**
 *
 * @author yvan
 */
public class Filter implements Serializable {

    private String patternName;
    private int ageMini;
    private int ageMaxi;

    public Filter(String patternName, String ageMiniAsString, String ageMaxiAsString) {
        this.patternName = (patternName == null || patternName.trim().isEmpty()) ? "%" : patternName.replaceAll("\\*", "%");
        try {
            ageMini = Integer.parseInt(ageMiniAsString);
        } catch (NumberFormatException ex) {
            ageMini = 0;
        }
        try {
            ageMaxi = Integer.parseInt(ageMaxiAsString);
        } catch (NumberFormatException ex) {
            ageMaxi = 99;
        }
    }

    public Filter() {
        this("", "", "");
    }

    public String getPatternName() {
        return patternName;
    }

    public int getAgeMini() {
        return ageMini;
    }

    public int getAgeMaxi() {
        return ageMaxi;
    }

}
