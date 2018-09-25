package unit;

import global.enums.weaponskills.WeaponColor;

public class AxeUnit extends Unit{

	public AxeUnit(String name, MoveType move, int hp, int atk, int spd, int def, int res) {
		super(name, move, WeaponColor.Green, hp, atk, spd, def, res);
	}
	
	public AxeUnit(String name, int hp, int atk, int spd, int def, int res) {
		super(name, MoveType.Infantry, WeaponColor.Green, hp, atk, spd, def, res);
	}

}