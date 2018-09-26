package global.enums.passiveskills;

import global.enums.unitinfo.StatType;
import global.interfaces.skillinfo.SkillActivation;
import unit.Unit;

public enum PassiveSkill {
	
	Default((unit,level) -> 0, PassiveSkillType.Empty),
	
	//Static Plus
	Hp((unit, level) -> unit.increaseCombatBonus(2 + level, StatType.Health), PassiveSkillType.Plus),
	Atk((unit, level) -> unit.increaseCombatBonus(level, StatType.Attack), PassiveSkillType.Plus),
	Spd((unit, level) -> unit.increaseCombatBonus(level, StatType.Speed), PassiveSkillType.Plus),
	Def((unit, level) -> unit.increaseCombatBonus(level, StatType.Defense), PassiveSkillType.Plus),
	Res((unit, level) -> unit.increaseCombatBonus(level, StatType.Resistance), PassiveSkillType.Plus),
	AllRangeCounter((unit, level) -> 0, PassiveSkillType.Plus),
	TriangleAdept((unit, level) -> 5 + 5 * level, PassiveSkillType.Plus),
	
	//Blows, Stances, and Defenses
	DeathBlow((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Attack), PassiveSkillType.Blow),
	DartingBlow((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Speed), PassiveSkillType.Blow),
	ArmoredBlow((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Defense), PassiveSkillType.Blow),
	WardingBlow((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Resistance), PassiveSkillType.Blow),
	FierceStance((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Attack), PassiveSkillType.Stance),
	DartingStance((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Speed), PassiveSkillType.Stance),
	SturdyStance((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Defense), PassiveSkillType.Stance),
	WardingStance((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Resistance), PassiveSkillType.Stance),
	CloseDefense((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Defense) + unit.increaseCombatBonus(2*level, StatType.Resistance), PassiveSkillType.Defense, StatType.Health, new int[] {1,1,1}),
	DistantDefense((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Defense) + unit.increaseCombatBonus(2*level, StatType.Resistance), PassiveSkillType.Defense, StatType.Health, new int[] {2,2,2}),
	
	//Brazens
	BrazenAtkSpd((unit, level) -> unit.increaseCombatBonus(1 + 2*level, StatType.Attack) + unit.increaseCombatBonus(1 + 2*level, StatType.Speed), PassiveSkillType.Brazen, StatType.Health, new int[] {80,80,80}),
	BrazenAtkDef((unit, level) -> unit.increaseCombatBonus(1 + 2*level, StatType.Attack) + unit.increaseCombatBonus(1 + 2*level, StatType.Defense), PassiveSkillType.Brazen, StatType.Health, new int[] {80,80,80}),
	BrazenAtkRes((unit, level) -> unit.increaseCombatBonus(1 + 2*level, StatType.Attack) + unit.increaseCombatBonus(1 + 2*level, StatType.Resistance), PassiveSkillType.Brazen, StatType.Health, new int[] {80,80,80}),
	BrazenSpdDef((unit, level) -> unit.increaseCombatBonus(1 + 2*level, StatType.Speed) + unit.increaseCombatBonus(1 + 2*level, StatType.Defense), PassiveSkillType.Brazen, StatType.Health, new int[] {80,80,80}),
	BrazenSpdRes((unit, level) -> unit.increaseCombatBonus(1 + 2*level, StatType.Speed) + unit.increaseCombatBonus(1 + 2*level, StatType.Resistance), PassiveSkillType.Brazen, StatType.Health, new int[] {80,80,80}),
	BrazenDefRes((unit, level) -> unit.increaseCombatBonus(1 + 2*level, StatType.Defense) + unit.increaseCombatBonus(1 + 2*level, StatType.Resistance), PassiveSkillType.Brazen, StatType.Health, new int[] {80,80,80}),

	//Pushes
	AtkSpdPush((unit, level) -> unit.increaseCombatBonus(2 + level, StatType.Attack) + unit.increaseCombatBonus(2 + level, StatType.Speed), PassiveSkillType.Push),
	AtkDefPush((unit, level) -> unit.increaseCombatBonus(2 + level, StatType.Attack) + unit.increaseCombatBonus(2 + level, StatType.Defense), PassiveSkillType.Push),
	AtkResPush((unit, level) -> unit.increaseCombatBonus(2 + level, StatType.Attack) + unit.increaseCombatBonus(2 + level, StatType.Resistance), PassiveSkillType.Push),
	SpdDefPush((unit, level) -> unit.increaseCombatBonus(2 + level, StatType.Speed) + unit.increaseCombatBonus(2 + level, StatType.Defense), PassiveSkillType.Push),
	SpdResPush((unit, level) -> unit.increaseCombatBonus(2 + level, StatType.Speed) + unit.increaseCombatBonus(2 + level, StatType.Resistance), PassiveSkillType.Push),
	DefResPush((unit, level) -> unit.increaseCombatBonus(2 + level, StatType.Defense) + unit.increaseCombatBonus(2 + level, StatType.Resistance), PassiveSkillType.Push),

	//Boosts and Blades
	FireBoost((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Attack), PassiveSkillType.Boost, StatType.Health, new int[] {3,3,3}),
	WindBoost((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Speed), PassiveSkillType.Boost, StatType.Health, new int[] {3,3,3}),
	EarthBoost((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Defense), PassiveSkillType.Boost, StatType.Health, new int[] {3,3,3}),
	WaterBoost((unit, level) -> unit.increaseCombatBonus(2*level, StatType.Resistance), PassiveSkillType.Boost, StatType.Health, new int[] {3,3,3}),

	HeavyBlade((unit, level) -> {unit.specialCdOnInitiateAttack = 2; unit.specialCdOnCounterAttack = 2; return 1;}, PassiveSkillType.Blade, StatType.Attack, new int[] {5,3,1}),
	FlashingBlade((unit, level) -> {unit.specialCdOnInitiateAttack = 2; unit.specialCdOnCounterAttack = 2; return 1;}, PassiveSkillType.Blade, StatType.Speed, new int[] {5,3,1}),
	Windsweep((unit, level) -> 0, PassiveSkillType.Blade, StatType.Speed, new int[] {5,3,1}),
	Watersweep((unit, level) -> 0, PassiveSkillType.Blade, StatType.Speed, new int[] {5,3,1}),

	//Special Charge Altering Skills
	SteadyBreath((unit,level) -> {unit.specialCdOnCounterAttack = 2; unit.specialCdOnCounterDefend = 2; return unit.increaseCombatBonus(4, StatType.Defense);}, PassiveSkillType.Stance),
	WardingBreath((unit,level) -> {unit.specialCdOnCounterAttack = 2; unit.specialCdOnCounterDefend = 2; return unit.increaseCombatBonus(4, StatType.Resistance);}, PassiveSkillType.Stance),
	VengefulFighter((unit,level) -> {unit.numCounterAttacks = 2; unit.specialCdOnCounterAttack = 2; return 1;}, PassiveSkillType.Undamaged, StatType.Health, new int[] {90,70,50}),
	BoldFighter((unit,level) -> {unit.numInitiateAttacks = 2; unit.specialCdOnInitiateAttack = 2; return 1;}, PassiveSkillType.Undamaged, StatType.Health, new int[] {100,50,0}),
	
	//Weapon Unique Skills
	InfantryEffective((unit, level) -> 0, PassiveSkillType.Plus),
	FlierEffective((unit, level) -> 0, PassiveSkillType.Plus),
	CavalryEffective((unit, level) -> 0, PassiveSkillType.Plus),
	ArmorEffective((unit, level) -> 0, PassiveSkillType.Plus),
	DragonEffective((unit, level) -> 0, PassiveSkillType.Plus),
	
	Firesweep((unit, level) -> 0, PassiveSkillType.Plus),
	Slaying((unit, level) -> unit.increaseBonusSpecialCharge(), PassiveSkillType.Plus),
	Wrathful((unit, level) -> unit.increaseBonusSpecialDamage(), PassiveSkillType.Plus);
	
	SkillActivation<Unit, Integer, Integer> skillEffect;
	PassiveSkillType type;
	StatType stat;
	int[] threshold;
	
	PassiveSkill(SkillActivation<Unit, Integer, Integer> activation, PassiveSkillType type){
		this(activation, type, StatType.None, new int[] {0,0,0});
	}
	
	PassiveSkill(SkillActivation<Unit, Integer, Integer> activation, PassiveSkillType type, StatType stat, int[] threshold){
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
