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
package edu.uha.miage.ref;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class Utils {
    
    public static int sum(int a, int b, int c) {
        return a+b+c;
    }
    
    public static double average(int a, int b, int c) {
        return (a+b+c)/3.0;
    }
    
    public static String conc(int a, int b, int c) {
        return ""+a+b+c;
    }
    
    public static boolean areSorted(int a, int b, int c, int d, int e, int f) {
        return a <= b && b <= c && c <= d && d <= e && e <= f;
    }
}
