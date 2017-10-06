package hr.corvus.krizickruzic.game.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.corvus.krizickruzic.game.enums.CellValue;
import hr.corvus.krizickruzic.game.enums.Strategy;
import hr.corvus.krizickruzic.game.resource.GameStatus;
import hr.corvus.krizickruzic.game.resource.PlayGame;

public class Computer {
	
	final static String CELL_VALUE = CellValue.O.toString();
	final static String OPPONENT_CELL_VALUE = CellValue.X.toString();

	public static void play(GameStatus gameStatus) {
		
		if (gameStatus.getStrategy() == Strategy.STRONG)
			playBetter(gameStatus);
		else
			playRandomly(gameStatus);
	}
	
	public static void playBetter(GameStatus gameStatus) {
		
		int cellPosition = findCellPositionToWin(gameStatus);
		if (cellPosition == -1)
			cellPosition = findCellPositionToBlockOpponent(gameStatus);
		
		if (cellPosition > -1) {
			// set the move
			gameStatus.getGame().get(cellPosition).setValue(CELL_VALUE);
			
			// remove computer move
			removeUsedCellPoistion(gameStatus, cellPosition);
			
		} else { // play randomly
			playRandomly(gameStatus);
		} 
	}
	
	
	public static void removeUsedCellPoistion(GameStatus gameStatus, int cellPosition) {
		
		int index = 0;
		ArrayList<Integer> computerMoves = gameStatus.getComputerMoves();
		
		for (Integer move : computerMoves) {
			
			if (move.intValue() == cellPosition) {
				computerMoves.remove(index);
				break;
			}
			
			index++;
		}
	}

	private static void playRandomly(GameStatus gameStatus) {
		
		ArrayList<Integer> computerMoves = gameStatus.getComputerMoves();
		Collections.shuffle(computerMoves);
		
		List<PlayGame> game = gameStatus.getGame();
		PlayGame playGame = game.get(computerMoves.get(0));
		playGame.setValue(CELL_VALUE);
		
		computerMoves.remove(0);
	} 
	
	private static int findCellPositionToWin(GameStatus gameStatus) {
		
		List<PlayGame> game = gameStatus.getGame();
		
		/*** take the win horizontally ***/
		// first row
		if (game.get(0).getValue().equals(CELL_VALUE) && game.get(1).getValue().equals(CELL_VALUE) && game.get(2).getValue().isEmpty())
			return 2;
		
		if (game.get(0).getValue().equals(CELL_VALUE) && game.get(1).getValue().isEmpty() && game.get(2).getValue().equals(CELL_VALUE))
			return 1;
		
		if (game.get(0).getValue().isEmpty() && game.get(1).getValue().equals(CELL_VALUE) && game.get(2).getValue().equals(CELL_VALUE))
			return 0;
		
		// second row
		if (game.get(3).getValue().equals(CELL_VALUE) && game.get(4).getValue().equals(CELL_VALUE) && game.get(5).getValue().isEmpty())
			return 5;
		
		if (game.get(3).getValue().equals(CELL_VALUE) && game.get(4).getValue().isEmpty() && game.get(5).getValue().equals(CELL_VALUE))
			return 4;
		
		if (game.get(3).getValue().isEmpty() && game.get(4).getValue().equals(CELL_VALUE) && game.get(5).getValue().equals(CELL_VALUE))
			return 3;
		
		// third row
		if (game.get(6).getValue().equals(CELL_VALUE) && game.get(7).getValue().equals(CELL_VALUE) && game.get(8).getValue().isEmpty())
			return 8;
		
		if (game.get(6).getValue().equals(CELL_VALUE) && game.get(7).getValue().isEmpty() && game.get(8).getValue().equals(CELL_VALUE))
			return 7;
		
		if (game.get(6).getValue().isEmpty() && game.get(7).getValue().equals(CELL_VALUE) && game.get(8).getValue().equals(CELL_VALUE))
			return 6;
		
		/*** take the win vertically ***/
		// first column
		if (game.get(0).getValue().equals(CELL_VALUE) && game.get(3).getValue().equals(CELL_VALUE) && game.get(6).getValue().isEmpty())
			return 6;
		
		if (game.get(0).getValue().equals(CELL_VALUE) && game.get(3).getValue().isEmpty() && game.get(6).getValue().equals(CELL_VALUE))
			return 3;
		
		if (game.get(0).getValue().isEmpty() && game.get(3).getValue().equals(CELL_VALUE) && game.get(6).getValue().equals(CELL_VALUE))
			return 0;
		
		// second column
		if (game.get(1).getValue().equals(CELL_VALUE) && game.get(4).getValue().equals(CELL_VALUE) && game.get(7).getValue().isEmpty())
			return 7;
		
		if (game.get(1).getValue().equals(CELL_VALUE) && game.get(4).getValue().isEmpty() && game.get(7).getValue().equals(CELL_VALUE))
			return 4;
		
		if (game.get(1).getValue().isEmpty() && game.get(4).getValue().equals(CELL_VALUE) && game.get(7).getValue().equals(CELL_VALUE))
			return 1;
		
		// third column
		if (game.get(2).getValue().equals(CELL_VALUE) && game.get(5).getValue().equals(CELL_VALUE) && game.get(8).getValue().isEmpty())
			return 8;
		
		if (game.get(2).getValue().equals(CELL_VALUE) && game.get(5).getValue().isEmpty() && game.get(8).getValue().equals(CELL_VALUE))
			return 5;
		
		if (game.get(2).getValue().isEmpty() && game.get(5).getValue().equals(CELL_VALUE) && game.get(8).getValue().equals(CELL_VALUE))
			return 2;
		
		/*** take the win diagonally ***/
		// left diagonal
		if (game.get(0).getValue().equals(CELL_VALUE) && game.get(4).getValue().equals(CELL_VALUE) && game.get(8).getValue().isEmpty())
			return 8;
		
		if (game.get(0).getValue().equals(CELL_VALUE) && game.get(4).getValue().isEmpty() && game.get(8).getValue().equals(CELL_VALUE))
			return 4;
		
		if (game.get(0).getValue().isEmpty() && game.get(4).getValue().equals(CELL_VALUE) && game.get(8).getValue().equals(CELL_VALUE))
			return 0;
		
		// right diagonal
		if (game.get(2).getValue().equals(CELL_VALUE) && game.get(4).getValue().equals(CELL_VALUE) && game.get(6).getValue().isEmpty())
			return 6;
		
		if (game.get(2).getValue().equals(CELL_VALUE) && game.get(4).getValue().isEmpty() && game.get(6).getValue().equals(CELL_VALUE))
			return 4;
		
		if (game.get(2).getValue().isEmpty() && game.get(4).getValue().equals(CELL_VALUE) && game.get(6).getValue().equals(CELL_VALUE))
			return 2;
		
		return -1;
	}

	private static int findCellPositionToBlockOpponent(GameStatus gameStatus) {
	
		List<PlayGame> game = gameStatus.getGame();
		
		/*** take the block horizontally ***/
		// first row
		if (game.get(0).getValue().equals(OPPONENT_CELL_VALUE) && game.get(1).getValue().equals(OPPONENT_CELL_VALUE) && game.get(2).getValue().isEmpty())
			return 2;
		
		if (game.get(0).getValue().equals(OPPONENT_CELL_VALUE) && game.get(1).getValue().isEmpty() && game.get(2).getValue().equals(OPPONENT_CELL_VALUE))
			return 1;
		
		if (game.get(0).getValue().isEmpty() && game.get(1).getValue().equals(OPPONENT_CELL_VALUE) && game.get(2).getValue().equals(OPPONENT_CELL_VALUE))
			return 0;
		
		// second row
		if (game.get(3).getValue().equals(OPPONENT_CELL_VALUE) && game.get(4).getValue().equals(OPPONENT_CELL_VALUE) && game.get(5).getValue().isEmpty())
			return 5;
		
		if (game.get(3).getValue().equals(OPPONENT_CELL_VALUE) && game.get(4).getValue().isEmpty() && game.get(5).getValue().equals(OPPONENT_CELL_VALUE))
			return 4;
		
		if (game.get(3).getValue().isEmpty() && game.get(4).getValue().equals(OPPONENT_CELL_VALUE) && game.get(5).getValue().equals(OPPONENT_CELL_VALUE))
			return 3;
		
		// third row
		if (game.get(6).getValue().equals(OPPONENT_CELL_VALUE) && game.get(7).getValue().equals(OPPONENT_CELL_VALUE) && game.get(8).getValue().isEmpty())
			return 8;
		
		if (game.get(6).getValue().equals(OPPONENT_CELL_VALUE) && game.get(7).getValue().isEmpty() && game.get(8).getValue().equals(OPPONENT_CELL_VALUE))
			return 7;
		
		if (game.get(6).getValue().isEmpty() && game.get(7).getValue().equals(OPPONENT_CELL_VALUE) && game.get(8).getValue().equals(OPPONENT_CELL_VALUE))
			return 6;
		
		/*** take the block vertically ***/
		// first column
		if (game.get(0).getValue().equals(OPPONENT_CELL_VALUE) && game.get(3).getValue().equals(OPPONENT_CELL_VALUE) && game.get(6).getValue().isEmpty())
			return 6;
		
		if (game.get(0).getValue().equals(OPPONENT_CELL_VALUE) && game.get(3).getValue().isEmpty() && game.get(6).getValue().equals(OPPONENT_CELL_VALUE))
			return 3;
		
		if (game.get(0).getValue().isEmpty() && game.get(3).getValue().equals(OPPONENT_CELL_VALUE) && game.get(6).getValue().equals(OPPONENT_CELL_VALUE))
			return 0;
		
		// second column
		if (game.get(1).getValue().equals(OPPONENT_CELL_VALUE) && game.get(4).getValue().equals(OPPONENT_CELL_VALUE) && game.get(7).getValue().isEmpty())
			return 7;
		
		if (game.get(1).getValue().equals(OPPONENT_CELL_VALUE) && game.get(4).getValue().isEmpty() && game.get(7).getValue().equals(OPPONENT_CELL_VALUE))
			return 4;
		
		if (game.get(1).getValue().isEmpty() && game.get(4).getValue().equals(OPPONENT_CELL_VALUE) && game.get(7).getValue().equals(OPPONENT_CELL_VALUE))
			return 1;
		
		// third column
		if (game.get(2).getValue().equals(OPPONENT_CELL_VALUE) && game.get(5).getValue().equals(OPPONENT_CELL_VALUE) && game.get(8).getValue().isEmpty())
			return 8;
		
		if (game.get(2).getValue().equals(OPPONENT_CELL_VALUE) && game.get(5).getValue().isEmpty() && game.get(8).getValue().equals(OPPONENT_CELL_VALUE))
			return 5;
		
		if (game.get(2).getValue().isEmpty() && game.get(5).getValue().equals(OPPONENT_CELL_VALUE) && game.get(8).getValue().equals(OPPONENT_CELL_VALUE))
			return 2;
		
		/*** take the block diagonally ***/
		// left diagonal
		if (game.get(0).getValue().equals(OPPONENT_CELL_VALUE) && game.get(4).getValue().equals(OPPONENT_CELL_VALUE) && game.get(8).getValue().isEmpty())
			return 8;
		
		if (game.get(0).getValue().equals(OPPONENT_CELL_VALUE) && game.get(4).getValue().isEmpty() && game.get(8).getValue().equals(OPPONENT_CELL_VALUE))
			return 4;
		
		if (game.get(0).getValue().isEmpty() && game.get(4).getValue().equals(OPPONENT_CELL_VALUE) && game.get(8).getValue().equals(OPPONENT_CELL_VALUE))
			return 0;
		
		// right diagonal
		if (game.get(2).getValue().equals(OPPONENT_CELL_VALUE) && game.get(4).getValue().equals(OPPONENT_CELL_VALUE) && game.get(6).getValue().isEmpty())
			return 6;
		
		if (game.get(2).getValue().equals(OPPONENT_CELL_VALUE) && game.get(4).getValue().isEmpty() && game.get(6).getValue().equals(OPPONENT_CELL_VALUE))
			return 4;
		
		if (game.get(2).getValue().isEmpty() && game.get(4).getValue().equals(OPPONENT_CELL_VALUE) && game.get(6).getValue().equals(OPPONENT_CELL_VALUE))
			return 2;
		
		return -1;
		
	}
}








