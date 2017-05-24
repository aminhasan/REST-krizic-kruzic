package hr.corvus.krizickruzic.game.resource;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class GameStatus {
	
	private Long gameId;
	private String firstPlayer;
	private String secondPlayer;
	private String status;
	private List<PlayGame> game = new ArrayList<>();
	
	@JsonIgnore
	private ArrayList<Integer> computerMoves = new ArrayList<Integer>();
	
	public GameStatus() {}
	
	public GameStatus(Long gameId, String firstPlayer, String secondPlayer) {
		this.gameId = gameId;
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		status = "inProgress";
		
		setRowAndColumnGame(game);
		setComputerMoves(computerMoves);
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

	public ArrayList<Integer> getComputerMoves() {
		return computerMoves;
	}

	private void setRowAndColumnGame(List<PlayGame> game) {
		for (int j = 1; j <= 3; j++) {
			for (int i = 1; i <= 3; i++) {
				game.add(new PlayGame(j, i, ""));
			}
		}
	}
	
	private void setComputerMoves(ArrayList<Integer> computerMoves) {
		for (int i = 0; i < 9; i++) {
			computerMoves.add(i);
		}
	}
}
