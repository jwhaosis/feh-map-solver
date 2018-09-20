package skill.weapon;


import java.util.ArrayList;
import java.util.List;

import skill.Skill;
import skill.passive.DefaultSkill;
import unit.Unit.MoveType;

public abstract class Weapon {
	
	public enum WeaponColor {Red, Blue, Green, Grey, None};
	public enum DamageType {Physical, Magical, Adaptive};
	
	//weapon stats
	public final String name;
	public final WeaponColor color;
	public final DamageType damageType;
	public final int[] weaponStats;
	public final int engageRange;
	
	//weapon effects
	public final Skill skill;
	public final boolean allRangeCounter;
	public final List<MoveType> effectiveBonus;
	
	public Weapon(String name, WeaponColor color, DamageType damageType, int engageRange){
		this.name = name;
		this.color = color;
		this.damageType = damageType;
		this.weaponStats = new int[] {0,0,0,0};
		this.engageRange = engageRange;
		
		this.allRangeCounter = false;
		this.skill = new DefaultSkill();
		this.effectiveBonus = new ArrayList<MoveType>();
	}
	
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
};
