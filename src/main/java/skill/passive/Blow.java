package skill.passive;

import global.enums.SkillType;
import skill.Skill;

public class Blow extends Skill {
	
	String name;
	int[] bonus;
	
	public Blow(int[] bonus) {
		super(bonus, SkillType.Blow);
	}
}
