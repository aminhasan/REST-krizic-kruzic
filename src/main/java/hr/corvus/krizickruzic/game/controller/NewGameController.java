package hr.corvus.krizickruzic.game.controller;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hr.corvus.krizickruzic.game.database.DatabaseClass;
import hr.corvus.krizickruzic.game.resource.GameStatus;
import hr.corvus.krizickruzic.game.resource.NewGame;

@RestController
@RequestMapping("/game")
public class NewGameController {

	private final AtomicLong counter = new AtomicLong();
	private Map<Long, GameStatus> gameStatus = DatabaseClass.getGameStatus();
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public NewGame newGame(@RequestParam(value="first") String firstPlayer, @RequestParam(value="second") String secondPlayer) {
		
		
		long gameId = counter.incrementAndGet();
		gameStatus.put(gameId, new GameStatus(gameId, firstPlayer, secondPlayer));
		
		return new NewGame(gameId);
	}
}
