package combatcalcs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import formatting.CombatStrings;
import global.enums.PassiveSkillType;
import global.enums.skill.BaseSkill;
import turn.combat.CombatActionQueue;
import unit.Unit;

public class CombatSkillTests {
	
	//Plus Skills
	@Test
    public void shouldApplyPlusSkillBonusOnInitiate() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.Atk);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 13), "The combat result is inconsistant, 13 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyPlusSkillBonusOnCounter() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillType.A, BaseSkill.Def);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 7), "The combat result is inconsistant, 7 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplySpeedBonus() {
    	Unit ally = new Unit("Player", 40,10,2,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.Spd);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
    }

	//Blow Skills
	@Test
    public void shouldApplyBlowSkillBonusAsWhacker() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.DeathBlow);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 16), "The combat result is inconsistant, 16 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBlowSkillBonusAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.ArmoredBlow);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 4), "The combat result is inconsistant, 4 damage should have been dealt.");
    }

	
	//Stance Skills
	@Test
    public void shouldApplyStanceSkillBonusAsWhacker() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillType.A, BaseSkill.FierceStance);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 6), "The combat result is inconsistant, 6 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyStanceSkillBonusAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillType.A, BaseSkill.SturdyStance);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 4), "The combat result is inconsistant, 4 damage should have been dealt.");
    }

	
	//Brazen Skills
	@Test
    public void shouldDisableBrazenAboveThreshold() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.BrazenAtkSpd);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }
	
	@Test
    public void shouldRoundDownThresholdForBrazen() {
    	Unit ally = new Unit("Player", 41,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.BrazenAtkSpd);
    	ally.takeDamage(8);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }

	@Test
    public void shouldApplyBrazenBonusOnInitiateAsWhacker() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.BrazenAtkRes);
    	ally.takeDamage(8);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 17), "The combat result is inconsistant, 17 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBrazenBonusOnInitiateAttackAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.BrazenAtkDef);
    	ally.takeDamage(8);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 3), "The combat result is inconsistant, 3 damage should have been dealt.");
    }

	@Test
    public void shouldApplyBrazenBonusOnCounterAsWhacker() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillType.A, BaseSkill.BrazenAtkDef);
    	enemy.takeDamage(10);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 7), "The combat result is inconsistant, 7 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBrazenBonusOnCounterAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillType.A, BaseSkill.BrazenAtkDef);
    	enemy.takeDamage(10);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 3), "The combat result is inconsistant, 3 damage should have been dealt.");
    }

	/*
	//Push Skills
	@Test
	public void shouldDisablePushBelowThreshold() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Push));
    	ally.takeDamage(1);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
	}
	
	@Test
    public void shouldApplyPushBonusOnInitiate() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Push));
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
    }

	@Test
    public void shouldApplyPushBonusOnCounter() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Push));
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
    }
	
	//Boost Skills
	@Test
	public void shouldDisableBoostBelowThreshold() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Boost));
    	ally.takeDamage(1);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
	}
	
	@Test
    public void shouldApplyBoostBonusOnExact() {
    	Unit ally = new Unit("Player", 53,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Boost));
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBoostBonusWhileSelfDamaged() {
    	Unit ally = new Unit("Player", 55,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Boost));
    	ally.takeDamage(2);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBoostBonusWhileEnemyDamaged() {
    	Unit ally = new Unit("Player", 50,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Boost));
    	enemy.takeDamage(5);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
    }
	
	@Test
    public void shouldDisableBoostBelowThresholdDamaged() {
    	Unit ally = new Unit("Player", 55,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Boost));
    	ally.takeDamage(5);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBoostBonusOnInitiate() {
    	Unit ally = new Unit("Player", 55,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Boost));
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
    }

	@Test
    public void shouldApplyBoostBonusOnCounter() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Boost));
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
    }
*/
}
