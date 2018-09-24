package skill.weapon;

import java.util.ArrayList;

import global.enums.unitinfo.WeaponType;
import unit.Unit.MoveType;

public class DefaultWeapon extends Weapon{

	public DefaultWeapon() {
		super("Training Plank", WeaponType.Plank);
	}
	
	public DefaultWeapon(WeaponType type) {
		super("Training Weapon", type);
	}
	
	public DefaultWeapon(WeaponType type, ArrayList<MoveType> effective) {
		super("Training Weapon", type, effective);
	}
	

}
