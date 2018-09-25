package turn.combat;

import java.lang.StringBuilder;

import formatting.CombatStrings;
import global.enums.passiveskills.ActivationPhase;
import global.enums.unitinfo.StatType;
import global.enums.weaponskills.DamageType;
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
	
	public CombatAction(Unit whacker, Unit sandbag, boolean preCombatSpecial) {
		this.whacker = whacker;
		this.sandbag = sandbag;
		this.currentPhase = ActivationPhase.Both;
		this.preCombatSpecial = preCombatSpecial;
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

		return resultString;
	}
	
	//calculate damage dealt for this attack
	private int calculateDamage() {
		int atk = whacker.getStat(StatType.Attack);
		int def = sandbag.getStat(targetDefensiveStat());
		
		//effectiveness weapons
		if(whacker.weapon().effectiveBonus.contains(sandbag.moveType)) {
			atk = (int) Math.floor(atk * 1.5);
		}

		//triangle adept and triangle advantage
		double triangleBonus = 0.2;
		
		//TODO: move skills off weapons and add them to skill class
		/*if(whacker.weapon().skill.triangleBonus() == true || sandbag.weapon().skill.triangleBonus() == true) {
			triangleBonus = 0.4;
		}*/
		if(whacker.weaponTriangleAdvantage() == sandbag.color) {
			atk = (int) Math.floor(atk * (1+triangleBonus));
		} else if (whacker.color == sandbag.weaponTriangleAdvantage()) {
			atk = (int) Math.ceil(atk * (1-triangleBonus));
		}
		
		return Math.max(0, atk-def);
	}
	
	//TODO: private int calculateDamage(boolean preCombatSpecial) {return 0;}
	
	//choose the target defensive stat
	private StatType targetDefensiveStat() {
		DamageType whackerType = whacker.weapon().damageType();
		if(whackerType == DamageType.Physical) {
			return StatType.Defense;
		} else if(whackerType == DamageType.Magical) {
			return StatType.Resistance;
		} else if(whackerType == DamageType.Adaptive && sandbag.weapon().range() == 2) {
			return sandbag.getAdaptiveDefense();
		} else /*if(whackerType == DamageType.Adaptive && sandbag.weapon().range() == 1)*/ {
			return StatType.Resistance;
		}
	}
}
