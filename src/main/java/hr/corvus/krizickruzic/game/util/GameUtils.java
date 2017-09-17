package hr.corvus.krizickruzic.game.util;

import hr.corvus.krizickruzic.game.enums.Status;
import hr.corvus.krizickruzic.game.resource.GameStatus;

public class GameUtils {
	
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
		
		return true;
	}
}
