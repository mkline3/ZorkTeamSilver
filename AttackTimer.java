
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * @author Xbox5
 *
 *AttackTimer class is used to set attack timers for 
 *enemies. This is the amount of time that passes 
 *before an enemy attacks you for being idle. 
 */
public class AttackTimer {

	private int seconds = 0;
    private int maxSeconds = 0;
   
    
    /**
     * 
     * @param ttl
     * This creates an attack timer that will cause
     * the enemy attack after the time runs out. 
     */
    public AttackTimer(int timeToWait){
    	this.maxSeconds = timeToWait;
    	
    }
    //These objects control the time.
    //The TimerTask contains the method run to keep
    //the timer going.
    private Timer timer = new Timer();
    private TimerTask task = new TimerTask() {
            public void run(){
            	
            	seconds++;
            	if(seconds == maxSeconds){
                   seconds = 0;
                   
            	}
                
            }
    };
    
	

	
	
	
	
}
