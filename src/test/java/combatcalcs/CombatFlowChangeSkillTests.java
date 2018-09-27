package combatcalcs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import formatting.CombatStrings;
import global.enums.passiveskills.PassiveSkill;
import global.enums.passiveskills.PassiveSkillSlot;
import global.enums.unitinfo.UnitType;
import turn.combat.CombatActionQueue;
import unit.Unit;

public class CombatFlowChangeSkillTests {
	
//All Range Counter
	@Test
	public void shouldCounterRangedAsMelee() {
	   	Unit ally = new Unit("Player", UnitType.CBow, 30,20,0,0,0);
	   	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
	   	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.AllRangeCounter);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
    	assertEquals(testOutput.size(), 4, "Should be 4 actions in the queue.");
	}
	@Test
	public void shouldCounterMeleeAsRanged() {
		Unit ally = new Unit("Player", 30,20,0,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.CBow, 50,10,0,0,0);
	   	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.AllRangeCounter);
	   	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	   	assertEquals(testOutput.size(), 4, "Should be 4 actions in the queue.");
	}
		
//Triangle Adept
	@Test
	public void shouldApplyTriangleAdeptOnTriangleAdvantage() {
		Unit ally = new Unit("Player", UnitType.Lance, 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.Sword, 50,0,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.TriangleAdept);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
		assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 14), "The combat result is inconsistant, 14 damage should have been dealt.");
	}
		
	@Test
	public void shouldApplyTriangleAdeptOnTriangleDisadvantage() {
		Unit ally = new Unit("Player", UnitType.Axe, 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.Sword, 50,0,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.TriangleAdept);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
		assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 6), "The combat result is inconsistant, 6 damage should have been dealt.");
	}
		
	@Test
	public void shouldDisableTriangleAdeptWithNoTriangle() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.TriangleAdept);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
		assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
	}
		
	@Test
	public void shouldApplyHigherRankedTriangleAdeptIfOnAlly() {
		Unit ally = new Unit("Player", UnitType.Lance, 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.Sword, 50,0,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.TriangleAdept, 3);
		enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.TriangleAdept, 1);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
		assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 14), "The combat result is inconsistant, 14 damage should have been dealt.");
	}
		
	@Test
	public void shouldApplyHigherRankedTriangleAdeptIfOnEnemy() {
		Unit ally = new Unit("Player", UnitType.Lance, 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.Sword, 50,0,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.TriangleAdept, 1);
		enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.TriangleAdept, 3);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 14), "The combat result is inconsistant, 14 damage should have been dealt.");
	}

	@Test
	public void shouldApplyTriangleAdeptOnInitiateAsWhacker() {
		Unit ally = new Unit("Player", UnitType.Lance, 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.Sword, 50,0,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.TriangleAdept);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 14), "The combat result is inconsistant, 14 damage should have been dealt.");
	}
		
	@Test
	public void shouldApplyTriangleAdeptOnCounterAsWhacker() {
		Unit ally = new Unit("Player", UnitType.Lance, 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.Sword, 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.TriangleAdept);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 6), "The combat result is inconsistant, 6 damage should have been dealt.");
	}

//Sweep Skills
	@Test
	public void shouldNotSweepWithoutSpeedAdvantage() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Windsweep);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 4, "Should be 4 actions in the queue.");
	}

	@Test
	public void shouldSweepPhysicalMeleeWithWindsweep() {
		Unit ally = new Unit("Player", 40,10,1,0,0);
		Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Windsweep);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 3, "Should be 3 actions in the queue.");
	}
	
	@Test
	public void shouldSweepPhysicalRangedWithWindsweep() {
		Unit ally = new Unit("Player", 40,10,1,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.CBow, 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Windsweep);
		enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.AllRangeCounter);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 3, "Should be 3 actions in the queue.");
	}
	
	@Test
	public void shouldSweepMagicalMeleeWithWatersweep() {
		Unit ally = new Unit("Player", 40,10,1,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.CBreath, 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Watersweep);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 3, "Should be 3 actions in the queue.");
	}
	
	@Test
	public void shouldSweepMagicalRangedWithWatersweep() {
		Unit ally = new Unit("Player", 40,10,1,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.CTome, 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Watersweep);
		enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.AllRangeCounter);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 3, "Should be 3 actions in the queue.");
	}

	@Test
	public void shouldNotSweepMagicalMeleeWithWindsweep() {
		Unit ally = new Unit("Player", 40,10,1,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.CBreath, 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Windsweep);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 4, "Should be 4 actions in the queue.");
	}
	
	@Test 
	public void shouldNotSweepPhysicalRangedWithWatersweep() {
		Unit ally = new Unit("Player", 40,10,1,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.CBow, 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Watersweep);
		enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.AllRangeCounter);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 4, "Should be 4 actions in the queue.");
	}
}
