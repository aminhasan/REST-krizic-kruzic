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
import hr.corvus.krizickruzic.game.enums.CellValue;
import hr.corvus.krizickruzic.game.enums.Result;
import hr.corvus.krizickruzic.game.enums.Status;
import hr.corvus.krizickruzic.game.resource.GameStatus;
import hr.corvus.krizickruzic.game.resource.PlayGame;
import hr.corvus.krizickruzic.game.util.Computer;
import hr.corvus.krizickruzic.game.util.GameUtils;

@RestController
@RequestMapping("/game")
public class PlayController {
	
	final static String CELL_VALUE = CellValue.X.toString();
	final static String NAME = "computer";
	
	private Map<Long, GameStatus> gameStatus = DatabaseClass.getGameStatus();

	@RequestMapping(value="/play", method=RequestMethod.GET)
	public String play(@RequestParam(value="gameId") Long gameId, @RequestParam(value="row") int row, @RequestParam(value="column") int column) {
		
		GameStatus status = gameStatus.get(gameId);
		
		if (! GameUtils.checkPlayParams(status, row, column))
				throw new IllegalArgumentException("Please check your parameters!");	
		
		// indices 0-8 represents cell position 1-9 
		List<PlayGame> game = status.getGame();
		int cellPosition = GameUtils.getCellPosition(row, column);
		
		PlayGame playGame = game.get(cellPosition);
		// player makes the move
		playGame.setValue(CELL_VALUE);
		
		game.set(cellPosition, playGame);
		Computer.removeUsedCellPoistion(status, cellPosition);
		
		if (GameUtils.isPlayerWin(game)) {
			
			status.setWinner(getName(status));
			status.setStatus(Status.finished.toString());
			
			GameUtils.setGameStats(getName(status), Result.WIN);
		}
		
		if (status.getStatus().equals(Status.inProgress.toString()) && (! GameUtils.isAllMovesUsed(status)) )
			Computer.play(status);
		
		if (GameUtils.isComputerWin(game)) {
			
			status.setWinner(NAME);
			status.setStatus(Status.finished.toString());
			
			GameUtils.setGameStats(getName(status), Result.LOSS);
		}
		
		// set draw in case no one wins
		if ( GameUtils.isAllMovesUsed(status) && (! GameUtils.isPlayerWin(game)) && (! GameUtils.isComputerWin(game)) ) {
			
			status.setStatus(Status.finished.toString());
			
			GameUtils.setGameStats(getName(status), Result.DRAW);
		}
		
		return "Successfully played. please check the game status at endpoint /game/status";
	}
	
	private String getName(GameStatus status) {
		return status.getFirstPlayer().equalsIgnoreCase(NAME) ? status.getSecondPlayer() : status.getFirstPlayer();
	}
	
	@ExceptionHandler
	void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.PRECONDITION_FAILED.value());
	}
	
}
