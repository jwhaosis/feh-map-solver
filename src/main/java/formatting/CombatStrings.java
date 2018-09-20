package formatting;

import unit.Unit;

public final class CombatStrings {
	
	public static final String DAMAGE(Unit whacker, Unit sandbag, int damage) {
		return whacker.name + " attacks " + sandbag.name + " for " + damage + " damage.";
	}
	
	public static final String OVERKILL(int overkill) {
		return " (" + overkill + " overkill)";
	}
	
	public static final String VICTORY(Unit winner) {
		return winner.name + " wins the combat. " + END();
	}
	
	public static final String BEGIN() {
		return "Combat has began.";
	}
	public static final String END() {
		return "Combat has ended.";
	}
}
