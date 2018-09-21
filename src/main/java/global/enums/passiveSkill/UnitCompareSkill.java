package global.enums.passiveSkill;

import java.util.function.Function;

import global.enums.StatType;
import global.interfaces.*;
import map.Map;
import unit.Unit;

public enum UnitCompareSkill {
	
	Boost((unit, enemy, stat, threshold, map) -> (unit.getStat(StatType.Health) >= enemy.getStat(stat) + threshold)),
	Blade((unit, enemy, stat, threshold, map) -> (unit.getStat(StatType.Health) >= enemy.getStat(stat) + threshold)),
	Bond((unit, enemy, stat, threshold, map) -> (map.nearbyUnitsAllyUnits(unit, 1).size() > 0)),
	Solo((unit, enemy, stat, threshold, map) -> (map.nearbyUnitsAllyUnits(unit, 1).size() == 0)),
	Vantage((unit, enemy, stat, threshold, map) -> (unit.getStat(stat) >= enemy.getStat(stat) + threshold)),
	Desperation((unit, enemy, stat, threshold, map) -> (unit.getStat(stat) >= enemy.getStat(stat) + threshold)),
	Brash((unit, enemy, stat, threshold, map) -> (unit.getStat(stat) >= enemy.getStat(stat) + threshold)),
	Riposte((unit, enemy, stat, threshold, map) -> (unit.getStat(stat) >= enemy.getStat(stat) + threshold)),
	SpecialOnInitiateAttack((unit, enemy, stat, threshold, map) -> (unit.getStat(stat) >= enemy.getStat(stat) + threshold)),
	SpecialOnInitiateDefend((unit, enemy, stat, threshold, map) -> (unit.getStat(stat) >= enemy.getStat(stat) + threshold)),
	SpecialOnCounterAttack((unit, enemy, stat, threshold, map) -> (unit.getStat(stat) >= enemy.getStat(stat) + threshold)),
	SpecialOnCounterDefend((unit, enemy, stat, threshold, map) -> (unit.getStat(stat) >= enemy.getStat(stat) + threshold));


	UnitCompareSkill(SkillCondition<Unit, Unit, StatType, Integer, Map, Boolean> condition){};

}
