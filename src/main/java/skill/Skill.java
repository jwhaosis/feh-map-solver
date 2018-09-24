package skill;

import global.enums.ActivationPhase;
import global.enums.SkillType;
import global.enums.StatType;
import unit.Unit;

public abstract class Skill {
	
	String name;
	
	int[] bonus;
	SkillType type;
	StatType compareStat;
	int threshold;
	
	boolean condition = false;
	
	//for combat bonuses
	public Skill(int[] bonus, SkillType type) {
		this.type = type;
		this.bonus = bonus;
		this.threshold = 0;
	}
	
	//for combat bonuses
	public Skill(int[] bonus, SkillType type, int threshold) {
		this.type = type;
		this.bonus = bonus;
		this.threshold = threshold;
	}

	
	public int[] getStatBonus(ActivationPhase currentPhase) {
		return bonus;
	}
	
	public SkillType getType() {
		return type;
	}
	
	public boolean isActive(Unit unit, Unit enemy, ActivationPhase currentPhase){
		return type.isActive(unit, enemy, compareStat, currentPhase, threshold);
	}
	
	public boolean triangleBonus() {
		return type == SkillType.Triangle;
	}
	

}
