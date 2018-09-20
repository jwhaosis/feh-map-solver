package skill.passive;

import global.enums.ActivationPhase;
import skill.Skill;

public class Stance extends Skill {
	
	public Stance(int[] bonus) {
		super(bonus, ActivationPhase.Counter);
	}

}
