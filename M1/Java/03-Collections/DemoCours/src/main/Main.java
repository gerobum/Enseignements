/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author yvan
 */
public class Main {

    public static void main(String[] args) {
        TreeSet<Object> s = new TreeSet<>(new Comparator<Object>() {

            @Override
            public int compare(Object t, Object u) {
                return t.hashCode() - u.hashCode();
            }
        });

        for (int i = 0; i < 10; ++i) {
            s.add(new Object());
        }

        for (Object o : s) {
            System.out.println(o);
        }
// Pour un ensemble d'entiers
        Set<Integer> si = new HashSet<>();
// Pour une liste de couleurs
        List<Color> lc = new LinkedList<>();
// Pourquoi pas un tableau de tableaux
        List<List<Double>> matrice = new ArrayList<>();
// Pour associer un ami à sa date anniversaire.
        Map<Ami, Date> anniv = new HashMap<>();
// Pour associer une date anniversaire à une liste d'amis.
// Un TreeMap est possible parce que
// java.util.Date est un Comparable.
// Il faut aussi que Ami soit défini.
        Map<Date, List<Ami>> vinna = new TreeMap<>();

        for (int i = 1; i < 10; ++i) {
            si.add(i);
        }

        lc.add(Color.blue);
        lc.add(Color.white);
        lc.add(Color.red);

        for (int i = 0; i < 3; ++i) {
            matrice.add(new ArrayList());
        }

        anniv.put(new Ami(), new Date());

        vinna.put(new Date(), new LinkedList<Ami>());
// Pour une liste de couleurs

        List<Color> france = Collections.synchronizedList(Collections.unmodifiableList(lc));
        // france.add(Color.red); // Impossible car unmodifiableList

//france.add(Color.black);
        for (Color c : france) {
            System.out.println(c);
        }

        Iterator<Color> i = france.iterator(); // Must be in synchronized block
        while (i.hasNext()) {
            System.out.println(i.next());
        }

        Map<Ami, Date> anniv_sync = Collections.synchronizedMap(anniv);
        
        

    }

}
