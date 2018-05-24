import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class GUI implements ActionListener {
	public static final String roomControlSubPanelTitle = "Server room ";
	public static final String infoPanelTitle = "System information";
	public static final String consolePanelTitle = "System Console";
	public static final int numberOfAgents = 4;

	MainUI mainUI;

	Env environment;

	public GUI(Env env) {
		mainUI = new MainUI(this);

		environment = env;
	}

	public GUI() {
		mainUI = new MainUI(this);
	}

	public void refreshSensorStates() {
		actionPerformed(null);
	}

	// Szenzor adatok küldése gomb eseménykezelő
	@Override
	public void actionPerformed(ActionEvent e) {
		environment.updateSensorStates(mainUI.getRoomControls());
	}

	public void log(String str) {
		String timeStamp = new SimpleDateFormat("HH:mm:ss.S").format(Calendar.getInstance().getTime());

		String logStr = " [ " + timeStamp.toString() + " ] " + str;
		mainUI.log(logStr);
	}

	public void log(String agentName, String str) {
		log(" [ " + agentName + " ] " + str);
	}

	public List<RoomControl> getRoomControls() {
		return mainUI.getRoomControls();
	}

	public List<AgentInfo> getAgentOutputs() {
		return mainUI.getRoomOutputs();
	}

	// for test purposes
	public static void main(String[] args) {
		GUI gui = new GUI();
	}

}
