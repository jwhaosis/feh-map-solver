package combatcalcs;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import formatting.CombatStrings;
import global.enums.passiveskills.ActivationPhase;
import global.enums.weaponskills.WeaponType;
import skill.weapon.DefaultWeapon;
import turn.combat.CombatAction;
import unit.AxeUnit;
import unit.LanceUnit;
import unit.SwordUnit;
import unit.Unit;
import unit.Unit.MoveType;

public class CombatActionTests {
	
//Basic Calculations and String Verification
	@Test
    public void shouldDealCorrectAmountofDamage() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }
	
	@Test
    public void shouldAppendOverkill() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 5,0,0,0,0);
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 5) + CombatStrings.OVERKILL(5), "The combat result is inconsistant, 5 damage and 5 overkill should have been dealt.");
    }
	
//Triangle Advantage and Effectiveness Weapons
	@Test
	public void shouldCalculateTriangleAdvantage() {
    	Unit ally = new LanceUnit("Player", 40,10,0,0,0);
    	Unit enemy = new SwordUnit("Sandbag", 50,0,0,0,0);
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 12), "The combat result is inconsistant, 12 damage should have been dealt.");
	}
	
	@Test
	public void shouldCalculateTriangleDisadvantage() {
    	Unit ally = new AxeUnit("Player", 40,10,0,0,0);
    	Unit enemy = new SwordUnit("Sandbag", 50,0,0,0,0);
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 8), "The combat result is inconsistant, 8 damage should have been dealt.");
	}
	
	@Test
	public void shouldRoundDownTriangleAdvantage() {
    	Unit ally = new LanceUnit("Player", 40,13,0,0,0);
    	Unit enemy = new SwordUnit("Sandbag", 50,0,0,0,0);
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
	}
	
	@Test
	public void shouldRoundUpTriangleDisadvantage() {
    	Unit ally = new AxeUnit("Player", 40,13,0,0,0);
    	Unit enemy = new SwordUnit("Sandbag", 50,0,0,0,0);
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 11), "The combat result is inconsistant, 11 damage should have been dealt.");
	}
	
	@Test
	public void shouldCalculateEffectiveDamage() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.weapon(new DefaultWeapon(WeaponType.Plank, new ArrayList<MoveType>() {{add(MoveType.Infantry);}}));
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
	}

	@Test
	public void shouldRoundDownEffectiveDamage() {
    	Unit ally = new Unit("Player", 40,11,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.weapon(new DefaultWeapon(WeaponType.Plank, new ArrayList<MoveType>() {{add(MoveType.Infantry);}}));
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 16), "The combat result is inconsistant, 16 damage should have been dealt.");
	}
	
	@Test
	public void shouldCalculateEffectiveAndTriangleAdvantage() {
    	Unit ally = new LanceUnit("Player", 40,10,0,0,0);
    	Unit enemy = new SwordUnit("Sandbag", 50,0,0,0,0);
    	ally.weapon(new DefaultWeapon(WeaponType.Lance, new ArrayList<MoveType>() {{add(MoveType.Infantry);}}));
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 18), "The combat result is inconsistant, 18 damage should have been dealt.");
	}
	
	@Test
	public void shouldCalculateEffectiveAndTriangleDisadvantage() {
    	Unit ally = new AxeUnit("Player", 40,10,0,0,0);
    	Unit enemy = new SwordUnit("Sandbag", 50,0,0,0,0);
    	ally.weapon(new DefaultWeapon(WeaponType.Axe, new ArrayList<MoveType>() {{add(MoveType.Infantry);}}));
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 12), "The combat result is inconsistant, 12 damage should have been dealt.");
	}
	
	@Test
	public void shouldRoundEffectiveAndTriangleAdvantageSeperately() {
    	Unit ally = new LanceUnit("Player", 40,13,0,0,0);
    	Unit enemy = new SwordUnit("Sandbag", 50,0,0,0,0);
    	ally.weapon(new DefaultWeapon(WeaponType.Lance, new ArrayList<MoveType>() {{add(MoveType.Infantry);}}));
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 22), "The combat result is inconsistant, 22 damage should have been dealt.");
	}

	@Test
	public void shouldRoundEffectiveAndTriangleDisadvantageSeperately() {
    	Unit ally = new AxeUnit("Player", 40,13,0,0,0);
    	Unit enemy = new SwordUnit("Sandbag", 50,0,0,0,0);
    	ally.weapon(new DefaultWeapon(WeaponType.Axe, new ArrayList<MoveType>() {{add(MoveType.Infantry);}}));
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 16), "The combat result is inconsistant, 16 damage should have been dealt.");
	}

//Invisible bonuses
	/*@Test 
	public void shouldIncludeAttackerBonus() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
	}
	
	@Test 
	public void shouldIncludeDefenderBonus() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
	}*/
	
//Weapon defensive stat targeting
	@Test 
	public void shouldTargetDefWithPhysical() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,5,0);
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
	}

	@Test 
	public void shouldTargetResWithMagical() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,5,0);
    	ally.weapon(new DefaultWeapon(WeaponType.Tome));
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
	}
	
	@Test 
	public void shouldTargetResWithBreath() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,5,0);
    	ally.weapon(new DefaultWeapon(WeaponType.Breath));
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
	}
	
	@Test 
	public void shouldTargetDefWhenRangedResHigher() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,5,10);
    	ally.weapon(new DefaultWeapon(WeaponType.Breath));
    	enemy.weapon(new DefaultWeapon(WeaponType.Bow));
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
	}

	@Test 
	public void shouldTargetResWhenRangedDefHigher() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,10,5);
    	ally.weapon(new DefaultWeapon(WeaponType.Breath));
    	enemy.weapon(new DefaultWeapon(WeaponType.Bow));
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
	}
}
