package hr.corvus.krizickruzic.game.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.corvus.krizickruzic.game.resource.GameStatus;
import hr.corvus.krizickruzic.game.resource.PlayGame;

public class Computer {

	public static void play(GameStatus gameStatus) {
		
		ArrayList<Integer> computerMoves = gameStatus.getComputerMoves();
		Collections.shuffle(computerMoves);
		
		List<PlayGame> game = gameStatus.getGame();
		PlayGame playGame = game.get(computerMoves.get(0));
		playGame.setValue("O");
		
		computerMoves.remove(0);
		
	}
	
}
