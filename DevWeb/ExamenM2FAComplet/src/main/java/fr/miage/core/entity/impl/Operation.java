/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package fr.miage.core.entity.impl;

import fr.miage.core.entity.IOperation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author yvan
 * 
 */
 public class Operation implements IOperation {

    private final Method method;
    private final String name;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Operation(Method method) {
        this.method = method;
        this.name = method.getName();
    }

    @Override
    public Double calcul(double operand) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return (Double) method.invoke(null, operand);
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
    
    
}
