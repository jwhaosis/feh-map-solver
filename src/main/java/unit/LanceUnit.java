package unit;

import global.enums.unitinfo.MoveType;
import global.enums.unitinfo.WeaponColor;
import global.enums.weaponskills.WeaponSkill;
import global.enums.weaponskills.WeaponType;

public class LanceUnit extends Unit{

	public LanceUnit(String name, MoveType move, int hp, int atk, int spd, int def, int res) {
		super(name, move, WeaponColor.Blue, WeaponType.Lance, WeaponSkill.Default, hp, atk, spd, def, res);
	}
	
	public LanceUnit(String name, int hp, int atk, int spd, int def, int res) {
		super(name, MoveType.Infantry, WeaponColor.Blue, WeaponType.Lance, WeaponSkill.Default, hp, atk, spd, def, res);
	}

}