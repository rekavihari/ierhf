// Environment code for project ierHF.mas2j

import jason.asSyntax.*;
import jason.environment.*;

import jason.asSyntax.Literal;
import jason.asSyntax.parser.ParseException;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Literal;
import jason.asSyntax.StringTerm;
import jason.asSyntax.Term;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

public class Env extends Environment {
	
    private GUI gui;
	
	private List<AlarmTimerThread> timers;
	
	public void log(String message){
		gui.log(message);
	}
	
	public void updateSensorStates(List<RoomControl> roomControls){
		RoomControl room0 = roomControls.get(0);
		RoomControl room1 = roomControls.get(1);
		RoomControl room2 = roomControls.get(2);
		RoomControl room3 = roomControls.get(3);
		
		try{
			for(int i = 0;i < GUI.numberOfAgents;i++){
				String safetyAgentName = "safety_" + i;
				String securityAgentName = "security_" + i;
				
				clearPercepts(safetyAgentName);
				addPercept(safetyAgentName, 
					ASSyntax.createLiteral("refresh",
						ASSyntax.parseTerm(String.valueOf(roomControls.get(i).getTemperatureSensorValue())),
						ASSyntax.parseTerm(String.valueOf(roomControls.get(i).isThereSmoke()))
						));
				
				clearPercepts(securityAgentName);
				addPercept(securityAgentName, 
					ASSyntax.createLiteral("refresh",
						ASSyntax.parseTerm(String.valueOf(roomControls.get(i).isAlarmed())),
						ASSyntax.parseTerm(String.valueOf(roomControls.get(i).isThereMotion())),
						ASSyntax.parseTerm(roomControls.get(i).getAlarmTextInputValue())		
						));	
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e2){
			e2.printStackTrace();
		}
	}

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
		
		 gui = new GUI(this);
		 
		 gui.refreshSensorStates();//init sensor values
		 
		 timers = new ArrayList<>();
		 
		 for(int i = 0; i < 4; i++){
			 timers.add(new AlarmTimerThread(gui.getAgentOutputs().get(i)));
		 }
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
		List<RoomControl> inputs = gui.getRoomControls();
		
		List<AgentInfo> outputs = gui.getAgentOutputs();
		
		AgentInfo output_0 = outputs.get(0);
		
		int roomNum = -1;
		String msg = null;
		
		switch (action.getFunctor()) {
			case  "print": 
				gui.log(agName, action.getTerm(0).toString());
				break;
			case  "callPolice": 
				roomNum = Integer.parseInt(action.getTerm(0).toString());
				msg = action.getTerm(1).toString();
				
				outputs.get(roomNum).callPolice(msg);				
				break;
			case  "callFireFighters": 
				roomNum = Integer.parseInt(action.getTerm(0).toString());
				msg = action.getTerm(1).toString();
				
				outputs.get(roomNum).callFireFighers(msg);	
				break;
			case  "emExit": 
				roomNum = Integer.parseInt(action.getTerm(0).toString());
				
					if("close".equals(action.getTerm(1).toString()))
						outputs.get(roomNum).closeDoors();
					if("open".equals(action.getTerm(1).toString()))
						outputs.get(roomNum).openDoors();
				break;
			case  "emSpk": 
				roomNum = Integer.parseInt(action.getTerm(0).toString());

				outputs.get(roomNum).setAlarm(action.getTerm(1).toString());	
				break;				
			case  "emDataSave": 
				roomNum = Integer.parseInt(action.getTerm(0).toString());
				
				if("start".equals(action.getTerm(1).toString()))
					outputs.get(roomNum).sendBackupData();
				
				if ("stop".equals(action.getTerm(1).toString()))
					outputs.get(roomNum).stopBackup();
				
				break;		
			case  "stopServers": 
				roomNum = Integer.parseInt(action.getTerm(0).toString());
				
				outputs.get(roomNum).stopServers();		
				break;	
			case  "startServers": 
				roomNum = Integer.parseInt(action.getTerm(0).toString());
				
				outputs.get(roomNum).startServers();
				break;			

//TODO innen majd nezd at, security agenshez tartozoak jonnek:
			case "resetAlarmCounter":
				roomNum = Integer.parseInt(action.getTerm(0).toString());
				
				outputs.get(roomNum).resetAlarmCounter();			
				break;
			case "startAlarmCounter":
				roomNum = Integer.parseInt(action.getTerm(0).toString());
				
				AlarmTimerThread timer = timers.get(roomNum);
				
				(new Thread(timer)).start();				
				break;				
			case "stopAlarmCounter":
				roomNum = Integer.parseInt(action.getTerm(0).toString());
				
				timers.get(roomNum).stopCounter();						
				break;
			case "activateAlarm":
				roomNum = Integer.parseInt(action.getTerm(0).toString());
				
				outputs.get(roomNum).activateAlarm();
				break;
			case "deactivateAlarm":
				roomNum = Integer.parseInt(action.getTerm(0).toString());
				
				inputs.get(roomNum).deactivateAlarm();
				gui.refreshSensorStates();
				
				outputs.get(roomNum).deactivateAlarm();
				break;
				
			default: gui.log("executing: "+action+", but not implemented!");
					 break;
		}
	
        if (true) { // you may improve this condition
             informAgsEnvironmentChanged();
        }
        return true; // the action was executed with success 
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}



