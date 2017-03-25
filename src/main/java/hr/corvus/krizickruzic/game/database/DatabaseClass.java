package hr.corvus.krizickruzic.game.database;

import java.util.HashMap;
import java.util.Map;

import hr.corvus.krizickruzic.game.resource.GameStatus;

public class DatabaseClass {
	
	private static Map<Long, GameStatus> gameStatus = new HashMap<>();
	
	public static Map<Long, GameStatus> getGameStatus() {
		return gameStatus;
	}

}
