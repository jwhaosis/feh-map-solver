package unit;

import global.enums.weaponskills.WeaponColor;
import skill.weapon.DefaultWeapon;
import skill.weapon.Weapon;

public class SwordUnit extends Unit{

	public SwordUnit(String name, MoveType move, Weapon weapon, int hp, int atk, int spd, int def, int res) {
		super(name, move, WeaponColor.Red, weapon, hp, atk, spd, def, res);
	}

	public SwordUnit(String name, MoveType move, int hp, int atk, int spd, int def, int res) {
		super(name, move, WeaponColor.Red, new DefaultWeapon(), hp, atk, spd, def, res);
	}
	
	public SwordUnit(String name, int hp, int atk, int spd, int def, int res) {
		super(name, MoveType.Infantry, WeaponColor.Red, new DefaultWeapon(), hp, atk, spd, def, res);
	}

}