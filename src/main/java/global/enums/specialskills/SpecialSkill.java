package global.enums.specialskills;

import global.enums.unitinfo.StatType;
import global.interfaces.skillinfo.SkillSpecialDamage;
import unit.Unit;

public enum SpecialSkill {
	
	Default((unit, enemy) -> 0, true, 0),

	DraconicAura((unit, enemy) -> (int) Math.floor(unit.getStat(StatType.Attack) * 0.3), true, 3),
	DragonFang((unit, enemy) -> (int) Math.floor(unit.getStat(StatType.Attack) * 0.5), true, 4),
	Bonfire((unit, enemy) -> (int) Math.floor(unit.getStat(StatType.Defense) * 0.5), true, 3),
	Ignis((unit, enemy) -> (int) Math.floor(unit.getStat(StatType.Defense) * 0.8), true, 4),
	Iceberg((unit, enemy) -> (int) Math.floor(unit.getStat(StatType.Resistance) * 0.5), true, 3),
	Glacies((unit, enemy) -> (int) Math.floor(unit.getStat(StatType.Resistance) * 0.8), true, 4),
	Moonbow((unit, enemy) -> (int) Math.floor(enemy.getStat(enemy.targetDefensiveStat(unit.weapon().damageType())) * 0.3), true, 2),
	Luna((unit, enemy) -> (int) Math.floor(enemy.getStat(enemy.targetDefensiveStat(unit.weapon().damageType())) * 0.5), true, 3);
	
	SkillSpecialDamage<Unit, Unit, Integer> damageCalc;
	boolean activateOnAttack;
	int charge;
	
	SpecialSkill(SkillSpecialDamage<Unit, Unit, Integer> damageCalc, boolean activateOnAttack, int charge){
		this.damageCalc = damageCalc;
		this.charge = charge;
	}
	
	public int activateSkill(Unit unit, Unit enemy, boolean thisAttacking, int currentCharge) {
		if(thisAttacking == activateOnAttack && currentCharge >= charge) {
			return Math.max(0, damageCalc.apply(unit, enemy));
		} else {
			return -1;
		}
		
	}
}
