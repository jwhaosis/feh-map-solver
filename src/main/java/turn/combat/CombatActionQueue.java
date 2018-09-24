package turn.combat;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import formatting.CombatStrings;
import global.enums.ActivationPhase;
import global.enums.StatType;
import map.Map;
import skill.Skill;
import unit.Unit;

public class CombatActionQueue {
	
	private Unit attackingUnit;
	private Unit defendingUnit;
	
	int[] attackingBonus;
	int[] defendingBonus;

	private int atkHits;
	private int defHits;
	

	public CombatActionQueue(Unit attackingUnit, Unit defendingUnit) {
		this.attackingUnit = attackingUnit;
		this.defendingUnit = defendingUnit;
		
		this.attackingBonus = getSkillBonus(attackingUnit);
		this.defendingBonus = getSkillBonus(defendingUnit);
		
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
		int speedDiff = getStatPlusBonus(attackingUnit, StatType.Speed) - getStatPlusBonus(defendingUnit, StatType.Speed);
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
	
	private void calculateSpecialCd(Unit unit) {
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
			return new CombatAction(attackingUnit, defendingUnit, attackingBonus, defendingBonus, ActivationPhase.Initiate);
		} else {
			return null;
		}
	}
	
	private CombatAction enqueueCounter() {
		if(defHits > 0) {
			defHits--;
			return new CombatAction(defendingUnit, attackingUnit, defendingBonus, attackingBonus, ActivationPhase.Initiate);
		} else {
			return null;
		}
	}
	
	private int[] getSkillBonus(Unit unit) {
		int[] totalBonus = new int[5];
		ActivationPhase phase = unit == attackingUnit ? ActivationPhase.Initiate : ActivationPhase.Counter;
		
		Iterator<Skill> skillIter = unit.unitSkills.values().iterator();
		while(skillIter.hasNext()) {
			//TODO: make map linked
			Map map = null;
			Skill currentSkill = skillIter.next();
			if(currentSkill.isActive(unit, (unit == attackingUnit ? defendingUnit : attackingUnit), phase)) {
				int[] skillBonus = currentSkill.getStatBonus(phase);
				for(int i = 0; i < 5; i++) {
					totalBonus[i] += skillBonus[i];
				}
			}
		}
		return totalBonus;
	}
	
	private int getStatPlusBonus(Unit unit, StatType stat) {
		return unit.getStat(stat) + (unit == attackingUnit ? attackingBonus : defendingBonus)[stat.index];
	}

	public static int getStatPlusBonus(Unit unit, int[] bonus, StatType stat) {
		return unit.getStat(stat) + bonus[stat.index];
	}
}
