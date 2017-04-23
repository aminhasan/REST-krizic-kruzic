package hr.corvus.krizickruzic.game.resource;

public class PlayGame {
	
	private int row;
	private int column;
	private String value;
	
	public PlayGame(int row, int column, String value) {
		this.row = row;
		this.column = column;
		this.value = value;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public String getValue() {
		return value;
	}
	
	

}
