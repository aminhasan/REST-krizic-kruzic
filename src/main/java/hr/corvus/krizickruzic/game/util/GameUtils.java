package hr.corvus.krizickruzic.game.util;

import java.util.List;

import hr.corvus.krizickruzic.game.database.DatabaseClass;
import hr.corvus.krizickruzic.game.enums.CellValue;
import hr.corvus.krizickruzic.game.enums.Result;
import hr.corvus.krizickruzic.game.enums.Status;
import hr.corvus.krizickruzic.game.enums.Strategy;
import hr.corvus.krizickruzic.game.resource.GameStatus;
import hr.corvus.krizickruzic.game.resource.PlayGame;
import hr.corvus.krizickruzic.game.resource.Statistic;

public class GameUtils {
	
	final static String PLAYER_CELL_VALUE = CellValue.X.toString();
	final static String COMPUTER_CELL_VALUE = CellValue.O.toString();
	
	public static int getCellPosition(int row, int column) {
		
		int cellPosition;
		String rowColumnMove = row + "," + column;
		
		switch(rowColumnMove) {
			case "1,1":
				cellPosition = 0;
				break;
				
			case "1,2":
				cellPosition = 1;
				break;
				
			case "1,3":
				cellPosition = 2;
				break;
				
			case "2,1":
				cellPosition = 3;
				break;
				
			case "2,2":
				cellPosition = 4;
				break;
				
			case "2,3":
				cellPosition = 5;
				break;
				
			case "3,1":
				cellPosition = 6;
				break;
				
			case "3,2":
				cellPosition = 7;
				break;
				
			default:
				cellPosition = 8;
		}
		
		return cellPosition;
	}

	public static boolean checkPlayParams(GameStatus gameStatus, int row, int column) {
		
		if (gameStatus == null || gameStatus.getStatus().equals(Status.finished.toString()))
			return false;
		
		if ((row < 0 && row > 3) || (column < 0 && column > 3))
			return false;
		
		int cellPosition = getCellPosition(row, column);
		List<PlayGame> game = gameStatus.getGame();
		PlayGame playGame = game.get(cellPosition);
		
		// check if value is used
		if (! playGame.getValue().isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isPlayerWin(List<PlayGame> game) {
		
		if (game.get(0).getValue().equals(PLAYER_CELL_VALUE) && game.get(1).getValue().equals(PLAYER_CELL_VALUE) && game.get(2).getValue().equals(PLAYER_CELL_VALUE))
			return true;
		
		if (game.get(3).getValue().equals(PLAYER_CELL_VALUE) && game.get(4).getValue().equals(PLAYER_CELL_VALUE) && game.get(5).getValue().equals(PLAYER_CELL_VALUE))
			return true;
		
		if (game.get(6).getValue().equals(PLAYER_CELL_VALUE) && game.get(7).getValue().equals(PLAYER_CELL_VALUE) && game.get(8).getValue().equals(PLAYER_CELL_VALUE))
			return true;
		
		if (game.get(0).getValue().equals(PLAYER_CELL_VALUE) && game.get(3).getValue().equals(PLAYER_CELL_VALUE) && game.get(6).getValue().equals(PLAYER_CELL_VALUE))
			return true;
		
		if (game.get(1).getValue().equals(PLAYER_CELL_VALUE) && game.get(4).getValue().equals(PLAYER_CELL_VALUE) && game.get(7).getValue().equals(PLAYER_CELL_VALUE))
			return true;
		
		if (game.get(2).getValue().equals(PLAYER_CELL_VALUE) && game.get(5).getValue().equals(PLAYER_CELL_VALUE) && game.get(8).getValue().equals(PLAYER_CELL_VALUE))
			return true;
		
		if (game.get(0).getValue().equals(PLAYER_CELL_VALUE) && game.get(4).getValue().equals(PLAYER_CELL_VALUE) && game.get(8).getValue().equals(PLAYER_CELL_VALUE))
			return true;
		
		if (game.get(2).getValue().equals(PLAYER_CELL_VALUE) && game.get(4).getValue().equals(PLAYER_CELL_VALUE) && game.get(6).getValue().equals(PLAYER_CELL_VALUE))
			return true;
		
		return false;
	}
	
	public static boolean isComputerWin(List<PlayGame> game) {
		
		if (game.get(0).getValue().equals(COMPUTER_CELL_VALUE) && game.get(1).getValue().equals(COMPUTER_CELL_VALUE) && game.get(2).getValue().equals(COMPUTER_CELL_VALUE))
			return true;
		
		if (game.get(3).getValue().equals(COMPUTER_CELL_VALUE) && game.get(4).getValue().equals(COMPUTER_CELL_VALUE) && game.get(5).getValue().equals(COMPUTER_CELL_VALUE))
			return true;
		
		if (game.get(6).getValue().equals(COMPUTER_CELL_VALUE) && game.get(7).getValue().equals(COMPUTER_CELL_VALUE) && game.get(8).getValue().equals(COMPUTER_CELL_VALUE))
			return true;
		
		if (game.get(0).getValue().equals(COMPUTER_CELL_VALUE) && game.get(3).getValue().equals(COMPUTER_CELL_VALUE) && game.get(6).getValue().equals(COMPUTER_CELL_VALUE))
			return true;
		
		if (game.get(1).getValue().equals(COMPUTER_CELL_VALUE) && game.get(4).getValue().equals(COMPUTER_CELL_VALUE) && game.get(7).getValue().equals(COMPUTER_CELL_VALUE))
			return true;
		
		if (game.get(2).getValue().equals(COMPUTER_CELL_VALUE) && game.get(5).getValue().equals(COMPUTER_CELL_VALUE) && game.get(8).getValue().equals(COMPUTER_CELL_VALUE))
			return true;
		
		if (game.get(0).getValue().equals(COMPUTER_CELL_VALUE) && game.get(4).getValue().equals(COMPUTER_CELL_VALUE) && game.get(8).getValue().equals(COMPUTER_CELL_VALUE))
			return true;
		
		if (game.get(2).getValue().equals(COMPUTER_CELL_VALUE) && game.get(4).getValue().equals(COMPUTER_CELL_VALUE) && game.get(6).getValue().equals(COMPUTER_CELL_VALUE))
			return true;
		
		return false;
	}
	
	public static boolean isAllMovesUsed(GameStatus gameStatus) {
		
		List<PlayGame> gameList = gameStatus.getGame();
		
		for (PlayGame game : gameList) {
			if (game.getValue().isEmpty())
				return false;
		}
		
		return true;
	}
	
	public static void setGameStats(String name, Result result) {
		
		int statsIndex = getIndexByNameFromStats(name);
		
		switch(result) {
		
			case WIN:			
				if (statsIndex == -1)
					DatabaseClass.getGameStats().getStats().add(new Statistic(name, 1, 0, 0));
				else
					updateStats(statsIndex, result);
				
				break;
				
			case LOSS:
				if (statsIndex == -1)
					DatabaseClass.getGameStats().getStats().add(new Statistic(name, 0, 1, 0));
				else
					updateStats(statsIndex, result);
				
				break;
				
			case DRAW:
				if (statsIndex == -1)
					DatabaseClass.getGameStats().getStats().add(new Statistic(name, 0, 0, 1));
				else
					updateStats(statsIndex, result);
				
				break;
		}
		
	}
	
	public static Strategy getGameStrategy(String name) {
		
		int index = getIndexByNameFromStats(name);
		if (index == -1)
			return Strategy.WEAK;
		
		Statistic statistic = DatabaseClass.getGameStats().getStats().get(index);
		
		int totalPlays = statistic.getWins() + statistic.getLosses() + statistic.getDraws();
		int winPercent = (int) (((double) statistic.getWins() / totalPlays) * 100);
		
		if (winPercent <= 30)
			return Strategy.WEAK;
		else
			return Strategy.STRONG;
	}
	
	private static int getIndexByNameFromStats(String name) {
		
		int index = 0;
		List<Statistic> statistics = DatabaseClass.getGameStats().getStats();
		
		for (Statistic statistic : statistics) {
			if (name.equalsIgnoreCase(statistic.getName())) {
				return index;
			}
			index++;
		}
		
		// not exist
		return -1;
	}
	
	private static void updateStats(int statsIndex, Result result) {
		
		Statistic statistic = DatabaseClass.getGameStats().getStats().get(statsIndex);
		
		switch(result) {
		
			case WIN:
				statistic.setWins(statistic.getWins() + 1);
				
				break;
				
			case LOSS:
				statistic.setLosses(statistic.getLosses() + 1);
				
				break;
				
			case DRAW:
				statistic.setDraws(statistic.getDraws() + 1);
				
				break;
		}
	}
}




