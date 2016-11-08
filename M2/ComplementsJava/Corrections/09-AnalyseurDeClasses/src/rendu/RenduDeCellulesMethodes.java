/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rendu;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author yvan
 */
public class RenduDeCellulesMethodes extends JLabel implements ListCellRenderer<Method> {

  @Override
  public Component getListCellRendererComponent(JList<? extends Method> list, Method value, int index, boolean isSelected, boolean cellHasFocus) {
    Color background, foreground;
    if (isSelected) {
      background = Color.GRAY;
      foreground = Color.WHITE;
    } else {
      background = Color.WHITE;
      foreground = Color.BLACK;
    }

    setBackground(background);
    setForeground(foreground);
    setOpaque(true);
    StringBuilder item;
    if (value == null) {
      item = new StringBuilder();
    } else {
      item = new StringBuilder(Modifier.toString(value.getModifiers()));
      item.append(" ").append(value.getReturnType().getName()).append(" ").append(value.getName()).append("(");
      if (value.getParameterTypes().length != 0) {
        item.append(value.getParameterTypes()[0].getCanonicalName());
        for (int i = 1; i < value.getParameterTypes().length; ++i) {
          item.append(", ").append(value.getParameterTypes()[i].getCanonicalName());
        }
      }
      item.append(")");



    }
    setText(item.toString());
    return this;
  }
}
