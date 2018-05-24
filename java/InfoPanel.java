import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class InfoPanel extends JPanel {

	private List<AgentInfo> agentInfoPanels;

	public InfoPanel() {
		super(new GridLayout(2, 1), true);

		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacio", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));

		setPreferredSize(new Dimension(480, 450));

		agentInfoPanels = new ArrayList<>(GUI.numberOfAgents);

		// for (int i = 0; i < GUI.numberOfAgents - 1; i++) {
		// final AgentInfoPanel agent = new AgentInfoPanel(i);
		// add(agent);
		// agentInfoPanels.add(agent);
		// }

		final StorageRoomInfoPanel storage = new StorageRoomInfoPanel();
		add(storage);

		final RestaurantInfoPanel restaurant = new RestaurantInfoPanel();
		add(restaurant);

	}

	public List<AgentInfo> getAgentInfoPanels() {
		return agentInfoPanels;
	}
}
