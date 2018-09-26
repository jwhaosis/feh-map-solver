package global.enums.weaponskills;

import java.util.ArrayList;
import java.util.Arrays;

import global.enums.passiveskills.PassiveSkill;
import global.enums.unitinfo.StatType;

public enum WeaponSkill {
	//Ranged (bows and tomes) mt can be equal to melee * 0.925 rounded down
	Default(0),
	Silver(15),
	
	Slaying(14),
	Wrathful(13),
	Brave(8),
	AntiArmor(12),
	AntiCavalry(12),
	Firesweep(15),
	Gem(12),
				
	
	
	placeholder(15);
	
	int might;
	
	StatType refine;
	ArrayList<Integer> refineBonus;
	ArrayList<PassiveSkill> weaponSkillList;
	
	WeaponSkill(int might, PassiveSkill ...skills){
		this.might = might;
		
		this.refine = StatType.None;
		this.refineBonus = new ArrayList<Integer>(Arrays.asList(5,3,3,4,4));
		this.weaponSkillList = new ArrayList<PassiveSkill>(Arrays.asList(skills));
	}
	
	public ArrayList<PassiveSkill> weaponPassiveSkills() {
		return weaponSkillList;
	}
	
}
