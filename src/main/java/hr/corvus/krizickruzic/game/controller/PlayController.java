package hr.corvus.krizickruzic.game.controller;

import java.io.IOException;
import java.util.List;
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
import hr.corvus.krizickruzic.game.resource.PlayGame;
import hr.corvus.krizickruzic.game.util.Computer;

@RestController
@RequestMapping("/game")
public class PlayController {
	
	private Map<Long, GameStatus> gameStatus = DatabaseClass.getGameStatus();

	@RequestMapping(value="/play", method=RequestMethod.GET)
	public void play(@RequestParam(value="gameId") Long gameId, @RequestParam(value="row") int row, @RequestParam(value="column") int column) {
		
		if (gameId == null || row == 0 || column == 0) {
			throw new IllegalArgumentException("Please check your parameters!");
		}
		
		GameStatus status = gameStatus.get(gameId);
		
		List<PlayGame> game = status.getGame();
		// dummy example
		PlayGame playGame = game.get(0);
		playGame.setValue("X");
		
		game.set(0, playGame);
		
		Computer.play(status);
		
	}
	
	@ExceptionHandler
	void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.PRECONDITION_FAILED.value());
	}
}
