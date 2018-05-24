import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AgentInfoPanel extends JPanel implements AgentInfo {
	private static final String defaultEmergencyMessage = "";

	private static final String defaultAlarmMessage = " ";
	private static final String alarmTurnedOn = "Activated";
	private static final String alarmTurnedOff = "Deactivated";
	private static final int defaultAlarmCounterValue = 10;

	private static final String defaultBackupMessage = "Backup done.";
	private static final String sendingDataMessage = "Saving data in progress...";
	private static final String serversTurnedOn = "Running";
	private static final String serversTurnedOff = "Turned off.";

	private static final String doorsOpenedMessage = "Opened";
	private static final String doorsClosedMessage = "Closed";

	private int alarmCounter = defaultAlarmCounterValue;

	private JTextField fireFightersTF;
	private JTextField PoliceTF;
	private JTextField alarmStateTF;
	private JTextField alarmCounterTF;
	private JTextField alarmMessageTF;
	private JTextField serversState;
	private JTextField DoorsTF;

	public AgentInfoPanel(int roomNumber) {
		super(new FlowLayout(FlowLayout.RIGHT, 0, 0));

		final String panelTitle = GUI.roomControlSubPanelTitle + " " + (roomNumber + 1);
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), panelTitle, TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));

		fireFightersTF = new JTextField();
		createSubPanelWithLabel("Firefighters state", fireFightersTF, defaultEmergencyMessage, 10);

		PoliceTF = new JTextField();
		createSubPanelWithLabel("Police state", PoliceTF, defaultEmergencyMessage, 10);

		serversState = new JTextField();
		createSubPanelWithLabel("Server state", serversState, serversTurnedOn, 10);

		DoorsTF = new JTextField();
		createSubPanelWithLabel("Door state", DoorsTF, doorsOpenedMessage, 10);

		alarmStateTF = new JTextField();
		createSubPanelWithLabel("Alarm state", alarmStateTF, alarmTurnedOff, 10);

		alarmCounterTF = new JTextField();
		createSubPanelWithLabel("Alarm countdown", alarmCounterTF, String.valueOf(alarmCounter), 10);

		alarmMessageTF = new JTextField();
		createSubPanelWithLabel("Agent message", alarmMessageTF, defaultAlarmMessage, 33);

	}

	private void createSubPanelWithLabel(String labelStr, JTextField tf, String defaultTFText, int tfWidth) {
		JPanel subPanel = new JPanel();
		JLabel label = new JLabel(labelStr);
		label.setPreferredSize(new Dimension(125, 20));
		tf.setEditable(false);
		tf.setText(defaultTFText);
		tf.setColumns(tfWidth);

		subPanel.add(label);
		subPanel.add(tf);
		add(subPanel);
	}

	synchronized public void decreaseAlarmTimer() {
		if (alarmCounter > 0)
			alarmCounter--;
		alarmCounterTF.setText(String.valueOf(alarmCounter));
	}

	public boolean isAlarmCounterReachedZero() {
		return alarmCounter == 0;
	}

	public void resetAlarmCounter() {
		alarmCounter = defaultAlarmCounterValue;
		alarmCounterTF.setText(String.valueOf(alarmCounter));
	}

	public void activateAlarm() {
		alarmStateTF.setText(alarmTurnedOn);
	}

	public void deactivateAlarm() {
		alarmStateTF.setText(alarmTurnedOff);
	}

	public void setAlarm(String message) {
		if (message == null)
			alarmMessageTF.setText(defaultAlarmMessage);
		else
			alarmMessageTF.setText("" + message);
	}

	public void callFireFighers(String s) {
		fireFightersTF.setText(s);
	}

	public void callPolice(String s) {
		PoliceTF.setText(s);
	}

	public void closeDoors() {
		DoorsTF.setText(doorsClosedMessage);
	}

	public void openDoors() {
		DoorsTF.setText(doorsOpenedMessage);
	}

	public void sendBackupData() {
		serversState.setText(sendingDataMessage);
	}

	public void stopBackup() {
		serversState.setText(defaultBackupMessage);
	}

	public void startServers() {
		serversState.setText(serversTurnedOn);
	}

	public void stopServers() {
		serversState.setText(serversTurnedOff);
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
