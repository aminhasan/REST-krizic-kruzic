package hr.corvus.krizickruzic.game.controller;

import java.util.HashMap;
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
		/*List<Map<String, String>> gameList = status.getGame();
		Map<String, String> game =  new HashMap<>();
		game.put("row", row);
		game.put("column", column);
		game.put("value", "X");
		
		gameList.add(game);*/
		
		List<PlayGame> gameList = status.getGame();
		gameList.add(new PlayGame(row, column, "X"));
	}
}
