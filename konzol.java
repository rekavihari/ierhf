package gui; 

import jason.asSyntax.*;
import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.text.*;
import java.util.*;

/** internal action that creates a simple GUI with two buttons that trigger AS plans */
public class konzol extends DefaultInternalAction{

    @Override
    public Object execute(final TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        
        // get the window title
        String title = ((StringTerm)args[0]).getString();
        
        String[] hours = new String[24];
        String[] minutes = new String[12];
        for (int i = 0; i<24; i++) {
            hours[i] = Integer.toString(i);
        }
        for (int i = 0; i<12; i++) {
            minutes[i] = Integer.toString(i*5);
        }

        // create the windows
        final JButton run = new JButton("SET");
        final JTextField textField = new JTextField(10);
        JComboBox endT_H = new JComboBox(hours);
        JComboBox endT_M = new JComboBox(minutes);
            
        JPanel panel = new JPanel();
        panel.add(new JLabel("Temperature:   "));        
        panel.add(textField);
        panel.add(new JLabel("     End:   "));
        panel.add(endT_H);
        panel.add(new JLabel("h "));
        panel.add(endT_M);
        panel.add(new JLabel("m    "));
        panel.add(run);

        JFrame frame = new JFrame(title);
        int pos = Integer.parseInt(title.substring(title.length() - 1));
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocation(10, pos * 70 - 200);
        frame.setVisible(true);
        
        // add the event listeners
        run.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int end = Integer.parseInt(endT_M.getSelectedItem().toString());
                end += Integer.parseInt(endT_H.getSelectedItem().toString())*60;

                ts.getC().addAchvGoal(Literal.parseLiteral("setOptimalTemperature_now(" + textField.getText() + "," + end + ")"), null);
            }
        });
        return true;
    }


  
}
