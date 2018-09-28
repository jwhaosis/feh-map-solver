package turn.combat;

import java.util.Collections;
import java.util.LinkedList;

import formatting.CombatStrings;
import global.enums.passiveskills.ActivationPhase;
import global.enums.passiveskills.PassiveSkill;
import global.enums.unitinfo.StatType;
import global.enums.unitinfo.UnitCombatInfo;
import global.enums.unitinfo.UnitType;
import unit.Unit;

public class CombatActionQueue {
	
	private Unit attackingUnit;
	private Unit defendingUnit;
	
	private int atkHits;
	private int defHits;
	

	public CombatActionQueue(Unit attackingUnit, Unit defendingUnit) {
		this.attackingUnit = attackingUnit;
		this.defendingUnit = defendingUnit;
		calculatePassiveSkillBonus();
		int[] totalHits = calculateHits();
		atkHits = totalHits[0];
		defHits = totalHits[1];
	}
	
	public LinkedList<String> execute() {
		LinkedList<CombatAction> actionQueue = buildQueue();
		LinkedList<String> actionOutput = new LinkedList<String>();
		actionOutput.add(CombatStrings.BEGIN());
		
		while(!actionQueue.isEmpty()) {
			actionOutput.add(actionQueue.remove().execute());
			String cont = checkUnitHealth();
			if(cont != null) {
				actionOutput.add(cont);
				return actionOutput;
			}
		}
		
		attackingUnit.ownTurnActionClear();
		defendingUnit.ownTurnActionClear();
		actionOutput.add(CombatStrings.END());
		return actionOutput;
	}
	
	private LinkedList<CombatAction> buildQueue() {
		LinkedList<CombatAction> resultQueue = new LinkedList<CombatAction>();
		if(checkPreCombatSpecial()) {
			//resultQueue.add(new CombatAction(attackingUnit, defendingUnit, true));
		}
		if(checkCounterPrioritySkill()) {
			resultQueue.add(enqueueCounter());
		}
		if(checkAttackPrioritySkill()) {
			resultQueue.add(enqueueAttack());
		}
		while(atkHits>0 || defHits>0) {
			resultQueue.add(enqueueAttack());
			resultQueue.add(enqueueCounter());
		}
		resultQueue.removeAll(Collections.singleton(null));
		return resultQueue;
	}
	
	private int[] calculateHits() {
		int[] resultHits = new int[2];
		int speedDiff = attackingUnit.getStat(StatType.Speed) - defendingUnit.getStat(StatType.Speed);
		
		resultHits[0] = 1 + Math.max(0, attackingUnit.countCombatInfo(UnitCombatInfo.freeInitiateAttack) 
						- attackingUnit.countCombatInfo(UnitCombatInfo.reduceOwnAttackCount) 
						- defendingUnit.countCombatInfo(UnitCombatInfo.reduceEnemyAttackCount));
		resultHits[1] = 1 + Math.max(0, defendingUnit.countCombatInfo(UnitCombatInfo.freeInitiateAttack) 
						- defendingUnit.countCombatInfo(UnitCombatInfo.reduceOwnAttackCount) 
						- attackingUnit.countCombatInfo(UnitCombatInfo.reduceEnemyAttackCount));
		
		if(speedDiff >= 5) {
			resultHits[0] += 1;
		} else if (speedDiff <= -5) {
			resultHits[1] += 1;
		}

		if(!checkAllowCounter(attackingUnit, defendingUnit, ActivationPhase.Initiate)) {
			resultHits[1] = 0;
		}
		return resultHits;
	}
	
	private String checkUnitHealth() {
		if(attackingUnit.currentHealth() == 0) {
			return CombatStrings.VICTORY(defendingUnit);
		} else if (defendingUnit.currentHealth() == 0) {
			return CombatStrings.VICTORY(attackingUnit);
		} else {
			return null;
		}
	}
	
	private boolean checkPreCombatSpecial(){
		return false;
	}
	
	private int calculatePassiveSkillBonus() {
		attackingUnit.activateSkills(defendingUnit, ActivationPhase.Initiate);
		defendingUnit.activateSkills(attackingUnit, ActivationPhase.Counter);
		return 1;
	}

	public static boolean checkAllowCounter(Unit attacker, Unit defender, ActivationPhase phase) {
		PassiveSkill sweep = defender.weaponType().damageType.identifier == 1 ? PassiveSkill.Windsweep : PassiveSkill.Watersweep;
		if(attacker.checkSkillActivation(defender, phase, sweep) != 0) {
			return false;
		}
		if(defender.checkSkillActivation(defender, ActivationPhase.Counter, PassiveSkill.AllRangeCounter) != 0 || defender.weaponType().range == attacker.weaponType().range) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void checkBreaker(Unit attacker, Unit defender) {
		PassiveSkill breaker;
		if(defender.type == UnitType.Sword) {
			breaker = PassiveSkill.Swordbreaker;
		} else if (defender.type == UnitType.Lance) {
			breaker = PassiveSkill.Lancebreaker;
		} else if (defender.type == UnitType.Axe) {
			breaker = PassiveSkill.Lancebreaker;
		} else if (defender.type == UnitType.CBow) {
			breaker = PassiveSkill.CBowbreaker;
		} else if (defender.type == UnitType.CDagger) {
			breaker = PassiveSkill.CDaggerbreaker;
		} else if (defender.type == UnitType.RTome) {
			breaker = PassiveSkill.RTomebreaker;
		} else if (defender.type == UnitType.BTome) {
			breaker = PassiveSkill.BTomebreaker;
		} else if (defender.type == UnitType.GTome) {
			breaker = PassiveSkill.GTomebreaker;
		} else {
			breaker = PassiveSkill.Empty;
		}
	}
	
	private boolean checkAttackPrioritySkill() {
		return attackingUnit.checkSkillActivation(defendingUnit, ActivationPhase.Initiate, PassiveSkill.Desperation) != 0;
	}

	private boolean checkCounterPrioritySkill() {
		if (!checkAllowCounter(attackingUnit, defendingUnit, ActivationPhase.Initiate)) {
			return false;
		} else {
			return defendingUnit.checkSkillActivation(attackingUnit, ActivationPhase.Counter, PassiveSkill.Vantage) != 0;
		}
	}
	
	private CombatAction enqueueAttack() {
		if(atkHits > 0) {
			atkHits--;
			return new CombatAction(attackingUnit, defendingUnit, ActivationPhase.Initiate);
		} else {
			return null;
		}
	}
	
	private CombatAction enqueueCounter() {
		if(defHits > 0) {
			defHits--;
			return new CombatAction(defendingUnit, attackingUnit, ActivationPhase.Initiate);
		} else {
			return null;
		}
	}
	
	
}
