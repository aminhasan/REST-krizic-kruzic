package hr.corvus.krizickruzic.game.resource;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import hr.corvus.krizickruzic.game.enums.Status;
import hr.corvus.krizickruzic.game.enums.Strategy;


public class GameStatus {
	
	private Long gameId;
	
	private String firstPlayer;
	private String secondPlayer;
	private String status;
	private String winner;
	
	private List<PlayGame> game = new ArrayList<>();
	
	@JsonIgnore
	private ArrayList<Integer> computerMoves = new ArrayList<Integer>();
	
	@JsonIgnore
	private Strategy strategy;
	
	public GameStatus() {}
	
	public GameStatus(Long gameId, String firstPlayer, String secondPlayer, Strategy strategy) {
		this.gameId = gameId;
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		status = Status.inProgress.toString();
		this.strategy = strategy;
		
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
	
	public void setSecondPlayer(String secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
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

	public Strategy getStrategy() {
		return strategy;
	}
	
}
