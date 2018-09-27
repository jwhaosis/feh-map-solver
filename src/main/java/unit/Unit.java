package unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import global.enums.passiveskills.ActivationPhase;
import global.enums.passiveskills.PassiveSkill;
import global.enums.passiveskills.PassiveSkillSlot;
import global.enums.specialskills.SpecialSkill;
import global.enums.unitinfo.MoveType;
import global.enums.unitinfo.StatType;
import global.enums.unitinfo.UnitCombatInfo;
import global.enums.unitinfo.UnitType;
import global.enums.unitinfo.WeaponColor;
import global.enums.weaponskills.DamageType;
import global.enums.weaponskills.WeaponSkill;
import global.enums.weaponskills.WeaponType;

public class Unit {	
		
	public final boolean ally;
	
	public final String name;
	public final MoveType moveType;
	final ArrayList<Integer> baseStat;

	public final WeaponColor color;	
	WeaponType weaponType;
	WeaponSkill weapon;
	//Skill assist;
	SpecialSkill special;
	final ArrayList<PassiveSkill> unitSkillList;
	final ArrayList<Integer> unitSkillLevels;
	final int defaultSkillLevel = 3;

	int currentHealth;
	ArrayList<Integer> allyTurnFieldBonus;
	ArrayList<Integer> enemyTurnFieldBonus;
	ArrayList<Integer> combatBonus;
	ArrayList<UnitCombatInfo> combatInfo;
		
	int currentSpecialCharge = 0;
	
	public Unit(String name, MoveType move, UnitType type, int hp, int atk, int spd, int def, int res) {
		this.ally = true;
		
		unitSkillList = new ArrayList<PassiveSkill>();
		unitSkillLevels = new ArrayList<Integer>();
		for(int i = PassiveSkillSlot.A.index; i <= PassiveSkillSlot.S.index; i++) {
			unitSkillList.add(i, PassiveSkill.Default);
			unitSkillLevels.add(i, defaultSkillLevel);
		}

		this.name = name;
		this.moveType = move;
		
		this.color = type.color;
		this.weaponType = type.type;
		this.weapon(type.weapon);
		
		this.special = SpecialSkill.Default;
		allyTurnFieldBonus = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0));
		enemyTurnFieldBonus = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0));
		combatBonus = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0));
		combatInfo = new ArrayList<UnitCombatInfo>();
		baseStat = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0));
		this.currentHealth = hp;
		baseStat.set(StatType.Health.index, hp);
		baseStat.set(StatType.Attack.index, atk);
		baseStat.set(StatType.Speed.index, spd);
		baseStat.set(StatType.Defense.index, def);
		baseStat.set(StatType.Resistance.index, res);
	}
	
	public Unit(String name, UnitType type, int hp, int atk, int spd, int def, int res) {
		this(name, MoveType.Infantry, type, hp, atk, spd, def, res);
	}
	
	public Unit(String name, int hp, int atk, int spd, int def, int res) {
		this(name, MoveType.Infantry, UnitType.Default, hp, atk, spd, def, res);
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
		} else if(damageType == DamageType.Adaptive && this.weaponType.range == 2) {
			return ((getStat(StatType.Defense) < getStat(StatType.Resistance)) ? StatType.Defense : StatType.Resistance);
		} else /*if(whackerType == DamageType.Adaptive && sandbag.weapon().range() == 1)*/ {
			return StatType.Resistance;
		}
	}

	//setters for unit stats
	public int quickenSpecialChargeBy(boolean thisAttacking, ActivationPhase currentPhase) {
		UnitCombatInfo specialBoost;
		if(currentPhase == ActivationPhase.Initiate && thisAttacking) {
			specialBoost = UnitCombatInfo.chargeOnInitiateAttack;
		} else if (currentPhase == ActivationPhase.Initiate && !thisAttacking) {
			specialBoost = UnitCombatInfo.chargeOnInitiateDefend;
		} else if (currentPhase == ActivationPhase.Counter && thisAttacking) {
			specialBoost = UnitCombatInfo.chargeOnCounterAttack;
		} else if (currentPhase == ActivationPhase.Counter && !thisAttacking) {
			specialBoost = UnitCombatInfo.chargeOnCounterDefend;
		} else {
			specialBoost = null;
		}
		return Math.max(1, Collections.frequency(combatInfo, specialBoost));
	}
	
	public int slowEnemySpecialChargeBy() {
		return Collections.frequency(combatInfo, UnitCombatInfo.reduceChargeGeneration);
	}
	
	public int increaseCurrentSpecialCharge(int amount) {
		return currentSpecialCharge += amount;
	}
	
	public int specialDamageBoost() {
		while(combatInfo.remove(UnitCombatInfo.wrathDamageIncrease));
		for(int i = 0; i < unitSkillList.size(); i++) {
			if(unitSkillList.get(i) == PassiveSkill.Wrath) {
				PassiveSkill.Wrath.activateSkill(this, this, ActivationPhase.Both, unitSkillLevels.get(i));
			}
		}
		return (Collections.frequency(combatInfo, UnitCombatInfo.wrathDamageIncrease) + Collections.frequency(combatInfo, UnitCombatInfo.specialDamageIncrease)) * 10;
	}
	
	public int addCombatInfo(UnitCombatInfo ...info) {
		for(UnitCombatInfo property : info) {
			combatInfo.add(property);
		}
		return info.length;
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
	
	public WeaponType weaponType() {
		return weaponType;
	}
	public WeaponSkill weapon() {
		return weapon;
	}

	public Unit weapon(WeaponSkill weapon) {
		//unitSkillList.subList(PassiveSkillSlot.S.index + 1, unitSkillList.size()-1).clear();
		//unitSkillLevels.subList(PassiveSkillSlot.S.index + 1, unitSkillLevels.size()-1).clear();
		//this.unitSkillList.addAll(this.weapon().weaponPassiveSkills());
		//this.unitSkillLevels.addAll();

		this.weapon = weapon;
		return this;
	}

	
	public PassiveSkill aSlot() {
		return unitSkillList.get(PassiveSkillSlot.A.index);
	}
	
	//setters for unit skills
	public Unit addSkill(PassiveSkillSlot slot, PassiveSkill skill) {
		unitSkillList.set(slot.index, skill);
		unitSkillLevels.set(slot.index, defaultSkillLevel);
		return this;
	}
	public Unit addSkill(PassiveSkillSlot slot, PassiveSkill skill, int level) {
		unitSkillList.set(slot.index, skill);
		unitSkillLevels.set(slot.index, level);
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
		combatInfo.clear();
	}
	
	public void ownTurnStartClear() {
		allyTurnFieldBonus.replaceAll(bonus -> bonus*0);

	}
	
	public int increaseCombatBonus(int bonus, StatType stat) {
		combatBonus.set(stat.index, combatBonus.get(stat.index)+bonus);
		return bonus;
	}
	
	public void activateSkills(Unit enemy, ActivationPhase currentPhase) {
		for(int i = 0; i < unitSkillList.size(); i++) {
			unitSkillList.get(i).activateSkill(this, enemy, currentPhase, unitSkillLevels.get(i));
		}
	}
	
	public int activateSpecial(Unit enemy, boolean thisAttacking) {
		int specialDamage = special.activateSkill(this, enemy, thisAttacking, currentSpecialCharge + Collections.frequency(combatInfo, UnitCombatInfo.chargeTotalQuicken));
		if(specialDamage != -1) {
			currentSpecialCharge = 0;
			return specialDamage;
		} else {
			return 0;
			//return -1;
		}
	}

	public boolean isEffectiveAgainst(MoveType move) {
		if(move.isMoveType(MoveType.Infantry) && unitSkillList.contains(PassiveSkill.InfantryEffective) ) {
			return true;
		} else if(move.isMoveType(MoveType.Cavalry) && unitSkillList.contains(PassiveSkill.CavalryEffective) ) {
			return true;
		} else if(move.isMoveType(MoveType.Flier) && unitSkillList.contains(PassiveSkill.FlierEffective) ) {
			return true;
		} else if(move.isMoveType(MoveType.Armor) && unitSkillList.contains(PassiveSkill.ArmorEffective) ) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hasAllRangeCounter() {
		return unitSkillList.contains(PassiveSkill.AllRangeCounter);
	}
	
	public double triangleAdeptBonus() {
		int index = unitSkillList.lastIndexOf(PassiveSkill.TriangleAdept);
		if(index != -1) {
			return PassiveSkill.TriangleAdept.activateSkill(this, this, ActivationPhase.Both, unitSkillLevels.get(index)) / 100.0;
		} else {
			return 0;
		}
	}
	
	public boolean enableSweep(Unit enemy) {
		int index = enemy.weaponType.damageType.identifier == 1 ? unitSkillList.lastIndexOf(PassiveSkill.Windsweep) : unitSkillList.lastIndexOf(PassiveSkill.Watersweep);
		if(index != -1) {
			return unitSkillList.get(index).activateSkill(this, enemy, ActivationPhase.Initiate, unitSkillLevels.get(index)) == 0;
		} else {
			return false;
		}
	}
}
