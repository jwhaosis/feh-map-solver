package combatcalcs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import formatting.CombatStrings;
import global.enums.passiveskills.PassiveSkill;
import global.enums.passiveskills.PassiveSkillSlot;
import global.enums.specialskills.SpecialSkill;
import global.enums.unitinfo.UnitCombatInfo;
import turn.combat.CombatActionQueue;
import unit.Unit;

public class CombatSpecialChangeSkillTests {
	
//Weapon and Wrath Special Damage Changes
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
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 23), "The combat result is inconsistant, 33 damage should have been dealt.");
	}

	@Test
	public void shouldAddWrathSpecialDamageExactlyOnce() {
	    Unit ally = new Unit("Player", 40,10,5,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
	    ally.addSpecial(SpecialSkill.Moonbow);
	    ally.addSkill(PassiveSkillSlot.B, PassiveSkill.Wrath);
	    ally.addCombatInfo(UnitCombatInfo.chargeTotalQuicken);
	    ally.addCombatInfo(UnitCombatInfo.chargeTotalQuicken);
	    ally.takeDamage(10);
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(3), CombatStrings.DAMAGE(ally, enemy, 23), "The combat result is inconsistant, 33 damage should have been dealt.");
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
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 33), "The combat result is inconsistant, 23 damage should have been dealt.");
	}
	

}
