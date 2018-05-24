import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JSpinner;
import javax.swing.JButton;;

public class EnvironmentPanel extends JPanel implements RoomControl {
  private JPanel panel1;

  private JButton sendButton;

  private JLabel temperatureLabel;
  private JSpinner temperatureSpinner;

  public EnvironmentPanel() {
    super(new GridLayout(2, 1), true);

    setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Kornyezet", TitledBorder.LEADING,
        TitledBorder.TOP, null, new Color(0, 0, 0)));

    panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 1), true);
    add(panel1);

    sendButton = new JButton("Kuldes");

    add(sendButton);

    temperatureLabel = new JLabel("Homerseklet");
    panel1.add(temperatureLabel);

    temperatureSpinner = new JSpinner();
    temperatureSpinner.setValue(24);
    panel1.add(temperatureSpinner);
  }

}

interface RoomControl {

}