package map;

import global.enums.gamemap.TileType;

public class Tile {
	
	private TileType type;
	
	public Tile(char tileType) {
		if(tileType == 'p') {
			type = TileType.Plains;
		} else if (tileType == 'f') {
			type = TileType.Forest;
		} else if (tileType == 's') {
			type = TileType.Sky;
		} else if (tileType == 't') {
			type = TileType.Trench;
		} else if (tileType == 'd') {
			type = TileType.Fort;
		} else if (tileType == 'e') {
			type = TileType.Entrenchment;
		} else {
			System.out.println("Tile type not valid, adding as Plains.");
			type = TileType.Plains;
		}
	}
	
	TileType getType() {
		return type;
	}
}
