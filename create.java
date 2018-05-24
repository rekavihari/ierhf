import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.*;

import jason.asSyntax.*;
import jason.asSyntax.Structure;
import jason.environment.Environment;


public class create extends Environment {

    private Gui gui;

    public void temp(String room, String temp, int start, int end) {
    	clearPercepts();
   		addPercept(Literal.parseLiteral("run(" + room +","+ temp+ ","+ Integer.toString(start)+","+Integer.toString(end)+")"));
    }

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
        gui = new Gui();
        gui.setEnvironment(this);
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        try{
        if (action.getFunctor().equals("time")) {
                int time = (int)((NumberTerm)action.getTerm(0)).solve();
                gui.settime(time);
            }
        if (action.getFunctor().equals("room_temp")) {
                int room = (int)((NumberTerm)action.getTerm(0)).solve();
                int temp = (int)((NumberTerm)action.getTerm(1)).solve();
                gui.setTemp(room, temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}

class Gui {
    private create environment;
    final JButton run = new JButton("run");
    final JTextField temp = new JTextField(10);
    final JLabel time = new JLabel("00 : 00");
    final JLabel room25 = new JLabel();
    final JLabel room26 = new JLabel();
    final JLabel room27 = new JLabel();
    final JLabel room28 = new JLabel();

    JComboBox startT_H;
	JComboBox startT_M;
	JComboBox endT_H;
	JComboBox endT_M;

    public void setEnvironment(create environment) {
        this.environment = environment;
    }

    public void settime(int t){
    	int h=t/60;
    	int m=t-h*60;
    	String text=Integer.toString(h)+" : "+Integer.toString(m);
    	time.setText(text);
    }

    public void setTemp(int room, int temp){
        JLabel roomLabel;
        String text="IB0"+Integer.toString(room)+":   "+Integer.toString(temp)+"\u2103";
         switch (room) {
            case 25: roomLabel=room25;
                     break;
            case 26: roomLabel=room26;
                     break;
            case 27: roomLabel=room27;
                     break;
            case 28: roomLabel=room28;
                     break;
            default: roomLabel = room25;
                     break;
        }
        roomLabel.setText(text);
    }

    public Gui(){
    	String[] hours = new String[24];
   		String[] minutes = new String[12];

		//Create the combo box
		String[] rooms = {"IB025", "IB026","IB027", "IB028" };
		for (int i = 0; i<24; i++) {
	    	hours[i] = Integer.toString(i);
	    }
	    for (int i = 0; i<12; i++) {
	    	minutes[i] = Integer.toString(i*5);
	    }

		startT_H = new JComboBox(hours);
        startT_M = new JComboBox(minutes);
        endT_H = new JComboBox(hours);
        endT_M = new JComboBox(minutes);

        JComboBox roomList = new JComboBox(rooms);
        roomList.setSelectedIndex(0);
            
        JPanel up = new JPanel();
        up.add(new JLabel("Start:   "));
        up.add(startT_H);
        up.add(new JLabel("h "));
        up.add(startT_M);
        up.add(new JLabel("m        End:   "));
        up.add(endT_H);
        up.add(new JLabel("h "));
        up.add(endT_M);
        up.add(new JLabel("m "));
        up.add(new JLabel("Temperature:   "));
        up.add(temp);
        up.add(roomList);
        up.add(run);
        up.add(time);

        JPanel down = new JPanel();
        down.setLayout(new FlowLayout(FlowLayout.CENTER,60,30));
        down.add(room25);
        down.add(room26);
        down.add(room27);
        down.add(room28);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(up);
        panel.add(down);

        JFrame frame = new JFrame("Kozponti konzol");
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocation(10,10);
        frame.setVisible(true);
         

        run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String room =  "\"" + roomList.getSelectedItem().toString() + "\"";
				int start= Integer.parseInt(startT_M.getSelectedItem().toString());
				start+=Integer.parseInt(startT_H.getSelectedItem().toString())*60;

				int end = Integer.parseInt(endT_M.getSelectedItem().toString());
				end += Integer.parseInt(endT_H.getSelectedItem().toString())*60;
				
				String t=temp.getText();
				if(t.length()<1){t="0";}
				
				environment.temp(room, t, start, end);
			}	
        });
        
        
    }

}