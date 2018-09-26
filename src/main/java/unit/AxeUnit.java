package unit;

import global.enums.unitinfo.MoveType;
import global.enums.unitinfo.WeaponColor;
import global.enums.weaponskills.WeaponSkill;
import global.enums.weaponskills.WeaponType;

public class AxeUnit extends Unit{

	public AxeUnit(String name, MoveType move, int hp, int atk, int spd, int def, int res) {
		super(name, move, WeaponColor.Green, WeaponType.Axe, WeaponSkill.Default, hp, atk, spd, def, res);
	}
	
	public AxeUnit(String name, int hp, int atk, int spd, int def, int res) {
		super(name, MoveType.Infantry, WeaponColor.Green, WeaponType.Axe, WeaponSkill.Default, hp, atk, spd, def, res);
	}

}