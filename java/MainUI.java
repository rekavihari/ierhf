import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;

public class MainUI extends JFrame {

	private ControlUI ControlUI;
	private ConsolePanel consolePanel;
	private InfoPanel infoPanel;

	public MainUI(GUI gui) {
		super();
		setTitle("IER Hazi Feladat");
		setResizable(true);
		setBounds(10, 10, 1400, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2, 1));

		ControlUI = new ControlUI(gui);
		consolePanel = new ConsolePanel();
		infoPanel = new InfoPanel();

		add(ControlUI);
		panel1.add(consolePanel);
		panel1.add(infoPanel);
		add(panel1);

		setVisible(true);
	}

	public void log(String str) {
		consolePanel.log(str);
	}

	public List<RoomControl> getRoomControls() {
		return ControlUI.getRoomControls();
	}

	public List<AgentInfo> getRoomOutputs() {
		return infoPanel.getAgentInfoPanels();
	}
}
