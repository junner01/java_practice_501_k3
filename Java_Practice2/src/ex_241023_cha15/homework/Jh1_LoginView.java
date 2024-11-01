package ex_241023_cha15.homework;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class Jh1_LoginView extends JPanel {

  private Jh1_IndexView index;
  private final JTextField email;
  private final JTextField password;
  private final JButton loginButton;

  public Jh1_LoginView(Jh1_IndexView index) {
    this.index = index;
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.setBorder(new EmptyBorder(10, 10, 10, 10));
    this.add(new JLabel("Let's Login!!!!!!!!"));
    this.add(new JLabel("email"));
    this.add(email = Jh1.textField(new JTextField()));
    this.add(new JLabel("password"));
    this.add(password = Jh1.textField(new JPasswordField()));
    this.add(loginButton = Jh1.button("Login", this::doLogin));
  }

  private void sehChildrenEnable(boolean b) {
    email.setEnabled(b);
    password.setEnabled(b);
    loginButton.setEnabled(b);
  }

  private void alert(String message, int type) {
    JOptionPane.showMessageDialog(this, message, "Login", type);
  }


  private void doLogin(ActionEvent a) {

    sehChildrenEnable(false);

    SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
      @Override
      protected Boolean doInBackground() {
        try {
          Jh1_MemberDTO member = Jh1_MemberDAO.getInstance().selectByEmailAndPassword(email.getText(), password.getText());
          boolean success = member != null;
          if (success) {
            alert("Hello, " + member.name, JOptionPane.PLAIN_MESSAGE);
            index.dispatchLogin(member);
//            PseudoAuthentication.loginAs = member;
          } else {
            alert("Invalid user", JOptionPane.ERROR_MESSAGE);
          }
          return success;
        } catch (SQLException sex) {
          sex.printStackTrace();
          alert("An error occurred on server. ("+ sex.getErrorCode() + ")", JOptionPane.INFORMATION_MESSAGE);
          return false;
        }
      }

      @Override
      protected void done() { sehChildrenEnable(true); }
    };

    worker.run();
  }



}
