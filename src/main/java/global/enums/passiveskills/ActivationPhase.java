package global.enums.passiveskills;

public enum ActivationPhase {
	Initiate,
	Counter,
	Both,
	Neither;
	
	public boolean isActive(ActivationPhase currentPhase) {
		if(this == ActivationPhase.Both || currentPhase == ActivationPhase.Both) {
			return true;
		} else if(this == ActivationPhase.Neither || currentPhase == ActivationPhase.Neither) {
			return false;
		} else {
			return this == currentPhase;
		}
	}
}
