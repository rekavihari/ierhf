import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class StorageRoomInfoPanel extends JPanel implements AgentInfo {
  private JTextField statusField;
  private JLabel statusLabel;

  private JTextField temperatureField;
  private JLabel temperatureLabel;

  private JTextField temperatureStatusField;
  private JLabel temperatureStatusLabel;

  private JTextField alertStatusField;
  private JLabel alertStatusLabel;

  private JTextField stockStatusField;
  private JLabel stockStatusLabel;

  public StorageRoomInfoPanel() {
    super(new GridLayout(5, 1), true);

    setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Raktar", TitledBorder.LEADING,
        TitledBorder.TOP, null, new Color(0, 0, 0)));

    // panel 1
    JPanel panel1 = new JPanel();

    statusLabel = new JLabel("Statusz");
    statusLabel.setPreferredSize(new Dimension(130, 25));
    panel1.add(statusLabel);

    statusField = new JTextField();
    statusField.setEditable(false);
    statusField.setText("Fut");
    statusField.setColumns(10);
    panel1.add(statusField);

    add(panel1);

    // panel 2
    JPanel panel2 = new JPanel();

    temperatureLabel = new JLabel("Homerseklet");
    temperatureLabel.setPreferredSize(new Dimension(130, 25));
    panel2.add(temperatureLabel);

    temperatureField = new JTextField();
    temperatureField.setEditable(false);
    temperatureField.setText("24");
    temperatureField.setColumns(10);
    panel2.add(temperatureField);
    add(panel2);

    // panel 3
    JPanel panel3 = new JPanel();

    temperatureStatusLabel = new JLabel("Homerseklet Statusz");
    temperatureStatusLabel.setPreferredSize(new Dimension(130, 25));
    panel3.add(temperatureStatusLabel);

    temperatureStatusField = new JTextField();
    temperatureStatusField.setEditable(false);
    temperatureStatusField.setText("OK");
    temperatureStatusField.setColumns(10);
    panel3.add(temperatureStatusField);

    add(panel3);

    // panel 4
    JPanel panel4 = new JPanel();

    alertStatusLabel = new JLabel("Riasztas Statusz");
    alertStatusLabel.setPreferredSize(new Dimension(130, 25));
    panel4.add(alertStatusLabel);

    alertStatusField = new JTextField();
    alertStatusField.setEditable(false);
    alertStatusField.setText("NEM");
    alertStatusField.setColumns(10);
    panel4.add(alertStatusField);

    add(panel4);

    // panel 5
    JPanel panel5 = new JPanel();

    stockStatusLabel = new JLabel("Keszlet Statusz");
    stockStatusLabel.setPreferredSize(new Dimension(130, 25));
    panel5.add(stockStatusLabel);

    stockStatusField = new JTextField();
    stockStatusField.setEditable(false);
    stockStatusField.setText("OK");
    stockStatusField.setColumns(10);
    panel5.add(stockStatusField);

    add(panel5);
  }

  synchronized public void decreaseAlarmTimer() {

  }

  public boolean isAlarmCounterReachedZero() {
    return true;
  }

  public void resetAlarmCounter() {

  }

  public void activateAlarm() {

  }

  public void deactivateAlarm() {

  }

  public void setAlarm(String message) {

  }

  public void callFireFighers(String s) {

  }

  public void callPolice(String s) {

  }

  public void closeDoors() {

  }

  public void openDoors() {

  }

  public void sendBackupData() {

  }

  public void stopBackup() {

  }

  public void startServers() {

  }

  public void stopServers() {

  }
}

interface AgentInfo {
  public void decreaseAlarmTimer();

  public void resetAlarmCounter();

  public boolean isAlarmCounterReachedZero();

  public void activateAlarm();

  public void deactivateAlarm();

  public void setAlarm(String message);

  public void callFireFighers(String s);

  public void callPolice(String s);

  public void closeDoors();

  public void openDoors();

  public void sendBackupData();

  public void stopBackup();

  public void startServers();

  public void stopServers();
}
