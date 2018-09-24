package unit;

import java.util.HashMap;
import java.util.Map;

import global.enums.skillinfo.ActivationPhase;
import global.enums.skillinfo.BaseSkill;
import global.enums.skillinfo.PassiveSkillType;
import global.enums.unitinfo.StatType;
import skill.weapon.*;

public class Unit {	
	
	public enum MoveType {Infantry, Cavalry, Flier, Armor, Dragon_Infantry, Dragon_Flier, Dragon_Armor};
	
	public final boolean ally;
	
	public final String name;
	public final MoveType moveType;
	
	final int[] baseStat;
	final Map<PassiveSkillType, BaseSkill> unitSkillList;
	final Map<PassiveSkillType, Integer> unitSkillLevels;
	public final int defaultSkillLevel = 3;
	Weapon weapon;
	//Skill assist;
	//Skill special;
	
	int currentHealth;
	int[] allyTurnFieldBonus;
	int[] enemyTurnFieldBonus;
	int[] combatBonus;
	
	public int numInitiateAttacks = 1;
	public int numCounterAttacks = 1;
	public int specialCdOnInitiateAttack = 1;
	public int specialCdOnInitiateDefend = 1;
	public int specialCdOnCounterAttack = 1;
	public int specialCdOnCounterDefend = 1;
		
	public Unit(String name, int health, int attack, int speed, int defense, int resistance) {

		this.ally = true;

		this.name = name;
		this.moveType = MoveType.Infantry;
		
		this.currentHealth = health;
		allyTurnFieldBonus = new int[5];
		enemyTurnFieldBonus = new int[5];
		combatBonus = new int[5];
		baseStat = new int[5];
		baseStat[StatType.Health.index] = health;
		baseStat[StatType.Attack.index] = attack;
		baseStat[StatType.Speed.index] = speed;
		baseStat[StatType.Defense.index] = defense;
		baseStat[StatType.Resistance.index] = resistance;
		unitSkillList = new HashMap<PassiveSkillType, BaseSkill>();
		unitSkillList.put(PassiveSkillType.A, BaseSkill.Default);
		unitSkillList.put(PassiveSkillType.B, BaseSkill.Default);
		unitSkillList.put(PassiveSkillType.C, BaseSkill.Default);
		unitSkillList.put(PassiveSkillType.S, BaseSkill.Default);
		unitSkillLevels = new HashMap<PassiveSkillType, Integer>();
		unitSkillLevels.put(PassiveSkillType.A, defaultSkillLevel);
		unitSkillLevels.put(PassiveSkillType.B, defaultSkillLevel);
		unitSkillLevels.put(PassiveSkillType.C, defaultSkillLevel);
		unitSkillLevels.put(PassiveSkillType.S, defaultSkillLevel);
		this.weapon = new DefaultWeapon();

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
	//TODO: Add combat and non-combat bonuses to the stat
	public int getStat(StatType stat) {
		return baseStat[stat.index] + allyTurnFieldBonus[stat.index] + enemyTurnFieldBonus[stat.index] + combatBonus[stat.index];
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

	
	public BaseSkill aSlot() {
		return unitSkillList.get(PassiveSkillType.A);
	}
	
	//Add a skill to a unit
	public Unit addSkill(PassiveSkillType slot, BaseSkill skill) {
		unitSkillList.put(slot, skill);
		return this;
	}
	public Unit addSkill(PassiveSkillType slot, BaseSkill skill, int level) {
		unitSkillList.put(slot, skill);
		unitSkillLevels.put(slot, level);
		return this;
	}
		
	public void ownTurnActionClear() {
		enemyTurnFieldBonus = new int[5];
		combatBonus = new int[5];
		numInitiateAttacks = 1;
		numCounterAttacks = 1;
		specialCdOnInitiateAttack = 1;
		specialCdOnInitiateDefend = 1;
		specialCdOnCounterAttack = 1;
		specialCdOnCounterDefend = 1;
	}
	
	public void ownTurnStartClear() {
		allyTurnFieldBonus = new int[5];
	}
	
	public int increaseCombatBonus(int bonus, StatType stat) {
		combatBonus[stat.index] += bonus;
		return bonus;
	}
	
	public void activateSkills(Unit enemy, ActivationPhase currentPhase) {
		for(PassiveSkillType slot : unitSkillList.keySet()) {
			unitSkillList.get(slot).activateSkill(this, enemy, currentPhase, Math.min(unitSkillLevels.get(slot), defaultSkillLevel));
		}
	}

}
