/*
 * Copyright (C) 2020 Yvan Maillot <yvan.maillot@uha.fr>
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
package edu.uha.miage.totest;

import edu.uha.miage.annotations.ToConstruct;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
@ToConstruct({"1", "2", "3"})
@ToConstruct({"2", "3", "4"})
@ToConstruct({"1", "2"})
@ToConstruct({"2", "3"})
@ToConstruct(
        types = {int.class, int.class, int.class, double.class, double.class, double.class},
        value = {"1", "2", "3", "0.5", "1.5", "2.5"}
)
public class TripletOfIntegers {
    private int a, b, c;
    private double e, f, g;
    
    public TripletOfIntegers(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public TripletOfIntegers(int a, int b) {
        this(a, b, 0);
    }
    
    public TripletOfIntegers(int a, int b, int c, double e, double f, double g) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.e = e;
        this.f = f;
        this.g = g;
    }
    
}
