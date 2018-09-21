package skill.passive;

import global.enums.SkillType;
import skill.Skill;

public class DefaultSkill extends Skill{

	public DefaultSkill() {
		super(new int[] {0,0,0,0,0}, SkillType.Empty);
	}
	
	public DefaultSkill(SkillType type) {
		super(new int[] {0,0,0,0,0}, type);
	}
	
	public DefaultSkill(int[] bonus, SkillType type) {
		super(bonus, type);
	}

}
