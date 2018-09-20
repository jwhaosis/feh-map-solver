package unit;

import global.enums.ActivationPhase;
import skill.Skill;
import skill.passive.DefaultSkill;
import skill.weapon.*;

public class Unit {	
	
	public enum MoveType {Infantry, Cavalry, Flier, Armor, Dragon_Infantry, Dragon_Flier, Dragon_Armor};
	
	public boolean ally;
	
	public String name;
	public MoveType moveType;
	
	int currentHealth;
	int maxHealth;
	int[] baseStat;
	int[] allyTurnStats;
	int[] enemyTurnStats;
	Weapon weapon;
	Skill assist;
	Skill special;
	Skill aSlot;
	Skill bSlot;
	Skill cSlot;
	Skill sSlot;
		
	public Unit(String name, int maxHealth, int attack, int speed, int defense, int resistance) {
		this.name = name;
		
		baseStat = new int[] {attack, speed, defense, resistance};
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
		
		this.weapon = new DefaultWeapon();
		this.aSlot = new DefaultSkill();
		this.bSlot = new DefaultSkill();
		this.cSlot = new DefaultSkill();
		this.sSlot = new DefaultSkill();
	}
	
	public int currentHealth() {
		return this.currentHealth;
	}

	public Unit currentHealth(int health) {
		this.currentHealth = health;
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
	
	public int attack(ActivationPhase currentPhase) {
		return baseStat[0]+activateSkills(0, currentPhase);
	}
	public Unit attack(int attack) {
		baseStat[0] = attack;
		return this;
	}
	
	public int speed(ActivationPhase currentPhase) {
		return baseStat[1]+activateSkills(1, currentPhase);
	}
	public Unit speed(int speed) {
		baseStat[1] = speed;
		return this;
	}

	public Weapon weapon() {
		return this.weapon;
	}

	public Unit weapon(Weapon weapon) {
		this.weapon = weapon;
		return this;
	}

	
	public Skill aSlot() {
		return this.aSlot;
	}
	
	public Unit aSlot(Skill aSlot) {
		this.aSlot = aSlot;
		return this;
	}
	
	
	public int activateSkills(int statIndex, ActivationPhase currentPhase) {
		return aSlot.getStatBonus(statIndex, currentPhase)+bSlot.getStatBonus(statIndex, currentPhase)+cSlot.getStatBonus(statIndex, currentPhase)+sSlot.getStatBonus(statIndex, currentPhase);//+weapon.getStatBonus(statIndex);
	}
	
	public int getDefensiveStat() {
		return baseStat[2];
	}
	
	public void ownTurnActionClear() {
		enemyTurnStats = new int[] {0,0,0,0};
	}
	
	public void ownTurnStartClear() {
		allyTurnStats = new int[] {0,0,0,0};
	}

}
