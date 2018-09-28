package global.interfaces.skillinfo;

@FunctionalInterface
public interface SkillCondition<Unit, Enemy, Phase, Stat, Threshold, Qualifier, Output> {

    public Output apply(Unit one, Enemy two, Phase three, Stat four, Threshold five, Qualifier six);

}