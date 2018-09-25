package global.interfaces.weaponinfo;

import global.enums.passiveskills.PassiveSkill;

public interface Weapon {
	
	
	enum Sword {
		Silver(15, PassiveSkill.Default);
		
		int might;

		Sword(int might, PassiveSkill temp) {
			this.might = might;
		}
	}
	
	enum Lance {
		Silver;
	}
	
	enum Axe {
		
	}
	
	enum Breath {
		
	}
	
	enum Bow {
		
	}
	
	enum Tome {
		
	}
	
	
}
