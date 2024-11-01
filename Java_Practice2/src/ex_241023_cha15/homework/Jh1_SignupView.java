package ex_241023_cha15.homework;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class Jh1_SignupView extends JPanel {
  private final JTextField username;
  private final JTextField email;
  private final JTextField password;
  private final JTextField passwordConfirm;
  private final JButton signupButton;

  public Jh1_SignupView() {
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.setBorder(new EmptyBorder(10, 10, 10, 10));
    this.add(new JLabel("This is Signup"));
    this.add(new JLabel("username"));
    this.add(username = Jh1.textField(new JTextField()));
    this.add(new JLabel("email"));
    this.add(email = Jh1.textField(new JTextField()));
    this.add(new JLabel("password"));
    this.add(password = Jh1.textField(new JPasswordField()));
    this.add(new JLabel("confirm"));
    this.add(passwordConfirm = Jh1.textField(new JPasswordField()));
    this.add(signupButton = Jh1.button("SIGN UPASDGIACVBFDAVON!!!!!", this::doSignup));
  }

  private void setChildrenEnable(boolean b) {
    username.setEnabled(b);
    email.setEnabled(b);
    password.setEnabled(b);
    passwordConfirm.setEnabled(b);
    signupButton.setEnabled(b);
  }

  private void wipe() {
    username.setText("");
    email.setText("");
    password.setText("");
    passwordConfirm.setText("");
  }


  private void alert(String message, int type) {
    JOptionPane.showMessageDialog(this, message, "Signup", type);
  }

  private void doSignup(ActionEvent a) {
    if (!password.getText().equals(passwordConfirm.getText())) {
      JOptionPane.showMessageDialog(this, "Check password again.");
      password.requestFocus();
      return;
    }

    setChildrenEnable(false);

    SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
      @Override
      protected Boolean doInBackground() {
        try {
          Jh1_MemberDAO.getInstance().insert(
            username.getText(),
            email.getText(),
            password.getText()
          );
          alert("Signed up successfully!!!!! Welcome, " + username.getText() + "!!!!!!!!!!!!", JOptionPane.INFORMATION_MESSAGE);
          wipe();
          return true;
        } catch (SQLException sex) {
          sex.printStackTrace();
          alert("An error occurred on server. ("+ sex.getErrorCode() + ")", JOptionPane.INFORMATION_MESSAGE);
          return false;
        }
      }

      @Override
      protected void done() {
        setChildrenEnable(true);
      }
    };

    worker.run();
  }

}
