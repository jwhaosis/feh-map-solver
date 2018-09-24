package turn.combat;

import java.util.Collections;
import java.util.LinkedList;

import formatting.CombatStrings;
import global.enums.ActivationPhase;
import global.enums.StatType;
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
		int totalHits = calculateHits();
		atkHits = totalHits / 10;
		defHits = totalHits % 10;
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
		
		actionOutput.add(CombatStrings.END());
		return actionOutput;
	}
	
	private LinkedList<CombatAction> buildQueue() {
		LinkedList<CombatAction> resultQueue = new LinkedList<CombatAction>();
		if(checkPreCombatSpecial()) {
			resultQueue.add(new CombatAction(attackingUnit, defendingUnit, true));
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
	
	private int calculateHits() {
		int resultHits = 11;
		int speedDiff = attackingUnit.getStat(StatType.Speed) - defendingUnit.getStat(StatType.Speed);
		if(speedDiff >= 5) {
			resultHits += 10;
		} else if (speedDiff <= -5) {
			resultHits += 1;
		}
		
		if(!checkAllowCounter(attackingUnit, defendingUnit)) {
			return (resultHits / 10) * 10;
		}
		return resultHits;
	}
	
	//TODO: private void calculateSpecialCd(Unit unit) {}
	
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
	
	private boolean checkAllowCounter(Unit whacker, Unit sandbag) {
		
		//sweep skills
		
		if(sandbag.weapon().allRangeCounter || sandbag.weapon().range() == whacker.weapon().range() ) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkCounterPrioritySkill() {
		if (!checkAllowCounter(attackingUnit, defendingUnit)) {
			return false;
		} else {
			return false;
		}
	}
	
	private boolean checkAttackPrioritySkill() {
		return false;
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
	
	private int calculatePassiveSkillBonus() {
		attackingUnit.activateSkills(defendingUnit, ActivationPhase.Initiate);
		defendingUnit.activateSkills(attackingUnit, ActivationPhase.Counter);
		return 1;
	}
}
