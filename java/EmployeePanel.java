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

public class EmployeePanel extends JPanel implements RoomControl {

  private static final FlowLayout subPanelLayout = new FlowLayout(FlowLayout.LEFT, 2, 1);

  private JPanel panel1;
  private JPanel panel2;

  private JSpinner quantitySpinner;
  private JComboBox<String> itemBox;
  private JLabel orderedItemLabel;
  private JLabel quantityLabel;
  private JButton sendOrderButton;

  String[] items = new String[] { "lfkanf", "Hfasfs", "fasfaf", "asfasf" };

  public EmployeePanel() {
    super(new GridLayout(3, 1), true);

    setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Alkalmazott", TitledBorder.LEADING,
        TitledBorder.TOP, null, new Color(0, 0, 0)));

    panel1 = new JPanel(subPanelLayout, true);
    add(panel1);
    panel2 = new JPanel(subPanelLayout, true);
    add(panel2);

    orderedItemLabel = new JLabel("Rendelt termek");
    panel1.add(orderedItemLabel);

    itemBox = new JComboBox<>(items);
    panel1.add(itemBox);

    quantityLabel = new JLabel("Rendelt mennyiseg");
    panel2.add(quantityLabel);

    quantitySpinner = new JSpinner();
    quantitySpinner.setValue(1);
    panel2.add(quantitySpinner);

    sendOrderButton = new JButton("Rendeles");

    add(sendOrderButton);
  }

  public int getQuantityValue() {
    return (int) quantitySpinner.getValue();
  }

}

interface RoomControl {
  public int getQuantityValue();
}
