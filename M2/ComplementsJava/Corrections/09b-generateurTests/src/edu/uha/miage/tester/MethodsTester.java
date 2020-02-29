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
import edu.uha.miage.annotations.SetOfTest;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class MethodsTester {
    
    private final List<Method> staticMethodsAnnotedSetOfTests;
    private final List<Method> staticMethodsAnnotedParamValues;

    public MethodsTester(List<Method> staticMethodsAnnotedSetOfTests, List<Method> staticMethodsAnnotedParamValues) {
        this.staticMethodsAnnotedSetOfTests = staticMethodsAnnotedSetOfTests;
        this.staticMethodsAnnotedParamValues = staticMethodsAnnotedParamValues;
    }
    
    

    public void testMethods() {
        staticMethodsAnnotedSetOfTests.stream()
                .forEach(m -> testMethodAnnotedSetOfTests(m));
        staticMethodsAnnotedParamValues.stream()
                .forEach(m -> testMethodParamValue(m));
    }

    private void testMethodAnnotedSetOfTests(Method m) {
        SetOfTest[] setOfTests = m.getAnnotationsByType(SetOfTest.class);

        ArrayList<Class<?>> params = new ArrayList<>(
                Arrays.asList(setOfTests[0].types())
        );
        for (int i = params.size(); i < m.getParameterCount(); ++i) {
            params.add(int.class);
        }

        testsMethod(m, setOfTests[0].value());

    }

    private void testsMethod(Method method, ParamValues[] paramValues) {
        System.out.println("-----------------------------------------------------");
        System.out.println(method);
        Arrays.stream(paramValues).forEach(
                paramValue -> {
                    testMethod(method, paramValue);
                }
        );
        System.out.println("-----------------------------------------------------");
    }

    private void testMethod(Method method, ParamValues paramValues) {
        String returnValue;
        String[] params;
        if (method.getReturnType() != void.class) {
            returnValue = paramValues.value()[0];
            params = Arrays.copyOfRange(paramValues.value(), 1, paramValues.value().length);
        } else {
            returnValue = null;
            params = paramValues.value();
        }
        testStaticMethod(method, returnValue, params);
    }

    private void testStaticMethod(Method method, String returnValueAsString, String[] paramValuesAsString) {
        if (returnValueAsString != null) {
            Object returnValue;
            Object[] paramValues;
            PropertyEditor editor;

            editor = PropertyEditorManager.findEditor(method.getReturnType());
            editor.setAsText(returnValueAsString);
            returnValue = editor.getValue();
            paramValues = new Object[paramValuesAsString.length];
            for (int i = 0; i < paramValuesAsString.length; ++i) {
                editor = PropertyEditorManager.findEditor(method.getParameterTypes()[i]);
                editor.setAsText(paramValuesAsString[i]);
                paramValues[i] = editor.getValue();
            }

            assertEquals(method, returnValue, paramValues);

        }
    }

    private boolean equals(Object oa, Object ob) {
        if (oa instanceof Number && ob instanceof Number) {
            double a = ((Number) oa).doubleValue();
            double b = ((Number) ob).doubleValue();
            return Math.abs(a - b) < 1e-6;
        } else {
            return oa.equals(ob);
        }
    }

    private void assertEquals(Method method, Object obj, Object expectedReturn, Object[] paramValues) {

        PropertyEditor editor;
        try {
            Object effectiveReturn = method.invoke(obj, paramValues);
            editor = PropertyEditorManager.findEditor(method.getReturnType());
            editor.setValue(effectiveReturn);
            System.out.println("-----------------------------------------------------");

            if (equals(expectedReturn, effectiveReturn)) {
                System.out.println("          SUCCES");
                editor.setValue(expectedReturn);
                System.out.println("La valeur attendue est " + editor.getAsText() + " et ");
                editor.setValue(effectiveReturn);
                System.out.println(toString(method, paramValues) + " = " + editor.getAsText());
            } else {
                System.out.println("********* ECHEC ************");
                editor.setValue(expectedReturn);
                System.out.println("La valeur attendue est " + editor.getAsText() + " alors que ");
                editor.setValue(effectiveReturn);
                System.out.println(toString(method, paramValues) + " = " + editor.getAsText());
            }
            System.out.println("-----------------------------------------------------");

        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | AssertionError ex) {

        }
    }

    private void assertEquals(Method method, Object expectedReturn, Object[] paramValues) {
        assertEquals(method, null, expectedReturn, paramValues);
    }

    private String toString(Method method, Object[] paramValues) {
        StringBuilder sb = new StringBuilder();
        PropertyEditor editor;
        sb.append(method.getName()).append("(");
        if (paramValues.length > 0) {
            editor = PropertyEditorManager.findEditor(method.getParameterTypes()[0]);
            editor.setValue(paramValues[0]);
            sb.append(editor.getAsText());

            for (int i = 1; i < paramValues.length; ++i) {
                editor = PropertyEditorManager.findEditor(method.getParameterTypes()[i]);
                editor.setValue(paramValues[i]);
                sb.append(", ").append(editor.getAsText());
            }
        }

        sb.append(")");

        return sb.toString();
    }

    private void testMethodParamValue(Method m) {

        ParamValues[] params = m.getAnnotationsByType(ParamValues.class);

        testsMethod(m, params);
    }

}
