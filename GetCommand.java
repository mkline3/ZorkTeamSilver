
public class GetCommand extends Command{

	private String whatToGet;
	
	GetCommand(String variable){
		this.whatToGet = variable;
	}
	
	String execute() {
		
		if(whatToGet.equalsIgnoreCase("health")){
			return "Your current health is " + GameState.instance().getHealth();
		}
		else if(whatToGet.equalsIgnoreCase("score")){
			return "Your current score is " + GameState.instance().getScore();
		}
		else
			return "I dont know what " + whatToGet + " is.";
		
	}

}
