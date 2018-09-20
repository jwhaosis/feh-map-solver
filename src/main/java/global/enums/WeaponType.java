package global.enums;

public enum WeaponType {
	
	Plank(DamageType.Physical, WeaponColor.Grey, 1),

	Sword(DamageType.Physical, WeaponColor.Red, 1),
	Lance(DamageType.Physical, WeaponColor.Blue, 1),
	Axe(DamageType.Physical, WeaponColor.Green, 1),
	
	RBreath(DamageType.Adaptive, WeaponColor.Red, 1),
	BBreath(DamageType.Adaptive,WeaponColor.Blue, 1),
	GBreath(DamageType.Adaptive,WeaponColor.Green, 1),
	
	RTome(DamageType.Magical, WeaponColor.Red, 2),
	BTome(DamageType.Magical, WeaponColor.Blue, 2),
	GTome(DamageType.Magical, WeaponColor.Green, 2),
	
	RBow(DamageType.Physical, WeaponColor.Red, 2),
	BBow(DamageType.Physical, WeaponColor.Blue, 2),
	GBow(DamageType.Physical, WeaponColor.Green, 2),

	RDagger(DamageType.Physical, WeaponColor.Red, 2),
	BDagger(DamageType.Physical, WeaponColor.Blue, 2),
	GDagger(DamageType.Physical, WeaponColor.Green, 2),
	
	Bow(DamageType.Physical, WeaponColor.Grey, 2),
	Dagger(DamageType.Physical, WeaponColor.Grey, 2),
	Staff(DamageType.Physical, WeaponColor.Grey, 2);
	
	public final DamageType damageType;
	public final WeaponColor color; 
	public final int range;
	
	private WeaponType(DamageType damageType, WeaponColor color, int range) { 
		this.damageType = damageType;
		this.color = color; 
		this.range = range;
	}
		
	public WeaponColor weaponTriangleAdvantage() {
		if(color == WeaponColor.Red) {
			return WeaponColor.Green;
		} else if(color == WeaponColor.Blue) {
			return WeaponColor.Red;
		} else if(color == WeaponColor.Green) {
			return WeaponColor.Blue;
		} else {
			return WeaponColor.None;
		}
	}

}
