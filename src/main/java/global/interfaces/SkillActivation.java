package global.interfaces;

@FunctionalInterface
public interface SkillActivation<One, Two, Output> {
	
    public Output apply(One one, Two two);
    
}