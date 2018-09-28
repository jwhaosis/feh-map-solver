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

public class CombatStatIncreaseSkillTests {

//Plus Skills
	@Test
    public void shouldApplyPlusSkillBonusOnInitiate() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Atk);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 13), "The combat result is inconsistant, 13 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyPlusSkillDefenseBonusOnCounter() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.Def);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 7), "The combat result is inconsistant, 7 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyPlusSkillResistanceBonusOnCounter() {
    	Unit ally = new Unit("Player", UnitType.RBreath, 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.Res);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 7), "The combat result is inconsistant, 7 damage should have been dealt.");
    }

	@Test
    public void shouldApplySpeedBonus() {
    	Unit ally = new Unit("Player", 40,10,2,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Spd);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
    }

//Basic Functionality Checks
	@Test
	public void shouldRecognizeASlotSkill() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Atk);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 13), "The combat result is inconsistant, 13 damage should have been dealt.");
	}

	@Test
	public void shouldRecognizeBSlotSkill() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.B, PassiveSkill.Atk);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 13), "The combat result is inconsistant, 13 damage should have been dealt.");
	}

	@Test
	public void shouldRecognizeCSlotSkill() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.C, PassiveSkill.Atk);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 13), "The combat result is inconsistant, 13 damage should have been dealt.");
	}

	@Test
	public void shouldRecognizeSSlotSkill() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.S, PassiveSkill.Atk);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 13), "The combat result is inconsistant, 13 damage should have been dealt.");
	}

	@Test
	public void shouldBeAbleToHaveMultipleSkills() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.Atk);
    	ally.addSkill(PassiveSkillSlot.B, PassiveSkill.Atk);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 16), "The combat result is inconsistant, 16 damage should have been dealt.");
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
	
//Blow Skills
	@Test
    public void shouldApplyBlowBonusOnInitiateAsWhacker() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.DeathBlow);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 16), "The combat result is inconsistant, 16 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBlowSpeedBonus() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.DartingBlow);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
    }

	@Test
    public void shouldApplyDefenseBlowBonusOnInitiateAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.ArmoredBlow);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 4), "The combat result is inconsistant, 4 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyResistanceBlowBonusOnInitiateAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", UnitType.RBreath, 50,10,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.WardingBlow);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 4), "The combat result is inconsistant, 4 damage should have been dealt.");
    }

	@Test
    public void shouldDisableBlowBonusOnCounterAsWhacker() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.DeathBlow);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 0), "The combat result is inconsistant, 0 damage should have been dealt.");
    }
	
	@Test
    public void shouldDisableBlowBonusOnCounterAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.ArmoredBlow);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }

//Stance and Breath Skills
	@Test
    public void shouldApplyStanceSpeedBonus() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.DartingStance);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
    }

	@Test
    public void shouldDisableStanceBonusOnInitiateAsWhacker() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.FierceStance);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }
	
	@Test
    public void shouldDisableStanceBonusOnInitiateAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.SturdyStance);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyStanceBonusOnCounterAsWhacker() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.FierceStance);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 6), "The combat result is inconsistant, 6 damage should have been dealt.");
    }
		
	@Test
    public void shouldApplyStanceDefenseBonusOnCounterAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.SturdyStance);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 4), "The combat result is inconsistant, 4 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBreathDefenseBonusOnCounterAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.SteadyBreath);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 6), "The combat result is inconsistant, 6 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyStanceResistanceBonusOnCounterAsSandbag() {
    	Unit ally = new Unit("Player", UnitType.RBreath, 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.WardingStance);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 4), "The combat result is inconsistant, 4 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBreathResistanceBonusOnCounterAsSandbag() {
    	Unit ally = new Unit("Player", UnitType.RBreath, 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.WardingBreath);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 6), "The combat result is inconsistant, 6 damage should have been dealt.");
    }

//Defense Skills
	@Test
    public void shouldDisableDefenseBonusOnInitiateAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.CloseDefense);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyDefenseBonusOnCounterAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.CloseDefense);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 4), "The combat result is inconsistant, 4 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyDistantDefenseVsRanged() {
    	Unit ally = new Unit("Player", UnitType.CBow, 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.DistantDefense);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 4), "The combat result is inconsistant, 4 damage should have been dealt.");
    }

	@Test
    public void shouldDisableCloseDefenseBonusVsRanged() {
    	Unit ally = new Unit("Player", UnitType.CBow, 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.CloseDefense);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }
	
	@Test
    public void shouldDisableDistantDefenseBonusVsMelee() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.DistantDefense);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }

//Brazen Skills
	@Test
    public void shouldDisableBrazenAboveThreshold() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BrazenAtkSpd);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }
	
	@Test
    public void shouldRoundDownThresholdForBrazen() {
    	Unit ally = new Unit("Player", 41,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BrazenAtkSpd);
    	ally.takeDamage(8);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }

	@Test
    public void shouldApplyBrazenSpeedBonus() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BrazenSpdRes);
    	ally.takeDamage(8);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
    }

	@Test
    public void shouldApplyBrazenBonusOnInitiateAsWhacker() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BrazenAtkRes);
    	ally.takeDamage(8);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 17), "The combat result is inconsistant, 17 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBrazenBonusOnInitiateAttackAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.BrazenAtkDef);
    	ally.takeDamage(8);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 3), "The combat result is inconsistant, 3 damage should have been dealt.");
    }

	@Test
    public void shouldApplyBrazenBonusOnCounterAsWhacker() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.BrazenAtkSpd);
    	enemy.takeDamage(10);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 7), "The combat result is inconsistant, 7 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBrazenBonusOnCounterAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.BrazenSpdDef);
    	enemy.takeDamage(10);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 3), "The combat result is inconsistant, 3 damage should have been dealt.");
    }
	
//Push Skills
	@Test
	public void shouldDisablePushBelowThreshold() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.AtkSpdPush);
    	ally.takeDamage(1);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
	}
	
	@Test
    public void shouldApplyPushBonusOnInitiate() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.AtkSpdPush);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 15), "The combat result is inconsistant, 15 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyPushSpeedBonus() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.SpdDefPush);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
    }
	
	@Test
    public void shouldApplyPushBonusOnInitiateAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.AtkDefPush);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
    }

	@Test
    public void shouldApplyPushBonusOnCounterAsWhacker() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.AtkResPush);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyPushBonusOnCounterAsSandbag() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.DefResPush);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 5), "The combat result is inconsistant, 5 damage should have been dealt.");
    }

//Boost Skills
	@Test
	public void shouldDisableBoostBelowThreshold() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.FireBoost);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
	}
	
	@Test
    public void shouldApplyBoostBonusOnExact() {
    	Unit ally = new Unit("Player", 53,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.FireBoost);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 16), "The combat result is inconsistant, 16 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBoostBonusWhileSelfDamaged() {
    	Unit ally = new Unit("Player", 55,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.FireBoost);
    	ally.takeDamage(2);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 16), "The combat result is inconsistant, 16 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBoostBonusWhileEnemyDamaged() {
    	Unit ally = new Unit("Player", 50,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.FireBoost);
    	enemy.takeDamage(5);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 16), "The combat result is inconsistant, 16 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBoostBonusWhileBothDamaged() {
    	Unit ally = new Unit("Player", 55,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.FireBoost);
    	ally.takeDamage(3);
    	enemy.takeDamage(3);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 16), "The combat result is inconsistant, 16 damage should have been dealt.");
    }

	@Test
    public void shouldDisableBoostWhileSelfDamagedBelowThreshold() {
    	Unit ally = new Unit("Player", 55,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.FireBoost);
    	ally.takeDamage(3);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 10), "The combat result is inconsistant, 10 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBoostSpeedBonus() {
    	Unit ally = new Unit("Player", 55,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.WindBoost);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
    }
	
	@Test
    public void shouldApplyBoostBonusOnInitiateAsWhacker() {
    	Unit ally = new Unit("Player", 55,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.FireBoost);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 16), "The combat result is inconsistant, 16 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBoostBonusOnInitiateAsSandBag() {
    	Unit ally = new Unit("Player", 55,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	ally.addSkill(PassiveSkillSlot.A, PassiveSkill.EarthBoost);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 4), "The combat result is inconsistant, 4 damage should have been dealt.");
    }

	@Test
    public void shouldApplyBoostBonusOnCounterAsWhacker() {
    	Unit ally = new Unit("Player", 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.FireBoost);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(2), CombatStrings.DAMAGE(enemy, ally, 6), "The combat result is inconsistant, 6 damage should have been dealt.");
    }
	
	@Test
    public void shouldApplyBoostBonusOnCounterAsSandBag() {
    	Unit ally = new Unit("Player", UnitType.CBreath, 40,10,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,0,0,0,0);
    	enemy.addSkill(PassiveSkillSlot.A, PassiveSkill.WaterBoost);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.get(1), CombatStrings.DAMAGE(ally, enemy, 4), "The combat result is inconsistant, 4 damage should have been dealt.");
    }

}
