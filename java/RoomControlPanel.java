import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;

public class RoomControlPanel extends JPanel implements RoomControl {

	private static final String motionStr = "Movement";
	private static final String smokeStr = "Smoke";
	private static final String alarmStr = "Alarm control panel";
	private static final String temperatureStr = "Temperature";
	private static final String alarmedStr = "Activate alarm";

	private static final int spinnerDefaultValue = 24;

	private int roomNumber = 1;

	private static final FlowLayout subPanelLayout = new FlowLayout(FlowLayout.LEFT, 2, 1);

	private JPanel panel1;

	private JPanel panel2;

	private JPanel panel3;

	private JSpinner temperatureSpinner;
	private JTextField alarmTextInput;
	private JCheckBox smokeCB;
	private JCheckBox motionCB;
	private JCheckBox alarmedCB;

	public RoomControlPanel(int roomNum) {
		super(new GridLayout(3, 1), true);

		this.roomNumber = roomNum;

		setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), GUI.roomControlSubPanelTitle + (roomNumber + 1),
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		panel1 = new JPanel(subPanelLayout, true);
		add(panel1);

		JLabel lblTemperature = new JLabel(temperatureStr);
		panel1.add(lblTemperature);

		temperatureSpinner = new JSpinner();
		temperatureSpinner.setValue(spinnerDefaultValue);
		panel1.add(temperatureSpinner);

		panel2 = new JPanel(subPanelLayout, true);
		add(panel2);

		JLabel lblRiasztKezelpanel = new JLabel(alarmStr);
		panel2.add(lblRiasztKezelpanel);

		alarmTextInput = new JTextField();
		panel2.add(alarmTextInput);
		alarmTextInput.setColumns(10);

		panel3 = new JPanel(subPanelLayout, true);
		add(panel3);

		smokeCB = new JCheckBox(smokeStr);
		smokeCB.setHorizontalTextPosition(SwingConstants.LEFT);
		panel3.add(smokeCB);

		motionCB = new JCheckBox(motionStr);
		motionCB.setHorizontalTextPosition(SwingConstants.LEFT);
		panel3.add(motionCB);

		alarmedCB = new JCheckBox(alarmedStr);
		alarmedCB.setHorizontalTextPosition(SwingConstants.LEFT);
		panel3.add(alarmedCB);

	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void clearFields() {
		temperatureSpinner.setValue(spinnerDefaultValue);
		alarmTextInput.setText("");
		smokeCB.setSelected(false);
		motionCB.setSelected(false);
		alarmedCB.setSelected(false);
	}

	public int getTemperatureSensorValue() {
		return (int) temperatureSpinner.getValue();
	}

	public String getAlarmTextInputValue() {		
		if(alarmTextInput.getText() == null || alarmTextInput.getText().isEmpty()){
			return "\"\"";
		}
			
		return alarmTextInput.getText();
	}

	public boolean isThereSmoke() {
		return smokeCB.isSelected();
	}

	public boolean isThereMotion() {
		return motionCB.isSelected();
	}

	public boolean isAlarmed() {
		return alarmedCB.isSelected();
	}
	
	public void deactivateAlarm(){
		alarmedCB.setSelected(false);
	}
}

interface RoomControl {
	public int getRoomNumber();

	public int getTemperatureSensorValue();

	public String getAlarmTextInputValue();

	public boolean isThereSmoke();

	public boolean isThereMotion();

	public boolean isAlarmed();
	public void deactivateAlarm();
}
