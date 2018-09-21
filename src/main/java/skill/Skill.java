package skill;

import global.enums.ActivationPhase;
import global.enums.SkillType;
import global.enums.StatType;
import map.Map;
import unit.Unit;

public abstract class Skill {
	
	String name;
	
	int[] bonus;
	SkillType type;
	StatType compareStat;
	
	boolean condition = false;
	
	//for combat bonuses
	public Skill(int[] bonus, SkillType type) {
		this.type = type;
		
		this.bonus = bonus;
	}
	
	public int[] getStatBonus(ActivationPhase currentPhase) {
		return bonus;
	}
	
	public SkillType getType() {
		return type;
	}
	
	public boolean isActive(Unit unit, Unit enemy, Map map, ActivationPhase currentPhase){
		return type.isActive(unit, enemy, map, compareStat, currentPhase);
	}
	
	public boolean triangleBonus() {
		return type == SkillType.Triangle;
	}
	

}
