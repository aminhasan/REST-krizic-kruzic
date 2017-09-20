package hr.corvus.krizickruzic.game.database;

import java.util.HashMap;
import java.util.Map;

import hr.corvus.krizickruzic.game.resource.GameStats;
import hr.corvus.krizickruzic.game.resource.GameStatus;

public class DatabaseClass {
	
	private static Map<Long, GameStatus> gameStatus = new HashMap<>();
	private static GameStats gameStats = new GameStats();
	
	public static Map<Long, GameStatus> getGameStatus() {
		return gameStatus;
	}

	public static GameStats getGameStats() {
		return gameStats;
	}
}
