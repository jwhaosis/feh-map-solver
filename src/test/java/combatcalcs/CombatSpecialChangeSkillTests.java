package combatcalcs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import formatting.CombatStrings;
import global.enums.passiveskills.PassiveSkill;
import global.enums.passiveskills.PassiveSkillSlot;
import global.enums.specialskills.SpecialSkill;
import global.enums.unitinfo.UnitType;
import turn.combat.CombatActionQueue;
import unit.Unit;

public class CombatSpecialChangeSkillTests {
	
//Weapon and Wrath Special Damage Changes
	@Test
	public void shouldAddWeaponChargeReduction() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.DraconicAura);
	    ally.addSkill(PassiveSkillSlot.S, PassiveSkill.Slaying);
	    ally.increaseCurrentSpecialCharge(2);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 13), "The combat result is inconsistant, 13 damage should have been dealt.");
	}
	
	@Test
	public void shouldAddWeaponChargeReductionFirstProc() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,10,0);
	    ally.addSpecial(SpecialSkill.Moonbow);
	    ally.addSkill(PassiveSkillSlot.S, PassiveSkill.Slaying);
	    ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Slaying);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 3), "The combat result is inconsistant, 3 damage should have been dealt.");
	}

	@Test
	public void shouldAddWeaponChargeReductionSecondProc() {
	    Unit ally = new Unit("Player", 40,10,5,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,10,0);
	    ally.addSpecial(SpecialSkill.Moonbow);
	    ally.addSkill(PassiveSkillSlot.S, PassiveSkill.Slaying);
	    ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Slaying);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 3), "The combat result is inconsistant, 3 damage should have been dealt.");
	}
	
	@Test
	public void shouldUseBothWeaponChargeAndNaturalCharge() {
	    Unit ally = new Unit("Player", 40,10,5,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,10,0);
	    ally.addSpecial(SpecialSkill.Luna);
	    ally.addSkill(PassiveSkillSlot.S, PassiveSkill.Slaying);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
	}
	
	@Test
	public void shouldAddWeaponSpecialDamage() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.DraconicAura);
	    ally.addSkill(PassiveSkillSlot.S, PassiveSkill.Wrathful);
	    ally.increaseCurrentSpecialCharge(3);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 23), "The combat result is inconsistant, 23 damage should have been dealt.");
	}
	
	@Test
	public void shouldAddMultipleWeaponSpecialDamage() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.DraconicAura);
	    ally.addSkill(PassiveSkillSlot.S, PassiveSkill.Wrathful);
	    ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Wrathful);
	    ally.increaseCurrentSpecialCharge(3);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 33), "The combat result is inconsistant, 33 damage should have been dealt.");
	}
	
	@Test
	public void shouldNotAddWrathSpecialDamageAboveThreshold() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.DraconicAura);
	    ally.addSkill(PassiveSkillSlot.B, PassiveSkill.Wrath);
	    ally.increaseCurrentSpecialCharge(3);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 13), "The combat result is inconsistant, 13 damage should have been dealt.");
	}

	@Test
	public void shouldAddWrathSpecialDamageBelowThreshold() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.DraconicAura);
	    ally.addSkill(PassiveSkillSlot.B, PassiveSkill.Wrath);
	    ally.increaseCurrentSpecialCharge(3);
	    ally.takeDamage(10);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 23), "The combat result is inconsistant, 23 damage should have been dealt.");
	}

	@Test
	public void shouldAddWrathSpecialDamageExactlyOnce() {
	    Unit ally = new Unit("Player", 40,10,5,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,10,0);
	    ally.addSpecial(SpecialSkill.Moonbow);
	    ally.addSkill(PassiveSkillSlot.B, PassiveSkill.Wrath);
	    ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Slaying);
	    ally.addSkill(PassiveSkillSlot.S, PassiveSkill.Slaying);
	    ally.takeDamage(10);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 13), "The combat result is inconsistant, 13 damage should have been dealt.");
	}

	@Test 
	public void shouldRoundDownWrathThreshold() {
	    Unit ally = new Unit("Player", 41,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.DraconicAura);
	    ally.addSkill(PassiveSkillSlot.B, PassiveSkill.Wrath);
	    ally.increaseCurrentSpecialCharge(3);
	    ally.takeDamage(10);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 13), "The combat result is inconsistant, 13 damage should have been dealt.");
	}
	
	@Test
	public void shouldAddWeaponAndWrathSpecialDamage() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.DraconicAura);
	    ally.addSkill(PassiveSkillSlot.S, PassiveSkill.Wrathful);
	    ally.addSkill(PassiveSkillSlot.B, PassiveSkill.Wrath);
	    ally.increaseCurrentSpecialCharge(3);
	    ally.takeDamage(10);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 33), "The combat result is inconsistant, 33 damage should have been dealt.");
	}

//Fighter (Bold/Vengeful/Special) Skills
	@Test
	public void shouldGetExtraChargeOnInitiateWhackBold() {
	    Unit ally = new Unit("Player", 40,10,0,10,0);
	    Unit enemy = new Unit("Sandbag", UnitType.CBow, 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.Bonfire);
	    ally.addSkill(PassiveSkillSlot.S, PassiveSkill.Slaying);
	    ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BoldFighter);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(2), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
	}
	
	@Test
	public void shouldNotGetExtraChargeOnInitiateSandbagBold() {
	    Unit ally = new Unit("Player", 40,10,0,10,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,10,0);
	    ally.addSpecial(SpecialSkill.Ignis);
	    ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BoldFighter);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 0), "The combat result is inconsistant, 0 damage should have been dealt.");
	}

	@Test
	public void shouldNotGetExtraChargeOnCounterBold() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,5,10,0);
	    enemy.addSpecial(SpecialSkill.Bonfire);
	    enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.BoldFighter);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(enemy, ally, 0), "The combat result is inconsistant, 0 damage should have been dealt.");
	}
	
	@Test
	public void shouldNotGetExtraChargeOnInitiateVengeful() {
	    Unit ally = new Unit("Player", 40,10,5,0,10);
	    Unit enemy = new Unit("Sandbag", 50,0,0,10,0);
	    ally.addSpecial(SpecialSkill.Iceberg);
	    ally.addSkill(PassiveSkillSlot.A, PassiveSkill.VengefulFighter);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 0), "The combat result is inconsistant, 0 damage should have been dealt.");
	}
	
	@Test
	public void shouldGetExtraChargeOnCounterAttackVengeful() {
	    Unit ally = new Unit("Player", 40,10,5,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,10);
	    enemy.addSpecial(SpecialSkill.Glacies);
	    enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.VengefulFighter);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(4), CombatStrings.DAMAGE(enemy, ally, 8), "The combat result is inconsistant, 8 damage should have been dealt.");
	}
	
	@Test
	public void shouldNotGetExtraChargeOnCounterSandbagVengeful() {
	    Unit ally = new Unit("Player", 40,10,5,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,10);
	    enemy.addSpecial(SpecialSkill.Glacies);
	    enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.VengefulFighter);
	    enemy.increaseCurrentSpecialCharge(-1);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(4), CombatStrings.DAMAGE(enemy, ally, 0), "The combat result is inconsistant, 0 damage should have been dealt.");
	}
	
	@Test
	public void shouldGetSpecialChargeOnInitiateWhackSpecial() {
	    Unit ally = new Unit("Player", 40,10,5,0,10);
	    Unit enemy = new Unit("Sandbag", UnitType.CBow, 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.Iceberg);
	    ally.addSkill(PassiveSkillSlot.S, PassiveSkill.Slaying);
	    ally.addSkill(PassiveSkillSlot.A, PassiveSkill.SpecialFighter);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(2), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
	}
	
	@Test
	public void shouldGetSpecialChargeOnInitiateSandbagSpecial() {
	    Unit ally = new Unit("Player", 40,10,5,0,10);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.Glacies);
	    ally.addSkill(PassiveSkillSlot.A, PassiveSkill.SpecialFighter);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 18), "The combat result is inconsistant, 18 damage should have been dealt.");
	}
	
	@Test
	public void shouldGetSpecialChargeOnBothCounterSpecial() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,5,0,10);
	    enemy.addSpecial(SpecialSkill.Glacies);
	    enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.SpecialFighter);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(enemy, ally, 8), "The combat result is inconsistant, 8 damage should have been dealt.");
	}

	@Test
	public void shouldDisableVengefulSpecialBonusUnderThreshold() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,5,10,10);
	    ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BoldFighter);
	    enemy.takeDamage(30);
	    enemy.addSpecial(SpecialSkill.Glacies);
	    enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.VengefulFighter);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(4), CombatStrings.DAMAGE(enemy, ally, 0), "The combat result is inconsistant, 0 damage should have been dealt.");
	}

	@Test
	public void shouldDisableSpecialFighterChargeUnderThreshold() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,5,0,10);
	    enemy.takeDamage(30);
	    enemy.addSpecial(SpecialSkill.Iceberg);
	    enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.SpecialFighter);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(enemy, ally, 0), "The combat result is inconsistant, 0 damage should have been dealt.");
	}

//Breath Skills
	@Test
	public void shouldGetSpecialChargeOnBothCounterSteady() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,5,0,10);
	    enemy.addSpecial(SpecialSkill.Glacies);
	    enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.SteadyBreath);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(enemy, ally, 8), "The combat result is inconsistant, 8 damage should have been dealt.");
	}
	
	@Test
	public void shouldGetSpecialChargeOnBothCounterWarding() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,5,10,0);
	    enemy.addSpecial(SpecialSkill.Ignis);
	    enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.WardingBreath);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(enemy, ally, 8), "The combat result is inconsistant, 8 damage should have been dealt.");
	}

	@Test
	public void shouldNotGetExtraChargeOnInitiateBreath() {
	    Unit ally = new Unit("Player", 40,10,5,0,10);
	    Unit enemy = new Unit("Sandbag", 50,0,0,10,0);
	    ally.addSpecial(SpecialSkill.Iceberg);
	    ally.addSkill(PassiveSkillSlot.A, PassiveSkill.SteadyBreath);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 0), "The combat result is inconsistant, 0 damage should have been dealt.");
	}

//Blade Skills
	@Test
	public void shouldGetExtraChargeOnBladeInitiateWhack() {
	    Unit ally = new Unit("Player", 40,10,5,0,0);
	    Unit enemy = new Unit("Sandbag", UnitType.CBow, 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.DraconicAura);
	    ally.addSkill(PassiveSkillSlot.S, PassiveSkill.Slaying);
	    ally.addSkill(PassiveSkillSlot.A, PassiveSkill.FlashingBlade);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(2), CombatStrings.DAMAGE(ally, enemy, 13), "The combat result is inconsistant, 13 damage should have been dealt.");
	}
	
	@Test
	public void shouldNotGetExtraChargeOnBladeInitiateSandbag() {
	    Unit ally = new Unit("Player", 40,10,5,10,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.Ignis);
	    ally.addSkill(PassiveSkillSlot.A, PassiveSkill.FlashingBlade);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
	}

	@Test
	public void shouldGetExtraChargeOnBladeCounterWhack() {
	    Unit ally = new Unit("Player", 40,10,5,0,0);
	    Unit enemy = new Unit("Sandbag", 50,20,0,10,0);
	    enemy.addSpecial(SpecialSkill.DragonFang);
	    enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.HeavyBlade);
	    enemy.addSkill(PassiveSkillSlot.B, PassiveSkill.QuickRiposte);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(4), CombatStrings.DAMAGE(enemy, ally, 30), "The combat result is inconsistant, 30 damage should have been dealt.");
	}
	
	@Test
	public void shouldNotGetExtraChargeOnBladeBelowThreshold() {
	    Unit ally = new Unit("Player", 40,10,5,0,0);
	    Unit enemy = new Unit("Sandbag", UnitType.CBow, 50,10,0,0,0);
	    ally.addSpecial(SpecialSkill.DraconicAura);
	    ally.addSkill(PassiveSkillSlot.S, PassiveSkill.Slaying);
	    ally.addSkill(PassiveSkillSlot.A, PassiveSkill.HeavyBlade);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(2), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
	}
	
//Guard Skill
	@Test
	public void shouldReduceEnemyChargeOnEngage() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,5,10,0);
	    ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Guard);
	    enemy.addSpecial(SpecialSkill.Bonfire);
	    enemy.addSkill(PassiveSkillSlot.S, PassiveSkill.Slaying);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(enemy, ally, 0), "The combat result is inconsistant, 0 damage should have been dealt.");
	}

	@Test
	public void shouldReduceEnemyChargeOnCounter() {
	    Unit ally = new Unit("Player", 40,10,5,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,10,0);
	    ally.addSpecial(SpecialSkill.Luna);
	    ally.addSkill(PassiveSkillSlot.S, PassiveSkill.Slaying);
	    enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.Guard);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 0), "The combat result is inconsistant, 0 damage should have been dealt.");
	}
	
	@Test
	public void shouldOnlyReduceChargeByOne() {
	    Unit ally = new Unit("Player", 40,10,5,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,10,0);
	    ally.addSpecial(SpecialSkill.Moonbow);
	    ally.addSkill(PassiveSkillSlot.S, PassiveSkill.Slaying);
	    ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BoldFighter);
	    enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.Guard);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 3), "The combat result is inconsistant, 3 damage should have been dealt.");
	}
	
	@Test
	public void shouldNotAffectOwnSpecialCharge() {
	    Unit ally = new Unit("Player", 40,10,5,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,10,0);
	    ally.addSpecial(SpecialSkill.Luna);
	    ally.addSkill(PassiveSkillSlot.S, PassiveSkill.Slaying);
	    ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Guard);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
	}

	@Test
	public void shouldNotStopSpecialsFromActivating() {
	    Unit ally = new Unit("Player", 40,10,5,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,10,0);
	    ally.addSpecial(SpecialSkill.Moonbow);
	    ally.addSkill(PassiveSkillSlot.S, PassiveSkill.Slaying);
	    ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Slaying);
	    enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.Guard);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 3), "The combat result is inconsistant, 3 damage should have been dealt.");
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 3), "The combat result is inconsistant, 3 damage should have been dealt.");
	}

}
