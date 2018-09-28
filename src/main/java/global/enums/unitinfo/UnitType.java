package global.enums.unitinfo;

import global.enums.weaponskills.WeaponSkill;
import global.enums.weaponskills.WeaponType;

public enum UnitType {
	Default(0, WeaponColor.Grey, WeaponType.Plank, WeaponSkill.Default),
	
	Sword(1, WeaponColor.Red, WeaponType.Sword, WeaponSkill.Default),
	Lance(2, WeaponColor.Blue, WeaponType.Lance, WeaponSkill.Default),
	Axe(3, WeaponColor.Green, WeaponType.Axe, WeaponSkill.Default),
	
	RBreath(4, WeaponColor.Red, WeaponType.Breath, WeaponSkill.Default),
	BBreath(5, WeaponColor.Blue, WeaponType.Breath, WeaponSkill.Default),
	GBreath(6, WeaponColor.Green, WeaponType.Breath, WeaponSkill.Default),
	
	RBow(7, WeaponColor.Red, WeaponType.Bow, WeaponSkill.Default),
	BBow(8, WeaponColor.Blue, WeaponType.Bow, WeaponSkill.Default),
	GBow(9, WeaponColor.Green, WeaponType.Bow, WeaponSkill.Default),
	
	RDagger(10, WeaponColor.Red, WeaponType.Dagger, WeaponSkill.Default),
	BDagger(11, WeaponColor.Blue, WeaponType.Dagger, WeaponSkill.Default),
	GDagger(12, WeaponColor.Green, WeaponType.Dagger, WeaponSkill.Default),
	
	RTome(13, WeaponColor.Red, WeaponType.Tome, WeaponSkill.Default),
	BTome(14, WeaponColor.Blue, WeaponType.Tome, WeaponSkill.Default),
	GTome(15, WeaponColor.Green, WeaponType.Tome, WeaponSkill.Default),
	
	CBreath(16, WeaponColor.Grey, WeaponType.Breath, WeaponSkill.Default),
	CBow(17, WeaponColor.Grey, WeaponType.Bow, WeaponSkill.Default),
	CDagger(18, WeaponColor.Grey, WeaponType.Dagger, WeaponSkill.Default),

	Staff(19, WeaponColor.Grey, WeaponType.Staff, WeaponSkill.Default),

	//doesn't exist but is here
	CTome(-1, WeaponColor.Grey, WeaponType.Tome, WeaponSkill.Default);
	
	
	public int id;
	public WeaponColor color;
	public WeaponType weaponType;
	public WeaponSkill weapon;
	
	UnitType(int id, WeaponColor color, WeaponType weaponType, WeaponSkill weapon){
		this.id = id;
		this.color = color;
		this.weaponType = weaponType;
		this.weapon = weapon;
	}
}
