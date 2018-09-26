package combatcalcs;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import formatting.CombatStrings;
import global.enums.unitinfo.UnitType;
import turn.combat.CombatActionQueue;
import unit.Unit;

public class CombatActionQueueTests {
	
    @Test
    public void shouldHaveCorrectNumberOfActions() {
    	Unit ally = new Unit("Player", 30,20,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 4, "Should be 4 actions in the queue.");
    }

    @Test
    public void shouldCalculateDoubleHits() {
    	Unit ally = new Unit("Player", 30,20,5,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 5, "Should be 5 actions in the queue.");
    }
    
    @Test
    public void shouldExitOnExactLethal() {
    	Unit ally = new Unit("Player", 10,20,5,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 4, "Should be 4 actions in the queue.");
    }

    @Test
    public void shouldExitOnOverkill() {
    	Unit ally = new Unit("Player", 1,20,5,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 4, "Should be 4 actions in the queue.");
    }
    
	@Test
	public void shouldNotAllowCounterAttackMeleeOnRanged() {
    	Unit ally = new Unit("Player", 30,20,0,0,0);
    	Unit enemy = new Unit("Sandbag", UnitType.CBow, 50,10,0,0,0);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 3, "Should be 3 actions in the queue.");
	}
	@Test
	public void shouldNotAllowCounterAttackRangedOnMelee() {
    	Unit ally = new Unit("Player", UnitType.CBow, 30,20,0,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.size(), 3, "Should be 3 actions in the queue.");
	}


    @Test
    public void shouldDisplayCombatStart() {
    	Unit ally = new Unit("Player", 30,20,5,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.remove(), CombatStrings.BEGIN(), "Should display " + CombatStrings.BEGIN());
    }

    @Test
    public void shouldDisplayCombatEnd() {
    	Unit ally = new Unit("Player", 30,20,5,0,0);
    	Unit enemy = new Unit("Sandbag", 50,10,0,0,0);
    	LinkedList<String> testOutput = new CombatActionQueue(ally, enemy).execute();
        assertEquals(testOutput.removeLast(), CombatStrings.END(), "Should display " + CombatStrings.END());
    }    
}
