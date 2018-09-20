package turn.combat;

import java.lang.StringBuilder;

import formatting.CombatStrings;
import global.enums.ActivationPhase;
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
		
	private int calculateDamage() {
		int atk = whacker.attack(currentPhase);
		int def = sandbag.getDefensiveStat();
		
		//effectiveness weapons
		if(whacker.weapon().effectiveBonus.contains(sandbag.moveType)) {
			atk = (int) Math.floor(atk * 1.5);
		}

		//triangle adept and triangle advantage
		double triangleBonus = 0.2;
		if(whacker.weapon().skill.triangleBonus() == true || sandbag.weapon().skill.triangleBonus() == true) {
			triangleBonus = 0.4;
		}
		if(whacker.weapon().weaponTriangleAdvantage() == sandbag.weapon().color) {
			atk = (int) Math.floor(atk * (1+triangleBonus));
		} else if (whacker.weapon().color == sandbag.weapon().weaponTriangleAdvantage()) {
			atk = (int) Math.ceil(atk * (1-triangleBonus));
		}
		
		return Math.max(0, atk-def);
	}
	
	private int calculateDamage(boolean preCombatSpecial) {
		int atk = whacker.attack(currentPhase);
		int def = sandbag.getDefensiveStat();
		
		//effectiveness weapons
		if(whacker.weapon().effectiveBonus.contains(sandbag.moveType)) {
			atk = (int) Math.floor(atk * 1.5);
		}

		//triangle adept and triangle advantage
		double triangleBonus = 0.2;
		if(whacker.weapon().skill.triangleBonus() == true || sandbag.weapon().skill.triangleBonus() == true) {
			triangleBonus = 0.4;
		}
		if(whacker.weapon().weaponTriangleAdvantage() == sandbag.weapon().color) {
			atk = (int) Math.floor(atk * (1+triangleBonus));
		} else if (whacker.weapon().color == sandbag.weapon().weaponTriangleAdvantage()) {
			atk = (int) Math.ceil(atk * (1-triangleBonus));
		}
		
		return Math.max(0, atk-def);
	}

		

}
