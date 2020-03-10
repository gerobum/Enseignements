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
package edu.uha.miage.tester;

import edu.uha.miage.annotations.ParamValues;
import edu.uha.miage.annotations.ParamsValues;
import edu.uha.miage.annotations.SetOfTest;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class TestsGenerator {

    private final MethodsTester methodsTester;

    public TestsGenerator(Class<?> toTest) {
        methodsTester = new MethodsTester(
                Arrays.stream(toTest.getDeclaredMethods())
                        .filter(m -> Modifier.isStatic(m.getModifiers()))
                        .filter(m -> m.isAnnotationPresent(SetOfTest.class))
                        .collect(Collectors.toList()),
                Arrays.stream(toTest.getDeclaredMethods())
                        .filter(m -> Modifier.isStatic(m.getModifiers()))
                        .filter(m -> m.isAnnotationPresent(ParamValues.class) || m.isAnnotationPresent(ParamsValues.class))
                        .collect(Collectors.toList())
        );
    }

    public void testMethods() {
        methodsTester.testMethods();
    }

}
