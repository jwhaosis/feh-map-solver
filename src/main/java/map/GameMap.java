package map;

import java.util.ArrayList;

import unit.Unit;

public class GameMap {
	
    private static GameMap instance = null;
    
    public static GameMap setInstance(String terrain) {
        if (instance == null) {
            instance = new GameMap(terrain);
        }
        return instance;
    }

    public static GameMap getInstance() {
        return instance;
    }

	Tile[][] map;
	Unit[][] units;
	
	public GameMap(String terrain){
		map = generateMap(terrain);
		units = generateUnits();
	}
	
	Tile[][] generateMap(String terrain){
		if(terrain.length()!=48) {
			System.out.println("Please enter a valid map string.");
			return null;
		}
		Tile[][] resultMap = new Tile[6][8];
	    for (int i = 0; i < 6; i++) {
	    	for(int j = 0; j < 8; j++) {
	    		resultMap[i][j] = new Tile(terrain.charAt(i*8+j));
	    	}
	    }
	    return resultMap;
	}
	
	Unit[][] generateUnits(){
		return new Unit[6][8];
	}
	
	//TODO: make these actually do something
	public ArrayList<Unit> nearbyUnits(Unit unit, int range) {
		return new ArrayList<Unit>();
	}
	
	public ArrayList<Unit> nearbyUnitsAllyUnits(Unit unit, int range) {
		return new ArrayList<Unit>();
	}
	
	public ArrayList<Unit> nearbyUnitsEnemyUnits(Unit unit, int range) {
		return new ArrayList<Unit>();
	}

	
}
