package combatcalcs;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import formatting.CombatStrings;
import global.enums.ActivationPhase;
import global.enums.WeaponType;
import skill.weapon.DefaultWeapon;
import turn.combat.CombatAction;
import unit.Unit;

public class CombatActionTests {
	
	@Test
    public void shouldDealCorrectAmountofDamage() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	CombatAction testAction = new CombatAction(ally, enemy, new int[5], new int[5], ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }
	
	@Test
    public void shouldAppendOverkill() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 5,0,0,0,0);
    	CombatAction testAction = new CombatAction(ally, enemy, new int[5], new int[5], ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 5) + CombatStrings.OVERKILL(5), "The combat result is inconsistant, 5 damage and 5 overkill should have been dealt.");
    }
	
	@Test
	public void shouldCalculateTriangleAdvantage() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.weapon(new DefaultWeapon(WeaponType.Lance));
    	enemy.weapon(new DefaultWeapon(WeaponType.Sword));
    	CombatAction testAction = new CombatAction(ally, enemy, new int[5], new int[5], ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 12), "The combat result is inconsistant, 12 damage should have been dealt.");
	}
	
	@Test
	public void shouldCalculateTriangleDisadvantage() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.weapon(new DefaultWeapon(WeaponType.Axe));
    	enemy.weapon(new DefaultWeapon(WeaponType.Sword));
    	CombatAction testAction = new CombatAction(ally, enemy, new int[5], new int[5], ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 8), "The combat result is inconsistant, 8 damage should have been dealt.");
	}
	
	@Test 
	public void shouldIncludeAttackerBonus() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	CombatAction testAction = new CombatAction(ally, enemy, new int[] {0,5,0,0,0}, new int[5], ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
	}
	
	@Test 
	public void shouldIncludeDefenderBonus() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	CombatAction testAction = new CombatAction(ally, enemy, new int[5], new int[] {0,0,0,5,5}, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
	}
	
	@Test 
	public void shouldTargetDefWithPhysical() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,5,0);
    	CombatAction testAction = new CombatAction(ally, enemy, new int[5], new int[5], ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
	}

	@Test 
	public void shouldTargetResWithBreath() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,5,0);
    	ally.weapon(new DefaultWeapon(WeaponType.RBreath));
    	CombatAction testAction = new CombatAction(ally, enemy, new int[5], new int[5], ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
	}
	
	@Test 
	public void shouldTargetDefWhenRangedResHigher() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,5,10);
    	ally.weapon(new DefaultWeapon(WeaponType.RBreath));
    	enemy.weapon(new DefaultWeapon(WeaponType.Bow));
    	CombatAction testAction = new CombatAction(ally, enemy, new int[5], new int[5], ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
	}

	@Test 
	public void shouldTargetResWhenRangedDefHigher() {
    	Unit ally = new Unit("Player", 30,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,10,5);
    	ally.weapon(new DefaultWeapon(WeaponType.RBreath));
    	enemy.weapon(new DefaultWeapon(WeaponType.Bow));
    	CombatAction testAction = new CombatAction(ally, enemy, new int[5], new int[5], ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
	}
}
