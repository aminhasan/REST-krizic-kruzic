package hr.corvus.krizickruzic.game.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
		if (status == null)
				throw new IllegalArgumentException("Wrong game id value!");
			
		
		return status;
	}

	@ExceptionHandler
	void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.PRECONDITION_FAILED.value());
	}
}
