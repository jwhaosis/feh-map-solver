package global.enums;

import map.Map;
import unit.Unit;

public enum SkillType {
	Empty,
	
	Plus,
	Blow,
	Stance,
	Breath,
	//HP threshold is calculated by taking the percent * total HP and then rounding down. EX. A unit with wary fighter that has 53HP will not lose it until 25HP.
	Brazen,
	Push,
	Nullify,
	Triangle,

	Boost,
	Blade,
	
	Bond,
	Solo;
	

	SkillType(){};
	
	public boolean isActive(Unit unit, Unit enemy, Map map, StatType stat, ActivationPhase currentPhase) {
		if(this == SkillType.Plus) {
			return true;
		} else if (this == SkillType.Blow && currentPhase == ActivationPhase.Initiate) {
			return true;
		} else if (this == SkillType.Blow && currentPhase == ActivationPhase.Initiate) {
			return true;
		} else if ((this == SkillType.Stance || this == SkillType.Breath) && currentPhase == ActivationPhase.Counter) {
			return true;
		} else if (this == SkillType.Brazen && (unit.currentHealth() <= (int) Math.floor(unit.getStat(StatType.Health) * 0.8))) {
			return true;
		} else if (this == SkillType.Push && unit.currentHealth() == unit.getStat(StatType.Health)) {
			return true;
		} else if (this == SkillType.Nullify || this == SkillType.Triangle) {
			return true;
		} else if(this == SkillType.Boost && unit.currentHealth() >= enemy.currentHealth() + 3) {
			return true;
		} else if(this == SkillType.Blade && unit.getStat(stat) >= enemy.getStat(stat)) {
			return true;
		} else if(this == SkillType.Bond && map.nearbyUnitsAllyUnits(unit, 1).size() > 0) {
			return true;
		} else if(this == SkillType.Solo && map.nearbyUnitsAllyUnits(unit, 1).size() == 0) {
			return true;
		} else {
			return false;
		}

		
	}
		
}
