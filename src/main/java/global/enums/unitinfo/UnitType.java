package global.enums.unitinfo;

import global.enums.weaponskills.WeaponSkill;
import global.enums.weaponskills.WeaponType;

public enum UnitType {
	Default(WeaponColor.Grey, WeaponType.Plank, WeaponSkill.Default),
	
	Sword(WeaponColor.Red, WeaponType.Sword, WeaponSkill.Default),
	Lance(WeaponColor.Blue, WeaponType.Lance, WeaponSkill.Default),
	Axe(WeaponColor.Green, WeaponType.Axe, WeaponSkill.Default),
	
	RBreath(WeaponColor.Red, WeaponType.Breath, WeaponSkill.Default),
	BBreath(WeaponColor.Blue, WeaponType.Breath, WeaponSkill.Default),
	GBreath(WeaponColor.Green, WeaponType.Breath, WeaponSkill.Default),
	
	RBow(WeaponColor.Red, WeaponType.Bow, WeaponSkill.Default),
	BBow(WeaponColor.Blue, WeaponType.Bow, WeaponSkill.Default),
	GBow(WeaponColor.Green, WeaponType.Bow, WeaponSkill.Default),
	
	RDagger(WeaponColor.Red, WeaponType.Dagger, WeaponSkill.Default),
	BDagger(WeaponColor.Blue, WeaponType.Dagger, WeaponSkill.Default),
	GDagger(WeaponColor.Green, WeaponType.Dagger, WeaponSkill.Default),
	
	RTome(WeaponColor.Red, WeaponType.Tome, WeaponSkill.Default),
	BTome(WeaponColor.Blue, WeaponType.Tome, WeaponSkill.Default),
	GTome(WeaponColor.Green, WeaponType.Tome, WeaponSkill.Default),
	
	CBreath(WeaponColor.Grey, WeaponType.Breath, WeaponSkill.Default),
	CBow(WeaponColor.Grey, WeaponType.Bow, WeaponSkill.Default),
	CDagger(WeaponColor.Grey, WeaponType.Dagger, WeaponSkill.Default),
	//doesn't exist but is here
	CTome(WeaponColor.Grey, WeaponType.Tome, WeaponSkill.Default),
	
	Staff(WeaponColor.Grey, WeaponType.Staff, WeaponSkill.Default);
	
	
	public WeaponColor color;
	public WeaponType type;
	public WeaponSkill weapon;
	
	UnitType(WeaponColor color, WeaponType type, WeaponSkill weapon){
		this.color = color;
		this.type = type;
		this.weapon = weapon;
	}
}
