package skill;

import global.enums.ActivationPhase;
import global.enums.StatType;

public abstract class Skill {
	
	String name;
	
	ActivationPhase activationPhase;

	int[] bonus;
	boolean triangleBonus;
	
	//for combat bonuses
	public Skill(int[] bonus, ActivationPhase activationPhase) {
		this.activationPhase = activationPhase;
		
		this.bonus = bonus;
		this.triangleBonus = false;
	}
	
	//for triangle adept
	public Skill(boolean triangleBonus) {
		this.activationPhase = ActivationPhase.Both;
		
		this.bonus = new int[] {0,0,0,0};
		this.triangleBonus = true;
	}

	
	public int getStatBonus(StatType stat, ActivationPhase currentPhase) {
		if(activationPhase.isActive(currentPhase)) {
			return bonus[stat.index];
		} else {
			return 0;
		}
	}
	
	public boolean triangleBonus() {
		return triangleBonus;
	}
	

}
