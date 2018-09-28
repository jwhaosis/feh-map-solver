package global.enums.passiveskills;

import global.enums.unitinfo.StatType;
import global.interfaces.skillinfo.SkillCondition;
import map.GameMap;
import turn.combat.CombatActionQueue;
import unit.Unit;

public enum PassiveSkillType {
	
	Empty((unit, enemy, stat, phase, threshold, qualifier) -> false),

	Plus((unit, enemy, phase, stat, threshold, qualifier) -> true),
	Effective((unit, enemy, phase, stat, threshold, qualifier) -> enemy.moveType.isMoveType(qualifier)),
	Nullify((unit, enemy, phase, stat, threshold, qualifier) -> true),
	Triangle((unit, enemy, phase, stat, threshold, qualifier) -> true),

	Blow((unit, enemy, phase, stat, threshold, qualifier) -> phase.isActive(ActivationPhase.Initiate)),
	Stance((unit, enemy, phase, stat, threshold, qualifier) -> phase.isActive(ActivationPhase.Counter)),
	Defense((unit, enemy, phase, stat, threshold, qualifier) -> phase.isActive(ActivationPhase.Counter) && qualifier == enemy.weaponType().range),

	Brazen((unit, enemy, phase, stat, threshold, qualifier) -> 100.0 * unit.currentHealth() / unit.getStat(StatType.Health) <= threshold),
	Push((unit, enemy, phase, stat, threshold, qualifier) -> unit.currentHealth() == unit.getStat(StatType.Health)),
	
	Boost((unit, enemy, phase, stat, threshold, qualifier) -> unit.currentHealth() >= (enemy.currentHealth() + threshold)),
	Blade((unit, enemy, phase, stat, threshold, qualifier) -> unit.getStat(stat) >= (enemy.getStat(stat) + threshold)),
	Sweep((unit, enemy, phase, stat, threshold, qualifier) -> unit.getStat(stat) >= (enemy.getStat(stat) + threshold) && phase.isActive(ActivationPhase.Initiate) && qualifier == enemy.weaponType().damageType.identifier),
	
	Bond((unit, enemy, phase, stat, threshold, qualifier) -> GameMap.getInstance().nearbyUnitsAllyUnits(unit, 1).size() > 0),
	Solo((unit, enemy, phase, stat, threshold, qualifier) -> GameMap.getInstance().nearbyUnitsAllyUnits(unit, 1).size() == 0),
	
	Damaged((unit, enemy, phase, stat, threshold, qualifier) -> 100.0 * unit.currentHealth() / unit.getStat(StatType.Health) <= threshold),
	Brash((unit, enemy, phase, stat, threshold, qualifier) -> 100.0 * unit.currentHealth() / unit.getStat(StatType.Health) <= threshold && CombatActionQueue.checkAllowCounter(unit, enemy, phase)),
	Undamaged((unit, enemy, phase, stat, threshold, qualifier) -> unit.currentHealth() >= Math.floor((threshold / 100.0) * unit.getStat(StatType.Health))),
	Breaker((unit, enemy, phase, stat, threshold, qualifier) -> unit.currentHealth() >= Math.floor((threshold / 100.0) * unit.getStat(StatType.Health)) && qualifier == enemy.type.id);

	private SkillCondition<Unit, Unit, ActivationPhase, StatType, Integer, Integer, Boolean> condition;
	
	PassiveSkillType(SkillCondition<Unit, Unit, ActivationPhase, StatType, Integer, Integer, Boolean> condition){
		this.condition = condition;
	};
	

	public boolean isActive(Unit unit, Unit enemy, ActivationPhase currentPhase, StatType stat, int threshold, int qualifier) {
		return this.condition.apply(unit, enemy, currentPhase, stat, threshold, qualifier); 
	}
		
}
