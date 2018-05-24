import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;;

public class StorageRoomPanel extends JPanel implements RoomControl {
  private JPanel panel1;
  private JPanel panel2;
  private JPanel panel3;

  private JSpinner quantitySpinner;
  private JComboBox<String> itemBox;
  private JLabel incomingItemLabel;
  private JLabel quantityLabel;
  private JButton recieveButton;

  private JButton invaderButton;

  private JLabel temperatureLabel;
  private JSpinner temperatureSpinner;

  String[] items = new String[] { "lfkanf", "Hfasfs", "fasfaf", "asfasf" };

  public StorageRoomPanel() {
    super(new GridLayout(5, 1), true);

    setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Raktar", TitledBorder.LEADING,
        TitledBorder.TOP, null, new Color(0, 0, 0)));

    panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 1), true);
    add(panel1);
    panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 1), true);
    add(panel2);
    panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 1), true);
    add(panel3);

    incomingItemLabel = new JLabel("Beerkezett termek");
    panel1.add(incomingItemLabel);

    itemBox = new JComboBox<>(items);
    panel1.add(itemBox);

    quantityLabel = new JLabel("Kapott mennyiseg");
    panel2.add(quantityLabel);

    quantitySpinner = new JSpinner();
    quantitySpinner.setValue(1);
    panel2.add(quantitySpinner);

    recieveButton = new JButton("Felvetel");
    invaderButton = new JButton("Behatolo");

    add(recieveButton);
    add(invaderButton);

    temperatureLabel = new JLabel("Homerseklet");
    panel3.add(temperatureLabel);

    temperatureSpinner = new JSpinner();
    temperatureSpinner.setValue(24);
    panel3.add(temperatureSpinner);
  }

  public int getQuantityValue() {
    return 1;
  }

}

interface RoomControl {
  public int getQuantityValue();
}