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
    	Unit ally = new Unit("Player", 30,20,5,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 20), "The combat result is inconsistant, 20 damage should have been dealt.");
    }
	
	@Test
    public void shouldAppendOverkill() {
    	Unit ally = new Unit("Player", 30,20,5,0,0);
    	Unit enemy = new Unit("Sandbag", 5,10,0,0,0);
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 5) + CombatStrings.OVERKILL(15), "The combat result is inconsistant, 5 damage and 15 overkill should have been dealt.");
    }
	
	@Test
	public void shouldCalculateTriangleAdvantage() {
    	Unit ally = new Unit("Player", 30,20,5,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	ally.weapon(new DefaultWeapon(WeaponType.Lance));
    	enemy.weapon(new DefaultWeapon(WeaponType.Sword));
    	CombatAction testAction = new CombatAction(ally, enemy, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(ally, enemy, 24), "The combat result is inconsistant, 24 damage should have been dealt.");
	}
	
	@Test
	public void shouldCalculateTriangleDisadvantage() {
    	Unit ally = new Unit("Player", 30,20,5,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	ally.weapon(new DefaultWeapon(WeaponType.Lance));
    	enemy.weapon(new DefaultWeapon(WeaponType.Sword));
    	CombatAction testAction = new CombatAction(enemy, ally, ActivationPhase.Both);
    	assertEquals(testAction.execute(), CombatStrings.DAMAGE(enemy, ally, 8), "The combat result is inconsistant, 8 damage should have been dealt.");
	}
	
}
