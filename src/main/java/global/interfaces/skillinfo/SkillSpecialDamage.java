package global.interfaces.skillinfo;

@FunctionalInterface
public interface SkillSpecialDamage<Unit, Enemy, Output> {
	
    public Output apply(Unit one, Enemy two);
    
}