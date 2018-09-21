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

	@Test
    public void shouldApplyBlowSkillBonus() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Blow));
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyStanceSkillBonus() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.aSlot(new DefaultSkill(new int[] {0,5,0,0,0}, SkillType.Stance));
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
    }
	
	
	@Test
    public void shouldApplyBrazenBonus() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.aSlot(new DefaultSkill(new int[] {0,0,5,0,0}, SkillType.Brazen));
    	ally.takeDamage((int) 6);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
    }



	
}
