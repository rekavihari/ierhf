import javax.swing.JPanel;
import java.awt.GridLayout;

public class ControlUI extends JPanel {

	public ControlUI(GUI gui) {
		super(new GridLayout(4, 1), true);

		// setPreferredSize(new Dimension(200, 500));

		// first panel
		final EmployeePanel employee = new EmployeePanel();
		add(employee);

		// secound panel
		final StorageRoomPanel storage = new StorageRoomPanel();
		add(storage);

		// third panel
		final RestaurantPanel restaurant = new RestaurantPanel();
		add(restaurant);

		final EnvironmentPanel environment = new EnvironmentPanel();
		add(environment);
	}

}
