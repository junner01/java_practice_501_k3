package ex_241023_cha15.homework;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import java.util.List;

public class Jh1_ListView extends JPanel {
  private final JTable table;

  private Object[][] createMatrix(List<Jh1_MemberDTO> list) {
    Object[][] matrix = new Object[list.size()][3];
    for (int i = 0; i < matrix.length; i += 1) {
      Jh1_MemberDTO member = list.get(i);
      matrix[i][0] = member.id;
      matrix[i][1] = member.name;
      matrix[i][2] = member.email;
    }
    return matrix;
  }

  public Jh1_ListView() {
    this.setLayout(new BorderLayout());
    this.setBorder(new EmptyBorder(10, 10, 10, 10));
    table = new JTable();
    JScrollPane scrollPane = new JScrollPane(table);
    table.setFillsViewportHeight(true);

    this.add(scrollPane);
    this.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentShown(ComponentEvent e) {
        fetchMembers();
      }
    });
  }

  private void alert(String message, int type) {
    JOptionPane.showMessageDialog(this, message, "Login", type);
  }

  void fetchMembers() {
    try {
      List<Jh1_MemberDTO> list =  Jh1_MemberDAO.getInstance().selectAll();
      table.setModel(new DefaultTableModel(createMatrix(list), new String[]{"id", "name", "email"}));
    } catch (SQLException sex) {
      sex.printStackTrace();
      alert("An error occurred on server. ("+ sex.getErrorCode() + ")", JOptionPane.INFORMATION_MESSAGE);

    }
  }

}
