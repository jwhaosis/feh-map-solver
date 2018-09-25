package combatcalcs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import formatting.CombatStrings;
import global.enums.specialskills.SpecialSkill;
import global.enums.weaponskills.WeaponType;
import skill.weapon.DefaultWeapon;
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
	public void shouldUseOwnDefenseForBonfire() {
	    Unit ally = new Unit("Player", 40,10,0,10,0);
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
	public void shouldUseEnemyDefenseForPhysicalLuna() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,4,0);
	    ally.addSpecial(SpecialSkill.Luna);
	    for(int i = 0; i < 4; i++) {ally.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 8), "The combat result is inconsistant, 8 damage should have been dealt.");
	}
	
	@Test
	public void shouldUseEnemyResistanceForMagicalLuna() {
	    Unit ally = new Unit("Player", 40,10,0,0,0);
	    Unit enemy = new Unit("Sandbag", 50,0,0,0,4);
	    ally.addSpecial(SpecialSkill.Luna);
    	ally.weapon(new DefaultWeapon(WeaponType.Tome));
	    for(int i = 0; i < 4; i++) {ally.increaseBonusSpecialCharge();}
	    LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
	    assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 8), "The combat result is inconsistant, 8 damage should have been dealt.");
	}


}
