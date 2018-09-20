package skill.weapon;

import global.enums.WeaponType;

public class DefaultWeapon extends Weapon{

	public DefaultWeapon() {
		super("Training Plank", WeaponType.Plank);
	}
	
	public DefaultWeapon(WeaponType type) {
		super("Training Weapon", type);
	}

}
