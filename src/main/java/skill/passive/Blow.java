package skill.passive;

import global.enums.ActivationPhase;
import skill.Skill;

public class Blow extends Skill {
	
	String name;
	int[] bonus;
	
	public Blow(int[] bonus) {
		super(bonus, ActivationPhase.Initiate);
	}
}
