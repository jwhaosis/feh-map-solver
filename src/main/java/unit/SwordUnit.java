package unit;

import global.enums.unitinfo.MoveType;
import global.enums.unitinfo.WeaponColor;
import global.enums.weaponskills.WeaponSkill;
import global.enums.weaponskills.WeaponType;

public class SwordUnit extends Unit{

	public SwordUnit(String name, MoveType move, WeaponSkill weapon, int hp, int atk, int spd, int def, int res) {
		super(name, move, WeaponColor.Red, WeaponType.Sword, weapon, hp, atk, spd, def, res);
	}

	public SwordUnit(String name, MoveType move, int hp, int atk, int spd, int def, int res) {
		super(name, move, WeaponColor.Red, WeaponType.Sword, WeaponSkill.Default, hp, atk, spd, def, res);
	}
	
	public SwordUnit(String name, int hp, int atk, int spd, int def, int res) {
		super(name, MoveType.Infantry, WeaponColor.Red, WeaponType.Sword, WeaponSkill.Default, hp, atk, spd, def, res);
	}

}