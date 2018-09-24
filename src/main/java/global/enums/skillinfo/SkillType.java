package global.enums.skillinfo;

import global.enums.unitinfo.StatType;
import global.interfaces.skillinfo.SkillCondition;
import map.GameMap;
import unit.Unit;

public enum SkillType {
	
	Empty((unit, enemy, stat, phase, threshold) -> false),

	Plus((unit, enemy, phase, stat, threshold) -> true),
	Nullify((unit, enemy, phase, stat, threshold) -> true),
	Triangle((unit, enemy, phase, stat, threshold) -> true),

	Blow((unit, enemy, phase, stat, threshold) -> phase.isActive(ActivationPhase.Initiate)),
	Stance((unit, enemy, phase, stat, threshold) -> phase.isActive(ActivationPhase.Counter)),
	Defense((unit, enemy, phase, stat, threshold) -> phase.isActive(ActivationPhase.Counter) && threshold == enemy.weapon().range()),

	Brazen((unit, enemy, phase, stat, threshold) -> 100.0 * unit.currentHealth() / unit.getStat(StatType.Health) <= threshold),
	Push((unit, enemy, phase, stat, threshold) -> unit.currentHealth() == unit.getStat(StatType.Health)),

	Bond((unit, enemy, phase, stat, threshold) -> GameMap.getInstance().nearbyUnitsAllyUnits(unit, 1).size() > 0),
	Solo((unit, enemy, phase, stat, threshold) -> GameMap.getInstance().nearbyUnitsAllyUnits(unit, 1).size() == 0),
	
	Damaged((unit, enemy, phase, stat, threshold) -> 100.0 * unit.currentHealth() / unit.getStat(StatType.Health) <= threshold),
	Undamaged((unit, enemy, phase, stat, threshold) -> 100.0 * unit.currentHealth() / unit.getStat(StatType.Health) >= threshold),

	//checks for stat thresholds (used for both boost and blade skills).
	Boost((unit, enemy, phase, stat, threshold) -> unit.currentHealth() >= (enemy.currentHealth() + threshold)),
	Blade((unit, enemy, phase, stat, threshold) -> unit.getStat(stat) >= (enemy.getStat(stat) + threshold));

	private SkillCondition<Unit, Unit, ActivationPhase, StatType, Integer, Boolean> condition;
	
	SkillType(SkillCondition<Unit, Unit, ActivationPhase, StatType, Integer, Boolean> condition){
		this.condition = condition;
	};
	

	public boolean isActive(Unit unit, Unit enemy, ActivationPhase currentPhase, StatType stat, int threshold) {
		return this.condition.apply(unit, enemy, currentPhase, stat, threshold); 
	}
		
}
