package global.enums.skillinfo;

import unit.Unit;

public enum ActivationPhase {
	Initiate,
	Counter,
	Both;
	
	public boolean isActive(ActivationPhase currentPhase) {
		if(this == ActivationPhase.Both || currentPhase == ActivationPhase.Both) {
			return true;
		} else {
			return this == currentPhase;
		}
	}
	
	public static ActivationPhase getCurrentPhase(Unit initiator, Unit whacker) {
		if(initiator == whacker) {
			return ActivationPhase.Initiate;
		} else {
			return ActivationPhase.Counter;
		}
	}
}
