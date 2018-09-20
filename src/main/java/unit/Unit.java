package unit;

import java.util.HashMap;
import java.util.Map;

import global.enums.ActivationPhase;
import global.enums.PassiveSkillType;
import global.enums.StatType;

import skill.Skill;
import skill.passive.DefaultSkill;
import skill.weapon.*;

public class Unit {	
	
	public enum MoveType {Infantry, Cavalry, Flier, Armor, Dragon_Infantry, Dragon_Flier, Dragon_Armor};
	
	public boolean ally;
	
	public String name;
	public MoveType moveType;
	
	int currentHealth;
	Map<StatType, Integer> baseStat;
	int[] allyTurnStats;
	int[] enemyTurnStats;
	Map<PassiveSkillType, Skill> unitSkills;
	Weapon weapon;
	Skill assist;
	Skill special;
		
	public Unit(String name, int health, int attack, int speed, int defense, int resistance) {
		this.name = name;
		this.currentHealth = health;
		
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
	private int getStat(StatType stat, ActivationPhase currentPhase) {
		return baseStat.get(stat) + activateSkills(stat, currentPhase);
	}
	
	public int attack(ActivationPhase currentPhase) {
		return getStat(StatType.Attack, currentPhase);
	}
	
	public int speed(ActivationPhase currentPhase) {
		return getStat(StatType.Speed, currentPhase);
	}
	
	public int getDefensiveStat() {
		return baseStat.get(StatType.Defense);
	}

	//setters, may remove later
	public Unit attack(int attack) {
		baseStat.put(StatType.Attack, attack);
		return this;
	}

	public Unit speed(int speed) {
		baseStat.put(StatType.Speed, speed);
		return this;
	}

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
	
	
	public int activateSkills(StatType stat, ActivationPhase currentPhase) {
		return unitSkills.get(PassiveSkillType.A).getStatBonus(stat, currentPhase)
				+ unitSkills.get(PassiveSkillType.B).getStatBonus(stat, currentPhase)
				+ unitSkills.get(PassiveSkillType.C).getStatBonus(stat, currentPhase)
				+ unitSkills.get(PassiveSkillType.S).getStatBonus(stat, currentPhase);
				//+weapon.getStatBonus();
	}
		
	public void ownTurnActionClear() {
		enemyTurnStats = new int[] {0,0,0,0};
	}
	
	public void ownTurnStartClear() {
		allyTurnStats = new int[] {0,0,0,0};
	}

}
