package skill.weapon;

public class DefaultWeapon extends Weapon{

	public DefaultWeapon() {
		super("Training Plank", WeaponColor.Grey, DamageType.Physical, 1);
	}
	
	public DefaultWeapon(WeaponColor color) {
		super("Training Plank", color, DamageType.Physical, 1);
	}
	
	public DefaultWeapon(int range) {
		super("Training Plank", WeaponColor.Grey, DamageType.Physical, range);
	}

}
