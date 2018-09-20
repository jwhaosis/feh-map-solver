package turn;

import global.enums.TurnActionType;
import unit.Unit;

public class TurnAction {
	
	
	public TurnActionType actionType;
	public Unit performer;
	public Unit target;
	
	public TurnAction() {
		
	}
	
	String execute() {
		if(actionType == TurnActionType.Attack) {
			
		} else if (actionType == TurnActionType.Rally) {
			
		} else if (actionType == TurnActionType.Assist) {
			
		} else if (actionType == TurnActionType.Move) {
			
		}
		
		return "Done";
	}
}

