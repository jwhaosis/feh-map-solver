package global.enums.passiveskills;

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
}
