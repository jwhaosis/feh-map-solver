package turn.combat;

import java.lang.StringBuilder;

import formatting.CombatStrings;
import global.enums.passiveskills.ActivationPhase;
import global.enums.unitinfo.StatType;
import unit.Unit;

public class CombatAction {
	
	Unit whacker;
	Unit sandbag;
	
	ActivationPhase currentPhase;
	boolean preCombatSpecial;
	
	public CombatAction(Unit whacker, Unit sandbag, ActivationPhase currentPhase) {
		this.whacker = whacker;
		this.sandbag = sandbag;
		this.currentPhase = currentPhase;
		this.preCombatSpecial = false;
	}

	//calculate damage and return the result string
	public String execute() {
		/*if(preCombatSpecial) {
			damageDealt = sandbag.takeDamage(calculatePreCombat());
		} else {
			damageDealt = sandbag.takeDamage(calculateDamage());
		}*/
		
		int theoreticalDamage = calculateDamage();//preCombatSpecial);
		int actualDamage = sandbag.takeDamage(theoreticalDamage);
		String resultString = CombatStrings.DAMAGE(whacker, sandbag, actualDamage);

		if (theoreticalDamage != actualDamage) {
			StringBuilder resultAppender = new StringBuilder(resultString);
			resultAppender.append(CombatStrings.OVERKILL(theoreticalDamage - actualDamage));
			return resultAppender.toString();
		}
		
		whacker.increaseCurrentSpecialCharge(Math.max(0, whacker.quickenSpecialChargeBy(true, currentPhase) - sandbag.slowEnemySpecialChargeBy()));
		sandbag.increaseCurrentSpecialCharge(Math.max(0, sandbag.quickenSpecialChargeBy(false, currentPhase) - whacker.slowEnemySpecialChargeBy()));
		
		return resultString;
	}
	
	//calculate damage dealt for this attack
	private int calculateDamage() {
		int atk = whacker.getStat(StatType.Attack);
		int def = sandbag.getStat(sandbag.targetDefensiveStat(whacker.weaponType().damageType));
		
		//TODO: implement flat damage (light brand)
		int flatDamage = 0;
		
		//TODO: implement forts
		int fortMitigation = 0;
		
		double effectivenessBonus = 1;
		double triangleBonus = 0;

		//effectiveness weapons
		if(whacker.isEffectiveAgainst(sandbag.moveType)) {
			effectivenessBonus = 1.5;
		}

		//triangle adept and triangle advantage
		double triangleAdept = Math.max(whacker.triangleAdeptBonus(), sandbag.triangleAdeptBonus());
		if(whacker.hasWeaponTriangleAdvantage(sandbag.type.color)) {
			triangleBonus = 0.2 + triangleAdept;
		} else if (sandbag.hasWeaponTriangleAdvantage(whacker.type.color)) {
			triangleBonus = - (0.2 + triangleAdept);
		} else {
			triangleBonus = 0;
		}
		
		int specialDamage = whacker.activateSpecial(sandbag, true);
		
		//int specialMitigation = sandbag.activateSpecial(whacker, false);
		
		//TODO: get map to check if unit is on a defensive tile
		fortMitigation = (int) Math.floor(def * fortMitigation);
		fortMitigation = 0;
		
		int roundedDamage = (int) (atk * effectivenessBonus) 
					+ (int) (Math.floor(atk * effectivenessBonus) * triangleBonus)
					+ specialDamage
					+ whacker.specialDamageBoost()
					- def
					- fortMitigation;

		return Math.max(0, Math.max(0, roundedDamage) + flatDamage);
	}
	
	//TODO: private int calculateDamage(boolean preCombatSpecial) {return 0;}
}
