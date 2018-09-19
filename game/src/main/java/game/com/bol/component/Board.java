package game.com.bol.component;

import java.util.List;

public class Board {
	
	private List<Pit> playerAPits;
	
	private Pit playerALargerPit;
	
	private List<Pit> playerBPits;
	
	private Pit playerBLargerPit;
	
	private Player currentPlayer;

	
	public Board(List<Pit> playerAPits, List<Pit> playerBPits) {
		this.playerAPits = playerAPits;
		this.playerALargerPit = new Pit("L",0);
		this.playerBPits = playerBPits;
		this.playerBLargerPit = new Pit("L",0);
	}


	public List<Pit> getPlayerAPits() {
		return playerAPits;
	}


	public void setPlayerAPits(List<Pit> playerAPits) {
		this.playerAPits = playerAPits;
	}


	public Pit getPlayerALargerPit() {
		return playerALargerPit;
	}


	public void setPlayerALargerPit(Pit playerALargerPit) {
		this.playerALargerPit = playerALargerPit;
	}


	public List<Pit> getPlayerBPits() {
		return playerBPits;
	}


	public void setPlayerBPits(List<Pit> playerBPits) {
		this.playerBPits = playerBPits;
	}


	public Pit getPlayerBLargerPit() {
		return playerBLargerPit;
	}


	public void setPlayerBLargerPit(Pit playerBLargerPit) {
		this.playerBLargerPit = playerBLargerPit;
	}


	public Player getCurrentPlayer() {
		return currentPlayer;
	}


	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

}
