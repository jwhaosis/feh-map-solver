package combatcalcs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import formatting.CombatStrings;
import global.enums.SkillType;
import skill.passive.DefaultSkill;
import turn.combat.CombatActionQueue;
import unit.Unit;

public class CombatSkillTests {
	
	//Plus Skills
	@Test
    public void shouldApplyPlusSkillBonusOnInitiate() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Plus));
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyPlusSkillBonusOnCounter() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Plus));
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplySpeedBonus() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.aSlot(new DefaultSkill(new int[] {0,0,5,0,0}, SkillType.Plus));
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
    }

	//Blow Skills
	@Test
    public void shouldApplyBlowSkillBonus() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Blow));
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
    }
	
	//Stance Skills
	@Test
    public void shouldApplyStanceSkillBonus() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Stance));
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
    }
	
	//Brazen Skills
	@Test
    public void shouldDisableBrazenAboveThreshold() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Brazen));
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }

	@Test
    public void shouldApplyBrazenBonusOnInitiate() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Brazen));
    	ally.takeDamage(6);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
    }

	@Test
    public void shouldApplyBrazenBonusOnCounter() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Brazen));
    	enemy.takeDamage(10);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
    }
	
	@Test
    public void shouldRoundDownThresholdForBrazen() {
    	Unit ally = new Unit("Player", 31,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Brazen));
    	ally.takeDamage(6);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }
	
	//Push Skills
	@Test
	public void shouldDisablePushBelowThreshold() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Push));
    	ally.takeDamage(1);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
	}
	
	@Test
    public void shouldApplyPushBonusOnInitiate() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Push));
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
    }

	@Test
    public void shouldApplyPushBonusOnCounter() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Push));
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
    }
	
	//Boost Skills
	@Test
	public void shouldDisableBoostBelowThreshold() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
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
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Boost));
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
    }

}
