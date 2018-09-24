package global.enums;

import global.interfaces.SkillCondition;
import map.Map;
import unit.Unit;

public enum SkillType {
	
	//provides stats
	Empty((unit, enemy, stat, phase, threshold) -> true),

	Plus((unit, enemy, stat, phase, threshold) -> true),
	Nullify((unit, enemy, stat, phase, threshold) -> true),
	Triangle((unit, enemy, stat, phase, threshold) -> true),

	Blow((unit, enemy, stat, phase, threshold) -> phase.isActive(ActivationPhase.Initiate)),
	Stance((unit, enemy, stat, phase, threshold) -> phase.isActive(ActivationPhase.Counter)),
	Brazen((unit, enemy, stat, phase, threshold) -> 100.0 * unit.currentHealth() / unit.getStat(StatType.Health) <= 0.8),
	Push((unit, enemy, stat, phase, threshold) -> unit.currentHealth() == unit.getStat(StatType.Health)),

	Boost((unit, enemy, stat, phase, threshold) -> unit.getStat(StatType.Health) >= (enemy.getStat(stat) + threshold)),
	Blade((unit, enemy, stat, phase, threshold) -> unit.getStat(StatType.Health) >= (enemy.getStat(stat) + threshold)),
	Bond((unit, enemy, stat, phase, threshold) -> Map.getInstance().nearbyUnitsAllyUnits(unit, 1).size() > 0),
	Solo((unit, enemy, stat, phase, threshold) -> Map.getInstance().nearbyUnitsAllyUnits(unit, 1).size() == 0),
	
	Damaged((unit, enemy, stat, phase, threshold) -> 100.0 * unit.currentHealth() / unit.getStat(StatType.Health) <= threshold),
	Undamaged((unit, enemy, stat, phase, threshold) -> 100.0 * unit.currentHealth() / unit.getStat(StatType.Health) >= threshold),
		
	//checks for the attacking unit
	SpecialOnInitiateAttack((unit, enemy, stat, phase, threshold) -> (phase.isActive(ActivationPhase.Initiate) && (100.0 * unit.currentHealth() / unit.getStat(StatType.Health) >= threshold))),
	SpecialOnCounterDefend((unit, enemy, stat, phase, threshold) -> (phase.isActive(ActivationPhase.Counter) && (100.0 * unit.currentHealth() / unit.getStat(StatType.Health) >= threshold))),
	//checks for the defending unit
	SpecialOnInitiateDefend((unit, enemy, stat, phase, threshold) -> (phase.isActive(ActivationPhase.Initiate) && (100.0 * enemy.currentHealth() / enemy.getStat(StatType.Health) >= threshold))),
	SpecialOnCounterAttack((unit, enemy, stat, phase, threshold) -> (phase.isActive(ActivationPhase.Counter) && (100.0 * enemy.currentHealth() / enemy.getStat(StatType.Health) >= threshold)));
	
	private SkillCondition<Unit, Unit, StatType, ActivationPhase, Integer, Boolean> condition;
	
	SkillType(SkillCondition<Unit, Unit, StatType, ActivationPhase, Integer, Boolean> condition){
		this.condition = condition;
	};
	

	public boolean isActive(Unit unit, Unit enemy, StatType stat, ActivationPhase currentPhase, int threshold) {
		return this.condition.apply(unit, enemy, stat, currentPhase, threshold); 
	}
		
}
