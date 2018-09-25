package unit;

import java.util.ArrayList;
import java.util.Arrays;

import global.enums.passiveskills.ActivationPhase;
import global.enums.passiveskills.PassiveSkill;
import global.enums.passiveskills.PassiveSkillSlot;
import global.enums.specialskills.SpecialSkill;
import global.enums.unitinfo.StatType;
import global.enums.weaponskills.DamageType;
import global.enums.weaponskills.WeaponColor;
import skill.weapon.*;

public class Unit {	
	
	public enum MoveType {Infantry, Cavalry, Flier, Armor, Dragon_Infantry, Dragon_Flier, Dragon_Armor};
	
	public final boolean ally;
	
	public final String name;
	public final MoveType moveType;
	final ArrayList<Integer> baseStat;

	public final WeaponColor color;	
	Weapon weapon;
	//Skill assist;
	SpecialSkill special;
	final ArrayList<PassiveSkill> unitSkillList;
	final ArrayList<Integer> unitSkillLevels;
	public final int defaultSkillLevel = 3;

	int currentHealth;
	ArrayList<Integer> allyTurnFieldBonus;
	ArrayList<Integer> enemyTurnFieldBonus;
	ArrayList<Integer> combatBonus;
	
	public int numInitiateAttacks = 1;
	public int numCounterAttacks = 1;
	public int specialCdOnInitiateAttack = 1;
	public int specialCdOnInitiateDefend = 1;
	public int specialCdOnCounterAttack = 1;
	public int specialCdOnCounterDefend = 1;
	int currentSpecialCharge = 0;
	int bonusSpecialCharge = 0;
	int bonusSpecialDamage = 0;
	
	public Unit(String name, MoveType move, WeaponColor color, Weapon weapon, int hp, int atk, int spd, int def, int res) {
		this.ally = true;

		this.name = name;
		this.moveType = move;
		this.color = color;
		
		allyTurnFieldBonus = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0));
		enemyTurnFieldBonus = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0));
		combatBonus = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0));
		baseStat = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0));
		this.currentHealth = hp;
		baseStat.add(StatType.Health.index, hp);
		baseStat.add(StatType.Attack.index, atk);
		baseStat.add(StatType.Speed.index, spd);
		baseStat.add(StatType.Defense.index, def);
		baseStat.add(StatType.Resistance.index, res);
		
		unitSkillList = new ArrayList<PassiveSkill>();
		unitSkillLevels = new ArrayList<Integer>();
		for(int i = PassiveSkillSlot.A.index; i <= PassiveSkillSlot.S.index; i++) {
			unitSkillList.add(i, PassiveSkill.Default);
			unitSkillLevels.add(i, defaultSkillLevel);
		}
		
		this.weapon = new DefaultWeapon();
		this.special = SpecialSkill.Default;
	}
	
	public Unit(String name, int hp, int atk, int spd, int def, int res) {
		this(name, MoveType.Infantry, WeaponColor.Grey, new DefaultWeapon(), hp, atk, spd, def, res);
	}
	
	//Get and Set current health
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
	//TODO: add some way to initialize spurs/field buffs before combat bonuses are calculated
	public int getStat(StatType stat) {
		return baseStat.get(stat.index) + allyTurnFieldBonus.get(stat.index) + enemyTurnFieldBonus.get(stat.index) + combatBonus.get(stat.index);
	}
	
	public StatType targetDefensiveStat(DamageType damageType) {
		if(damageType == DamageType.Physical) {
			return StatType.Defense;
		} else if(damageType == DamageType.Magical) {
			return StatType.Resistance;
		} else if(damageType == DamageType.Adaptive && this.weapon().range() == 2) {
			return ((getStat(StatType.Defense) < getStat(StatType.Resistance)) ? StatType.Defense : StatType.Resistance);
		} else /*if(whackerType == DamageType.Adaptive && sandbag.weapon().range() == 1)*/ {
			return StatType.Resistance;
		}
	}

	//setters for unit stats
	public void increaseCurrentSpecialCharge(boolean thisAttacking, ActivationPhase currentPhase) {
		if(currentPhase == ActivationPhase.Initiate && thisAttacking) {
			currentSpecialCharge += specialCdOnInitiateAttack;
		} else if (currentPhase == ActivationPhase.Initiate && !thisAttacking) {
			currentSpecialCharge += specialCdOnInitiateDefend;
		} else if (currentPhase == ActivationPhase.Counter && thisAttacking) {
			currentSpecialCharge += specialCdOnCounterAttack;
		} else if (currentPhase == ActivationPhase.Counter && !thisAttacking) {
			currentSpecialCharge += specialCdOnCounterDefend;
		} else {
			currentSpecialCharge += 1;
		}
	}
	
	public int increaseBonusSpecialCharge() {
		return bonusSpecialCharge += 1;
	}
	
	public int increaseBonusSpecialDamage() {
		return bonusSpecialDamage += 10;
	}

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
	
	public int activateSpecial(Unit enemy, boolean thisAttacking) {
		int specialDamage = special.activateSkill(this, enemy, thisAttacking, currentSpecialCharge + bonusSpecialCharge);
		if(specialDamage != -1) {
			currentSpecialCharge = 0;
			return specialDamage;
		} else {
			return 0;
			//return -1;
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
		return unitSkillList.get(PassiveSkillSlot.A.index);
	}
	
	//setters for unit skills
	public Unit addSkill(PassiveSkillSlot slot, PassiveSkill skill) {
		unitSkillList.add(slot.index, skill);
		return this;
	}
	public Unit addSkill(PassiveSkillSlot slot, PassiveSkill skill, int level) {
		unitSkillList.add(slot.index, skill);
		unitSkillLevels.add(slot.index, level);
		return this;
	}
	
	public SpecialSkill getSpecial() {
		return special;
	}

	public Unit addSpecial(SpecialSkill skill) {
		special = skill;
		return this;
	}
	
	//TODO: organize these other methods
	public void ownTurnActionClear() {
		enemyTurnFieldBonus.replaceAll(bonus -> bonus*0);
		combatBonus.replaceAll(bonus -> bonus*0);
		numInitiateAttacks = 1;
		numCounterAttacks = 1;
		specialCdOnInitiateAttack = 1;
		specialCdOnInitiateDefend = 1;
		specialCdOnCounterAttack = 1;
		specialCdOnCounterDefend = 1;
		bonusSpecialCharge = 0;
		bonusSpecialDamage = 0;
	}
	
	public void ownTurnStartClear() {
		allyTurnFieldBonus.replaceAll(bonus -> bonus*0);

	}
	
	public int increaseCombatBonus(int bonus, StatType stat) {
		combatBonus.set(stat.index, combatBonus.get(stat.index)+bonus);
		return bonus;
	}
	
	public void activateSkills(Unit enemy, ActivationPhase currentPhase) {
		for(int i = 0; i < unitSkillList.size()-1; i++) {
			unitSkillList.get(i).activateSkill(this, enemy, currentPhase, unitSkillLevels.get(i));
		}
	}

}
