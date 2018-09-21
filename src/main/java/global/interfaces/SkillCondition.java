package global.interfaces;

import global.enums.StatType;
import map.Map;
import unit.Unit;

@FunctionalInterface
public interface SkillCondition<One, Two, Three, Four, Five, Output> {
	
    public Output apply(One one, Two two, Three three, Four four, Five five);
    
    public static void main(String[] args) throws Exception {
    	SkillCondition<Unit, Unit, StatType, Integer, Map, Boolean> func = (a, b, c, d, e) -> true;
    }
}