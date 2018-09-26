package global.enums.unitinfo;

public enum MoveType {
	Infantry, 
	Cavalry, 
	Flier, 
	Armor, 
	Dragon,
	Dragon_Infantry, 
	Dragon_Flier, 
	Dragon_Armor;
	
	public boolean isMoveType(MoveType type) {
		if(type == MoveType.Infantry) {
			return this == MoveType.Infantry || this == MoveType.Dragon_Infantry;
		} else if (type == MoveType.Cavalry) {
			return this == MoveType.Cavalry;
		} else if (type == MoveType.Flier) {
			return this == MoveType.Flier || this == MoveType.Dragon_Flier;
		} else if (type == MoveType.Armor) {
			return this == MoveType.Armor || this == MoveType.Dragon_Armor;
		} else if (type == MoveType.Dragon) {
			return this == MoveType.Dragon_Infantry || this == MoveType.Dragon_Flier || this == MoveType.Dragon_Armor;
		} else {
			return false;
		}
	}
	
}
