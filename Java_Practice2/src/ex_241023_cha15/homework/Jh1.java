package ex_241023_cha15.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Jh1 {
  public static JButton button(String text, ActionListener l) {
    var _button = new JButton(text);
    _button.addActionListener(l);
    return _button;
  }

  public static <T extends JTextField> T textField(T t) {
    t.setMaximumSize(new Dimension(400, 20)); // Limit height
    return t;
  }
}