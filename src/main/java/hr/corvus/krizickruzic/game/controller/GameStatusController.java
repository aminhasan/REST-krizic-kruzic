package hr.corvus.krizickruzic.game.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hr.corvus.krizickruzic.game.database.DatabaseClass;
import hr.corvus.krizickruzic.game.resource.GameStatus;

@RestController
@RequestMapping("/game")
public class GameStatusController {
	
	private Map<Long, GameStatus> gameStatus = DatabaseClass.getGameStatus();
	
	@RequestMapping(value="/status", method=RequestMethod.GET)
	public GameStatus getGameStatusById(@RequestParam(value="gameId") Long gameId) {
		
		GameStatus status = gameStatus.get(gameId);
		
		return status;
	}

}
