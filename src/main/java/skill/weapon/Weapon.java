package skill.weapon;


import java.util.ArrayList;
import java.util.List;

import global.enums.weaponskills.DamageType;
import global.enums.weaponskills.WeaponColor;
import global.enums.weaponskills.WeaponType;
import unit.Unit.MoveType;

public abstract class Weapon {//extends Skill {
		
	//weapon stats
	public final String name;
	final WeaponType weaponType;
	public final int[] weaponStats;
	
	//weapon effects
	//public final Skill skill;
	public final boolean allRangeCounter;
	public final List<MoveType> effectiveBonus;
	
	public Weapon(String name, WeaponType weaponType){
		this.name = name;
		this.weaponType = weaponType;
		this.weaponStats = new int[] {0,0,0,0};
		
		this.allRangeCounter = false;
		//this.skill = new DefaultSkill();
		this.effectiveBonus = new ArrayList<MoveType>();
	}
	
	public Weapon(String name, WeaponType weaponType, ArrayList<MoveType> effective){
		this.name = name;
		this.weaponType = weaponType;
		this.weaponStats = new int[] {0,0,0,0};
		
		this.allRangeCounter = false;
		//this.skill = new DefaultSkill();
		this.effectiveBonus = effective;
	}

	
	public WeaponType type() {
		return weaponType;
	}
	
	public DamageType damageType() {
		return weaponType.damageType;
	}
	
	public int range() {
		return weaponType.range;
	}

};
