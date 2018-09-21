package unit;

import java.util.HashMap;
import java.util.Map;

import global.enums.PassiveSkillType;
import global.enums.StatType;

import skill.Skill;
import skill.passive.DefaultSkill;
import skill.weapon.*;

public class Unit {	
	
	public enum MoveType {Infantry, Cavalry, Flier, Armor, Dragon_Infantry, Dragon_Flier, Dragon_Armor};
	
	public final boolean ally;
	
	public final String name;
	public final MoveType moveType;
	
	final Map<StatType, Integer> baseStat;
	public final Map<PassiveSkillType, Skill> unitSkills;
	Weapon weapon;
	Skill assist;
	Skill special;
	
	int currentHealth;
	int[] allyTurnStats;
	int[] enemyTurnStats;
	int numInitiateAttacks = 1;
	int numCounterAttacks = 1;
	int specialCdOnInitiateAttack = 1;
	int specialCdOnInitiateDefend = 1;
	int specialCdOnCounterAttack = 1;
	int specialCdOnCounterDefend = 1;
		
	public Unit(String name, int health, int attack, int speed, int defense, int resistance) {
		this.name = name;
		this.currentHealth = health;
		
		this.ally = true;
		this.moveType = MoveType.Infantry;
		this.weapon = new DefaultWeapon();

		baseStat = new HashMap<StatType, Integer>();
		baseStat.put(StatType.Health, health);
		baseStat.put(StatType.Attack, attack);
		baseStat.put(StatType.Speed, speed);
		baseStat.put(StatType.Defense, defense);
		baseStat.put(StatType.Resistance, resistance);
		
		unitSkills = new HashMap<PassiveSkillType, Skill>();
		unitSkills.put(PassiveSkillType.A, new DefaultSkill());
		unitSkills.put(PassiveSkillType.B, new DefaultSkill());
		unitSkills.put(PassiveSkillType.C, new DefaultSkill());
		unitSkills.put(PassiveSkillType.S, new DefaultSkill());
	}
	
	public int currentHealth() {
		return currentHealth;
	}

	public Unit currentHealth(int health) {
		currentHealth = health;
		if(currentHealth < 0) {
			currentHealth = 0;
		}
		return this;
	}
	
	public int takeDamage(int damage) {
		if(currentHealth < damage) {
			int damageDealt = currentHealth;
			currentHealth = 0;
			return damageDealt;
		} else {
			currentHealth -= damage;
			return damage;
		}
	}
	
	//getters, use these to access unit stats
	public int getStat(StatType stat) {
		return baseStat.get(stat);
	}
	
	public StatType getAdaptiveDefense() {
		return ((getStat(StatType.Defense) < getStat(StatType.Resistance)) ? StatType.Defense : StatType.Resistance);
	}

	//setter using the builder syntax
	/*public Unit attack(int attack) {
		baseStat.put(StatType.Attack, attack);
		return this;
	}*/

	//unit skills
	public Weapon weapon() {
		return this.weapon;
	}

	public Unit weapon(Weapon weapon) {
		this.weapon = weapon;
		return this;
	}

	
	public Skill aSlot() {
		return unitSkills.get(PassiveSkillType.A);
	}
	
	public Unit aSlot(Skill aSlot) {
		unitSkills.put(PassiveSkillType.A, aSlot);
		return this;
	}
		
	public void ownTurnActionClear() {
		enemyTurnStats = new int[] {0,0,0,0};
		numInitiateAttacks = 1;
		numCounterAttacks = 1;
		specialCdOnInitiateAttack = 1;
		specialCdOnInitiateDefend = 1;
		specialCdOnCounterAttack = 1;
		specialCdOnCounterDefend = 1;
	}
	
	public void ownTurnStartClear() {
		allyTurnStats = new int[] {0,0,0,0};
	}

}
