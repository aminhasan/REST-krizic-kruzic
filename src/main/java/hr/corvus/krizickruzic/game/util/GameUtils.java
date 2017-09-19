package hr.corvus.krizickruzic.game.util;

import java.util.List;

import hr.corvus.krizickruzic.game.enums.CellValue;
import hr.corvus.krizickruzic.game.enums.Status;
import hr.corvus.krizickruzic.game.resource.GameStatus;
import hr.corvus.krizickruzic.game.resource.PlayGame;

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
		
		if (game.get(6).getValue().equals(PLAYER_CELL_VALUE) && game.get(6).getValue().equals(PLAYER_CELL_VALUE) && game.get(8).getValue().equals(PLAYER_CELL_VALUE))
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
		
		if (game.get(6).getValue().equals(COMPUTER_CELL_VALUE) && game.get(6).getValue().equals(COMPUTER_CELL_VALUE) && game.get(8).getValue().equals(COMPUTER_CELL_VALUE))
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
}
