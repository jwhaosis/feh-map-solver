package global.interfaces.skillinfo;

@FunctionalInterface
public interface SkillCondition<One, Two, Three, Four, Five, Output> {

    public Output apply(One one, Two two, Three three, Four four, Five five);

}
