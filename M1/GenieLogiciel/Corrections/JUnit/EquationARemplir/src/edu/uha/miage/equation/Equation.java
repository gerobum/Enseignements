/*
 * Copyright (C) 2021 Yvan Maillot <yvan.maillot@uha.fr>.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package edu.uha.miage.equation;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class Equation extends AbstractEquation {

    private int rootsCount;
    private double x1, x2;

    public Equation(int a, int b, int c) {
        super(a, b, c);
        double delta = b * b - 4 * a * c;
        if (delta < 0) {
            rootsCount = 0;
            x1 = x2 = Double.NaN;
        } else if (delta == 0.0) {
            rootsCount = 1;
            x1 = x2 = (-b*1.0)/(2*a);
        } else {
            rootsCount = 2;
            x1 = (-b - Math.sqrt(delta))/(2*a);
            x2 = (-b + Math.sqrt(delta))/(2*a);
        }
    }

    @Override
    public int getRootsCount() {
        return rootsCount;
    }

    @Override
    public double getX1() {
        return x1;
    }

    @Override
    public double getX2() {
        return x2;
    }

}
