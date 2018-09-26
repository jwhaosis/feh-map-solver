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

public class CombatSpecialTests {
	
//In Combat Specials
	@Test
	public void shouldUseOwnAttackForDraconicAura() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.DraconicAura);
	    for(int i = 0; i < 4; i++) {ally.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 13), "The combat result is inconsistant, 13 damage should have been dealt.");
	}
	
	@Test
	public void shouldRoundDownForDraconicAura() {
	    Unit ally = new Unit("Player", 40,12,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.DraconicAura);
	    for(int i = 0; i < 4; i++) {ally.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
	}
	
	@Test
	public void shouldUseOwnDefenseForBonfire() {
	    Unit ally = new Unit("Player", 40,10,0,10,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.Bonfire);
	    for(int i = 0; i < 4; i++) {ally.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
	}
	
	@Test
	public void shouldRoundDownForBonfire() {
	    Unit ally = new Unit("Player", 40,10,0,11,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.Bonfire);
	    for(int i = 0; i < 4; i++) {ally.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
	}
	
	@Test
	public void shouldUseOwnResistanceForIceberg() {
	    Unit ally = new Unit("Player", 40,10,0,0,10);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.Iceberg);
	    for(int i = 0; i < 4; i++) {ally.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
	}
	
	@Test
	public void shouldRoundDownForIceberg() {
	    Unit ally = new Unit("Player", 40,10,0,0,11);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.Iceberg);
	    for(int i = 0; i < 4; i++) {ally.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
	}

	@Test
	public void shouldUseEnemyDefenseForPhysicalLuna() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,4,0);
	    ally.addSpecial(SpecialSkill.Luna);
	    for(int i = 0; i < 4; i++) {ally.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 8), "The combat result is inconsistant, 8 damage should have been dealt.");
	}
	
	@Test
	public void shouldRoundDownForPhysicalLuna() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,5,0);
	    ally.addSpecial(SpecialSkill.Luna);
	    for(int i = 0; i < 4; i++) {ally.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 7), "The combat result is inconsistant, 7 damage should have been dealt.");
	}

	@Test
	public void shouldUseEnemyResistanceForMagicalLuna() {
	    Unit ally = new Unit("Player", UnitType.CTome, 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,4);
	    ally.addSpecial(SpecialSkill.Luna);
	    for(int i = 0; i < 4; i++) {ally.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 8), "The combat result is inconsistant, 8 damage should have been dealt.");
	}
	
	@Test
	public void shouldRoundDownForMagicalLuna() {
	    Unit ally = new Unit("Player", UnitType.CTome, 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,5);
	    ally.addSpecial(SpecialSkill.Luna);
	    for(int i = 0; i < 4; i++) {ally.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 7), "The combat result is inconsistant, 7 damage should have been dealt.");
	}

	@Test
	public void shouldActivateWhenChargeIsFull() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.DraconicAura);
	    for(int i = 0; i < 3; i++) {ally.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 13), "The combat result is inconsistant, 13 damage should have been dealt.");
	}
	
	@Test
	public void shouldNotActivateWhenChargeIsLessThan() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.DraconicAura);
	    for(int i = 0; i < 1; i++) {ally.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
	}
	
	@Test
	public void shouldNotActivateWhenChargeIsEmpty() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.DraconicAura);
	    for(int i = 0; i < 0; i++) {ally.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
	}

	@Test
	public void shouldIncreaseChargeAndActivateWhenFull() {
	    Unit ally = new Unit("Player", 40,10,5,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.DraconicAura);
	    for(int i = 0; i < 1; i++) {ally.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 13), "The combat result is inconsistant, 13 damage should have been dealt.");
	}

	@Test
	public void shouldActivateOnCounterAttack() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
	    enemy.addSpecial(SpecialSkill.DraconicAura);
	    for(int i = 0; i < 3; i++) {enemy.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 13), "The combat result is inconsistant, 13 damage should have been dealt.");
	}
	
	@Test
	public void shouldBeIncludedInMitigatedDamage() {
	    Unit ally = new Unit("Player", 40,20,0,10,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,22,0);
	    ally.addSpecial(SpecialSkill.Bonfire);
	    for(int i = 0; i < 4; i++) {ally.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 3), "The combat result is inconsistant, 3 damage should have been dealt.");
	}
	
	@Test
	public void shouldNotBeAffectedByTriangleAdvantage() {
	    Unit ally = new Unit("Player", UnitType.Lance, 40,10,0,0,20);
	    Unit enemy = new Unit("Sandbag", UnitType.Sword, 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.Iceberg);
	    for(int i = 0; i < 4; i++) {ally.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 22), "The combat result is inconsistant, 22 damage should have been dealt.");
	}

	@Test
	public void shouldNotBeAffectedByTriangleDisadvantage() {
	    Unit ally = new Unit("Player", UnitType.Axe, 40,10,0,0,20);
	    Unit enemy = new Unit("Sandbag", UnitType.Sword, 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.Iceberg);
	    for(int i = 0; i < 4; i++) {ally.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 18), "The combat result is inconsistant, 18 damage should have been dealt.");
	}
	
	@Test
	public void shouldNotBeAffectedByEffectivenessDamage() {
	    Unit ally = new Unit("Player", 40,10,0,0,20);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.Iceberg);
    	ally.addSkill(PassiveSkillSlot.S, PassiveSkill.InfantryEffective);
	    for(int i = 0; i < 4; i++) {ally.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 25), "The combat result is inconsistant, 25 damage should have been dealt.");
	}

}
