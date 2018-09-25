package global.enums.weaponskills;

public enum WeaponType {
	
	None(DamageType.Physical, 0),
	Plank(DamageType.Physical, 1),
	Sword(DamageType.Physical, 1),
	Lance(DamageType.Physical, 1),
	Axe(DamageType.Physical, 1),
	Breath(DamageType.Adaptive, 1),
	Tome(DamageType.Magical, 2),	
	Bow(DamageType.Physical, 2),
	Dagger(DamageType.Physical, 2),
	Staff(DamageType.Physical, 2);
	
	public final DamageType damageType;
	public final int range;
	
	private WeaponType(DamageType damageType, int range) { 
		this.damageType = damageType;
		this.range = range;
	}
}
