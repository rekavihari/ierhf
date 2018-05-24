import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JSpinner;
import javax.swing.JButton;;

public class RestaurantPanel extends JPanel implements RoomControl {
  private JPanel panel1;

  private JButton recieveButton;

  private JLabel temperatureLabel;
  private JSpinner temperatureSpinner;

  public RestaurantPanel() {
    super(new GridLayout(2, 1), true);

    setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Etterem", TitledBorder.LEADING,
        TitledBorder.TOP, null, new Color(0, 0, 0)));

    panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 1), true);
    add(panel1);

    recieveButton = new JButton("Felvetel");

    add(recieveButton);

    temperatureLabel = new JLabel("Homerseklet");
    panel1.add(temperatureLabel);

    temperatureSpinner = new JSpinner();
    temperatureSpinner.setValue(24);
    panel1.add(temperatureSpinner);
  }

}

interface RoomControl {

}