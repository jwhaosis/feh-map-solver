package global.enums.unitinfo;

public enum StatType {
	None(-1),
	Health(0),
	Attack(1),
	Speed(2),
	Defense(3),
	Resistance(4);
	
	public int index;
	
	StatType(int index) {
		this.index = index;
	}
}
