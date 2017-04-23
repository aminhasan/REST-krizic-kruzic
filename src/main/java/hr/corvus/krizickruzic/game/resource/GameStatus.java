package hr.corvus.krizickruzic.game.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameStatus {
	
	private Long gameId;
	private String firstPlayer;
	private String secondPlayer;
	private String status;
	//private List<Map<String, String>> game = new ArrayList<>();
	private List<PlayGame> game = new ArrayList<>();
	
	public GameStatus() {}
	
	public GameStatus(Long gameId, String firstPlayer, String secondPlayer) {
		this.gameId = gameId;
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		status = "inProgress";	
	}

	public Long getGameId() {
		return gameId;
	}

	public String getFirstPlayer() {
		return firstPlayer;
	}

	public String getSecondPlayer() {
		return secondPlayer;
	}
	
	public String getStatus() {
		return status;
	}

	public List<PlayGame> getGame() {
		return game;
	}

	public void setGame(List<PlayGame> game) {
		this.game = game;
	}
	

	/*public List<Map<String, String>> getGame() {
		return game;
	}

	public void setGame(List<Map<String, String>> game) {
		this.game = game;
	}*/


	
}
