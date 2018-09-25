package global.interfaces.skillinfo;

@FunctionalInterface
public interface SkillActivation<Unit, Level, Output> {
	
    public Output apply(Unit one, Level two);
    
}