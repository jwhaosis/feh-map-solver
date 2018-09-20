import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import skill.weapon.Weapon;
import skill.passive.Stance;
import skill.weapon.Lance;
import skill.weapon.Sword;
import skill.weapon.Weapon.DamageType;
import skill.weapon.Weapon.WeaponColor;
import turn.combat.CombatActionQueue;
import unit.Unit;

public class CombatTests {

    @Test
    public void creationOfSwordShouldPopulateFields() {
    	String swordName = "Training Sword";
        Weapon testSword = new Sword(swordName);

        assertEquals(testSword.name, swordName, "Sword name must be " + swordName +".");
        assertEquals(testSword.color, WeaponColor.Red, "Sword color must be " + WeaponColor.Red +".");
        assertEquals(testSword.damageType, DamageType.Physical, "Sword damage type must be " + DamageType.Physical +".");
        assertEquals(testSword.engageRange, 1, "Sword range must be 1.");
    }
    
    @Test
    public void combatShouldFunction() {
    	Unit ally = new Unit("Player", 30,50,10,0,0);
    	Unit enemy = new Unit("Sandbag", 50,4,0,0,0);
    	ally.weapon(new Sword("Training Sword"));
    	enemy.weapon(new Lance("Training Lance"));
    	enemy.aSlot(new Stance(new int[]{6,0,0,0}));
    	CombatActionQueue testActions = new CombatActionQueue(ally, enemy);
    	LinkedList<String> testOutput = testActions.execute();
    	for(int i = 0; i < testOutput.size(); i++) {
    		System.out.println(testOutput.get(i));
    	}
		
    }
    
    @Test
    public void triangleSwordLanceIsCorrect() {
    	Unit ally = new Unit("Player", 10,10,10,20,20);
    	Unit enemy = new Unit("Sandbag", 10,20,10,10,10);
    	ally.weapon(new Sword("Training Sword"));
    	enemy.weapon(new Lance("Training Lance"));
    	CombatActionQueue testActions = new CombatActionQueue(ally, enemy);
    	LinkedList<String> testOutput = testActions.execute();
    	
    }
}
