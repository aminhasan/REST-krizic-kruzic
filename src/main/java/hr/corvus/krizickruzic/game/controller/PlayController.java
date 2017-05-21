package hr.corvus.krizickruzic.game.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hr.corvus.krizickruzic.game.database.DatabaseClass;
import hr.corvus.krizickruzic.game.resource.GameStatus;
import hr.corvus.krizickruzic.game.resource.PlayGame;

@RestController
@RequestMapping("/game")
public class PlayController {
	
	private Map<Long, GameStatus> gameStatus = DatabaseClass.getGameStatus();

	@RequestMapping(value="/play", method=RequestMethod.GET)
	public void play(@RequestParam(value="gameId") Long gameId, @RequestParam(value="row") int row, @RequestParam(value="column") int column) {
		
		GameStatus status = gameStatus.get(gameId);
		
		List<PlayGame> game = status.getGame();
		// dummy example
		PlayGame playGame = game.get(0);
		playGame.setValue("X");
		
		game.set(0, playGame);
		
	}
}
