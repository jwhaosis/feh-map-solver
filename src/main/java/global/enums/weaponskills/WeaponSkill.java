package global.enums.weaponskills;

import global.enums.unitinfo.StatType;

public enum WeaponSkill {
	Silver(15, new WeaponType[] {WeaponType.Sword, WeaponType.Lance, WeaponType.Axe}),
	
	
	/*Slaying(14, new WeaponType[] {WeaponType.Sword, WeaponType.Lance, WeaponType.Axe}),
	Wrathful,
	Brave,
	AntiArmor,
	AntiCavalry,
	Firesweep,
	Gem,
	
	Safeguard,
	Barrier,
	
	Seasonal,
	
	LightningBreath,*/
	
		
	
	
	placeholder(15, new WeaponType[] {WeaponType.Sword, WeaponType.Lance, WeaponType.Axe});
	
	WeaponType[] restrictions;
	int might;
	
	StatType refine;
	int[] refineBonus;
	
	WeaponSkill(int might, WeaponType[] restrictions){
		this.might = might;
		this.restrictions = restrictions;
		
		this.refine = StatType.None;
		this.refineBonus = new int[] {5,3,3,4,4};
	}
}
