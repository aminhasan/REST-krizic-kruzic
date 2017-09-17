package hr.corvus.krizickruzic.game.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.corvus.krizickruzic.game.enums.CellValue;
import hr.corvus.krizickruzic.game.resource.GameStatus;
import hr.corvus.krizickruzic.game.resource.PlayGame;

public class Computer {
	
	final static String CELL_VALUE = CellValue.O.toString();

	public static void play(GameStatus gameStatus) {
		
		ArrayList<Integer> computerMoves = gameStatus.getComputerMoves();
		Collections.shuffle(computerMoves);
		
		List<PlayGame> game = gameStatus.getGame();
		PlayGame playGame = game.get(computerMoves.get(0));
		playGame.setValue(CELL_VALUE);
		
		computerMoves.remove(0);
		
	}
	
	
	public static void removeUsedCellPoistion(GameStatus gameStatus, int cellPosition) {
		ArrayList<Integer> computerMoves = gameStatus.getComputerMoves();
		computerMoves.remove(cellPosition);
	}
}
