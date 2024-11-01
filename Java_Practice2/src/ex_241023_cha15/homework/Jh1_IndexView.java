package ex_241023_cha15.homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Jh1_IndexView extends JFrame {

  private JPanel card;
  private CardLayout cardLayout;
  private final JButton showListButton;
  private final JButton logoutButton;

  public Jh1_IndexView() {
    this.setSize(600, 360);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    showListButton = Jh1.button("List", Jh1_IndexView.this::tryOpenList);
    showListButton.setEnabled(false);

    logoutButton = Jh1.button("Logout", e -> this.dispatchLogout());

    this.add(new JPanel() {{
      this.setLayout(new FlowLayout());
      this.add(Jh1.button("Login", _ -> cardLayout.show(card, "Login")));
      this.add(Jh1.button("Sign Up", _ -> cardLayout.show(card, "Signup")));
      this.add(showListButton);
    }}, BorderLayout.NORTH);

    this.add(card = new JPanel() {{
      this.setLayout(cardLayout = new CardLayout());
      this.add(new Jh1_LoginView(Jh1_IndexView.this), "Login");
      this.add(new Jh1_SignupView(), "Signup");
      this.add(new Jh1_ListView(), "List");
    }}, BorderLayout.CENTER);

    this.setVisible(true);
  }

  void tryOpenList(ActionEvent e) {
    cardLayout.show(card, "List");
  }

  public static void main(String[] args) {
    new Jh1_IndexView();
  }

  void dispatchLogin(Jh1_MemberDTO member) {
    showListButton.setEnabled(true);
    this.setTitle(String.format("Login as : %s <%s>", member.name, member.email));
    this.add(logoutButton, BorderLayout.SOUTH);
    this.repaint();
  }
  void dispatchLogout() {
    showListButton.setEnabled(false);
    cardLayout.show(card, "Login");
    this.remove(logoutButton);
    this.repaint();
  }
}
