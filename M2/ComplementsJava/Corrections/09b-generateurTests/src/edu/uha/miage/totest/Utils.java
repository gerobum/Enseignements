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


import edu.uha.miage.annotations.ParamValues;
import edu.uha.miage.annotations.SetOfTest;


/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class Utils {

    

    @ParamValues({"6", "1", "2", "3"})
    @ParamValues({"0", "-1", "-2", "3"})
    @ParamValues({"9", "2", "3", "4"})
    public static int sum(int a, int b, int c) {
        return a+b+c;
    }

    @SetOfTest(
            types = {double.class, int.class, int.class, int.class},
            value = {
                @ParamValues({"2.5", "1", "2", "3"}),
                @ParamValues({"0.0", "-1", "-2", "3"}),
                @ParamValues({"2.666666666667", "2", "3", "3"}),}
    )
    public static double average(int a, int b, int c) {
        return (a+b+c)/3.0;
    }

    @SetOfTest(
            types = {String.class, int.class, int.class, int.class},
            value = {
                @ParamValues({"123", "1", "2", "3"}),
                @ParamValues({"-1-2-3", "-1", "-2", "-3"}),
                @ParamValues({"233", "2", "3", "3"}),}
    )
    public static String conc(int a, int b, int c) {
        return ""+a+b+c;
    }

    @SetOfTest(
            types = {boolean.class, int.class, int.class, int.class, int.class, int.class, int.class},
            value = {
                @ParamValues({"true", "1", "2", "3", "4", "5", "6"}),
                @ParamValues({"false", "6", "2", "3", "4", "5", "6"})
            }
    )
    public static boolean areSorted(int a, int b, int c, int d, int e, int f) {
        return a <= b && b <= c && c <= d && d <= e && e <= f;
    }
}
