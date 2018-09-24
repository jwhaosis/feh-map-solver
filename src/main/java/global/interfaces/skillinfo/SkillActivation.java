package global.interfaces.skillinfo;

@FunctionalInterface
public interface SkillActivation<One, Two, Output> {
	
    public Output apply(One one, Two two);
    
}