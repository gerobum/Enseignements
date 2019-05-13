/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package fr.miage.core.entity;

/**
 *
 * @author yvan
 */
public interface IOperation {
    public Double calcul(double operand) throws Exception;
    public String getName();
}
