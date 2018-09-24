package global.enums.skill;

import global.enums.SkillType;
import global.enums.StatType;

public enum BasicSkill {
	//Stat Plus Skills
	Hp3(new int[] {3,0,0,0,0}, SkillType.Plus),
	Hp4(new int[] {4,0,0,0,0}, SkillType.Plus),
	Hp5(new int[] {5,0,0,0,0}, SkillType.Plus),
	Atk1(new int[] {0,1,0,0,0}, SkillType.Plus),
	Atk2(new int[] {0,2,0,0,0}, SkillType.Plus),
	Atk3(new int[] {0,3,0,0,0}, SkillType.Plus),
	Spd1(new int[] {0,0,1,0,0}, SkillType.Plus),
	Spd2(new int[] {0,0,2,0,0}, SkillType.Plus),
	Spd3(new int[] {0,0,3,0,0}, SkillType.Plus),
	Def1(new int[] {0,0,0,1,0}, SkillType.Plus),
	Def2(new int[] {0,0,0,2,0}, SkillType.Plus),
	Def3(new int[] {0,0,0,3,0}, SkillType.Plus),
	Res1(new int[] {0,0,0,0,1}, SkillType.Plus),
	Res2(new int[] {0,0,0,0,2}, SkillType.Plus),
	Res3(new int[] {0,0,0,0,3}, SkillType.Plus),
	Fury1(new int[] {0,1,1,1,1}, SkillType.Plus),
	Fury2(new int[] {0,2,2,2,2}, SkillType.Plus),
	Fury3(new int[] {0,3,3,3,3}, SkillType.Plus),

	//Nullifier Skills
	GranisShield(new int[] {0,0,0,0,0}, SkillType.Nullify),
	IotesShield(new int[] {0,0,0,0,0}, SkillType.Nullify),
	SvalinnShield(new int[] {0,0,0,0,0}, SkillType.Nullify),

	//Blow Skills
	DeathBlow1(new int[] {0,2,0,0,0}, SkillType.Blow),
	DeathBlow2(new int[] {0,4,0,0,0}, SkillType.Blow),
	DeathBlow3(new int[] {0,6,0,0,0}, SkillType.Blow),
	DartingBlow1(new int[] {0,0,2,0,0}, SkillType.Blow),
	DartingBlow2(new int[] {0,0,4,0,0}, SkillType.Blow),
	DartingBlow3(new int[] {0,0,6,0,0}, SkillType.Blow),
	ArmoredBlow1(new int[] {0,0,0,2,0}, SkillType.Blow),
	ArmoredBlow2(new int[] {0,0,0,4,0}, SkillType.Blow),
	ArmoredBlow3(new int[] {0,0,0,6,0}, SkillType.Blow),
	WardingBlow1(new int[] {0,0,0,0,2}, SkillType.Blow),
	WardingBlow2(new int[] {0,0,0,0,4}, SkillType.Blow),
	WardingBlow3(new int[] {0,0,0,0,6}, SkillType.Blow),

	//Stance Skills
	FierceStance1(new int[] {0,2,0,0,0}, SkillType.Stance),
	FierceStance2(new int[] {0,4,0,0,0}, SkillType.Stance),
	FierceStance3(new int[] {0,6,0,0,0}, SkillType.Stance),
	DartingStance1(new int[] {0,0,2,0,0}, SkillType.Stance),
	DartingStance2(new int[] {0,0,4,0,0}, SkillType.Stance),
	DartingStance3(new int[] {0,0,6,0,0}, SkillType.Stance),
	SteadyStance1(new int[] {0,0,0,2,0}, SkillType.Stance),
	SteadyStance2(new int[] {0,0,0,4,0}, SkillType.Stance),
	SteadyStance3(new int[] {0,0,0,6,0}, SkillType.Stance),
	WardingStance1(new int[] {0,0,0,0,2}, SkillType.Stance),
	WardingStance2(new int[] {0,0,0,0,4}, SkillType.Stance),
	WardingStance3(new int[] {0,0,0,0,6}, SkillType.Stance),
	
	//Defense Skills
	CloseDefense1(new int[] {0,0,0,2,2}, SkillType.Defense, 1),
	CloseDefense2(new int[] {0,0,0,4,4}, SkillType.Defense, 1),
	CloseDefense3(new int[] {0,0,0,6,6}, SkillType.Defense, 1),
	DistantDefense1(new int[] {0,0,0,2,2}, SkillType.Defense, 2),
	DistantDefense2(new int[] {0,0,0,4,4}, SkillType.Defense, 2),
	DistantDefense3(new int[] {0,0,0,6,6}, SkillType.Defense, 2),
	
	//Brazen Skills
	BrazenAtkSpd1(new int[] {0,3,3,0,0}, SkillType.Brazen, 80),
	BrazenAtkSpd2(new int[] {0,5,5,0,0}, SkillType.Brazen, 80),
	BrazenAtkSpd3(new int[] {0,7,7,0,0}, SkillType.Brazen, 80),
	BrazenAtkDef1(new int[] {0,3,0,3,0}, SkillType.Brazen, 80),
	BrazenAtkDef2(new int[] {0,5,0,5,0}, SkillType.Brazen, 80),
	BrazenAtkDef3(new int[] {0,7,0,7,0}, SkillType.Brazen, 80),
	BrazenAtkRes1(new int[] {0,3,0,0,3}, SkillType.Brazen, 80),
	BrazenAtkRes2(new int[] {0,5,0,0,5}, SkillType.Brazen, 80),
	BrazenAtkRes3(new int[] {0,7,0,0,7}, SkillType.Brazen, 80),
	BrazenSpdDef1(new int[] {0,0,3,3,0}, SkillType.Brazen, 80),
	BrazenSpdDef2(new int[] {0,0,5,5,0}, SkillType.Brazen, 80),
	BrazenSpdDef3(new int[] {0,0,7,7,0}, SkillType.Brazen, 80),
	BrazenSpdRes1(new int[] {0,0,3,0,3}, SkillType.Brazen, 80),
	BrazenSpdRes2(new int[] {0,0,5,0,5}, SkillType.Brazen, 80),
	BrazenSpdRes3(new int[] {0,0,7,0,7}, SkillType.Brazen, 80),
	BrazenDefRes1(new int[] {0,0,0,3,3}, SkillType.Brazen, 80),
	BrazenDefRes2(new int[] {0,0,0,5,5}, SkillType.Brazen, 80),
	BrazenDefRes3(new int[] {0,0,0,7,7}, SkillType.Brazen, 80),

	//Push Skills
	AtkSpdPush1(new int[] {0,3,3,0,0}, SkillType.Push),
	AtkSpdPush2(new int[] {0,4,4,0,0}, SkillType.Push),
	AtkSpdPush3(new int[] {0,5,5,0,0}, SkillType.Push),
	AtkDefPush1(new int[] {0,3,0,3,0}, SkillType.Push),
	AtkDefPush2(new int[] {0,4,0,4,0}, SkillType.Push),
	AtkDefPush3(new int[] {0,5,0,5,0}, SkillType.Push),
	AtkResPush1(new int[] {0,3,0,0,3}, SkillType.Push),
	AtkResPush2(new int[] {0,4,0,0,4}, SkillType.Push),
	AtkResPush3(new int[] {0,5,0,0,5}, SkillType.Push),
	SpdDefPush1(new int[] {0,0,3,3,0}, SkillType.Push),
	SpdDefPush2(new int[] {0,0,4,4,0}, SkillType.Push),
	SpdDefPush3(new int[] {0,0,5,5,0}, SkillType.Push),
	SpdResPush1(new int[] {0,0,3,0,3}, SkillType.Push),
	SpdResPush2(new int[] {0,0,4,0,4}, SkillType.Push),
	SpdResPush3(new int[] {0,0,5,0,5}, SkillType.Push),
	DefResPush1(new int[] {0,0,0,3,3}, SkillType.Push),
	DefResPush2(new int[] {0,0,0,4,4}, SkillType.Push),
	DefResPush3(new int[] {0,0,0,5,5}, SkillType.Push),

	//Bond Skills
	AtkSpdBond1(new int[] {0,3,3,0,0}, SkillType.Bond),
	AtkSpdBond2(new int[] {0,4,4,0,0}, SkillType.Bond),
	AtkSpdBond3(new int[] {0,5,5,0,0}, SkillType.Bond),
	AtkDefBond1(new int[] {0,3,0,3,0}, SkillType.Bond),
	AtkDefBond2(new int[] {0,4,0,4,0}, SkillType.Bond),
	AtkDefBond3(new int[] {0,5,0,5,0}, SkillType.Bond),
	AtkResBond1(new int[] {0,3,0,0,3}, SkillType.Bond),
	AtkResBond2(new int[] {0,4,0,0,4}, SkillType.Bond),
	AtkResBond3(new int[] {0,5,0,0,5}, SkillType.Bond),
	SpdDefBond1(new int[] {0,0,3,3,0}, SkillType.Bond),
	SpdDefBond2(new int[] {0,0,4,4,0}, SkillType.Bond),
	SpdDefBond3(new int[] {0,0,5,5,0}, SkillType.Bond),
	SpdResBond1(new int[] {0,0,3,0,3}, SkillType.Bond),
	SpdResBond2(new int[] {0,0,4,0,4}, SkillType.Bond),
	SpdResBond3(new int[] {0,0,5,0,5}, SkillType.Bond),
	DefResBond1(new int[] {0,0,0,3,3}, SkillType.Bond),
	DefResBond2(new int[] {0,0,0,4,4}, SkillType.Bond),
	DefResBond3(new int[] {0,0,0,5,5}, SkillType.Bond),

	//Solo Skills
	AtkSpdSolo1(new int[] {0,2,2,0,0}, SkillType.Solo),
	AtkSpdSolo2(new int[] {0,4,4,0,0}, SkillType.Solo),
	AtkSpdSolo3(new int[] {0,6,6,0,0}, SkillType.Solo),
	AtkDefSolo1(new int[] {0,2,0,2,0}, SkillType.Solo),
	AtkDefSolo2(new int[] {0,4,0,4,0}, SkillType.Solo),
	AtkDefSolo3(new int[] {0,6,0,6,0}, SkillType.Solo),
	AtkResSolo1(new int[] {0,2,0,0,2}, SkillType.Solo),
	AtkResSolo2(new int[] {0,4,0,0,4}, SkillType.Solo),
	AtkResSolo3(new int[] {0,6,0,0,6}, SkillType.Solo),
	SpdDefSolo1(new int[] {0,0,2,2,0}, SkillType.Solo),
	SpdDefSolo2(new int[] {0,0,4,4,0}, SkillType.Solo),
	SpdDefSolo3(new int[] {0,0,6,6,0}, SkillType.Solo),
	SpdResSolo1(new int[] {0,0,2,0,2}, SkillType.Solo),
	SpdResSolo2(new int[] {0,0,4,0,4}, SkillType.Solo),
	SpdResSolo3(new int[] {0,0,6,0,6}, SkillType.Solo),
	DefResSolo1(new int[] {0,0,0,2,2}, SkillType.Solo),
	DefResSolo2(new int[] {0,0,0,4,4}, SkillType.Solo),
	DefResSolo3(new int[] {0,0,0,6,6}, SkillType.Solo),
	
	//Boost Skills
	FireBoost1(new int[] {0,2,0,0,0}, SkillType.Boost, 3),
	FireBoost2(new int[] {0,4,0,0,0}, SkillType.Boost, 3),
	FireBoost3(new int[] {0,6,0,0,0}, SkillType.Boost, 3),
	WindBoost1(new int[] {0,0,2,0,0}, SkillType.Boost, 3),
	WindBoost2(new int[] {0,0,4,0,0}, SkillType.Boost, 3),
	WindBoost3(new int[] {0,0,6,0,0}, SkillType.Boost, 3),
	EarthBoost1(new int[] {0,0,0,2,0}, SkillType.Boost, 3),
	EarthBoost2(new int[] {0,0,0,4,0}, SkillType.Boost, 3),
	EarthBoost3(new int[] {0,0,0,6,0}, SkillType.Boost, 3),
	WaterBoost1(new int[] {0,0,0,0,2}, SkillType.Boost, 3),
	WaterBoost2(new int[] {0,0,0,0,4}, SkillType.Boost, 3),
	WaterBoost3(new int[] {0,0,0,0,6}, SkillType.Boost, 3),
	
	//Blade Skills
	HeavyBlade1(new int[] {0,0,0,0,0}, SkillType.Boost, StatType.Attack, 5),
	HeavyBlade2(new int[] {0,0,0,0,0}, SkillType.Boost, StatType.Attack, 3),
	HeavyBlade3(new int[] {0,0,0,0,0}, SkillType.Boost, StatType.Attack, 1),
	FlashingBlade1(new int[] {0,0,0,0,0}, SkillType.Boost, StatType.Speed, 5),
	FlashingBlade2(new int[] {0,0,0,0,0}, SkillType.Boost, StatType.Speed, 3),
	FlashingBlade3(new int[] {0,0,0,0,0}, SkillType.Boost, StatType.Speed, 1),

	//Health Threshold Skills
	Vantage1(new int[] {0,0,0,0,0}, SkillType.Damaged, 25),
	Vantage2(new int[] {0,0,0,0,0}, SkillType.Damaged, 50),
	Vantage3(new int[] {0,0,0,0,0}, SkillType.Damaged, 75),
	Desperation1(new int[] {0,0,0,0,0}, SkillType.Damaged, 25),
	Desperation2(new int[] {0,0,0,0,0}, SkillType.Damaged, 50),
	Desperation3(new int[] {0,0,0,0,0}, SkillType.Damaged, 75),
	BrashAssault1(new int[] {0,0,0,0,0}, SkillType.Damaged, 30),
	BrashAssault2(new int[] {0,0,0,0,0}, SkillType.Damaged, 40),
	BrashAssault3(new int[] {0,0,0,0,0}, SkillType.Damaged, 50),
	Wrath1(new int[] {0,0,0,0,0}, SkillType.Damaged, 25),
	Wrath2(new int[] {0,0,0,0,0}, SkillType.Damaged, 50),
	Wrath3(new int[] {0,0,0,0,0}, SkillType.Damaged, 75),

	QuickRiposte1(new int[] {0,0,0,0,0}, SkillType.Undamaged, 90),
	QuickRiposte2(new int[] {0,0,0,0,0}, SkillType.Undamaged, 80),
	QuickRiposte3(new int[] {0,0,0,0,0}, SkillType.Undamaged, 70),
	Guard1(new int[] {0,0,0,0,0}, SkillType.Undamaged, 100),
	Guard2(new int[] {0,0,0,0,0}, SkillType.Undamaged, 90),
	Guard3(new int[] {0,0,0,0,0}, SkillType.Undamaged, 80),
	
	//Armor Locked Skills
	WaryFighter1(new int[] {0,0,0,0,0}, SkillType.Undamaged, 90),
	WaryFighter2(new int[] {0,0,0,0,0}, SkillType.Undamaged, 70),
	WaryFighter3(new int[] {0,0,0,0,0}, SkillType.Undamaged, 50),
	BoldFighter1(new int[] {0,0,0,0,0}, SkillType.Undamaged, 100),
	BoldFighter2(new int[] {0,0,0,0,0}, SkillType.Undamaged, 50),
	BoldFighter3(new int[] {0,0,0,0,0}, SkillType.Undamaged, 0),
	FollowupRing(new int[] {0,0,0,0,0}, SkillType.Undamaged, 50),
	VengefulFighter1(new int[] {0,0,0,0,0}, SkillType.Undamaged, 90),
	VengefulFighter2(new int[] {0,0,0,0,0}, SkillType.Undamaged, 70),
	VengefulFighter3(new int[] {0,0,0,0,0}, SkillType.Undamaged, 50),
	SpecialFighter1(new int[] {0,0,0,0,0}, SkillType.Undamaged, 90),
	SpecialFighter2(new int[] {0,0,0,0,0}, SkillType.Undamaged, 70),
	SpecialFighter3(new int[] {0,0,0,0,0}, SkillType.Undamaged, 50),
	
	
	filler(new int[] {3,0,0,0,0}, SkillType.Plus);

	String name;
	int[] bonus;
	SkillType type;
	StatType statType;
	int threshold;
	
	/*BasicSkill(String name, int[] bonus, SkillType type) {
		this.name = name;
		this.bonus = bonus;
		this.type = type;
		this.threshold = 0;
	}*/

	BasicSkill(int[] bonus, SkillType type, StatType statType, int threshold) {
		this.name = this.name().replaceAll("([^_])([A-Z]|[0-9])", "$1 $2");
		this.bonus = bonus;
		this.type = type;
		this.statType = statType;
		this.threshold = threshold;
	}
	
	BasicSkill(int[] bonus, SkillType type, int threshold) {
		this(bonus, type, StatType.Health, threshold);
	}

	BasicSkill(int[] bonus, SkillType type) {
		this(bonus, type, StatType.Health, 0);
	}

}
