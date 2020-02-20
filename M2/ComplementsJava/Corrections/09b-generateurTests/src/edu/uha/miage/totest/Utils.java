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

<<<<<<< HEAD
import edu.uha.miage.annotations.ParamValues;
=======
>>>>>>> 9cbb4fafef69031985d694228a49a5499749f347
import edu.uha.miage.annotations.SetOfTest;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class Utils {
<<<<<<< HEAD

    @ParamValues({"6", "1", "2", "3"})
    @ParamValues({"0", "-1", "-2", "3"})
    @ParamValues({"9", "2", "3", "4"})
    public static int sum(int a, int b, int c) {
        return 0;
    }

    @SetOfTest(
            types = {double.class, int.class, int.class, int.class},
            value = {
                @ParamValues({"2.0", "1", "2", "3"}),
                @ParamValues({"0.0", "-1", "-2", "3"}),
                @ParamValues({"2.666666666667", "2", "3", "3"}),}
    )
    public static double average(int a, int b, int c) {
        return 0.0;
    }

    @SetOfTest(
            types = {String.class, int.class, int.class, int.class},
            value = {
                @ParamValues({"123", "1", "2", "3"}),
                @ParamValues({"-1-2-3", "-1", "-2", "3"}),
                @ParamValues({"233", "2", "3", "3"}),}
    )
    public static String conc(int a, int b, int c) {
        return "";
    }

    @SetOfTest(
            types = {boolean.class, int.class, int.class, int.class},
            value = {
                @ParamValues({"true", "1", "2", "3", "4", "5", "6"}),
                @ParamValues({"false", "6", "2", "3", "4", "5", "6"})
            }
    )
=======
    
    @SetOfTest({"6", "1", "2", "3"})
    @SetOfTest({"0", "-1", "-2", "3"})
    @SetOfTest({"9", "2", "3", "4"})
    public static int sum(int a, int b, int c) {
        return 0;
    }
    
    @SetOfTest(
            types = {double.class, int.class, int.class, int.class},
            value = {"2.0", "1", "2", "3"})
    @SetOfTest(
            types = {double.class, int.class, int.class, int.class},
            value = {"0.0", "-1", "-2", "3"})
    @SetOfTest(
            types = {double.class, int.class, int.class, int.class},
            value = {"3.0", "2", "3", "4"})
    @SetOfTest(
            types = {double.class, int.class, int.class, int.class},
            value = {"2.666666666667", "2", "3", "3"})
    public static double average(int a, int b, int c) {
        return 0.0;
    }
    
    public static String conc(int a, int b, int c) {
        return "";
    }
    
>>>>>>> 9cbb4fafef69031985d694228a49a5499749f347
    public static boolean areSorted(int a, int b, int c, int d, int e, int f) {
        return false;
    }
}
