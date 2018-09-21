package game.com.bol;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import game.com.bol.component.Board;
import game.com.bol.component.Pit;
import game.com.bol.component.Player;
import game.com.bol.exception.LoginException;

@Component
public class GameContext {

	private Board board;
	private Player currentPlayer;
	
	private Player firstPlayer;
	private Player secondPlayer;
	
	@PostConstruct
	public void run() {
	    board = initialBoard();
	}
	
	public void refreshContext() {
		firstPlayer = null;
		secondPlayer = null;
		currentPlayer = null;
		board = initialBoard();
	}
	
	private Board initialBoard() {
		
		List<Pit> pitsA = new ArrayList<Pit>();
		Pit pa1 = new Pit("a",6);
		Pit pa2 = new Pit("b",6);
		Pit pa3 = new Pit("c",6);
		Pit pa4 = new Pit("d",6);
		Pit pa5 = new Pit("e",6);
		Pit pa6 = new Pit("f",6);
		pitsA.add(pa1);
		pitsA.add(pa2);
		pitsA.add(pa3);
		pitsA.add(pa4);
		pitsA.add(pa5);
		pitsA.add(pa6);
		
		List<Pit> pitsB = new ArrayList<Pit>();
		Pit pb1 = new Pit("a",6);
		Pit pb2 = new Pit("b",6);
		Pit pb3 = new Pit("c",6);
		Pit pb4 = new Pit("d",6);
		Pit pb5 = new Pit("e",6);
		Pit pb6 = new Pit("f",6);
		pitsB.add(pb1);
		pitsB.add(pb2);
		pitsB.add(pb3);
		pitsB.add(pb4);
		pitsB.add(pb5);
		pitsB.add(pb6);
		
		Board board = new Board(pitsA,pitsB);
		return board;
	}

	public Board getBoard() {
		return board;
	}
	
	public void reloadBoard(Board board) {
		this.board = board;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
		this.currentPlayer = firstPlayer;
	}

	public Player getSecondPlayer() {
		return secondPlayer;
	}

	public void setSecondPlayer(Player secondPlayer) {
		this.secondPlayer = secondPlayer;
	}
	
	public void addPlayer(Player newPlayer) throws LoginException{
		if(firstPlayer==null)
			firstPlayer = newPlayer;
		else if(secondPlayer==null) 
			secondPlayer = newPlayer;
		else throw new LoginException("2 Other User are playing now");
	}
}
