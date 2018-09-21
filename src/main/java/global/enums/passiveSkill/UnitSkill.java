package global.enums.passiveSkill;

import java.util.function.Function;

import global.enums.StatType;
import unit.Unit;

public enum UnitSkill {
	
	Brazen(unit -> unit.currentHealth() <= (int) Math.floor(unit.getStat(StatType.Health) * 0.8)),
	Push(unit -> unit.currentHealth() == unit.getStat(StatType.Health));

	
	UnitSkill(Function<Unit, Boolean> condition){};

}
