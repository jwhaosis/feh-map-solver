package global.enums.passiveskills;

import global.enums.unitinfo.StatType;

public enum BasicSkill {
	//Stat Plus Skills
	Hp3(new int[] {3,0,0,0,0}, PassiveSkillType.Plus),
	Hp4(new int[] {4,0,0,0,0}, PassiveSkillType.Plus),
	Hp5(new int[] {5,0,0,0,0}, PassiveSkillType.Plus),
	Atk1(new int[] {0,1,0,0,0}, PassiveSkillType.Plus),
	Atk2(new int[] {0,2,0,0,0}, PassiveSkillType.Plus),
	Atk3(new int[] {0,3,0,0,0}, PassiveSkillType.Plus),
	Spd1(new int[] {0,0,1,0,0}, PassiveSkillType.Plus),
	Spd2(new int[] {0,0,2,0,0}, PassiveSkillType.Plus),
	Spd3(new int[] {0,0,3,0,0}, PassiveSkillType.Plus),
	Def1(new int[] {0,0,0,1,0}, PassiveSkillType.Plus),
	Def2(new int[] {0,0,0,2,0}, PassiveSkillType.Plus),
	Def3(new int[] {0,0,0,3,0}, PassiveSkillType.Plus),
	Res1(new int[] {0,0,0,0,1}, PassiveSkillType.Plus),
	Res2(new int[] {0,0,0,0,2}, PassiveSkillType.Plus),
	Res3(new int[] {0,0,0,0,3}, PassiveSkillType.Plus),
	Fury1(new int[] {0,1,1,1,1}, PassiveSkillType.Plus),
	Fury2(new int[] {0,2,2,2,2}, PassiveSkillType.Plus),
	Fury3(new int[] {0,3,3,3,3}, PassiveSkillType.Plus),

	//Nullifier Skills
	GranisShield(new int[] {0,0,0,0,0}, PassiveSkillType.Nullify),
	IotesShield(new int[] {0,0,0,0,0}, PassiveSkillType.Nullify),
	SvalinnShield(new int[] {0,0,0,0,0}, PassiveSkillType.Nullify),

	//Blow Skills
	DeathBlow1(new int[] {0,2,0,0,0}, PassiveSkillType.Blow),
	DeathBlow2(new int[] {0,4,0,0,0}, PassiveSkillType.Blow),
	DeathBlow3(new int[] {0,6,0,0,0}, PassiveSkillType.Blow),
	DartingBlow1(new int[] {0,0,2,0,0}, PassiveSkillType.Blow),
	DartingBlow2(new int[] {0,0,4,0,0}, PassiveSkillType.Blow),
	DartingBlow3(new int[] {0,0,6,0,0}, PassiveSkillType.Blow),
	ArmoredBlow1(new int[] {0,0,0,2,0}, PassiveSkillType.Blow),
	ArmoredBlow2(new int[] {0,0,0,4,0}, PassiveSkillType.Blow),
	ArmoredBlow3(new int[] {0,0,0,6,0}, PassiveSkillType.Blow),
	WardingBlow1(new int[] {0,0,0,0,2}, PassiveSkillType.Blow),
	WardingBlow2(new int[] {0,0,0,0,4}, PassiveSkillType.Blow),
	WardingBlow3(new int[] {0,0,0,0,6}, PassiveSkillType.Blow),

	//Stance Skills
	FierceStance1(new int[] {0,2,0,0,0}, PassiveSkillType.Stance),
	FierceStance2(new int[] {0,4,0,0,0}, PassiveSkillType.Stance),
	FierceStance3(new int[] {0,6,0,0,0}, PassiveSkillType.Stance),
	DartingStance1(new int[] {0,0,2,0,0}, PassiveSkillType.Stance),
	DartingStance2(new int[] {0,0,4,0,0}, PassiveSkillType.Stance),
	DartingStance3(new int[] {0,0,6,0,0}, PassiveSkillType.Stance),
	SteadyStance1(new int[] {0,0,0,2,0}, PassiveSkillType.Stance),
	SteadyStance2(new int[] {0,0,0,4,0}, PassiveSkillType.Stance),
	SteadyStance3(new int[] {0,0,0,6,0}, PassiveSkillType.Stance),
	WardingStance1(new int[] {0,0,0,0,2}, PassiveSkillType.Stance),
	WardingStance2(new int[] {0,0,0,0,4}, PassiveSkillType.Stance),
	WardingStance3(new int[] {0,0,0,0,6}, PassiveSkillType.Stance),
	
	//Defense Skills
	CloseDefense1(new int[] {0,0,0,2,2}, PassiveSkillType.Defense, 1),
	CloseDefense2(new int[] {0,0,0,4,4}, PassiveSkillType.Defense, 1),
	CloseDefense3(new int[] {0,0,0,6,6}, PassiveSkillType.Defense, 1),
	DistantDefense1(new int[] {0,0,0,2,2}, PassiveSkillType.Defense, 2),
	DistantDefense2(new int[] {0,0,0,4,4}, PassiveSkillType.Defense, 2),
	DistantDefense3(new int[] {0,0,0,6,6}, PassiveSkillType.Defense, 2),
	
	//Brazen Skills
	BrazenAtkSpd1(new int[] {0,3,3,0,0}, PassiveSkillType.Brazen, 80),
	BrazenAtkSpd2(new int[] {0,5,5,0,0}, PassiveSkillType.Brazen, 80),
	BrazenAtkSpd3(new int[] {0,7,7,0,0}, PassiveSkillType.Brazen, 80),
	BrazenAtkDef1(new int[] {0,3,0,3,0}, PassiveSkillType.Brazen, 80),
	BrazenAtkDef2(new int[] {0,5,0,5,0}, PassiveSkillType.Brazen, 80),
	BrazenAtkDef3(new int[] {0,7,0,7,0}, PassiveSkillType.Brazen, 80),
	BrazenAtkRes1(new int[] {0,3,0,0,3}, PassiveSkillType.Brazen, 80),
	BrazenAtkRes2(new int[] {0,5,0,0,5}, PassiveSkillType.Brazen, 80),
	BrazenAtkRes3(new int[] {0,7,0,0,7}, PassiveSkillType.Brazen, 80),
	BrazenSpdDef1(new int[] {0,0,3,3,0}, PassiveSkillType.Brazen, 80),
	BrazenSpdDef2(new int[] {0,0,5,5,0}, PassiveSkillType.Brazen, 80),
	BrazenSpdDef3(new int[] {0,0,7,7,0}, PassiveSkillType.Brazen, 80),
	BrazenSpdRes1(new int[] {0,0,3,0,3}, PassiveSkillType.Brazen, 80),
	BrazenSpdRes2(new int[] {0,0,5,0,5}, PassiveSkillType.Brazen, 80),
	BrazenSpdRes3(new int[] {0,0,7,0,7}, PassiveSkillType.Brazen, 80),
	BrazenDefRes1(new int[] {0,0,0,3,3}, PassiveSkillType.Brazen, 80),
	BrazenDefRes2(new int[] {0,0,0,5,5}, PassiveSkillType.Brazen, 80),
	BrazenDefRes3(new int[] {0,0,0,7,7}, PassiveSkillType.Brazen, 80),

	//Push Skills
	AtkSpdPush1(new int[] {0,3,3,0,0}, PassiveSkillType.Push),
	AtkSpdPush2(new int[] {0,4,4,0,0}, PassiveSkillType.Push),
	AtkSpdPush3(new int[] {0,5,5,0,0}, PassiveSkillType.Push),
	AtkDefPush1(new int[] {0,3,0,3,0}, PassiveSkillType.Push),
	AtkDefPush2(new int[] {0,4,0,4,0}, PassiveSkillType.Push),
	AtkDefPush3(new int[] {0,5,0,5,0}, PassiveSkillType.Push),
	AtkResPush1(new int[] {0,3,0,0,3}, PassiveSkillType.Push),
	AtkResPush2(new int[] {0,4,0,0,4}, PassiveSkillType.Push),
	AtkResPush3(new int[] {0,5,0,0,5}, PassiveSkillType.Push),
	SpdDefPush1(new int[] {0,0,3,3,0}, PassiveSkillType.Push),
	SpdDefPush2(new int[] {0,0,4,4,0}, PassiveSkillType.Push),
	SpdDefPush3(new int[] {0,0,5,5,0}, PassiveSkillType.Push),
	SpdResPush1(new int[] {0,0,3,0,3}, PassiveSkillType.Push),
	SpdResPush2(new int[] {0,0,4,0,4}, PassiveSkillType.Push),
	SpdResPush3(new int[] {0,0,5,0,5}, PassiveSkillType.Push),
	DefResPush1(new int[] {0,0,0,3,3}, PassiveSkillType.Push),
	DefResPush2(new int[] {0,0,0,4,4}, PassiveSkillType.Push),
	DefResPush3(new int[] {0,0,0,5,5}, PassiveSkillType.Push),

	//Bond Skills
	AtkSpdBond1(new int[] {0,3,3,0,0}, PassiveSkillType.Bond),
	AtkSpdBond2(new int[] {0,4,4,0,0}, PassiveSkillType.Bond),
	AtkSpdBond3(new int[] {0,5,5,0,0}, PassiveSkillType.Bond),
	AtkDefBond1(new int[] {0,3,0,3,0}, PassiveSkillType.Bond),
	AtkDefBond2(new int[] {0,4,0,4,0}, PassiveSkillType.Bond),
	AtkDefBond3(new int[] {0,5,0,5,0}, PassiveSkillType.Bond),
	AtkResBond1(new int[] {0,3,0,0,3}, PassiveSkillType.Bond),
	AtkResBond2(new int[] {0,4,0,0,4}, PassiveSkillType.Bond),
	AtkResBond3(new int[] {0,5,0,0,5}, PassiveSkillType.Bond),
	SpdDefBond1(new int[] {0,0,3,3,0}, PassiveSkillType.Bond),
	SpdDefBond2(new int[] {0,0,4,4,0}, PassiveSkillType.Bond),
	SpdDefBond3(new int[] {0,0,5,5,0}, PassiveSkillType.Bond),
	SpdResBond1(new int[] {0,0,3,0,3}, PassiveSkillType.Bond),
	SpdResBond2(new int[] {0,0,4,0,4}, PassiveSkillType.Bond),
	SpdResBond3(new int[] {0,0,5,0,5}, PassiveSkillType.Bond),
	DefResBond1(new int[] {0,0,0,3,3}, PassiveSkillType.Bond),
	DefResBond2(new int[] {0,0,0,4,4}, PassiveSkillType.Bond),
	DefResBond3(new int[] {0,0,0,5,5}, PassiveSkillType.Bond),

	//Solo Skills
	AtkSpdSolo1(new int[] {0,2,2,0,0}, PassiveSkillType.Solo),
	AtkSpdSolo2(new int[] {0,4,4,0,0}, PassiveSkillType.Solo),
	AtkSpdSolo3(new int[] {0,6,6,0,0}, PassiveSkillType.Solo),
	AtkDefSolo1(new int[] {0,2,0,2,0}, PassiveSkillType.Solo),
	AtkDefSolo2(new int[] {0,4,0,4,0}, PassiveSkillType.Solo),
	AtkDefSolo3(new int[] {0,6,0,6,0}, PassiveSkillType.Solo),
	AtkResSolo1(new int[] {0,2,0,0,2}, PassiveSkillType.Solo),
	AtkResSolo2(new int[] {0,4,0,0,4}, PassiveSkillType.Solo),
	AtkResSolo3(new int[] {0,6,0,0,6}, PassiveSkillType.Solo),
	SpdDefSolo1(new int[] {0,0,2,2,0}, PassiveSkillType.Solo),
	SpdDefSolo2(new int[] {0,0,4,4,0}, PassiveSkillType.Solo),
	SpdDefSolo3(new int[] {0,0,6,6,0}, PassiveSkillType.Solo),
	SpdResSolo1(new int[] {0,0,2,0,2}, PassiveSkillType.Solo),
	SpdResSolo2(new int[] {0,0,4,0,4}, PassiveSkillType.Solo),
	SpdResSolo3(new int[] {0,0,6,0,6}, PassiveSkillType.Solo),
	DefResSolo1(new int[] {0,0,0,2,2}, PassiveSkillType.Solo),
	DefResSolo2(new int[] {0,0,0,4,4}, PassiveSkillType.Solo),
	DefResSolo3(new int[] {0,0,0,6,6}, PassiveSkillType.Solo),
	
	//Boost Skills
	FireBoost1(new int[] {0,2,0,0,0}, PassiveSkillType.Boost, 3),
	FireBoost2(new int[] {0,4,0,0,0}, PassiveSkillType.Boost, 3),
	FireBoost3(new int[] {0,6,0,0,0}, PassiveSkillType.Boost, 3),
	WindBoost1(new int[] {0,0,2,0,0}, PassiveSkillType.Boost, 3),
	WindBoost2(new int[] {0,0,4,0,0}, PassiveSkillType.Boost, 3),
	WindBoost3(new int[] {0,0,6,0,0}, PassiveSkillType.Boost, 3),
	EarthBoost1(new int[] {0,0,0,2,0}, PassiveSkillType.Boost, 3),
	EarthBoost2(new int[] {0,0,0,4,0}, PassiveSkillType.Boost, 3),
	EarthBoost3(new int[] {0,0,0,6,0}, PassiveSkillType.Boost, 3),
	WaterBoost1(new int[] {0,0,0,0,2}, PassiveSkillType.Boost, 3),
	WaterBoost2(new int[] {0,0,0,0,4}, PassiveSkillType.Boost, 3),
	WaterBoost3(new int[] {0,0,0,0,6}, PassiveSkillType.Boost, 3),
	
	//Blade Skills
	HeavyBlade1(new int[] {0,0,0,0,0}, PassiveSkillType.Boost, StatType.Attack, 5),
	HeavyBlade2(new int[] {0,0,0,0,0}, PassiveSkillType.Boost, StatType.Attack, 3),
	HeavyBlade3(new int[] {0,0,0,0,0}, PassiveSkillType.Boost, StatType.Attack, 1),
	FlashingBlade1(new int[] {0,0,0,0,0}, PassiveSkillType.Boost, StatType.Speed, 5),
	FlashingBlade2(new int[] {0,0,0,0,0}, PassiveSkillType.Boost, StatType.Speed, 3),
	FlashingBlade3(new int[] {0,0,0,0,0}, PassiveSkillType.Boost, StatType.Speed, 1),

	//Health Threshold Skills
	Vantage1(new int[] {0,0,0,0,0}, PassiveSkillType.Damaged, 25),
	Vantage2(new int[] {0,0,0,0,0}, PassiveSkillType.Damaged, 50),
	Vantage3(new int[] {0,0,0,0,0}, PassiveSkillType.Damaged, 75),
	Desperation1(new int[] {0,0,0,0,0}, PassiveSkillType.Damaged, 25),
	Desperation2(new int[] {0,0,0,0,0}, PassiveSkillType.Damaged, 50),
	Desperation3(new int[] {0,0,0,0,0}, PassiveSkillType.Damaged, 75),
	BrashAssault1(new int[] {0,0,0,0,0}, PassiveSkillType.Damaged, 30),
	BrashAssault2(new int[] {0,0,0,0,0}, PassiveSkillType.Damaged, 40),
	BrashAssault3(new int[] {0,0,0,0,0}, PassiveSkillType.Damaged, 50),
	Wrath1(new int[] {0,0,0,0,0}, PassiveSkillType.Damaged, 25),
	Wrath2(new int[] {0,0,0,0,0}, PassiveSkillType.Damaged, 50),
	Wrath3(new int[] {0,0,0,0,0}, PassiveSkillType.Damaged, 75),

	QuickRiposte1(new int[] {0,0,0,0,0}, PassiveSkillType.Undamaged, 90),
	QuickRiposte2(new int[] {0,0,0,0,0}, PassiveSkillType.Undamaged, 80),
	QuickRiposte3(new int[] {0,0,0,0,0}, PassiveSkillType.Undamaged, 70),
	Guard1(new int[] {0,0,0,0,0}, PassiveSkillType.Undamaged, 100),
	Guard2(new int[] {0,0,0,0,0}, PassiveSkillType.Undamaged, 90),
	Guard3(new int[] {0,0,0,0,0}, PassiveSkillType.Undamaged, 80),
	
	//Armor Locked Skills
	WaryFighter1(new int[] {0,0,0,0,0}, PassiveSkillType.Undamaged, 90),
	WaryFighter2(new int[] {0,0,0,0,0}, PassiveSkillType.Undamaged, 70),
	WaryFighter3(new int[] {0,0,0,0,0}, PassiveSkillType.Undamaged, 50),
	BoldFighter1(new int[] {0,0,0,0,0}, PassiveSkillType.Undamaged, 100),
	BoldFighter2(new int[] {0,0,0,0,0}, PassiveSkillType.Undamaged, 50),
	BoldFighter3(new int[] {0,0,0,0,0}, PassiveSkillType.Undamaged, 0),
	FollowupRing(new int[] {0,0,0,0,0}, PassiveSkillType.Undamaged, 50),
	VengefulFighter1(new int[] {0,0,0,0,0}, PassiveSkillType.Undamaged, 90),
	VengefulFighter2(new int[] {0,0,0,0,0}, PassiveSkillType.Undamaged, 70),
	VengefulFighter3(new int[] {0,0,0,0,0}, PassiveSkillType.Undamaged, 50),
	SpecialFighter1(new int[] {0,0,0,0,0}, PassiveSkillType.Undamaged, 90),
	SpecialFighter2(new int[] {0,0,0,0,0}, PassiveSkillType.Undamaged, 70),
	SpecialFighter3(new int[] {0,0,0,0,0}, PassiveSkillType.Undamaged, 50),
	
	
	filler(new int[] {3,0,0,0,0}, PassiveSkillType.Plus);

	String name;
	int[] bonus;
	PassiveSkillType type;
	StatType statType;
	int threshold;
	
	/*BasicSkill(String name, int[] bonus, SkillType type) {
		this.name = name;
		this.bonus = bonus;
		this.type = type;
		this.threshold = 0;
	}*/

	BasicSkill(int[] bonus, PassiveSkillType type, StatType statType, int threshold) {
		this.name = this.name().replaceAll("([^_])([A-Z]|[0-9])", "$1 $2");
		this.bonus = bonus;
		this.type = type;
		this.statType = statType;
		this.threshold = threshold;
	}
	
	BasicSkill(int[] bonus, PassiveSkillType type, int threshold) {
		this(bonus, type, StatType.Health, threshold);
	}

	BasicSkill(int[] bonus, PassiveSkillType type) {
		this(bonus, type, StatType.Health, 0);
	}

}
