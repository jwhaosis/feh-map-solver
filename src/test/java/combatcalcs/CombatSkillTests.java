package combatcalcs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import formatting.CombatStrings;
import global.enums.skillinfo.BaseSkill;
import global.enums.skillinfo.PassiveSkillType;
import turn.combat.CombatActionQueue;
import unit.Unit;

public class CombatSkillTests {
	
	//Plus Skills
	@Test
    public void shouldApplyPlusSkillBonusOnInitiate() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.Atk);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 13), "The combat result is inconsistant, 13 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyPlusSkillBonusOnCounter() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillType.A, BaseSkill.Def);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 7), "The combat result is inconsistant, 7 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplySpeedBonus() {
    	Unit ally = new Unit("Player", 40,10,2,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.Spd);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
    }

//Blow Skills
	@Test
    public void shouldApplyBlowBonusOnInitiateAsWhacker() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.DeathBlow);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 16), "The combat result is inconsistant, 16 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBlowBonusOnInitiateAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.ArmoredBlow);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 4), "The combat result is inconsistant, 4 damage should have been dealt.");
    }

	@Test
    public void shouldDisableBlowBonusOnCounterAsWhacker() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillType.A, BaseSkill.DeathBlow);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 0), "The combat result is inconsistant, 0 damage should have been dealt.");
    }
	
	@Test
    public void shouldDisableBlowBonusOnCounterAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillType.A, BaseSkill.ArmoredBlow);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }

//Stance Skills
	@Test
    public void shouldDisableStanceBonusOnInitiateAsWhacker() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.FierceStance);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyStanceBonusOnInitiateAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.SturdyStance);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }

	@Test
    public void shouldApplyStanceBonusOnCounterAsWhacker() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillType.A, BaseSkill.FierceStance);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 6), "The combat result is inconsistant, 6 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyStanceBonusOnCounterAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillType.A, BaseSkill.SturdyStance);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 4), "The combat result is inconsistant, 4 damage should have been dealt.");
    }

	
//Brazen Skills
	@Test
    public void shouldDisableBrazenAboveThreshold() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.BrazenAtkSpd);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }
	
	@Test
    public void shouldRoundDownThresholdForBrazen() {
    	Unit ally = new Unit("Player", 41,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.BrazenAtkSpd);
    	ally.takeDamage(8);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }

	@Test
    public void shouldApplyBrazenBonusOnInitiateAsWhacker() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.BrazenAtkRes);
    	ally.takeDamage(8);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 17), "The combat result is inconsistant, 17 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBrazenBonusOnInitiateAttackAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.BrazenAtkDef);
    	ally.takeDamage(8);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 3), "The combat result is inconsistant, 3 damage should have been dealt.");
    }

	@Test
    public void shouldApplyBrazenBonusOnCounterAsWhacker() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillType.A, BaseSkill.BrazenAtkDef);
    	enemy.takeDamage(10);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 7), "The combat result is inconsistant, 7 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBrazenBonusOnCounterAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillType.A, BaseSkill.BrazenAtkDef);
    	enemy.takeDamage(10);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 3), "The combat result is inconsistant, 3 damage should have been dealt.");
    }

//Push Skills
	@Test
	public void shouldDisablePushBelowThreshold() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.AtkSpdPush);
    	ally.takeDamage(1);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
	}
	
	@Test
    public void shouldApplyPushBonusOnInitiate() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.AtkDefPush);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyPushBonusOnInitiateAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.AtkDefPush);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
    }

	@Test
    public void shouldApplyPushBonusOnCounterAsWhacker() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillType.A, BaseSkill.AtkDefPush);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyPushBonusOnCounterAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillType.A, BaseSkill.AtkDefPush);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
    }

//Boost Skills
	@Test
	public void shouldDisableBoostBelowThreshold() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.FireBoost);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
	}
	
	@Test
    public void shouldApplyBoostBonusOnExact() {
    	Unit ally = new Unit("Player", 53,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.FireBoost);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 16), "The combat result is inconsistant, 16 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBoostBonusWhileSelfDamaged() {
    	Unit ally = new Unit("Player", 55,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.FireBoost);
    	ally.takeDamage(2);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 16), "The combat result is inconsistant, 16 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBoostBonusWhileEnemyDamaged() {
    	Unit ally = new Unit("Player", 50,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.FireBoost);
    	enemy.takeDamage(5);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 16), "The combat result is inconsistant, 16 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBoostBonusWhileBothDamaged() {
    	Unit ally = new Unit("Player", 55,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.FireBoost);
    	ally.takeDamage(3);
    	enemy.takeDamage(3);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 16), "The combat result is inconsistant, 16 damage should have been dealt.");
    }

	@Test
    public void shouldDisableBoostWhileSelfDamagedBelowThreshold() {
    	Unit ally = new Unit("Player", 55,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.FireBoost);
    	ally.takeDamage(3);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }
	
	
	@Test
    public void shouldApplyBoostBonusOnInitiateAsWhacker() {
    	Unit ally = new Unit("Player", 55,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.FireBoost);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 16), "The combat result is inconsistant, 16 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBoostBonusOnInitiateAsSandBag() {
    	Unit ally = new Unit("Player", 55,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	ally.addSkill(PassiveSkillType.A, BaseSkill.EarthBoost);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 4), "The combat result is inconsistant, 4 damage should have been dealt.");
    }

	@Test
    public void shouldApplyBoostBonusOnCounterAsWhacker() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillType.A, BaseSkill.FireBoost);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 6), "The combat result is inconsistant, 6 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBoostBonusOnCounterAsSandBag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillType.A, BaseSkill.EarthBoost);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 4), "The combat result is inconsistant, 4 damage should have been dealt.");
    }

}
