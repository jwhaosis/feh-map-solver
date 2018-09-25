package unit;

import global.enums.weaponskills.WeaponColor;

public class LanceUnit extends Unit{

	public LanceUnit(String name, MoveType move, int hp, int atk, int spd, int def, int res) {
		super(name, move, WeaponColor.Blue, hp, atk, spd, def, res);
	}
	
	public LanceUnit(String name, int hp, int atk, int spd, int def, int res) {
		super(name, MoveType.Infantry, WeaponColor.Blue, hp, atk, spd, def, res);
	}

}