public class AlarmTimerThread implements Runnable{

	private boolean stopped = false;
	
	private AgentInfo infopanel;
	
	public AlarmTimerThread(AgentInfo panel){
		this.infopanel = panel;
	}
	
	@Override
	public void run(){
		stopped = false;
		
		try{Thread.sleep(1000);}catch(Exception e){}
		
		while(!stopped && !infopanel.isAlarmCounterReachedZero()){			
			infopanel.decreaseAlarmTimer();		
			
			try{Thread.sleep(1000);}catch(Exception e){}
		}	
	}
	
	public void stopCounter(){
		stopped = true;
	}
}