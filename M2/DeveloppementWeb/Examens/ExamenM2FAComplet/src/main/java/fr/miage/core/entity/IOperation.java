/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package fr.miage.core.entity;

import java.util.Optional;


/**
 *
 * @author yvan
 */
@FunctionalInterface
public interface IOperation {
    public Double calcul(double operand) throws Exception;
}
