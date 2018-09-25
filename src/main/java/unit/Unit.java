package unit;

import java.util.HashMap;
import java.util.Map;

import global.enums.passiveskills.ActivationPhase;
import global.enums.passiveskills.PassiveSkill;
import global.enums.passiveskills.PassiveSkillSlot;
import global.enums.unitinfo.StatType;
import global.enums.weaponskills.WeaponColor;
import skill.weapon.*;

public class Unit {	
	
	public enum MoveType {Infantry, Cavalry, Flier, Armor, Dragon_Infantry, Dragon_Flier, Dragon_Armor};
	
	public final boolean ally;
	
	public final String name;
	public final MoveType moveType;
	public final WeaponColor color;
	
	final int[] baseStat;
	final Map<PassiveSkillSlot, PassiveSkill> unitSkillList;
	final Map<PassiveSkillSlot, Integer> unitSkillLevels;
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
		
	public Unit(String name, MoveType move, WeaponColor color, int[] stats) {
		this.ally = true;

		this.name = name;
		this.moveType = move;
		this.color = color;
		
		allyTurnFieldBonus = new int[5];
		enemyTurnFieldBonus = new int[5];
		combatBonus = new int[5];
		baseStat = new int[5];
		this.currentHealth = stats[0];
		baseStat[StatType.Health.index] = currentHealth;
		baseStat[StatType.Attack.index] = stats[1];
		baseStat[StatType.Speed.index] = stats[2];
		baseStat[StatType.Defense.index] = stats[3];
		baseStat[StatType.Resistance.index] = stats[4];
		
		unitSkillList = new HashMap<PassiveSkillSlot, PassiveSkill>();
		unitSkillList.put(PassiveSkillSlot.A, PassiveSkill.Default);
		unitSkillList.put(PassiveSkillSlot.B, PassiveSkill.Default);
		unitSkillList.put(PassiveSkillSlot.C, PassiveSkill.Default);
		unitSkillList.put(PassiveSkillSlot.S, PassiveSkill.Default);
		unitSkillLevels = new HashMap<PassiveSkillSlot, Integer>();
		unitSkillLevels.put(PassiveSkillSlot.A, defaultSkillLevel);
		unitSkillLevels.put(PassiveSkillSlot.B, defaultSkillLevel);
		unitSkillLevels.put(PassiveSkillSlot.C, defaultSkillLevel);
		unitSkillLevels.put(PassiveSkillSlot.S, defaultSkillLevel);
		this.weapon = new DefaultWeapon();
	}
	
	
	public Unit(String name, int[] stats) {
		this(name, MoveType.Infantry, WeaponColor.Grey, stats);
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
	
	//getters for unit stats
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

	//getters for unit skills
	//TODO: add owl skills in and maybe include the triangle advantage value here
	public WeaponColor weaponTriangleAdvantage() {
		if(color == WeaponColor.Red) {
			return WeaponColor.Green;
		} else if(color == WeaponColor.Blue) {
			return WeaponColor.Red;
		} else if(color == WeaponColor.Green) {
			return WeaponColor.Blue;
		} else {
			return WeaponColor.None;
		}
	}

	public Weapon weapon() {
		return this.weapon;
	}

	public Unit weapon(Weapon weapon) {
		this.weapon = weapon;
		return this;
	}

	
	public PassiveSkill aSlot() {
		return unitSkillList.get(PassiveSkillSlot.A);
	}
	
	//setters for unit skills
	public Unit addSkill(PassiveSkillSlot slot, PassiveSkill skill) {
		unitSkillList.put(slot, skill);
		return this;
	}
	public Unit addSkill(PassiveSkillSlot slot, PassiveSkill skill, int level) {
		unitSkillList.put(slot, skill);
		unitSkillLevels.put(slot, level);
		return this;
	}
		
	//TODO: organize these other methods
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
		for(PassiveSkillSlot slot : unitSkillList.keySet()) {
			unitSkillList.get(slot).activateSkill(this, enemy, currentPhase, Math.min(unitSkillLevels.get(slot), defaultSkillLevel));
		}
	}

}
