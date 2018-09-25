package global.enums.specialskills;

import global.enums.unitinfo.StatType;
import global.interfaces.skillinfo.SkillSpecialDamage;
import unit.Unit;

public enum SpecialSkill {
	
	Default((unit, enemy) -> 0, true, 0),

	DraconicAura((unit, enemy) -> (int)(unit.getStat(StatType.Attack) * 0.3), true, 3),
	DragonFang((unit, enemy) -> (int)(unit.getStat(StatType.Attack) * 0.5), true, 4),
	Bonfire((unit, enemy) -> (int)(unit.getStat(StatType.Defense) * 0.5), true, 3),
	Ignis((unit, enemy) -> (int)(unit.getStat(StatType.Defense) * 0.8), true, 4),
	Iceberg((unit, enemy) -> (int)(unit.getStat(StatType.Resistance) * 0.5), true, 3),
	Glacies((unit, enemy) -> (int)(unit.getStat(StatType.Resistance) * 0.8), true, 4),
	Moonbow((unit, enemy) -> (int)(enemy.getStat(enemy.targetDefensiveStat(unit.weapon().damageType())) * 0.3), true, 2),
	Luna((unit, enemy) -> (int)(enemy.getStat(enemy.targetDefensiveStat(unit.weapon().damageType())) * 0.5), true, 3);
	
	SkillSpecialDamage<Unit, Unit, Integer> damageCalc;
	boolean activateOnAttack;
	int charge;
	
	SpecialSkill(SkillSpecialDamage<Unit, Unit, Integer> damageCalc, boolean activateOnAttack, int charge){
		this.damageCalc = damageCalc;
		this.activateOnAttack = activateOnAttack;
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
