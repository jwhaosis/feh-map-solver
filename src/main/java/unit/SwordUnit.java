package unit;

import global.enums.weaponskills.WeaponColor;

public class SwordUnit extends Unit{

	public SwordUnit(String name, MoveType move, int[] stats) {
		super(name, move, WeaponColor.Red, stats);
	}
}
