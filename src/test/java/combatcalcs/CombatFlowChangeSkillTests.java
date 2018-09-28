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
	
	@Test
	public void shouldNotDoubleWithWindsweepOnInitiateOnProc() {
		Unit ally = new Unit("Player", 40,10,10,0,0);
		Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Windsweep);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 3, "Should be 3 actions in the queue.");
	}
	
	@Test
	public void shouldNotDoubleWithWindsweepOnInitiateOnNoProc() {
		Unit ally = new Unit("Player", 40,10,10,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.RBreath, 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Windsweep);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 10), "The combat result is inconsistant, the enemy should be attacking.");
        assertEquals(testOutput.size(), 4, "Should be 4 actions in the queue.");
	}

	@Test
	public void shouldNotDoubleWithWatersweepOnInitiateOnProc() {
		Unit ally = new Unit("Player", 40,10,10,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.RBreath, 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Watersweep);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 3, "Should be 3 actions in the queue.");
	}
	
	@Test
	public void shouldNotDoubleWithWatersweepOnInitiateOnNoProc() {
		Unit ally = new Unit("Player", 40,10,10,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.Sword, 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Watersweep);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 10), "The combat result is inconsistant, the enemy should be attacking.");
        assertEquals(testOutput.size(), 4, "Should be 4 actions in the queue.");
	}
	
	@Test
	public void shouldDoubleWithWindsweepOnCounter() {
		Unit ally = new Unit("Player", 40,10,10,0,0);
		Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Windsweep);
		LinkedList<String> testOutput = new CombatActionQueue(enemy, ally).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
	}

	@Test
	public void shouldDoubleWithWatersweepOnCounter() {
		Unit ally = new Unit("Player", 40,10,10,0,0);
		Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Watersweep);
		LinkedList<String> testOutput = new CombatActionQueue(enemy, ally).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
	}

//Free Initiate and Counter attack skills (Bold/Vengeful + Brash/QR)
	@Test
	public void shouldNotGiveFreeInitiateVengeful() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.VengefulFighter);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 4, "Should be 4 actions in the queue.");
	}
	
	@Test
	public void shouldGiveFreeCounterVengeful() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
		enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.VengefulFighter);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
	}
	
	@Test
	public void shouldGiveFreeCounterVengefulWhenDoubled() {
		Unit ally = new Unit("Player", 40,10,5,0,0);
		Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
		enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.VengefulFighter);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 6, "Should be 6 actions in the queue.");
	}
	
	@Test
	public void shouldNotMakeMoreThanTwoAttacksVengeful() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
		enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.VengefulFighter);
		enemy.addSkill(PassiveSkillSlot.B, PassiveSkill.VengefulFighter);
		enemy.addSkill(PassiveSkillSlot.C, PassiveSkill.VengefulFighter);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
	}

	@Test
	public void shouldNotGiveFreeCounterVengefulUnderThreshold() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
		enemy.takeDamage(26);
		enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.VengefulFighter);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 4, "Should be 4 actions in the queue.");
	}
	
	@Test
	public void shouldRoundDownVengefulThreshold() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", 51,10,0,0,0);
		enemy.takeDamage(26);
		enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.VengefulFighter);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 4, "Should be 4 actions in the queue.");
	}
	
	@Test
	public void shouldGiveFreeInitiateBold() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BoldFighter);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
	}
		
	@Test
	public void shouldGiveFreeInitiateBoldWhenDoubled() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", 50,10,5,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BoldFighter);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 6, "Should be 6 actions in the queue.");
	}
	
	@Test
	public void shouldGiveFreeInitiateBoldUnCountered() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.CBow, 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BoldFighter);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 4, "Should be 4 actions in the queue.");
	    assertEquals(testOutput.get(2), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, the ally should be attacking.");
	}
	
	@Test
	public void shouldNotMakeMoreThanTwoAttacksBold() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BoldFighter);
		ally.addSkill(PassiveSkillSlot.B, PassiveSkill.BoldFighter);
		ally.addSkill(PassiveSkillSlot.C, PassiveSkill.BoldFighter);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
	}

	@Test
	public void shouldNotGiveFreeCounterBold() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
		enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.BoldFighter);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 4, "Should be 4 actions in the queue.");
	}
	
	@Test
	public void shouldNotGiveFreeInitiateBoldUnderThreshold() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BoldFighter, 2);
		ally.takeDamage(21);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 4, "Should be 4 actions in the queue.");
	}

	@Test
	public void shouldGiveFreeCounterQR() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
		enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.QuickRiposte);
		enemy.takeDamage(15);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
	}

	@Test
	public void shouldRoundDownBoldThreshold() {
		Unit ally = new Unit("Player", 41,10,0,0,0);
		Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BoldFighter, 2);
		ally.takeDamage(21);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 4, "Should be 4 actions in the queue.");
	}
	
	@Test
	public void shouldRoundDownQRThreshold() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", 51,10,0,0,0);
		enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.QuickRiposte);
		enemy.takeDamage(16);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 4, "Should be 4 actions in the queue.");
	}
	
	@Test
	public void shouldNotGiveFreeInitiateBrashAboveThreshold() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BrashAssault);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 4, "Should be 4 actions in the queue.");
	}

	@Test
	public void shouldGiveFreeInitiateBrashCountered() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BrashAssault);
		ally.takeDamage(20);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
	}
	
	@Test
	public void shouldNotGiveFreeInitiateBrashUnCountered() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.CBow, 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BrashAssault);
		ally.takeDamage(20);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 3, "Should be 3 actions in the queue.");
	}
	
	@Test
	public void shouldRoundDownBrashThreshold() {
		Unit ally = new Unit("Player", 41,10,0,0,0);
		Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BrashAssault);
		ally.takeDamage(20);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 4, "Should be 4 actions in the queue.");
	}
	
//Breaker Skills
	@Test
	public void shouldGiveFreeInitiateAndStopDoubleSwordbreaker() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.Sword, 50,10,10,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Swordbreaker);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, the ally should be attacking.");
	}
	
	@Test
	public void shouldGiveFreeInitiateAndStopDoubleLancebreaker() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.Lance, 50,10,10,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Lancebreaker);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, the ally should be attacking.");
	}

	@Test
	public void shouldGiveFreeInitiateAndStopDoubleAxebreaker() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.Axe, 50,10,10,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Axebreaker);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, the ally should be attacking.");
	}

	@Test
	public void shouldGiveFreeInitiateAndStopDoubleCBowbreaker() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.CBow, 50,10,10,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.CBowbreaker);
		enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.AllRangeCounter);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, the ally should be attacking.");
	}

	@Test
	public void shouldGiveFreeInitiateAndStopDoubleCDaggerbreaker() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.CDagger, 50,10,10,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.CDaggerbreaker);
		enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.AllRangeCounter);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, the ally should be attacking.");
	}

	@Test
	public void shouldGiveFreeInitiateAndStopDoubleRTomebreaker() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.RTome, 50,10,10,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.RTomebreaker);
		enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.AllRangeCounter);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, the ally should be attacking.");
	}
	
	@Test
	public void shouldGiveFreeInitiateAndStopDoubleBTomebreaker() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.BTome, 50,10,10,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BTomebreaker);
		enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.AllRangeCounter);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, the ally should be attacking.");
	}
	
	@Test
	public void shouldGiveFreeInitiateAndStopDoubleGTomebreaker() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.GTome, 50,10,10,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.GTomebreaker);
		enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.AllRangeCounter);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, the ally should be attacking.");
	}
	
	@Test
	public void shouldNotGiveBreakerBonusWrongColorTome() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.RTome, 50,10,10,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BTomebreaker);
		enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.AllRangeCounter);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(enemy, ally, 10), "The combat result is inconsistant, the enemy should be attacking.");
	}
	
	@Test
	public void shouldNotGiveBreakerBonusWrongWeaponType() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.Lance, 50,10,10,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Swordbreaker);
		LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(enemy, ally, 10), "The combat result is inconsistant, the enemy should be attacking.");
	}

	@Test
	public void shouldGiveFreeCounterAndStopDouble() {
		Unit ally = new Unit("Player", 40,10,0,0,0);
		Unit enemy = new Unit("Sandbag", UnitType.Sword, 50,10,10,0,0);
		ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Swordbreaker);
		LinkedList<String> testOutput = new CombatActionQueue(enemy, ally).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, the ally should be attacking.");
	}

//TODO: Priority Change Skill Tests (Vantage/Desperation)
	@Test
	public void shouldGiveDesperationPriorityInitiateOnDouble() {
		
	}
	
	@Test
	public void shouldNotGiveDesperationFreeDouble() {
		
	}
	
	@Test
	public void shouldNotGiveDesperationPriorityInitiateAboveThreshold() {
		
	}

	@Test
	public void shouldNotGiveDesperationPriorityCounter() {
		
	}
	
	@Test
	public void shouldGiveVantagePriorityCounter() {
		
	}
	
	@Test
	public void shouldNotGiveVantageFreeDouble() {
		
	}
	
	@Test
	public void shouldNotGiveVantagePriorityCounterAboveThreshold() {
		
	}

	@Test
	public void shouldNotGiveVantagePriorityInitiate() {
		
	}
}
