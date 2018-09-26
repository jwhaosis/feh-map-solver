package unit;

import global.enums.unitinfo.MoveType;
import global.enums.unitinfo.WeaponColor;
import global.enums.weaponskills.WeaponSkill;
import global.enums.weaponskills.WeaponType;

public class TomeUnit extends Unit{

	public TomeUnit(String name, MoveType move, WeaponColor color, WeaponSkill weapon, int hp, int atk, int spd, int def, int res) {
		super(name, move, color, WeaponType.Tome, weapon, hp, atk, spd, def, res);
	}

	public TomeUnit(String name, MoveType move, WeaponColor color, int hp, int atk, int spd, int def, int res) {
		super(name, move, color, WeaponType.Tome, WeaponSkill.Default, hp, atk, spd, def, res);
	}
	
	public TomeUnit(String name, int hp, int atk, int spd, int def, int res) {
		super(name, MoveType.Infantry, WeaponColor.Grey, WeaponType.Tome, WeaponSkill.Default, hp, atk, spd, def, res);
	}

}