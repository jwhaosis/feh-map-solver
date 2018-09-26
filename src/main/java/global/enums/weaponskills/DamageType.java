package global.enums.weaponskills;

public enum DamageType {
	Physical(1), 
	Magical(2), 
	Adaptive(2);
	
	public int identifier;
	
	DamageType(int identifier) {
		this.identifier = identifier;
	}
};
