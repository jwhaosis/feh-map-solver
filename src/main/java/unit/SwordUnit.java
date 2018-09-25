package unit;

import global.enums.weaponskills.WeaponColor;

public class SwordUnit extends Unit{

	public SwordUnit(String name, MoveType move, int hp, int atk, int spd, int def, int res) {
		super(name, move, WeaponColor.Red, hp, atk, spd, def, res);
	}
	
	public SwordUnit(String name, int hp, int atk, int spd, int def, int res) {
		super(name, MoveType.Infantry, WeaponColor.Red, hp, atk, spd, def, res);
	}

}