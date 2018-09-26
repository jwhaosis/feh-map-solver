package unit;

import global.enums.unitinfo.MoveType;
import global.enums.unitinfo.WeaponColor;
import global.enums.weaponskills.WeaponSkill;
import global.enums.weaponskills.WeaponType;

public class BowUnit extends Unit{

	public BowUnit(String name, MoveType move, WeaponSkill weapon, int hp, int atk, int spd, int def, int res) {
		super(name, move, WeaponColor.Grey, WeaponType.Bow, weapon, hp, atk, spd, def, res);
	}

	public BowUnit(String name, MoveType move, int hp, int atk, int spd, int def, int res) {
		super(name, move, WeaponColor.Grey, WeaponType.Bow, WeaponSkill.Default, hp, atk, spd, def, res);
	}
	
	public BowUnit(String name, int hp, int atk, int spd, int def, int res) {
		super(name, MoveType.Infantry, WeaponColor.Grey, WeaponType.Bow, WeaponSkill.Default, hp, atk, spd, def, res);
	}

}