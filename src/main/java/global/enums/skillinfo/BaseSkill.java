package global.enums.skillinfo;

import global.enums.unitinfo.StatType;
import global.interfaces.skillinfo.SkillActivation;
import unit.Unit;

public enum BaseSkill {
	
	Default((unit,level) -> 0, SkillType.Empty),
	
	//Static Plus
	Hp((unit, level) -> unit.increaseCombatBonus(2 + level, StatType.Health), SkillType.Plus),
	Atk((unit, level) -> unit.increaseCombatBonus(level, StatType.Attack), SkillType.Plus),
	Spd((unit, level) -> unit.increaseCombatBonus(level, StatType.Speed), SkillType.Plus),
	Def((unit, level) -> unit.increaseCombatBonus(level, StatType.Defense), SkillType.Plus),
	Res((unit, level) -> unit.increaseCombatBonus(level, StatType.Resistance), SkillType.Plus),

	//Blows, Stances, and Defenses
	DeathBlow((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Attack), SkillType.Blow),
	DartingBlow((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Speed), SkillType.Blow),
	ArmoredBlow((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Defense), SkillType.Blow),
	WardingBlow((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Resistance), SkillType.Blow),
	FierceStance((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Attack), SkillType.Stance),
	DartingStance((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Speed), SkillType.Stance),
	SturdyStance((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Defense), SkillType.Stance),
	WardingStance((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Resistance), SkillType.Stance),
	CloseDefense((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Defense) + unit.increaseCombatBonus(2*level, StatType.Resistance), SkillType.Defense, StatType.Health, new int[] {1,1,1}),
	DistantDefense((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Defense) + unit.increaseCombatBonus(2*level, StatType.Resistance), SkillType.Defense, StatType.Health, new int[] {1,1,1}),
	
	//Brazens
	BrazenAtkSpd((unit, level) -> unit.increaseCombatBonus(1 + 2*level, StatType.Attack) + unit.increaseCombatBonus(1 + 2*level, StatType.Speed), SkillType.Brazen, StatType.Health, new int[] {80,80,80}),
	BrazenAtkDef((unit, level) -> unit.increaseCombatBonus(1 + 2*level, StatType.Attack) + unit.increaseCombatBonus(1 + 2*level, StatType.Defense), SkillType.Brazen, StatType.Health, new int[] {80,80,80}),
	BrazenAtkRes((unit, level) -> unit.increaseCombatBonus(1 + 2*level, StatType.Attack) + unit.increaseCombatBonus(1 + 2*level, StatType.Resistance), SkillType.Brazen, StatType.Health, new int[] {80,80,80}),
	BrazenSpdDef((unit, level) -> unit.increaseCombatBonus(1 + 2*level, StatType.Speed) + unit.increaseCombatBonus(1 + 2*level, StatType.Defense), SkillType.Brazen, StatType.Health, new int[] {80,80,80}),
	BrazenSpdRes((unit, level) -> unit.increaseCombatBonus(1 + 2*level, StatType.Speed) + unit.increaseCombatBonus(1 + 2*level, StatType.Resistance), SkillType.Brazen, StatType.Health, new int[] {80,80,80}),
	BrazenDefRes((unit, level) -> unit.increaseCombatBonus(1 + 2*level, StatType.Defense) + unit.increaseCombatBonus(1 + 2*level, StatType.Resistance), SkillType.Brazen, StatType.Health, new int[] {80,80,80}),

	//Pushes
	AtkSpdPush((unit, level) -> unit.increaseCombatBonus(2 + level, StatType.Attack) + unit.increaseCombatBonus(2 + level, StatType.Speed), SkillType.Push),
	AtkDefPush((unit, level) -> unit.increaseCombatBonus(2 + level, StatType.Attack) + unit.increaseCombatBonus(2 + level, StatType.Defense), SkillType.Push),
	AtkResPush((unit, level) -> unit.increaseCombatBonus(2 + level, StatType.Attack) + unit.increaseCombatBonus(2 + level, StatType.Resistance), SkillType.Push),
	SpdDefPush((unit, level) -> unit.increaseCombatBonus(2 + level, StatType.Speed) + unit.increaseCombatBonus(2 + level, StatType.Defense), SkillType.Push),
	SpdResPush((unit, level) -> unit.increaseCombatBonus(2 + level, StatType.Speed) + unit.increaseCombatBonus(2 + level, StatType.Resistance), SkillType.Push),
	DefResPush((unit, level) -> unit.increaseCombatBonus(2 + level, StatType.Defense) + unit.increaseCombatBonus(2 + level, StatType.Resistance), SkillType.Push),

	//Boosts and Blades
	FireBoost((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Attack), SkillType.Boost, StatType.Health, new int[] {3,3,3}),
	WindBoost((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Speed), SkillType.Boost, StatType.Health, new int[] {3,3,3}),
	EarthBoost((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Defense), SkillType.Boost, StatType.Health, new int[] {3,3,3}),
	WaterBoost((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Resistance), SkillType.Boost, StatType.Health, new int[] {3,3,3}),

	HeavyBlade((unit, level) -> {unit.specialCdOnInitiateAttack = 2; unit.specialCdOnCounterAttack = 2; return 1;}, SkillType.Blade, StatType.Attack, new int[] {5,3,1}),
	FlashingBlade((unit, level) -> {unit.specialCdOnInitiateAttack = 2; unit.specialCdOnCounterAttack = 2; return 1;}, SkillType.Blade, StatType.Speed, new int[] {5,3,1}),

	//Special Charge Altering Skills
	SteadyBreath((unit,level) -> {unit.specialCdOnCounterAttack = 2; unit.specialCdOnCounterDefend = 2; return unit.increaseCombatBonus(4, StatType.Defense);}, SkillType.Stance),
	WardingBreath((unit,level) -> {unit.specialCdOnCounterAttack = 2; unit.specialCdOnCounterDefend = 2; return unit.increaseCombatBonus(4, StatType.Resistance);}, SkillType.Stance),
	VengefulFighter((unit,level) -> {unit.numCounterAttacks = 2; unit.specialCdOnCounterAttack = 2; return 1;}, SkillType.Undamaged, StatType.Health, new int[] {90,70,50}),
	BoldFighter((unit,level) -> {unit.numInitiateAttacks = 2; unit.specialCdOnInitiateAttack = 2; return 1;}, SkillType.Undamaged, StatType.Health, new int[] {100,50,0});

	SkillActivation<Unit, Integer, Integer> skillEffect;
	SkillType type;
	StatType stat;
	int[] threshold;
	
	BaseSkill(SkillActivation<Unit, Integer, Integer> activation, SkillType type){
		this(activation, type, StatType.None, new int[] {0,0,0});
	}
	
	BaseSkill(SkillActivation<Unit, Integer, Integer> activation, SkillType type, StatType stat, int[] threshold){
		this.skillEffect = activation;
		this.type = type;
		this.stat = stat;
		this.threshold = threshold;
	}

	
	public int activateSkill(Unit unit, Unit enemy, ActivationPhase currentPhase, int level) {
		if(type.isActive(unit, enemy, currentPhase, stat, threshold[level - 1])) {
			return skillEffect.apply(unit, level);
		} else {
			return -1;
		}
	}
}
