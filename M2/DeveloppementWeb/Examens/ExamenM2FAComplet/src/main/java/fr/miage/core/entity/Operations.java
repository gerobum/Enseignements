/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package fr.miage.core.entity;

import fr.miage.core.entity.impl.Operation;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.stereotype.Component;

/**
 *
 * @author yvan
 */
@Component
public class Operations implements Serializable {

    private final List<Operation> listOp;
    //private Operation selectedOp;
    @Min(value = 0, message = "Il faut choisir une opération")
    private int selectedOpId;
    @NotNull
    @Pattern(regexp = "-?[0-9]*\\.?[0-9]*", message = "Entrez un nombre (double)")
    private String operand;
    private Double result;

    public Operations() {
        listOp = Arrays.stream(Math.class.getDeclaredMethods())
                .filter(p -> p.getReturnType() == double.class)
                .filter(p -> p.getParameterCount() == 1)
                .filter(p -> p.getParameterTypes()[0] == double.class)
                .map(p -> new Operation(p))
                .collect(Collectors.toList());
        //selectedOp = null;
        selectedOpId = -1;
        result = null;
    }

    /*public Double calcul() throws Exception {
        if (selectedOp != null) {
            return listOp.get(selectedOpId).calcul(operand);
        } else {
            return null;
        }
    }*/
    public void calculWithId() throws Exception {
        if (selectedOpId < 0) {
            result = null;
        } else {
            result = listOp.get(selectedOpId).calcul(Double.parseDouble(operand));
        }
        System.out.println(result);
    }

    public int getSelectedOpId() {
        return selectedOpId;
    }

    public void setSelectedOpId(int selectedOpId) {
        this.selectedOpId = selectedOpId;
    }


    /*public Operation getSelectedOp() {
        return selectedOp;
    }

    public void setSelectedOp(Operation selectedOp) {
        this.selectedOp = selectedOp;
    }*/
    public List<Operation> getListOp() {
        return listOp;
    }

    public String getOperand() {
        return operand;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }

    public Double getResult() {
        return result;
    }

}
