import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Prueba extends JPanel {
  GridBagConstraints constraints = new GridBagConstraints();

  public Prueba() {
    setLayout(new GridBagLayout());
    constraints.weightx = 1.0;
    constraints.weighty = 1.0;
    constraints.fill = GridBagConstraints.BOTH;
    int x, y;
    constraints.gridheight = 2; // span two rows
    addGB(new JButton("one"), x = 0, y = 0);
    constraints.gridheight = 1; // set it back
    addGB(new JButton("two"), x = 1, y = 0);
    addGB(new JButton("three"), x = 2, y = 0);
    constraints.gridwidth = 2; // span two columns
    addGB(new JButton("four"), x = 1, y = 1);
    constraints.gridwidth = 1; // set it back
  }

  void addGB(Component component, int x, int y) {
    constraints.gridx = x;
    constraints.gridy = y;
    add(component, constraints);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("GridBag3");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(200, 200);
    frame.setLocation(200, 200);
    frame.setContentPane(new Prueba());
    frame.setVisible(true);
  }
}
