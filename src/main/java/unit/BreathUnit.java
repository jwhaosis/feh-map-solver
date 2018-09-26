package unit;

import global.enums.unitinfo.MoveType;
import global.enums.unitinfo.WeaponColor;
import global.enums.weaponskills.WeaponSkill;
import global.enums.weaponskills.WeaponType;

public class BreathUnit extends Unit{

	public BreathUnit(String name, MoveType move, WeaponColor color, WeaponSkill weapon, int hp, int atk, int spd, int def, int res) {
		super(name, move, color, WeaponType.Breath, weapon, hp, atk, spd, def, res);
	}

	public BreathUnit(String name, MoveType move, WeaponColor color, int hp, int atk, int spd, int def, int res) {
		super(name, move, color, WeaponType.Breath, WeaponSkill.Default, hp, atk, spd, def, res);
	}
	
	public BreathUnit(String name, int hp, int atk, int spd, int def, int res) {
		super(name, MoveType.Infantry, WeaponColor.Grey, WeaponType.Breath, WeaponSkill.Default, hp, atk, spd, def, res);
	}

}