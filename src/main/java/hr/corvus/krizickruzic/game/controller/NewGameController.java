package hr.corvus.krizickruzic.game.controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hr.corvus.krizickruzic.game.database.DatabaseClass;
import hr.corvus.krizickruzic.game.resource.GameStatus;
import hr.corvus.krizickruzic.game.resource.NewGame;
import hr.corvus.krizickruzic.game.util.Computer;

@RestController
@RequestMapping("/game")
public class NewGameController {

	private final AtomicLong counter = new AtomicLong();
	private Map<Long, GameStatus> gameStatus = DatabaseClass.getGameStatus();
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public NewGame newGame(@RequestParam(value="first") String firstPlayer, @RequestParam(value="second") String secondPlayer) {
	
		if (firstPlayer.isEmpty() || secondPlayer.isEmpty()) {
			throw new IllegalArgumentException("Please check your parameters");
		}
		
		long gameId = counter.incrementAndGet();
		gameStatus.put(gameId, new GameStatus(gameId, firstPlayer, secondPlayer));
		
		GameStatus status = gameStatus.get(gameId);
		
		// set secondPlayer value to computer for convenient
		if (firstPlayer.equalsIgnoreCase(secondPlayer)) {
			status.setSecondPlayer("computer");
		}
		
		// computer plays first
		if (firstPlayer.toLowerCase().equals("computer")) {
			Computer.play(status);
		}
		
		return new NewGame(gameId);
	}
	
	@ExceptionHandler
	void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.PRECONDITION_FAILED.value());
	}
}
