/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package fr.miage.core.entity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author yvan
 * 
 */
 public class Operation implements IOperation {
    private final Method method;
    private final String name;
    public Operation(Method method) {
        this.method = method;
        this.name = method.getName();
    }
    @Override
    public Double calcul(double operand) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return (Double) method.invoke(null, operand);
    }
    @Override
    public String getName() {
        return name;
    }    
}
