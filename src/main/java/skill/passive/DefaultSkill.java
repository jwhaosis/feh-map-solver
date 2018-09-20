package skill.passive;

import global.enums.ActivationPhase;
import skill.Skill;

public class DefaultSkill extends Skill{

	public DefaultSkill() {
		super(new int[] {0,0,0,0}, ActivationPhase.Both);
	}

}
