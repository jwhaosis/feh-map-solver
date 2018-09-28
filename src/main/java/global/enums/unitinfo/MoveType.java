package global.enums.unitinfo;

public enum MoveType {
	Infantry(0), 
	Cavalry(1), 
	Flier(2), 
	Armor(3), 
	//Dragon alone should never be a movetype for a unit, but is used for effectiveness checks
	Dragon(4),
	Dragon_Infantry(4), 
	Dragon_Flier(4), 
	Dragon_Armor(4);
	
	public int id;
	
	MoveType(int id){
		this.id = id;
	}
	
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
	
	public boolean isMoveType(int id) {
		if(id == MoveType.Infantry.id) {
			return this == MoveType.Infantry || this == MoveType.Dragon_Infantry;
		} else if (id == MoveType.Cavalry.id) {
			return this == MoveType.Cavalry;
		} else if (id == MoveType.Flier.id) {
			return this == MoveType.Flier || this == MoveType.Dragon_Flier;
		} else if (id == MoveType.Armor.id) {
			return this == MoveType.Armor || this == MoveType.Dragon_Armor;
		} else if (id == MoveType.Dragon.id) {
			return this == MoveType.Dragon_Infantry || this == MoveType.Dragon_Flier || this == MoveType.Dragon_Armor;
		} else {
			return false;
		}
	}
}
