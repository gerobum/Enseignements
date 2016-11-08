/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rendu;

import java.awt.Color;
import java.awt.Component;
import java.lang.reflect.Field;
import javax.swing.JLabel;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author yvan
 */
public class RenduDeCellulesAttributs extends JLabel implements ListCellRenderer<Field> {

  @Override
  public Component getListCellRendererComponent(JList<? extends Field> list, Field value, int index, boolean isSelected, boolean cellHasFocus) {
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
      item.append(" ").append(value.getType()).append(" ").append(value.getName());



    }
    setText(item.toString());
    return this;
  }
}
