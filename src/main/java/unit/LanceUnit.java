package unit;

import global.enums.weaponskills.WeaponColor;
import skill.weapon.DefaultWeapon;

public class LanceUnit extends Unit{

	public LanceUnit(String name, MoveType move, int hp, int atk, int spd, int def, int res) {
		super(name, move, WeaponColor.Blue, new DefaultWeapon(), hp, atk, spd, def, res);
	}
	
	public LanceUnit(String name, int hp, int atk, int spd, int def, int res) {
		super(name, MoveType.Infantry, WeaponColor.Blue, new DefaultWeapon(), hp, atk, spd, def, res);
	}

}